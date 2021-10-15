package io;

import types.HufdanEncoded;
import types.HufdanInternalNode;
import types.HufdanLeafNode;
import types.IHufdanNode;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class HufdanFileWriter {
    private final HufdanSerializable hufdanSerilizable;

    public static void printTree(IHufdanNode node) {
        if(node instanceof HufdanLeafNode) {
            System.out.println(((HufdanLeafNode) node).getVal());
        } else if (node instanceof HufdanInternalNode) {
            printTree(((HufdanInternalNode) node).getLeft());
            printTree(((HufdanInternalNode) node).getRight());
        }
    }

    public HufdanFileWriter(HufdanEncoded hufdanEncoded) {
        StringBuilder stringBuilder = new StringBuilder();
        BitSetBuilder bitSetBuilder = new BitSetBuilder();
        var current = hufdanEncoded.getTree();

        preOrderCollect(current, stringBuilder, bitSetBuilder);

        this.hufdanSerilizable = new HufdanSerializable(
                stringBuilder.toString().toCharArray(),
                bitSetBuilder.buildWithFence(),
                hufdanEncoded.getEncoded());
    }

    private void preOrderCollect(IHufdanNode node, StringBuilder builder, BitSetBuilder bs) {
        if(node instanceof HufdanLeafNode) {
            bs.append(true);
            builder.append(((HufdanLeafNode) node).getVal());
        } else if (node instanceof HufdanInternalNode) {
            bs.append(false);
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
