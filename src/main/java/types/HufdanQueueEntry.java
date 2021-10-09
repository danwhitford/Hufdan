package types;

public class HufdanQueueEntry {
    private IHufdanNode node;
    private String code;

    public HufdanQueueEntry(IHufdanNode node, String code) {
        this.node = node;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public IHufdanNode getNode() {
        return node;
    }

}
