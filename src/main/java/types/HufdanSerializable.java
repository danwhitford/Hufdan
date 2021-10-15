package types;

import types.HufdanEncoded;
import types.HufdanInternalNode;
import types.HufdanLeafNode;
import types.IHufdanNode;

import java.io.Serializable;
import java.util.BitSet;

public class HufdanSerializable implements Serializable {
    private final char[] tree;
    private final BitSet treeKey;
    private final BitSet encoded;

    public HufdanSerializable(char[] tree, BitSet treeKey, BitSet encoded) {
        this.tree = tree;
        this.treeKey = treeKey;
        this.encoded = encoded;
    }

    public char[] getTree() {
        return tree;
    }

    public BitSet getEncoded() {
        return encoded;
    }

    public BitSet getTreeKey() {
        return treeKey;
    }
}
