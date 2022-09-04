package tutorials;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public class MinimumKnightMove {


    // Driver code
    public static void main(String[] args) {
       /* int N[] = {8, 8};
        int knightPos[] = {0, 0};
        int targetPos[] = {7, 7};
        System.out.println(
                minStepToReachTarget(
                        knightPos, targetPos, N));

        int N2[] = {8, 8};
        int knightPos2[] = {1, 1};
        int targetPos2[] = {8, 8};

        System.out.println(
                minStepToReachTarget(
                        knightPos2, targetPos2, N2));

        int N3[] = {8, 1000000000};
        int knightPos3[] = {3, 3};
        int targetPos3[] = {3, 999999999};

        System.out.println(
                minStepToReachTarget(
                        knightPos3, targetPos3, N3));

        */

        System.out.println(distance(7,7));
    }



    private static int distance(int x, int y){

        //axes symmetry
        x=Math.abs(x);
        y=Math.abs(y);

        //diagonal symmetry
        if(x<y){
            int t;
            t=x;
            y=x;
            y=t;
        }

        // 2 corner cases
        if(x==1 && y == 0){
            return 3;
        }
        if(x==2 && y == 2){
            return 4;
        }

        // main formula
        int delta = x-y;
        if(y>delta){
            return Double.valueOf(delta - 2*Math.floor((delta-y)/3)).intValue();
        }
        else{
            return Double.valueOf(delta - 2*Math.floor((delta-y)/4)).intValue();
        }


    }


    // Class for storing a cell's data
    static class cell {
        int x, y;
        int dis;

        public cell(int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }
    }


    // Utility method returns true if (x, y) lies
    // inside Board
    static boolean isInside(int x, int y, int N[]) {
        if (x >= 1 && x <= N[0] && y >= 1 && y <= N[1])
            return true;
        return false;
    }

    // Method returns minimum step
    // to reach target position
    static int minStepToReachTarget(
            int knightPos[], int targetPos[],
            int N[]) {
        // x and y direction, where a knight can move
        int dx[] = {-2, -1, 1, 2, -2, -1, 1, 2};
        int dy[] = {-1, -2, -2, -1, 1, 2, 2, 1};

        // queue for storing states of knight in board
        Queue<cell> q = new LinkedList<>();

        // push starting position of knight with 0 distance
        q.add(new cell(knightPos[0], knightPos[1], 0));

        cell t;
        int x, y;
        boolean visit[][] = new boolean[N[0] + 1][N[1] + 1]; //default initialized to false

        // visit starting state
        visit[knightPos[0]][knightPos[1]] = true;
        int tDis = 0;

        // loop until we have one element in queue
        while (!q.isEmpty()) {
            t = q.poll();
            q.remove(0);

            // if current cell is equal to target cell,
            // return its distance
            if (t.x == targetPos[0] && t.y == targetPos[1]) {
                return t.dis;

            }


            // loop for all reachable states
            for (int i = 0; i < 8; i++) {
                x = t.x + dx[i];
                y = t.y + dy[i];

                // If reachable state is not yet visited and
                // inside board, push that state into queue
                if (isInside(x, y, N) && !visit[x][y]) {
                    visit[x][y] = true;
                    q.add(new cell(x, y, t.dis + 1));

                }
            }
        }
        return Integer.MAX_VALUE;
    }

}
