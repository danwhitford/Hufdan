package io;

import types.HufdanEncoded;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.BitSet;

public class HufdanFileWriter {
    private HufdanSerilizable hufdanSerilizable;

    public HufdanFileWriter(HufdanEncoded hufdanEncoded) {
        var bits = messageToBits(hufdanEncoded);
        var dict = hufdanEncoded.getDictionary();
        this.hufdanSerilizable = new HufdanSerilizable(dict, bits);
    }

    private BitSet messageToBits(HufdanEncoded hufdanEncoded) {
        BitSet bitSet = new BitSet();
        for (int i=0; i < hufdanEncoded.getEncoded().size(); ++i) {
            bitSet.set(i, hufdanEncoded.getEncoded().get(i));
        }
        return bitSet;
    }

    public void write(String fname) throws IOException {
        FileOutputStream fout = new FileOutputStream(fname);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(this.hufdanSerilizable);
    }
}
