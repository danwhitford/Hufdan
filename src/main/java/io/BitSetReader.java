package io;

import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.BitSet;

public class BitSetReader {
    private final BitSet bs;
    private final int length;
    private int next = 0;

    public BitSetReader(BitSet bs) {
        this.bs = bs;
        this.length = bs.length();
    }

    public boolean read() {
        if (next >= length) {
            throw new RuntimeException("End of bitset");
        }
        int tmp = next;
        next++;
        return bs.get(tmp);
    }
}
