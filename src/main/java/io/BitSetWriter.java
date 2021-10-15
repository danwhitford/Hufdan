package io;

import java.util.BitSet;

public class BitSetWriter {
    private int next = 0;
    private final BitSet bs = new BitSet();

    public void write(boolean b) {
        bs.set(next, b);
        next++;
    }

    public void write(BitSet other) {
        for(int i = 0; i < other.length(); ++i) {
            bs.set(next, other.get(i));
            next++;
        }
    }

    public BitSet getBitSet() {
        return bs;
    }
}
