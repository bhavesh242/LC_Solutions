package com.bhavesh.solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***
 * Solution :
 * Perform DFS, get a list of coordinates of all the cells for the individual islands,
 * Normalize the cells : The normalization process takes the shape of an island obtained from the dfs and
 * generates all possible rotations and reflections.
 * This is done by permuting the cell coordinates (i, j) as described in the code (e.g., shapes[0].append([i, j])
 * for the original shape, shapes[1].append([i, -j]) for a reflection, etc.).
 * The 8 different transformations correspond to 4 rotations (0, 90, 180, 270 degrees) and their mirrored reflections.
 * Canonical Representation: After generating all transformations of an island's shape, each transformation is sorted
 * to place the cells in a consistent order. Then, the coordinates are adjusted by subtracting the coordinates of the
 * first cell from all cells to ensure that the 'top-left' cell of the shape is brought to (0,0).
 * Among all permutations, the lexicographically smallest is selected as the canonical shape.
 */

/***
 * How to calculate the rotation (90, 180, or 270 degrees only) or reflection (left/right direction or up/down direction).
 * Total of 8 transformations :
 * Original :  (x,y)
 * Y Axis Mirror : (-x, y)
 * X Axis Mirror : (x, -y)
 * Origin Mirror : (-x, -y)
 * Diagonal Mirror : (y,x)
 * Y-Axis mirror of  Diagonal Mirror : (-y, x)
 * X-Axis Mirror of Diagonal Mirror : (y, -x)
 * Origin Mirror of Diagonal Mirror : (-y, -x)
 */
public class Leetcode711 {

    int directions[][] = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    int transformations[][] = {{1,1}, {-1, 1}, {1, -1}, {-1, -1}};
    public int numDistinctIslands2(int[][] grid) {
        HashSet<String> normalizedIslands = new HashSet<>();
        for(int x=0; x<grid.length; x++) {
            for(int y=0; y<grid[0].length; y++) {
                if(grid[x][y] == 1) {
                    List<int[]> cells = new ArrayList<int[]>();
                    dfs(grid, x, y, cells);
                    normalizedIslands.add(normalize(cells));
                }
            }
        }
        return normalizedIslands.size();
    }

    private void dfs (int [][]grid, int x, int y, List<int[]> cells) {
        cells.add(new int[]{x, y});
        grid[x][y] = 0;
        for(int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if(newX >= 0 && newX < grid.length &&
                    newY >= 0 && newY < grid[0].length
                    && grid[newX][newY] == 1) {
                dfs(grid, newX, newY, cells);
            }
        }
    }

    private String normalize(List<int[]> cells) {
        List<String> allForms = new ArrayList<>();
        for(int [] tf : transformations) {
            List<int[]> rotations = new ArrayList<>();
            List<int[]> reflectionRotations = new ArrayList<>();
            for(int []cell: cells) {
                rotations.add(new int[]{cell[0]*tf[0], cell[1]*tf[1]});
                reflectionRotations.add(new int[]{cell[1]*tf[1], cell[0]*tf[0]});
            }
            allForms.add(getKey(rotations));
            allForms.add(getKey(reflectionRotations));
        }

        Collections.sort(allForms);
        return allForms.get(0);
    }

    private String getKey(List<int[]> cells) {
        Collections.sort(cells, new Comparator<int[]>() {
            @Override
            public int compare(int a[], int b[]) {
                if(a[0] != b[0]) {
                    return a[0] - b[0];
                }
                return a[1] - b[1];
            }
        });

        StringBuilder sb = new StringBuilder();
        int x = cells.get(0)[0];
        int y = cells.get(0)[1];
        for(int cell[] : cells) {
            sb.append((cell[0] - x) + ":" + (cell[1] - y) + ":");
        }

        return sb.toString();
    }

}