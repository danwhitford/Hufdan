package io;

import types.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

public class HufdanFileReader {
    private static List<Boolean> bitsToEncoded(BitSet bs) {
        List<Boolean> encoded = new ArrayList<>();
        for(int i=0; i < bs.length() - 1; ++i) {
            encoded.add(bs.get(i));
        }
        return encoded;
    }

    private static IHufdanNode arrayToTree(StringReader reader) throws IOException {
        var head = reader.read();
        if (head == '1') {
            var ch = new String(Character.toChars(reader.read()));
            return new HufdanLeafNode(ch, 0L);
        } else {
            var left = arrayToTree(reader);
            var right = arrayToTree(reader);
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
        IHufdanNode tree = arrayToTree(new StringReader(new String(e.getTree())));

        return new HufdanEncoded(encoded, tree);
    }
}
