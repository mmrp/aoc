import java.io.IOException;
import java.nio.file.*;
import java.util.*;



public class Day51 {

    public static int process(Integer node, HashMap<Integer, TreeSet<Integer>> graph, HashMap<Integer, HashSet<Integer>> neighbors) {
 
        int next_node = -1;
        // get all neighbors of the node
        HashSet<Integer> nbrs = neighbors.get(node);
        for (Integer nbr : nbrs) {
            // iterate over the graph and remove the node from the neighbors
            graph.get(nbr).remove(node);
            if (next_node == -1 && graph.get(nbr).isEmpty()) {
                next_node = nbr;
            }
        }
        // remove the node from the graph
        graph.remove(node);
        return next_node;
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("input.txt"));
        List<List<Integer>> orders = new ArrayList<>();

        HashMap<Integer, TreeSet<Integer>> graph = new HashMap<>();
        HashMap<Integer, HashSet<Integer>> neighbors = new HashMap<>();

        boolean secondSection = false;
        for (String line : lines) {
            if (line.trim().isEmpty()) {
                secondSection = true;
                continue;
            }
            if (!secondSection) {
                String[] parts = line.split("\\|");
                Integer n1 = Integer.parseInt(parts[0]);
                Integer n2 = Integer.parseInt(parts[1]);
                graph.putIfAbsent(n1, new TreeSet<>());
                graph.putIfAbsent(n2, new TreeSet<>());
                graph.get(n2).add(n1);
                neighbors.putIfAbsent(n1, new HashSet<>());
                neighbors.putIfAbsent(n2, new HashSet<>());
                neighbors.get(n1).add(n2);
            } else {
                List<Integer> order = new ArrayList<>();
                for (String s : line.split(",")) {
                    order.add(Integer.parseInt(s.trim()));
                }
                orders.add(order);
            }


        }

        HashMap<Integer, Integer> node2index = new HashMap<>();
        int idx = 0;
        int next_node = -1;

        
        for (Integer key : graph.keySet()) {
            if (graph.get(key).isEmpty()) {
                next_node = key;
                break;
            }
        }

        System.out.println(next_node);
        

        for (Integer key : graph.keySet()) {
            System.out.println(key + " -> " + graph.get(key));
        }

        System.out.println(next_node);
        while (next_node != -1) {
            node2index.put(next_node, idx++);
            next_node = process(next_node, graph, neighbors);
        }

        for (Integer key : node2index.keySet()) {
            System.out.println(key + " -> " + node2index.get(key));
        }

        
        int valid_orders  = 0;
        for (List<Integer> order : orders) {
            int prev = -1;
            boolean valid = true;
            for (Integer node : order) {
                if (node2index.get(node) < prev) {
                    System.out.println("Invalid order");    
                    valid = false;
                    break;
                }
                prev = node2index.get(node);
            }
            if (valid) {
                valid_orders++;
            }
        }
        System.out.println(valid_orders);
    }
}
