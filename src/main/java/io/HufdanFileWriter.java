package io;

import types.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class HufdanFileWriter {
    private final HufdanSerializable hufdanSerilizable;

    public HufdanFileWriter(HufdanEncoded hufdanEncoded) {
        this.hufdanSerilizable = HufdanConverter.toSerializable(hufdanEncoded);
    }

    public void write(String fname) throws IOException {
        FileOutputStream fout = new FileOutputStream(fname);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(this.hufdanSerilizable);
    }
}
