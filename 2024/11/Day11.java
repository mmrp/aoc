import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;


public class Day11 {
    public static String cleanup(String s) {
        // remove leading zeros
        while( s.startsWith("0") ) {
            s = s.substring(1);
        }
        return s.equals("") ? "0" : s;
    }

    static HashMap<String, Long> cache = new HashMap<>();

    public static long count(int loops, long n) {
        if (loops == 0) {
            return 1;
        }

        if (cache.containsKey(loops + "#" + n)) {
            return cache.get(loops + "#" + n);
        }

        if (n == 0) {
            return count(loops-1, 1);
        }

        long val = 0;
        String s = String.valueOf(n);
        if (s.length() % 2 == 0) {
            int sz = s.length() / 2;
            String s1 = cleanup(s.substring(0, sz));
            String s2 = cleanup(s.substring(sz));
            val =  count(loops-1, Integer.parseInt(s1)) + count(loops-1, Integer.parseInt(s2));
        } else {
            val = count(loops-1, n * 2024);
        }
        
        cache.put(loops + "#" + n, val);
        return val;
    }

        
    public static void main(String[] args) {
        String input = null;
        try {
            input = Files.readString(Path.of("input.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> parts = Arrays.asList(input.split("\n")[0].split(" "));       // get parts of the line


        int loops = 75;
        long ans = 0;
        
        List<String> new_parts = new ArrayList<>();
        for (int i = 0; i < parts.size(); i++) {
            ans += count(loops, Integer.parseInt(parts.get(i)));
        }
        System.out.println(ans);
    }
}
