

/*
 * CS54204 2017 Link State Routing Simulator(Dijkstra�s algorithm)
 * DijkstraAlgorithm class is used to find the shortest path from source to destination
 * Build a Forward Table
 * Shortest Path to Destination Router
 *
 * @author Ahsan Burney
 */
import java.util.LinkedList;

public class DijkstraAlgorithm {
    static int V;
    static int root[];
    static Boolean visitedArray[];
    static int sDistance[];



    //Dijkstra method is used to build the algorithm
    void dijkstra(int[][] g, int sNode) {
        V = g.length;
        int i;
        int max = Integer.MAX_VALUE;
        sDistance = new int[V];
        visitedArray = new Boolean[V];
        for (int j : root = new int[V]) {}
        i = 0;
        while (i < V) {
            sDistance[i] = max;
            visitedArray[i] = false;
            root[sNode] = -1;
            i++;
        }
        sDistance[sNode] = 0;
        int count = 0;
        while (count < V - 1) {
            int nextNode;
            nextNode = shortestRoute(sDistance, visitedArray);
            visitedArray[nextNode] = true;
            int l = 0;
            while (l < V) {
                if (g[nextNode][l] == -1) {
                    g[nextNode][l] = 0;
                }
                if (!(visitedArray[l] || !(g[nextNode][l] != 0) || !(sDistance[nextNode] != max) || !((sDistance[nextNode] + g[nextNode][l]) < sDistance[l]))) {
                    sDistance[l] = sDistance[nextNode] + g[nextNode][l];
                    root[l] = nextNode;
                }
                l++;
            }
            count++;
        }
    }

    //Shortest Route method calculates shortest Distance node from the set of nodes

    static int shortestRoute(int shortestValue[], Boolean storedValue[]) {
        int notConnected;
        int maxValue;
        maxValue = Integer.MAX_VALUE;
        notConnected = -1;
        int l = 0;
        while (l < V) {
            if (!storedValue[l]) {
                if (shortestValue[l] > maxValue) {
                } else {
                    maxValue = shortestValue[l];
                    notConnected = l;
                }
            }
            l++;
        }
        return notConnected;
    }



}
