import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Day42 {
    public static List<List<Character>> ReadChars() {
        List<List<Character>> charMatrix = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<Character> charList = line.chars() // Convert String to IntStream (stream of chars)
                                       .mapToObj(c -> (char) c) // Map each int (char) to Character
                                       .collect(Collectors.toList()); // Collect into a List

                charMatrix.add(charList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Example: print the matrix
        for (List<Character> row : charMatrix) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
        return charMatrix;
    }


    public static int match(List<String> grid, List<List<String>> grids) {
        int ans = 0;
        for (List<String> g : grids) {
            boolean match = true;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    char gChar = g.get(i).charAt(j);
                    if (gChar == '.') {
                        continue;
                    }
                    if (gChar != grid.get(i).charAt(j)) {
                        match = false;
                        break;
                    }
                }
            }
            if (match) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        List<List<Character>> charMatrix = ReadChars();
        // create list of strings with below grids
        List<List<String>> grids = new ArrayList<>();
        grids.add(Arrays.asList("M.S", ".A.", "M.S"));
        grids.add(Arrays.asList("M.M", ".A.", "S.S"));
        grids.add(Arrays.asList("S.M", ".A.", "S.M"));
        grids.add(Arrays.asList("S.S", ".A.", "M.M"));
        int ans = 0;
        for (int i = 0; i < charMatrix.size()-2; i++) {
            for (int j = 0; j < charMatrix.get(i).size()-2; j++) {
                // create grid of 3 x 3 and see if it matches with given 3x3 grids
                List<String> grid = new ArrayList<>();
                for (int k = 0; k < 3; k++) {
                    String row = "";
                    for (int l = 0; l < 3; l++) {
                        row += charMatrix.get(i + k).get(j + l);
                    }
                    grid.add(row);
                }

                // . means ignore character
                ans += match(grid, grids);
            }
        }

        System.out.println(ans);
    }
}
