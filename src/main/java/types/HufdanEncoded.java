package types;

import java.io.Serializable;
import java.util.BitSet;
import java.util.List;
import java.util.Map;

public class HufdanEncoded implements Serializable {
    private final BitSet encodedBits;
    private final Map<String, List<Boolean>> dictionary;

    public HufdanEncoded(List<Boolean> encoded, Map<String, List<Boolean>> dictionary) {
        this.dictionary = dictionary;

        this.encodedBits = new BitSet();
        for(int i=0; i < encoded.size(); ++i) {
            encodedBits.set(i, encoded.get(i));
        }
    }

    public BitSet getEncodedBits() {
        return encodedBits;
    }

    public Map<String, List<Boolean>> getDictionary() {
        return dictionary;
    }
}
