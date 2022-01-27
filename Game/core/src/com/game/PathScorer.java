public interface PathScorer<T extends GraphNode> {
    double computeCost(T from, T to);
}