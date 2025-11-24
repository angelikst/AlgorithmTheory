import java.util.PriorityQueue;
import java.util.Queue;

public class Lab6 {
    final static int V = 8;
    final static int INF = Integer.MAX_VALUE;


    public static void main(String[] args) {
        int[][] G = {
                {0,6,2,4, INF, INF, INF, INF},
                {6, 0, INF,6, INF,6, INF, INF},
                {2, INF, 0, INF, INF, INF, INF,4},
                {4,6, INF, 0,1, INF, INF, INF},
                {INF, INF, INF,1, 0,4,7, INF},
                {INF,6, INF, INF,4, 0,5,3},
                {INF, INF, INF, INF,7,5, 0,5},
                {INF, INF,4, INF, INF,3,5, 0}
        };

        dijkstra(G,0);

        int [][] W = floyd(G);
    }

    static int[] pred = new int[V];
    static int[] dist = new int[V];

    public static void dijkstra(int[][] G, int s) {


        Queue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));

        for(int i =  0; i < V; i++){
            pred[i] = -1;
            if(i == s) dist[i] = 0;
            else dist[i] = INF;
            pq.add(new int[]{i,dist[i]});
        }

        System.out.print("      1 2 3 4 5 6 7 8            1 2 3 4 5 6 7 8\n");
        while (!pq.isEmpty()) {
            int[] rec = pq.poll();
            int u = rec[0];
            int du = rec[1];

            if (du != dist[u]) continue;


            for (int i = 0; i < 8; i++) {

                if (G[u][i] == INF) continue;

                if (dist[i] > dist[u] + G[u][i]) {
                    dist[i] = dist[u] + G[u][i];
                    pred[i] = u;
                    pq.add(new int[]{i, dist[i]});
                }
            }
            printDijkstra(dist,pred);
        }

    }

    private static void printDijkstra(int[] dist, int[] pred) {

        for (int i=0; i <V;i++) {
            System.out.print("\nDistance to the element "+(i+1)+": ");
            if (dist[i] == INF) System.out.print("X  ");
            else System.out.print(dist[i] + " ");
        }

        for (int i=0; i <V;i++) {
            System.out.print("\nPred for the element "+i+": ");
            System.out.print(pred[i] + " ");
        }

        System.out.println();
    }


    static int[][]  floyd(int[][]G){
        int[][] W = new  int[V][V];

        for (int i = 0; i < V; i++) {
            System.arraycopy(G[i], 0, W[i], 0, V);
        }

        for (int k = 0; k < V; k++) {
            System.out.println("k = " + (k + 1) + "\n");

            for (int i = 0; i < V; i++) {
                System.out.println("i=" + (i + 1) + " j=1-8");

                for (int j = 0; j < V; j++) {

                    int old = W[i][j];
                    int left = W[i][k];
                    int right = W[k][j];

                    String L = (left == INF ? "∞" : String.valueOf(left));
                    String R = (right == INF ? "∞" : String.valueOf(right));
                    String O = (old == INF ? "∞" : String.valueOf(old));

                    int newVal = (left == INF || right == INF) ? INF : left + right;

                    String NV = (newVal == INF ? "∞" : String.valueOf(newVal));

                    System.out.println(
                            "d" + (i+1) + "," + (j+1) +
                                    " = min{" + L + "+" + R + ";" + O + "}=" + NV
                    );

                    if (newVal < old) {
                        W[i][j] = newVal;
                    }
                }
            }

            printFloyd(W);
            System.out.println();
        }

        return W;
    }
    static void printFloyd(int[][] G){
        System.out.print("    1   2   3   4   5   6   7   8\n");
        System.out.print("   ______________________________\n");
        for (int i = 0; i < V; i++) {
            System.out.print((i+1)+"|  ");
            for (int j = 0; j < V; j++) {
                if(G[i][j] == INF){
                    System.out.print("∞  ");
                }
                else{
                    System.out.printf("%-4d", G[i][j]);
                }
            }
            System.out.print("\n");
        }
    }


}
