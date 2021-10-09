package encode;

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

    private static Map<String, List<Boolean>> toHufdanDict(IHufdanNode root) {
        Map<String, List<Boolean>> ret = new HashMap<>();
        Queue<HufdanQueueEntry> q = new ArrayDeque<>();
        q.add(new HufdanQueueEntry(root, new ArrayList<>()));

        while (!q.isEmpty()) {
            var head = q.poll();
            if (head.getNode() instanceof HufdanLeafNode) {
                ret.put(((HufdanLeafNode) head.getNode()).getVal(), head.getCode());
            } else if (head.getNode() instanceof HufdanInternalNode) {
                var internal = (HufdanInternalNode) head.getNode();
                List<Boolean> code = head.getCode();
                List<Boolean> leftCode = new ArrayList<>(code);
                leftCode.add(false);
                List<Boolean> rightCode = new ArrayList<>(code);
                rightCode.add(true);
                q.offer(new HufdanQueueEntry(internal.getLeft(), leftCode));
                q.offer(new HufdanQueueEntry(internal.getRight(), rightCode));
            } else {
                throw new RuntimeException("Unknown Hufdan node type");
            }
        }

        return ret;
    }

    public static List<Boolean> encodeMessage(String message, Map<String, List<Boolean>> dictionary) {
        List<Boolean> foo = new ArrayList<>();
        message.chars()
                .mapToObj(Character::toString)
                .map(dictionary::get)
                .forEach(foo::addAll);

        return foo;
    }

    public static Map<String, List<Boolean>> generateDictionary(String s) {
        var charCount = countChars(s);
        var pq = toPriorityQueue(charCount);
        var tree = collapseToTree(pq);
        return toHufdanDict(tree);
    }

    public static HufdanEncoded encode(String s) {
        Map<String, List<Boolean>> dictionary = generateDictionary(s);
        return new HufdanEncoded(encodeMessage(s, dictionary), dictionary);
    }
}
