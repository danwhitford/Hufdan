package types;

public class HufdanLeafNode implements Comparable<IHufdanNode>, IHufdanNode {
    private final String val;
    private final Long weight;

    public HufdanLeafNode(String val, Long weight) {
        this.val = val;
        this.weight = weight;
    }

    @Override
    public int compareTo(IHufdanNode o) {
        return this.getWeight().compareTo(o.getWeight());
    }

    public Long getWeight() {
        return this.weight;
    }

    public String getVal() {
        return val;
    }
}

