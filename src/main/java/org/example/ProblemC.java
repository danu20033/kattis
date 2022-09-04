package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ProblemC {

    // x and y direction, where a knight can move
    int dx[] = {-2, -1, 1, 2, -2, -1, 1, 2};
    int dy[] = {-1, -2, -2, -1, 1, 2, 2, 1};


    //Hold surrounding squares of start point
    Set<Square> nearStartingSquares = new HashSet<>();
    //Hold surrounding squares of target point.
    Set<Square> nearCapitalSquares = new HashSet<>();

    Kingdom kingdom;


    /**
     * Validate square is within the kingdom or not
     *
     * @param x
     * @param y
     * @return true if position is outside.
     */
    boolean isOutSideKingdom(int x, int y) {
        return (x < 0 || y < 0 || x >= kingdom.sizeX || y >= kingdom.sizeY);
    }

    static class Kingdom {
        int sizeX;
        int sizeY;

        public Kingdom(int sizeX, int sizeY) {
            this.sizeX = sizeX;
            this.sizeY = sizeY;
        }
    }


    class Square {

        int x;
        int y;
        int distance;

        public Square(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Square)) return false;
            Square square = (Square) o;
            return x == square.x && y == square.y && distance == square.distance;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, distance);
        }
    }

    /**
     * Find relative distance based on the knight possible moves
     * <p>
     * possible square max(x/2,y/2,(x+y)/2)
     *
     * @param diffX
     * @param diffY
     * @return
     */
    int findRelativeDistance(int diffX, int diffY) {

        diffX = Math.abs(diffX);
        diffY = Math.abs(diffY);
        //set max value as y;
        if (diffX > diffY) {
            int temp = diffX;
            diffX = diffY;
            diffY = temp;
        }

        if (diffX <= diffY / 2) {
            //if this condition succeed , the possible move = y/2
            if (diffY % 2 == 1)
                return -1;
            if ((diffX + diffY / 2) % 2 == 1)
                return -1;
            return diffY / 2;
        } else {

            //this is for the middle cases
            if ((diffX + diffY) % 3 != 0)
                return -1;
            return (diffX + diffY) / 3;
        }
    }


    /**
     * Create surrounding squares for the given positions
     * This will create squares recursively until it reaches the distance conditions
     * @param x
     * @param y
     * @param distance
     * @param squareSet
     */
    public void createSquare(int x, int y, int distance, Set<Square> squareSet) {

        if (isOutSideKingdom(x, y)) {
            return;

        }
        squareSet.add(new Square(x, y, distance));

        //Calculate for the 2 distances,
        if (distance >= 2) {
            return;
        }

        //create item recursively.

        for (int i = 0; i < dy.length; i++) {
            createSquare(x + dx[i], y + dy[i], distance + 1, squareSet);
        }


    }


    public int findMinimumDistance(Kingdom kingdom, int startX, int startY, int endX, int endY) {
        this.kingdom = kingdom; // set kingdom

        //create near starting squares
        createSquare(startX, startY, 0, nearStartingSquares);
        //create near capital squares.
        createSquare(endX, endY, 0, nearCapitalSquares);

        int totalDistance = 1000000000;

        for (Square start : nearStartingSquares) {
            for (Square end : nearCapitalSquares) {
                    //find valid relative distance for each nearby squares.
                int d = findRelativeDistance(start.x - end.x, start.y - end.y);

                if (d < 0) {
                    continue;
                }
                // add relative distance with each square distance to get total distance
                d += start.distance + end.distance;

                // find shortest path distance
                if (d < totalDistance) {
                    totalDistance = d;
                }


            }

        }

        return totalDistance;

    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputValues;

        int kingdomX, kingdomY, startX, startY, endX, endY;
        inputValues = reader.readLine().split("\\s");
        kingdomX = Integer.parseInt(inputValues[0]);
        kingdomY = Integer.parseInt(inputValues[1]);
        inputValues = reader.readLine().split("\\s");
        startX = Integer.parseInt(inputValues[0]);
        startY = Integer.parseInt(inputValues[1]);
        inputValues = reader.readLine().split("\\s");
        endX = Integer.parseInt(inputValues[0]);
        endY = Integer.parseInt(inputValues[1]);

        Kingdom kingdom = new Kingdom(kingdomX, kingdomY);
        ProblemC problemC = new ProblemC();
        System.out.println(problemC.findMinimumDistance(kingdom, startX, startY, endX, endY));
        reader.close();


    }
}
