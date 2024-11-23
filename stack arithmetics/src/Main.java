public class Main {
    public static void main(String[] args) {
        System.out.println(infoxtopostfix("(a+b/c*(d+e)-f)"));
    }

    public static char getLastChar(String s) {
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException("String is null or empty!");
        }
        return s.charAt(s.length() - 1); // Access the last character
    }

    public static String infoxtopostfix(String s){



        Stack<Character> stack = new Stack<>();

        String result = "";

        int i = 0;
        while ( i < s.length()){
            if ((stack.peek() == '-' || stack.peek() == '+')){
                
                infoxtopostfix(stack);
            }
            if ( s.charAt(i) == '/' || s.charAt(i) == '*' || s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '('|| s.charAt(i) == ')'){
                if (i==0){
                    stack.push(s.charAt(i));
                }
                else if ((stack.peek() == '*'  || stack.peek() == '/') && (s.charAt(i) == '+' || s.charAt(i) == '-')){
                    result = result + stack.pop();
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
                    stack.push(s.charAt(i));

                }

                else if ((stack.peek() == '/'  || stack.peek() == '*') && (s.charAt(i) == '/' || s.charAt(i) == '*')){
                    result = result + stack.pop();
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

        stack.printStack();

        return result;
    }
}