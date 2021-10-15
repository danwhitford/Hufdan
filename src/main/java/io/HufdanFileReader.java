package io;

import types.HufdanEncoded;
import types.HufdanSerializable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class HufdanFileReader {

    public static HufdanEncoded read(String fname) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(fname);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        var e = (HufdanSerializable) in.readObject();
        in.close();
        fileIn.close();

        return HufdanConverter.fromSerializable(e);
    }
}
