import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

class Calculator {
    static final HashMap<String, List<Integer>> precedence = new HashMap<>();


    Solution() {
        //precedence and associativity [0 - left, 1 - right]
        precedence.put("+", new ArrayList<>(Arrays.asList(1, 0)));
        precedence.put("-", new ArrayList<>(Arrays.asList(1, 0)));
        precedence.put("*", new ArrayList<>(Arrays.asList(2, 0)));
        precedence.put("/", new ArrayList<>(Arrays.asList(2, 0)));
        precedence.put("%", new ArrayList<>(Arrays.asList(2, 0)));
        precedence.put("^", new ArrayList<>(Arrays.asList(3, 1)));
        precedence.put("(", new ArrayList<>(Arrays.asList(6, 0)));
        precedence.put(")", new ArrayList<>(Arrays.asList(6, 0))); // high precedence
        precedence.put("UMINUS", new ArrayList<>(Arrays.asList(5,  0))); // higher than binary and power
        precedence.put("SENTINAL", new ArrayList<>(Arrays.asList(-1, 0))); // sentinal
    }


    // Shunting yard: infix to postfix
    public List<String> infixToPostfix(List<String> tokens) {

        List<String> output = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        for (String token : tokens) {
            if (precedence.containsKey(token)) {
                // case 1: ")"
                if (token.equals(")")) { // pop until we see (
                    while (!stack.empty() && !stack.peek().equals("("))
                        output.add(stack.pop());
                    stack.pop();
                    continue;
                }
                // case 2: operator
                // pop all operators with higher or equal precedence until we see "(" which should be matched with ")"

                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    List<Integer> p_stk = precedence.get(stack.peek());
                    List<Integer> p_token = precedence.get(token);
                    // greater or equal precedence and left associativity
                    if (p_stk.get(0) > p_token.get(0) || (p_stk.get(0).equals(p_token.get(0)) && p_token.get(1).equals(0))) {
                        output.add(stack.pop());
                    } else {
                        break;
                    }
                }
                stack.push(token);
            } else {
                // case 3: operand
                output.add(token);
            }
            System.out.println("output: " + output);
        }

        while (!stack.isEmpty()) {
            output.add(stack.pop());
        }
        return output;
    }

    

    // Evaluate postfix
    private int evalPostfix(List<String> postfix) {
        Stack<Integer> stack = new Stack<>();
        for (String token : postfix) {
            if (token.matches("-?\\d+")) {
                stack.add(Integer.parseInt(token));
            } else if (precedence.containsKey(token)) {
                int b = stack.pop();
                switch (token) {
                    case "+": {
                        int a = stack.pop();
                        stack.add(a + b);
                    } break;
                    case "-": {
                        int a = stack.pop();
                        stack.add(a - b);
                    } break;
                   case "*": {
                        int a = stack.pop();
                        stack.add(a * b);
                   } break;
                   case "UMINUS": stack.add(-b); break;

                    case "/": {
                        int a = stack.pop();
                        stack.add(b == 0 ? 0 : a / b);
                    } break;
                    case "%": {
                        int a = stack.pop();
                        stack.add(b == 0 ? 0 : a % b);
                    } break;
                    case "^": {
                        int a = stack.pop();
                        stack.add((int)Math.pow(a, b));
                    } break;
                }
            }
        }
        return stack.peek();
    }

    public String calculate(String s) {
        List<String> tokens = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);        
            if (ch == ' ') {
                continue;
            }
            
            System.err.println("ch: " + ch);
            //final String operators = "+()-";
            String op = String.valueOf(ch);
            if (precedence.containsKey (op)) {
                if (op.equals("-") && (tokens.isEmpty() || (precedence.containsKey(tokens.get(tokens.size() - 1)) && !tokens.get(tokens.size() - 1).equals(")")))) {
                    tokens.add("UMINUS");
                }  else {   
                    tokens.add(op);
                }
                continue;
            }

            ch = s.charAt(i);
            if  (ch >= '0' && ch <= '9') {
                String num = "";
                while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                    num += s.charAt(i);
                    i++;
                }
                i--;
                tokens.add(num);
            }
        }
        System.out.println(tokens);
        List<String> postfix = infixToPostfix(tokens);
        System.out.println("postfix: " + postfix);
        return String.valueOf(evalPostfix(postfix));
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "-3 + 4 * 2 / (1 - 5) ^ 2 ^ 3 + (8 + (3 * (4 + 5))) / 2 * -7 * (6 - 3) + 10 ^ (2 + -1)";
        System.out.println(solution.calculate(s));
    }
}
