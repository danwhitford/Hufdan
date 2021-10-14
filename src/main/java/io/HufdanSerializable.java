package io;

import types.IHufdanNode;

import java.io.Serializable;
import java.util.BitSet;

public class HufdanSerializable implements Serializable {
    private final char[] tree;
    private final BitSet encoded;

    public HufdanSerializable(char[] tree, BitSet encoded) {
        this.tree = tree;
        this.encoded = encoded;
    }

    public char[] getTree() {
        return tree;
    }

    public BitSet getEncoded() {
        return encoded;
    }
}
