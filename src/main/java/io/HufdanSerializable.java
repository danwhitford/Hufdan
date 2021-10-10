package io;

import java.io.Serializable;
import java.util.BitSet;
import java.util.List;
import java.util.Map;

public class HufdanSerializable implements Serializable {
    private final Map<String, List<Boolean>> dictionary;
    private final BitSet encoded;

    public HufdanSerializable(Map<String, List<Boolean>> dictionary, BitSet encoded) {
        this.dictionary = dictionary;
        this.encoded = encoded;
    }

    public Map<String, List<Boolean>> getDictionary() {
        return dictionary;
    }

    public BitSet getEncoded() {
        return encoded;
    }
}
