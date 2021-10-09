package types;

import java.util.List;

public class HufdanQueueEntry {
    private IHufdanNode node;
    private List<Boolean> code;

    public HufdanQueueEntry(IHufdanNode node, List<Boolean> code) {
        this.node = node;
        this.code = code;
    }

    public List<Boolean> getCode() {
        return code;
    }

    public void setCode(List<Boolean> code) {
        this.code = code;
    }

    public IHufdanNode getNode() {
        return node;
    }

}
