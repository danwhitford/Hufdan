package io;

import java.util.BitSet;

public class BitSetBuilder {

    private int next = 0;
    private BitSet bs = new BitSet();

    public BitSetBuilder() {
    }

    public BitSetBuilder(BitSetBuilder code) {
        var other = code.bs;
        for (int i = 0; i < code.next; ++i) {
            bs.set(i, other.get(i));
        }
        next = code.next;
    }

    public void append(boolean b) {
        bs.set(next, b);
        next++;
    }

    public void concat(BitSetBuilder other) {
        for(int i = 0; i < other.next; ++i) {
            bs.set(i + next, other.bs.get(i));
        }
        next += other.next;
    }

    public BitSet buildWithFence() {
        BitSet ret = (BitSet) bs.clone();
        ret.set(next);
        return ret;
    }
}
