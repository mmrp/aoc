import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
public class Day71 {
    // Helper to get precedence

    // private static int precedence(char op) {
    //     HashMap<Character, Integer> precedence = new HashMap<>();
    //     precedence.put('+', 1);
    //     precedence.put('-', 1);
    //     precedence.put('*', 2);
    //     precedence.put('/', 2);
    //     precedence.put('%', 2);
    //     precedence.put('^', 3);
    //     precedence.put('(', 4);
    //     precedence.put(')', 4);
    //     precedence.put('$', -1); // sentinal
    //     return precedence.getOrDefault(op, 0);
    // }

    // // Shunting yard: infix to postfix
    // public static List<String> infixToPostfix(List<String> tokens) {
    //     List<String> output = new ArrayList<>();
    //     Stack<String> stack = new Stack<>();
    //     for (String token : tokens) {
    //         if ("+-*/%()^$".contains(token)) {
    //             char op = token.charAt(0);
    //             if (op == ')') {
    //                 while (!stack.empty() && stack.peek().charAt(0) != '(')
    //                     output.add(stack.pop());
    //                 stack.pop();
    //             } else {
    //                 while (!stack.isEmpty() && stack.peek().charAt(0) != '(' && precedence(stack.peek().charAt(0)) >= precedence(op))
    //                     output.add(stack.pop());
    //                 stack.push(token);
    //             }
    //         } else {
    //             output.add(token);
    //         }
    //         //System.out.println(output);
    //     }
    //     while (!stack.isEmpty()) {
    //         output.add(stack.pop().toString());
    //     }
    //     return output;
    // }

    

    // // Evaluate postfix
    // private static int evalPostfix(List<String> postfix) {
    //     List<Integer> stack = new ArrayList<>();
    //     for (String token : postfix) {
    //         if (token.matches("-?\\d+")) {
    //             stack.add(Integer.parseInt(token));
    //         } else if (token.length() == 1 && "+-*/%".contains(token)) {
    //             int b = stack.remove(stack.size()-1);
    //             int a = stack.remove(stack.size()-1);
    //             switch (token.charAt(0)) {
    //                 case '+': stack.add(a + b); break;
    //                 case '-': stack.add(a - b); break;
    //                 case '*': stack.add(a * b); break;
    //                 case '/': stack.add(b == 0 ? 0 : a / b); break;
    //                 case '%': stack.add(b == 0 ? 0 : a % b); break;
    //             }
    //         }
    //     }
    //     return stack.get(0);
    // }


    public static HashMap<Integer, List<String>> tops_mp = new HashMap<>();
    public static List<String> tops = new ArrayList<>();
    public static void GenerateOpers(int n, String s) {
        if (n == 0) {
            tops.add(s);
            return;
        }
        GenerateOpers(n-1, s + "*");
        GenerateOpers(n-1, s + "+");
        GenerateOpers(n-1, s + "|");

    }

    public static boolean check(List<Long> d) {
       final int N = d.size()-2;
        if (!tops_mp.containsKey(N)) {
            tops.clear();
            GenerateOpers(N, "");
            System.out.println("Generated" + String.valueOf(N) + ":" + String.valueOf(tops.get(1)));
            tops_mp.put(N, new ArrayList<>(tops));
        }

        System.out.printf("N: %d, size: %d\n", N, tops_mp.get(N).size());
        
        for (String op: tops_mp.get(N)) {
            List<String> tokens = new ArrayList<>();

            Long a = d.get(1);
            for (int i = 0; i < op.length(); i++) {
                long b = d.get(2+i);
                switch (op.charAt(i)) {
                    case '+': a = a + b; break;
                    case '*': a = a * b; break;
                    case '|': a = Long.parseLong(String.valueOf(a) + String.valueOf(b)); break;

                }
            }
            if (a.equals(d.get(0))) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        List<List<Long>> data = new ArrayList<>();
        try (Scanner scanner = new Scanner(new java.io.File("input.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) break;
                String[] parts = line.split(":");
                if (parts.length != 2) continue;
                List<Long> entry = new ArrayList<>();
                entry.add(Long.parseLong(parts[0].trim()));
                for (String numStr : parts[1].trim().split(" ")) {
                    if (!numStr.isEmpty()) {
                        entry.add(Long.parseLong(numStr));
                    }
                }
                data.add(entry);
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }
        long sum = 0;
        for (List<Long> d: data) {
            if (check(d)) {
                sum += d.get(0);
                System.out.println(d);
            }
        }
        System.out.println(sum);
    }

}



