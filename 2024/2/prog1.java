
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class prog1 {
    public static boolean check(List<Integer> nums) {
        int a = nums.get(0);
        int b = nums.get(1);
        int dir = b - a;

        int dir_abs = Math.abs(dir);
        if (dir_abs > 3 || dir_abs < 1) {
            return false;
        }
        
        for (int i = 2; i < nums.size(); i++) {
            int diff = nums.get(i) - b;
            if ((dir * diff) < 0) {
                return false;
            }
            diff = Math.abs(diff);
            if (diff < 1 || diff > 3) {
                return false;
            }
            b = nums.get(i);
        }
        return true;
    }

    public static void main(String[] args) {
        // read rows from file
        List<String> rows = new ArrayList<>();
        try {
            rows = Files.readAllLines(Paths.get("input.txt"));
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        // convert the row to a list of integers
        int ans = 0;
        for (String row : rows) {
            List<Integer> nums = Arrays.stream(row.split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
            if (check(nums)) {
                ans++;
            }
        }
        System.out.println(ans);
    }
}
