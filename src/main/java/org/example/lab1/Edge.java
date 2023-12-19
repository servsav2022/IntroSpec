package org.example.lab1;

public class Edge {
    public final int u; // вершина "откуда"
    public final int v; // вершина "куда"
    public Edge(int u, int v) {
        this.u = u;
        this.v = v;
    }
    public Edge reversed() {
        return new Edge(v, u);
    }
    @Override
    public String toString() {
        return u + " -> " + v;
    }
}