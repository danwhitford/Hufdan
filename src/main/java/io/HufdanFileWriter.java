package io;

import decode.HufdanDecoder;
import types.HufdanEncoded;
import types.HufdanInternalNode;
import types.HufdanLeafNode;
import types.IHufdanNode;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.BitSet;

public class HufdanFileWriter {
    private HufdanSerializable hufdanSerilizable;

    public HufdanFileWriter(HufdanEncoded hufdanEncoded) {
        var bits = messageToBits(hufdanEncoded);
        var treeArray = treeToArray(hufdanEncoded);
        this.hufdanSerilizable = new HufdanSerializable(treeArray, bits);
    }

    private BitSet messageToBits(HufdanEncoded hufdanEncoded) {
        BitSet bitSet = new BitSet();
        for (int i=0; i < hufdanEncoded.getEncoded().size(); ++i) {
            bitSet.set(i, hufdanEncoded.getEncoded().get(i));
        }
        bitSet.set(hufdanEncoded.getEncoded().size(), true);
        return bitSet;
    }

    private String preOrderCollect(IHufdanNode node, StringBuilder builder) {
        if(node instanceof HufdanLeafNode) {
            builder.append(1);
            builder.append(((HufdanLeafNode) node).getVal());
        } else if (node instanceof HufdanInternalNode) {
            builder.append(0);
            preOrderCollect(((HufdanInternalNode) node).getLeft(), builder);
            preOrderCollect(((HufdanInternalNode) node).getRight(), builder);
        }
        return builder.toString();
    }

    private char[] treeToArray(HufdanEncoded hufdanEncoded) {
        StringBuilder stringBuilder = new StringBuilder();
        var current = hufdanEncoded.getTree();
        return preOrderCollect(current, stringBuilder).toCharArray();
    }

    public void write(String fname) throws IOException {
        FileOutputStream fout = new FileOutputStream(fname);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(this.hufdanSerilizable);
    }
}
