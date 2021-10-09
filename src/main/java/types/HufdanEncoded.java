package types;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HufdanEncoded {
    private List<Boolean> encoded;
    private Map<String, List<Boolean>> dictionary;

    public HufdanEncoded(List<Boolean> encoded, Map<String, List<Boolean>> dictionary) {
        this.encoded = encoded;
        this.dictionary = dictionary;
    }

    public List<Boolean> getEncoded() {
        return encoded;
    }

    public Map<String, List<Boolean>> getDictionary() {
        return dictionary;
    }
}
