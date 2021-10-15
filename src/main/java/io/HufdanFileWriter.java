package io;

import types.HufdanEncoded;
import types.HufdanInternalNode;
import types.HufdanLeafNode;
import types.IHufdanNode;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.BitSet;

public class HufdanFileWriter {
    private final HufdanSerializable hufdanSerilizable;

    public HufdanFileWriter(HufdanEncoded hufdanEncoded) {
        var bits = messageToBits(hufdanEncoded);

        StringBuilder stringBuilder = new StringBuilder();
        BitSetBuilder bitSetBuilder = new BitSetBuilder();
        var current = hufdanEncoded.getTree();
        preOrderCollect(current, stringBuilder, bitSetBuilder);

        this.hufdanSerilizable = new HufdanSerializable(
                stringBuilder.toString().toCharArray(),
                bitSetBuilder.getBitSet(),
                bits);
    }

    private BitSet messageToBits(HufdanEncoded hufdanEncoded) {
        BitSet bitSet = new BitSet();
        for (int i=0; i < hufdanEncoded.getEncoded().size(); ++i) {
            bitSet.set(i, hufdanEncoded.getEncoded().get(i));
        }
        bitSet.set(hufdanEncoded.getEncoded().size(), true);
        return bitSet;
    }

    private void preOrderCollect(IHufdanNode node, StringBuilder builder, BitSetBuilder bs) {
        if(node instanceof HufdanLeafNode) {
            bs.write(true);
            builder.append(((HufdanLeafNode) node).getVal());
        } else if (node instanceof HufdanInternalNode) {
            bs.write(false);
            preOrderCollect(((HufdanInternalNode) node).getLeft(), builder, bs);
            preOrderCollect(((HufdanInternalNode) node).getRight(), builder, bs);
        }
    }

    public void write(String fname) throws IOException {
        FileOutputStream fout = new FileOutputStream(fname);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(this.hufdanSerilizable);
    }
}
