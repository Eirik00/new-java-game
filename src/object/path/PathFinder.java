package object.path;

import object.tile.Tile;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class PathFinder {
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static ArrayList<GridPoint> findPath(ArrayList<ArrayList<Tile>> grid, int startX, int startY, int endX, int endY) {

        int gridSize = 64;
        boolean[][] visited = new boolean[gridSize][gridSize];
        Queue<GridPoint> queue = new LinkedList<>();
        queue.add(new GridPoint(startX, startY, null));
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            GridPoint current = queue.poll();

            if (current.x == endX && current.y == endY) {
                // We found the destination, build the path
                ArrayList<GridPoint> path = new ArrayList<>();
                while (current != null) {
                    path.add(0, current);
                    current = current.parent;
                }
                return path;
            }

            for (int[] direction : DIRECTIONS) {
                int newX = current.x + direction[0];
                int newY = current.y + direction[1];

                if (isValid(gridSize, newX, newY) && !visited[newX][newY]) {
                    queue.add(new GridPoint(newX, newY, current));
                    visited[newX][newY] = true;
                }
            }
        }

        return null; // No path found
    }

    private static boolean isValid(int gridSize, int x, int y) {
        return x >= 0 && x < gridSize && y >= 0 && y < gridSize;
    }
}