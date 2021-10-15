package io;

import types.*;

import java.io.*;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class HufdanFileReader {
    private static List<Boolean> bitsToEncoded(BitSet bs) {
        List<Boolean> encoded = new ArrayList<>();
        for(int i=0; i < bs.length(); ++i) {
            encoded.add(bs.get(i));
        }
        return encoded;
    }

    private static IHufdanNode arrayToTree(StringReader reader, BitsetReader bitsetReader) throws IOException {
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

    public static HufdanEncoded read(String fname) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(fname);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        var e = (HufdanSerializable) in.readObject();
        in.close();
        fileIn.close();

        var encoded = bitsToEncoded(e.getEncoded());
        IHufdanNode tree = arrayToTree(
                new StringReader(new String(e.getTree())),
                new BitsetReader(e.getTreeKey())
        );

        return new HufdanEncoded(encoded, tree);
    }
}
