import java.io.IOException;
import java.nio.file.*;
import java.util.*;

// ....#.....
// ........>#
// ..........
// ..#.......
// .......#..
// ..........
// .#........
// ........#.
// #.........
// ......#...


public class Day61 {
    static int ans = 1;
    static boolean done = false;
    public static void dfs(char[][] grid, int i, int j, int dir) {
        if (grid[i][j] == '.') {
            ans++;
        }
        grid[i][j] = 'X';
        if (i == 0 || i == grid.length - 1 || j == 0 || j == grid[0].length - 1) {
            done = true;
            return;
        }


        // up, right, down, left
        int[][] dirs = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};


        int ni = i + dirs[dir][0];
        int nj = j + dirs[dir][1];
        
    
        if (grid[ni][nj] == '#') {
            dir = (dir + 1) % 4; // change direction
            ni = i + dirs[dir][0];
            nj = j + dirs[dir][1];
        }
        
        dfs(grid, ni, nj, dir);
    }
    
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("input.txt"));
        int rows = lines.size();
        int cols = lines.get(0).length();
        char[][] grid = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            grid[i] = lines.get(i).toCharArray();
        }


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (done) {
                    break;
                }
                switch (grid[i][j]) {
                    case '^' -> dfs(grid, i, j, 0);
                    case '>' -> dfs(grid, i, j, 1);
                    case 'v' -> dfs(grid, i, j, 2);
                    case '<' -> dfs(grid, i, j, 3);
                }
            }
        }
        System.out.println(ans);
        // for (char[] row : grid) {
        //     System.out.println(Arrays.toString(row));
        // }
    }
}
