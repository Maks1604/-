package Lesson7;

public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph(13);
        /*graph.addEdge(0, 6);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(5, 3);
        graph.addEdge(5, 4);
        graph.addEdge(5, 0);
        graph.addEdge(6, 4);
        graph.addEdge(3, 4);

        graph.addEdge(7, 8);

        graph.addEdge(9, 10);
        graph.addEdge(9, 12);
        graph.addEdge(9, 11);
        graph.addEdge(11, 12);

        DepthFirstPaths dfs = new DepthFirstPaths(graph, 0);

        System.out.println(dfs.hasPathTo(5));
        System.out.println(dfs.hasPathTo(7));
        System.out.println(dfs.hasPathTo(11));
        if (dfs.hasPathTo(5)) {
            System.out.println(dfs.pathTo(5));
        }*/
        graph.addEdge(0, 2);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(3, 5);
        graph.addEdge(3, 4);
        graph.addEdge(3, 2);
        graph.addEdge(4, 2);
        graph.addEdge(5, 0);

        BreadthFirstSearch bfs = new BreadthFirstSearch(graph, 0);

        System.out.println(bfs.hasPathTo(3));
        System.out.println(bfs.pathTo(3));
        System.out.println(bfs.distTo(3));

    }
}
