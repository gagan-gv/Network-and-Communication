import java.util.*;

class MakeGraph{
    int nodes;
    int[][] graphMatrix;

    public MakeGraph(int nodes){
        this.nodes = nodes;
        graphMatrix = new int[nodes][nodes];
    }

    public void denoteEdge(int source, int destination, int weight){
        graphMatrix[source][destination] = weight;
    }

    int minVertex(boolean[] flag, int[] distance){
        int minDistance = Integer.MAX_VALUE;
        int node = -1;
        for (int i = 0; i < nodes; i++) {
            if(flag[i] == false && minDistance > distance[i]){
                minDistance = distance[i];
                node = i;
            }
        }
        return node;
    }

    public void print(int source, int[] distance){
        for (int i = 0; i < nodes; i++) {
            System.out.println("Source: " + source + " To: " + i + " Minimum Distance: " + distance[i]);
        }
    }

    public void getMinDistance(int source){
        boolean[] flag = new boolean[nodes];
        int[] distance = new int[nodes];

        int inf = Integer.MAX_VALUE;
        for (int i = 0; i < nodes; i++) {
            distance[i] = inf;
        }
        distance[source] = 0;
        for (int i = 0; i < nodes; i++) {
            int x = minVertex(flag, distance);
            flag[x] = true;

            for (int y = 0; y < nodes; y++){
                if(graphMatrix[x][y] > 0){
                    if(flag[y] == false && graphMatrix[x][y] != inf){
                        int updatedDistance = graphMatrix[x][y] + distance[x];
                        if(updatedDistance < distance[y]){
                            distance[y] = updatedDistance;
                        }
                    }
                }
            }
        }
        print(source, distance);
    }
}

public class DijkstraAlgo {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args){
        System.out.println("Enter number of nodes: ");
        int nodes = sc.nextInt();
        System.out.println("Enter the source node: ");
        int source = sc.nextInt();

        MakeGraph makeGraph = new MakeGraph(nodes);
        for (int i = 0; i < nodes; i++) {
            System.out.println("How many nodes are connected to node " + i + ": ");
            int conn = sc.nextInt();
            for (int j = 0; j < conn; j++) {
                System.out.println("Enter connected node " + (j+1) +" value: ");
                int connNode = sc.nextInt();
                System.out.println("Enter the weight: ");
                int weight = sc.nextInt();
                makeGraph.denoteEdge(i, connNode, weight);
            }
        }
        makeGraph.getMinDistance(source);
    }
}