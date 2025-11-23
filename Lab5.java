import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lab5 {

    final static int V = 8;
    final static int INF = 10000000;

    public static void main(String[] args) {
        int[][] G = {
                {INF,6,2,4, INF, INF, INF, INF},
                {6, INF, INF,6, INF,6, INF, INF},
                {2, INF, INF, INF, INF, INF, INF,4},
                {4,6, INF, INF,1, INF, INF, INF},
                {INF, INF, INF,1, INF,4,7, INF},
                {INF,6, INF, INF,4, INF,5,3},
                {INF, INF, INF, INF,7,5, INF,5},
                {INF, INF,4, INF, INF,3,5, INF}
        };
        prim(G);

        System.out.println("\n\n");
        kruskal(G);
    }

    public static void prim(int[][] G) {
        int no_edge = 0;
        int[] selected = new int[V];

        selected[0] = 1;

        int x, y, total =0;

        System.out.println("Edge : Weight");
        while (no_edge < V - 1) {
            int min = INF;
            x = 0;
            y = 0;

            for (int i = 0; i < V; i++) {
                if (selected[i] == 1) {

                    for (int j = 0; j < V; j++) {

                        if (selected[j] == 0 && G[i][j] != INF) {

                            if (min > G[i][j]) {
                                min = G[i][j];
                                x = i;
                                y = j;
                            }
                        }
                    }
                }
            }
            total+=G[x][y];
            System.out.println((x + 1) + " - " + (y + 1) + " : " + G[x][y]);
            selected[y] = 1;
            no_edge++;
        }
        System.out.println("Total weight: " + total);
    }

    public static void kruskal(int[][] G) {
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            for (int j = i + 1; j < V; j++) {
                if (G[i][j] != INF) edges.add(new Edge(i, j, G[i][j]));
            }
        }

        Collections.sort(edges);
        int[] parent = new int[V];
        for (int i = 0; i < V; i++) parent[i] = i;

        List<Edge> mst = new ArrayList<>();
        int totalWeight = 0;

        for (Edge e : edges) {
            int uRep = findSet(parent, e.u);
            int vRep = findSet(parent, e.v);
            if (uRep != vRep) {
                mst.add(e);
                totalWeight += e.w;
                unionSet(parent, uRep, vRep);
            }
        }

        System.out.println("Edge : Weight");
        for (Edge e : mst) {
            System.out.println((e.u + 1) + " - " + (e.v + 1) + " : " + e.w);
        }
        System.out.println("Total weight: " + totalWeight);
    }

    private static int findSet(int[] parent, int i) {
        if (parent[i] == i) return i;
        return parent[i] = findSet(parent, parent[i]);
    }

    private static void unionSet(int[] parent, int u, int v) {
        parent[u] = v;
    }

    static class Edge implements Comparable<Edge> {
        int u, v, w;
        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
        public int compareTo(Edge other) {
            return Integer.compare(this.w, other.w);
        }
    }
}
