package io;

import types.HufdanEncoded;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class HufdanFileReader {
    public static HufdanEncoded read(String fname) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(fname);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        var e = (HufdanSerializable) in.readObject();
        in.close();
        fileIn.close();

        List<Boolean> encoded = new ArrayList<>();
        var bs = e.getEncoded();
        var dict = e.getDictionary();
        for(int i=0; i < bs.length(); ++i) {
            encoded.add(bs.get(i));
        }
        return new HufdanEncoded(encoded, dict);
    }
}
