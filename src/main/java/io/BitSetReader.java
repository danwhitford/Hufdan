package io;

import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.BitSet;
import java.util.Iterator;

public class BitSetReader implements Iterable<Boolean> {
    private final BitSet bs;
    private final int length;
    private int next = 0;

    public BitSetReader(BitSet bs) {
        this.bs = bs;
        this.length = bs.length();
    }

    public boolean read() {
        if (next > length) {
            throw new RuntimeException("End of bitset");
        }
        int tmp = next;
        next++;
        return bs.get(tmp);
    }

    @Override
    public Iterator<Boolean> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return next < length - 1;
            }

            @Override
            public Boolean next() {
                var tmp = next;
                next++;
                return bs.get(tmp);
            }
        };
    }
}
