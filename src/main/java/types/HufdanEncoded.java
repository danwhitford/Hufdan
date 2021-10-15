package types;

import java.util.List;

public class HufdanEncoded {
    private final List<Boolean> encoded;
    private final IHufdanNode tree;

    public HufdanEncoded(List<Boolean> encoded, IHufdanNode tree) {
        this.encoded = encoded;
        this.tree = tree;
    }

    public List<Boolean> getEncoded() {
        return encoded;
    }

    public IHufdanNode getTree() {
        return tree;
    }

}
