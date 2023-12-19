package org.example.lab1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.IntConsumer;

public class WeightedGraph<V> extends Graph<V, WeightedEdge> {

    public WeightedGraph(List<V> vertices) {
        super(vertices);
    }
    // Это невзвешенный граф, поэтому мы всегда
    // добавляем ребра в обоих направлениях
    public void addEdge(WeightedEdge edge) {
        edges.get(edge.u).add(edge);
        edges.get(edge.v).add(edge.reversed());
    }
    public void addEdge(int u, int v, float weight) {
        addEdge(new WeightedEdge(u, v, weight));
    }
    public void addEdge(V first, V second, float weight) {
        addEdge(indexOf(first), indexOf(second), weight);
    }
    // упрощенная печать графика
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getVertexCount(); i++) {
            sb.append(vertexAt(i));
            sb.append(" -> ");
            sb.append(Arrays.toString(edgesOf(i).stream()
                    .map(we -> "(" + vertexAt(we.v) + ", " + we.weight +
                            ")").toArray()));
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
// представляет 15 крупнейших MSA в США.
        WeightedGraph<String> cityGraph2 = new WeightedGraph<>(
                List.of("Seattle", "San Francisco", "Los Angeles", "Riverside",
                        "Phoenix", "Chicago", "Boston", "New York", "Atlanta", "Miami",
                        "Dallas", "Houston", "Detroit", "Philadelphia", "Washington"));
        cityGraph2.addEdge("Seattle", "Chicago", 1737);
        cityGraph2.addEdge("Seattle", "San Francisco", 678);
        cityGraph2.addEdge("San Francisco", "Riverside", 386);
        cityGraph2.addEdge("San Francisco", "Los Angeles", 348);
        cityGraph2.addEdge("Los Angeles", "Riverside", 50);
        cityGraph2.addEdge("Los Angeles", "Phoenix", 357);
        cityGraph2.addEdge("Riverside", "Phoenix", 307);
        cityGraph2.addEdge("Riverside", "Chicago", 1704);
        cityGraph2.addEdge("Phoenix", "Dallas", 887);
        cityGraph2.addEdge("Phoenix", "Houston", 1015);
        cityGraph2.addEdge("Dallas", "Chicago", 805);
        cityGraph2.addEdge("Dallas", "Atlanta", 721);
        cityGraph2.addEdge("Dallas", "Houston", 225);
        cityGraph2.addEdge("Houston", "Atlanta", 702);
        cityGraph2.addEdge("Houston", "Miami", 968);
        cityGraph2.addEdge("Atlanta", "Chicago", 588);
        cityGraph2.addEdge("Atlanta", "Washington", 543);
        cityGraph2.addEdge("Atlanta", "Miami", 604);
        cityGraph2.addEdge("Miami", "Washington", 923);
        cityGraph2.addEdge("Chicago", "Detroit", 238);
        cityGraph2.addEdge("Detroit", "Boston", 613);
        cityGraph2.addEdge("Detroit", "Washington", 396);
        cityGraph2.addEdge("Detroit", "New York", 482);
        cityGraph2.addEdge("Boston", "New York", 190);
        cityGraph2.addEdge("New York", "Philadelphia", 81);
        cityGraph2.addEdge("Philadelphia", "Washington", 123);
        System.out.println(cityGraph2);
        List<WeightedEdge> mst = cityGraph2.mst(0);
        cityGraph2.printWeightedPath(mst);
    }

    public static double totalWeight(List<WeightedEdge> path) {
        return path.stream().mapToDouble(we -> we.weight).sum();
    }
    public List<WeightedEdge> mst(int start) {
        LinkedList<WeightedEdge> result = new LinkedList<>(); // здесь мы уже были
        if (start < 0 || start > (getVertexCount()-1)) {
            return result;
        }
        PriorityQueue<WeightedEdge> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[getVertexCount()];
// это похоже на внутреннюю функцию "visit"
        IntConsumer visit = index -> {
            visited[index] = true; // пометить как прочитанное
            for (WeightedEdge edge : edgesOf(index)) {
// добавляем все ребра отсюда в pq
                if (!visited[edge.v]) {
                    pq.offer(edge);
                }
            }
        };
        visit.accept(start); // первая вершина, с которой все начинается
        while (!pq.isEmpty()) { // продолжаем, пока остаются необработанные вершины
            WeightedEdge edge = pq.poll();
            if (visited[edge.v]) {
                continue; // никогда не просматриваем дважды
            }
// на данный момент это минимальный вес, так что добавляем в дерево
            result.add(edge);
            visit.accept(edge.v); // посетите места соединений
        }
        return result;
    }
    public void printWeightedPath(List<WeightedEdge> wp) {
        for (WeightedEdge edge : wp) {
            System.out.println(vertexAt(edge.u) + " "
                    + edge.weight + "> " + vertexAt(edge.v));
        }
        System.out.println("Total Weight: " + totalWeight(wp));
    }
}