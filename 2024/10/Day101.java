import java.nio.file.Files;
import java.nio.file.Path;

public class Day101 {
    static int ans = 0;
    public static void count(int [][] grid, int i, int j, int value) {
        grid[i][j] = '.';
        if (value ==   10) {
            ans++;
            return;
        }

        int [][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int [] direction : directions) {
            int x = i + direction[0];
            int y = j + direction[1];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] != '.' && (grid[x][y] == value)) {
                count(grid, x, y, value + 1);
            }
        }
    }

    public static void main(String[] args) {

        String[] lines = null;
        try {
            String input = Files.readString(Path.of("input.txt"));
            lines = input.split("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
   
        int [][] grid = new int[lines.length][lines[0].length()];
        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[i].length(); j++) {
                if (lines[i].charAt(j) != '.') {
                    grid[i][j] = lines[i].charAt(j) - '0';
                } else {
                    grid[i][j] = '.';
                }
            } 
        }

        int k = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    int [][] grid1 = new int[grid.length][grid[0].length];
                    for (int x = 0; x < grid.length; x++) {
                        for (int y = 0; y < grid[0].length; y++) {
                            grid1[x][y] = grid[x][y];
                        }
                    }
                    count(grid1, i, j, 1);
                }
            }
        }
        System.out.println(ans);
    }
}