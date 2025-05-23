import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day01_1 {


    public static void main(String[] args) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get("input.txt"));
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        
        List<Integer> nums1 = new ArrayList<>();
        List<Integer> nums2 = new ArrayList<>();
        for (String line : lines) {
            System.err.println("line: " + line);
            // scan line and get two numbers
            String[] parts = line.split("\\s+");
            int num1 = Integer.parseInt(parts[0]);
            int num2 = Integer.parseInt(parts[1]);
            nums1.add(num1);
            nums2.add(num2);

        }
        Collections.sort(nums1);
        Collections.sort(nums2);

        int ans = 0;
        for (int i = 0; i < nums1.size(); i++) {
            ans += Math.abs(nums1.get(i) - nums2.get(i));
            
        }
        System.out.println(ans);
    }
}