package types;

import io.BitSetBuilder;

import java.util.List;

public class HufdanQueueEntry {
    private IHufdanNode node;
    private BitSetBuilder code;

    public HufdanQueueEntry(IHufdanNode node, BitSetBuilder code) {
        this.node = node;
        this.code = code;
    }

    public BitSetBuilder getCode() {
        return code;
    }

    public IHufdanNode getNode() {
        return node;
    }

}
