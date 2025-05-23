import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.stream.Collectors;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

class Day41 {
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

    public static int findMatch(List<List<Character>> charMatrix, int r, int c, String match) {
        int R = charMatrix.size();
        int C = charMatrix.get(0).size();

        int ans = 0;
        // // add for eight directions
        // List<Integer> dirs = Arrays.asList(1, 0, -1, 0, 1, -1, -1, 1, 1);
        // for (int i = 0; i < dirs.size()-1; i++) {
        //     int nx = r + dirs.get(i);
        //     int ny = c + dirs.get(i + 1);
        //     if (nx >= 0 && nx < R && ny >= 0 && ny <  C) {
        //         char ch = charMatrix.get(nx).get(ny);
        //         if (ch != match.charAt(p)) {
        //             continue;
        //         }
        //         charMatrix.get(nx).set(ny, '.');
        //         ans += findMatch(charMatrix, nx, ny, match, p + 1);
        //         charMatrix.get(nx).set(ny, ch);
        //     }   
        // }
        // return ans;
        
        // check front
        boolean found = true;
        for (int i = 0; i < match.length(); i++) {
            if (c + i >= C || charMatrix.get(r).get(c + i) != match.charAt(i)) {
                found = false;
            }
        }
        ans += found ? 1 : 0;
        // check back
        found = true;
        for (int i = 0; i < match.length(); i++) {
            if (c - i < 0 || charMatrix.get(r).get(c - i) != match.charAt(i)) {
                found = false;
            }
        }
        ans += found ? 1 : 0;
        // check up 
        found = true;
        for (int i = 0; i < match.length(); i++) {
            if (r - i < 0 || charMatrix.get(r - i).get(c) != match.charAt(i)) {
                found = false;
            }
        }
        ans += found ? 1 : 0;
        // check down
        found = true;
        for (int i = 0; i < match.length(); i++) {
            if (r + i >= R || charMatrix.get(r + i).get(c) != match.charAt(i)) {
                found = false;
            }
        }
        ans += found ? 1 : 0;
        // check up-left
        found = true;
        for (int i = 0; i < match.length(); i++) {
            if (r - i < 0 || c - i < 0 || charMatrix.get(r - i).get(c - i) != match.charAt(i)) {
                found = false;
            }
        }
        ans += found ? 1 : 0;
        // check up-right
        found = true;
        for (int i = 0; i < match.length(); i++) {
            if (r - i < 0 || c + i >= C || charMatrix.get(r - i).get(c + i) != match.charAt(i)) {
                found = false;
            }
        }
        ans += found ? 1 : 0;
        // check down-left
        found = true;
        for (int i = 0; i < match.length(); i++) {
            if (r + i >= R || c - i < 0 || charMatrix.get(r + i).get(c - i) != match.charAt(i)) {
                found = false;
            }
        }
        ans += found ? 1 : 0;
        // check down-right
        found = true;
        for (int i = 0; i < match.length(); i++) {
            if (r + i >= R || c + i >= C || charMatrix.get(r + i).get(c + i) != match.charAt(i)) {
                found = false;
            }
        }
        ans += found ? 1 : 0;
        return ans;
    }
    public static void main(String[] args) {
        List<List<Character>> charMatrix = ReadChars();
        int ans = 0;
        for (int i = 0; i < charMatrix.size(); i++) {
            for (int j = 0; j < charMatrix.get(i).size(); j++) {
                if (charMatrix.get(i).get(j) == 'X') {
                    ans += findMatch(charMatrix, i, j, "XMAS");
                }
            }
        }

        System.out.println(ans);
    }
}
