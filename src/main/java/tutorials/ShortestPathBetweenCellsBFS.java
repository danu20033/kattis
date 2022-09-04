package tutorials;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ShortestPathBetweenCellsBFS {

    public static void main(String[] args) {
        int[][] matrix = {{1, 0, 1}, {0, 1, 1}, {0, 0, 1}};

        int start[] = {0, 0};
        int end[] = {1, 1};
        System.out.println("cast 1: ");
        shortestPath(matrix, start, end);

        //case 2 , there is path
        int[] start1 = {0, 2};
        int[] end1 = {1, 1};
        System.out.println("case 2: ");
        shortestPath(matrix, start1, end1);
    }


    public static void shortestPath(int[][] matrix, int[] start, int[] end) {
        int sx = start[0], sy = start[1];
        int dx = end[0], dy = end[1];

        if (matrix[sx][sy] == 0 || matrix[dx][dy] == 0) {
            System.out.println("There is no path.");
            return;
        }

        //initialize cells
        int m = matrix.length;
        int n = matrix[0].length;

        Cell[][] cells = new Cell[m][n];

        //build the graph
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != 0) {
                    cells[i][j] = new Cell(i, j, Integer.MAX_VALUE, null);
                }
            }

        }

        //BFS

        Queue<Cell> queue = new LinkedList<>();
        //create source cell and add it to the queue.
        Cell source = cells[sx][sy];
        source.dist = 0;
        queue.add(source);

        Cell dest = null;
        Cell p;

        while ((p = queue.poll()) != null) {
            //check the destination
            if (p.x == dx && p.y == dy) {
                dest = p;
                break;
            }

            //moving up
            visit(cells, queue, p.x - 1, p.y, p);
            //moving down
            visit(cells, queue, p.x + 1, p.y, p);
            //moving left
            visit(cells, queue, p.x, p.y - 1, p);
            //moving right
            visit(cells, queue, p.x, p.y + 1, p);


        }

        // find the path.
        if (dest == null) {
            System.out.println("there is no path.");
            return;
        } else {
            LinkedList<Cell> path = new LinkedList<>();
            p = dest;
            do {
                path.addFirst(p);

            } while ((p = p.prev) != null);

            System.out.println(path);
        }


    }

    private static void visit(Cell[][] cells, Queue<Cell> queue, int x, int y, Cell parent) {
        // check for out of bound.
        if (x < 0 || x >= cells.length || y < 0 || y >= cells[0].length || cells[x][y] == null) {
            return;
        }
        //update distance,and previous node
        int dist = parent.dist + 1;
        Cell p = cells[x][y];
        if (dist < p.dist) {
            p.dist = dist;
            p.prev = parent;
            queue.add(p);
        }
    }


    private static class Cell {
        int x;
        int y;
        int dist;
        Cell prev;

        public Cell(int x, int y, int dist, Cell prev) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.prev = prev;
        }

        @Override
        public String toString() {
            return "( " + x + "," + y + ")";
        }
    }

}
