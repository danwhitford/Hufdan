package types;

public class HufdanInternalNode implements Comparable<IHufdanNode>, IHufdanNode {
    private final IHufdanNode right;
    private final IHufdanNode left;
    private final long weight;

    public HufdanInternalNode(IHufdanNode left, IHufdanNode right) {
        this.left = left;
        this.right = right;
        this.weight = left.getWeight() + right.getWeight();
    }

    @Override
    public Long getWeight() {
        return this.weight;
    }

    @Override
    public int compareTo(IHufdanNode o) {
        return this.getWeight().compareTo(o.getWeight());
    }

    public IHufdanNode getRight() {
        return right;
    }

    public IHufdanNode getLeft() {
        return left;
    }
}
