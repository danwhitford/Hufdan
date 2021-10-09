package encode;

import types.HufdanInternalNode;
import types.HufdanLeafNode;
import types.HufdanQueueEntry;
import types.IHufdanNode;

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

    private static Map<String, String> toHufdanDict(IHufdanNode root) {
        Map<String, String> ret = new HashMap<>();
        Queue<HufdanQueueEntry> q = new ArrayDeque<>();
        q.add(new HufdanQueueEntry(root, ""));

        while (!q.isEmpty()) {
            var head = q.poll();
            if (head.getNode() instanceof HufdanLeafNode) {
                ret.put(((HufdanLeafNode) head.getNode()).getVal(), head.getCode());
            } else if (head.getNode() instanceof HufdanInternalNode) {
                var internal = (HufdanInternalNode) head.getNode();
                var code = head.getCode();
                q.offer(new HufdanQueueEntry(internal.getLeft(), code + "0"));
                q.offer(new HufdanQueueEntry(internal.getRight(), code + "1"));
            } else {
                throw new RuntimeException("Unknown Hufdan node type");
            }
        }

        return ret;
    }

    public static byte[] encode(String s) {
        var charCount = countChars(s);
        var pq = toPriorityQueue(charCount);
        var tree = collapseToTree(pq);
        var dict = toHufdanDict(tree);
        return s.getBytes();
    }
}
