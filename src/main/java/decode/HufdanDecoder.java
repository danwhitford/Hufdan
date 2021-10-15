package decode;

import io.BitSetReader;
import types.HufdanEncoded;
import types.HufdanInternalNode;
import types.HufdanLeafNode;
import types.IHufdanNode;

import java.util.List;

public class HufdanDecoder {
    public static String decode(HufdanEncoded encoded) {
        IHufdanNode root = encoded.getTree();
        IHufdanNode current = root;

        var coded = new BitSetReader(encoded.getEncoded());
        StringBuilder stringBuilder = new StringBuilder();

        for (Boolean b : coded) {
            if (current instanceof HufdanInternalNode) {
                if (b) {
                    current = ((HufdanInternalNode) current).getRight();
                } else {
                    current = ((HufdanInternalNode) current).getLeft();
                }
            }

            if (current instanceof HufdanLeafNode) {
                stringBuilder.append(((HufdanLeafNode) current).getVal());
                current = root;
            }
        }

        return stringBuilder.toString();
    }
}
