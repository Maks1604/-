package Lesson7;

public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph(10);
        graph.addEdge(0, 2);
        graph.addEdge(0, 1);
        graph.addEdge(2, 1);
        graph.addEdge(1, 3);
        graph.addEdge(3, 7);
        graph.addEdge(5, 0);
        graph.addEdge(7, 4);
        graph.addEdge(4, 3);

        graph.addEdge(3, 5);

        graph.addEdge(5, 6);
        graph.addEdge(6, 7);
        graph.addEdge(7, 2);
        graph.addEdge(2, 9);
        graph.addEdge(9, 8);
        graph.addEdge(8, 0);

        DepthFirstPaths dfs = new DepthFirstPaths(graph, 0);

        System.out.println(dfs.hasPathTo(5));
        System.out.println(dfs.hasPathTo(8));
        System.out.println(dfs.hasPathTo(9));
        if (dfs.hasPathTo(5)) {
            System.out.println(dfs.pathTo(5));
        }


        BreadthFirstSearch bfs = new BreadthFirstSearch(graph, 0);

        System.out.println(bfs.hasPathTo(9));
        System.out.println(bfs.pathTo(9));
        System.out.println(bfs.distTo(9));

    }
}
