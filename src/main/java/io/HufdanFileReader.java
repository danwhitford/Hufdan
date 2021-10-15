package io;

import types.*;

import java.io.*;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class HufdanFileReader {

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

    public static HufdanEncoded read(String fname) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(fname);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        var e = (HufdanSerializable) in.readObject();
        in.close();
        fileIn.close();

        var encoded = e.getEncoded();

        var stringReader = new StringReader(new String(e.getTree()));
        var bitSetReader = new BitSetReader(e.getTreeKey());
        IHufdanNode tree = arrayToTree(
                stringReader, bitSetReader
        );

        return new HufdanEncoded(encoded, tree);
    }
}
