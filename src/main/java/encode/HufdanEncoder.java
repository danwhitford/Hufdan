package encode;

import io.BitSetBuilder;
import types.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HufdanEncoder {
    private static Map<String, Long> countChars(String s) {
        return s.chars()
                .mapToObj(Character::toChars)
                .map(String::new)
                .collect(
                        Collectors.groupingBy(
                                Function.identity(), Collectors.counting()
                        )
                );
    }

    private static PriorityQueue<IHufdanNode> toPriorityQueue(Map<String, Long> counts) {
        return counts.entrySet()
                .stream()
                .map(c -> new HufdanLeafNode(c.getKey(), c.getValue()))
                .collect(Collectors.toCollection(PriorityQueue::new));
    }

    private static IHufdanNode collapseToTree(PriorityQueue<IHufdanNode> pq) {
        while(pq.size() > 1) {
            var a = pq.poll();
            var b = pq.poll();
            assert b != null;
            pq.offer(new HufdanInternalNode(a, b));
        }
        return pq.poll();
    }

    private static Map<String, BitSetBuilder> toHufdanDict(IHufdanNode root) {
        Map<String, BitSetBuilder> ret = new HashMap<>();
        Queue<HufdanQueueEntry> q = new ArrayDeque<>();
        q.add(new HufdanQueueEntry(root, new BitSetBuilder()));

        while (!q.isEmpty()) {
            var head = q.poll();
            if (head.getNode() instanceof HufdanLeafNode) {
                ret.put(((HufdanLeafNode) head.getNode()).getVal(), head.getCode());
            } else if (head.getNode() instanceof HufdanInternalNode) {
                var internal = (HufdanInternalNode) head.getNode();

                var code = head.getCode();
                var leftCode = new BitSetBuilder(code);
                leftCode.append(false);
                var rightCode = new BitSetBuilder(code);
                rightCode.append(true);

                q.offer(new HufdanQueueEntry(internal.getLeft(), leftCode));
                q.offer(new HufdanQueueEntry(internal.getRight(), rightCode));
            } else {
                throw new RuntimeException("Unknown Hufdan node type");
            }
        }

        return ret;
    }

    public static BitSet encodeMessage(String message, Map<String, BitSetBuilder> dictionary) {
        BitSetBuilder builder = new BitSetBuilder();
        message.chars()
                .mapToObj(Character::toString)
                .map(dictionary::get)
                .forEach(builder::concat);

        return builder.buildWithFence();
    }


    public static IHufdanNode generateTree(String s) {
        var charCount = countChars(s);
        var pq = toPriorityQueue(charCount);
        return collapseToTree(pq);
    }


    public static HufdanEncoded encode(String s) {
        var tree = generateTree(s);
        var dictionary = toHufdanDict(tree);
        var encoded = encodeMessage(s, dictionary);
        return new HufdanEncoded(encoded, tree);
    }
}
