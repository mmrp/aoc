import java.io.IOException;
import java.nio.file.*;
import java.util.*;



public class Day51 {

  
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("input.txt"));
        List<List<Integer>> orders = new ArrayList<>();

        boolean secondSection = false;
        HashMap<Integer, HashSet<Integer>> hset = new HashMap<>();

        for (String line : lines) {
            if (line.trim().isEmpty()) {
                secondSection = true;
                continue;
            }
            if (!secondSection) {
                String[] parts = line.split("\\|");
                Integer n1 = Integer.parseInt(parts[0]);
                Integer n2 = Integer.parseInt(parts[1]);
                hset.putIfAbsent(n1, new HashSet<>());
                hset.get(n1).add(n2);
            } else {
                List<Integer> order = new ArrayList<>();
                for (String s : line.split(",")) {
                    order.add(Integer.parseInt(s.trim()));
                }
                orders.add(order);
            }


        }

        int valid_orders  = 0;
        int sum = 0;
        int invalid_sum = 0;
        for (List<Integer> order : orders) {

            // get the set contains and check whether b is in the set
            List<Integer> sorted_order = new ArrayList<>(order);
            Collections.sort(sorted_order, (a, b) -> {
                if(hset.containsKey(a)) {
                    return hset.get(a).contains(b) ? -1 : 1;
                }
                return 0;
            });
            System.out.println(sorted_order);
            System.out.println(order);
            valid_orders += sorted_order.equals(order) ? 1 : 0;
            if (sorted_order.equals(order)) {
                sum += order.get(order.size() / 2);
            } else {
                invalid_sum += sorted_order.get(sorted_order.size() / 2);
            }
        }
        System.out.println(valid_orders);
        System.out.println(sum);
        System.out.println(invalid_sum);
    }
}
