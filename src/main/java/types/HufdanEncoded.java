package types;

import java.util.BitSet;

public class HufdanEncoded {
    private final BitSet encoded;
    private final IHufdanNode tree;

    public HufdanEncoded(BitSet encoded, IHufdanNode tree) {
        this.encoded = encoded;
        this.tree = tree;
    }

    public BitSet getEncoded() {
        return encoded;
    }

    public IHufdanNode getTree() {
        return tree;
    }

}
