package io;

import io.BitSetBuilder;
import io.BitSetReader;
import types.*;

import java.io.IOException;
import java.io.StringReader;

public class HufdanConverter {

    private static void preOrderCollect(IHufdanNode node, StringBuilder builder, BitSetBuilder bs) {
        if(node instanceof HufdanLeafNode) {
            bs.append(true);
            builder.append(((HufdanLeafNode) node).getVal());
        } else if (node instanceof HufdanInternalNode) {
            bs.append(false);
            preOrderCollect(((HufdanInternalNode) node).getLeft(), builder, bs);
            preOrderCollect(((HufdanInternalNode) node).getRight(), builder, bs);
        }
    }

    private static IHufdanNode arrayToTree(StringReader reader, BitSetReader bitsetReader) throws IOException {
        var nextBit = bitsetReader.read();
        if (nextBit) {
            var ch = new String(Character.toChars(reader.read()));
            return new HufdanLeafNode(ch, 0L);
        } else {
            var left = arrayToTree(reader, bitsetReader);
            var right = arrayToTree(reader, bitsetReader);
            return new HufdanInternalNode(left, right);
        }
    }

    public static HufdanSerializable toSerializable (HufdanEncoded hufdanEncoded) {
        StringBuilder stringBuilder = new StringBuilder();
        BitSetBuilder bitSetBuilder = new BitSetBuilder();
        var current = hufdanEncoded.getTree();

        preOrderCollect(current, stringBuilder, bitSetBuilder);

        return new HufdanSerializable(
            stringBuilder.toString().toCharArray(),
            bitSetBuilder.buildWithFence(),
            hufdanEncoded.getEncoded()
        );
    }

    public static HufdanEncoded fromSerializable (HufdanSerializable hufdanSerializable) throws IOException {
        var encoded = hufdanSerializable.getEncoded();

        var stringReader = new StringReader(new String(hufdanSerializable.getTree()));
        var bitSetReader = new BitSetReader(hufdanSerializable.getTreeKey());
        IHufdanNode tree = arrayToTree(
                stringReader, bitSetReader
        );
        return new HufdanEncoded(encoded, tree);
    }
}
