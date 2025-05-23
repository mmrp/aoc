import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day01_2 {


    public static void main(String[] args) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get("input.txt"));
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> nums1 = new ArrayList<>();
        for (String line : lines) {
            System.err.println("line: " + line);
            // scan line and get two numbers
            String[] parts = line.split("\\s+");
            int num1 = Integer.parseInt(parts[0]);
            int num2 = Integer.parseInt(parts[1]);
            nums1.add(num1);
            map.put(num2, map.getOrDefault(num2, 0) + 1);
            // List<Integer> nums = Arrays.stream(line.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            // nums1.add(nums.get(0));
            // map.put(nums.get(1), map.getOrDefault(nums.get(1), 0) + 1);
        }
        int ans = 0;
        for (int num : nums1) {
            ans += num * map.getOrDefault(num, 0);
            
        }
        System.out.println(ans);
    }
}