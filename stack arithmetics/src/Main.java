import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Stack<Character> stack = new Stack<>();
        System.out.println(infixToPostfix2(stack,"a+b*c/d-e"));
       // System.out.println(evaluatePostfix(infixToPostfix2(stack,"5+1*-2")));
//        System.out.println(infixToPostfix(stack,"(a+b/c*(d+e)-f)"));

        // Example postfix expression (corresponding to 2*x + 1)
//        String infix = "x^2";// Example for 2*x + 1
//
//        // Example postfix expression (corresponding to x^2 + y^2)
//        String postfix = "x2^y2^+";  // Example for x^2 + y^2 (a 3D paraboloid)
//
//        // Create and display the graph
//        JFrame frame = new JFrame("Postfix 3D Graph Plotter");
//        PostfixGraph3DPlotter panel = new PostfixGraph3DPlotter(postfix);
//        frame.add(panel);
//        frame.setSize(500, 500);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);

//        String postfix = "x2^";  // Example for x^2
//
//        // Create and display the graph
//        JFrame frame = new JFrame("Postfix Graph Plotter");
//        PostfixGraphPlotter panel = new PostfixGraphPlotter(postfix);
//        frame.add(panel);
//        frame.setSize(500, 500);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);

    }




    public static int evaluatePostfix(String postfix) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < postfix.length(); i++) {
            char current = postfix.charAt(i);

            // If the character is an operand (digit), push it onto the stack
            if (Character.isDigit(current)) {
                stack.push(current - '0'); // Convert char to int
            }
            // If the character is an operator, perform the operation
            else if (current == '+' || current == '-' || current == '*' || current == '/' || current == '^') {
                // Pop two operands
                int b = stack.pop();
                int a = stack.pop();

                // Perform the operation
                switch (current) {
                    case '+': stack.push(a + b); break;
                    case '-': stack.push(a - b); break;
                    case '*': stack.push(a * b); break;
                    case '/': stack.push(a / b); break;
                    case '^': stack.push((int)Math.pow(a, b)); break;
                }
            }
        }

        // The result will be the only element left in the stack
        return stack.pop();
    }

    public static String infixToPrefix(String s) {
        String reversed = new StringBuilder(s).reverse().toString();

        // Step 2: Swap '(' with ')' and vice versa
        StringBuilder modified = new StringBuilder();
        for (char ch : reversed.toCharArray()) {
            if (ch == '(') {
                modified.append(')');
            } else if (ch == ')') {
                modified.append('(');
            } else {
                modified.append(ch);
            }
        }

        // Step 3: Convert the modified infix expression to postfix
        String postfix = infixToPostfix(new Stack<>(), modified.toString());

        // Step 4: Reverse the postfix result to get the prefix expression
        return new StringBuilder(postfix).reverse().toString();

    }

    public static String infixToPostfix2(Stack<Character> stack, String s) {
        String result = "";
        int i = 0;
        boolean lastWasOperator = true; // Flag to track if the last character was an operator or '('

        while (i < s.length()) {
            char current = s.charAt(i);

            // Check for operators and parentheses
            if (current == '/' || current == '*' || current == '+' || current == '-' || current == '(' || current == ')' || current == '^') {
                if (stack.isEmpty() || current == '(') {
                    stack.push(current);
                    lastWasOperator = current != ')'; // Update the flag
                }
                else if (current == ')') {
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        result += stack.pop();
                    }
                    stack.pop(); // Remove '(' from the stack
                    lastWasOperator = false; // Reset the flag
                }
                else {
                    // Handle unary '-' for negative numbers
                    if (current == '-' && lastWasOperator) {
                        result += "0"; // Add a "0" to handle negative numbers
                    }
                    // Pop elements from the stack based on precedence
                    while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(current)) {
                        if (stack.peek() == '(') break;
                        result += stack.pop();
                    }
                    stack.push(current);
                    lastWasOperator = true; // Update the flag
                }
            }
            else {
                // Append operands (numbers, variables) directly to the result
                result += current;
                lastWasOperator = false; // Reset the flag
            }

            i++;
        }

        // Pop all the remaining operators from the stack
        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    // Helper function to define operator precedence
    private static int precedence(char operator) {
        switch (operator) {
            case '^': return 3;
            case '*':
            case '/': return 2;
            case '+':
            case '-': return 1;
            default: return 0;
        }
    }


    public static String infixToPostfix(Stack<Character> stack, String s) {
        String result = "";

        int i = 0;
        while (i < s.length()) {
            char current = s.charAt(i);
            if (current == '/' || current == '*' || current == '+' || current == '-' || current == '(' || current == ')' || current == '^') {
                if (stack.isEmpty() || current == '(') {
                    stack.push(current);
                }
                else if (current == ')') {
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        result += stack.pop();
                    }
                    stack.pop();
                }
                else {
                    while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(current)) {
                        if (stack.peek() == '(') break;
                        result += stack.pop();
                    }
                    stack.push(current);
                }
            }
            else {
                result += current;
            }

            i++;
        }

        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    public static String infoxtopostfix(Stack<Character> stack,String s){



        String result = "";

        int i = 0;
        while ( i < s.length()){

            if ( s.charAt(i) == '/' || s.charAt(i) == '*' || s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '('|| s.charAt(i) == ')' || s.charAt(i) == '^'){
                if (i==0 || stack.isEmpty()){
                    stack.push(s.charAt(i));
                }
                else if ((stack.peek() == '*'  || stack.peek() == '/') && (s.charAt(i) == '+' || s.charAt(i) == '-')){
                    result = result + stack.pop();
                    stack.push(s.charAt(i));

                }

                else if ((stack.peek() == '*'  || stack.peek() == '/' || stack.peek() == '+'  || stack.peek() == '-') && (s.charAt(i) == '^')){
                    stack.push(s.charAt(i));
                }
                else if (stack.peek() == '^' && s.charAt(i) == '^') {
                    result = result + stack.pop();
                   // infoxtopostfix(stack, s.substring(i));
                    stack.push(s.charAt(i));

                }
                else if (s.charAt(i) == ')'){
                    String mid = "";
                    while (stack.peek() != '(') {
                        mid = mid + stack.pop();
                    }
                    result = result + mid;
                    stack.pop();
                }
                else if ((stack.peek() == '+'  || stack.peek() == '-') && (s.charAt(i) == '+' || s.charAt(i) == '-') ){
                    result = result + stack.pop();
//                    infoxtopostfix(stack, s.substring(i));
                    stack.push(s.charAt(i));

                }
                else if ((stack.peek() == '+'  || stack.peek() == '-') && (s.charAt(i) == '*' || s.charAt(i) == '/') ){
                    result = result + stack.pop();
//                    infoxtopostfix(stack, s.substring(i));
                    stack.push(s.charAt(i));


                }

                else if ((stack.peek() == '/'  || stack.peek() == '*') && (s.charAt(i) == '/' || s.charAt(i) == '*')){
                    result = result + stack.pop();
//                    infoxtopostfix(stack, s.substring(i));
                    stack.push(s.charAt(i));


                }
                else {
                    stack.push(s.charAt(i));
                }
            }
            else {
                result = result + s.charAt(i);
            }

            i++;
        }

        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }
}


