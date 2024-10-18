import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

    }

    public static int problem17(String s, int x) {
        s = s.trim();

        if (s.equals("x")) {
            return x;
        }

        int i = s.length() - 1;
        while (i >= 0) {
            if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                if (s.charAt(i) == '+') {
                    return problem17(s.substring(0, i), x) + problem17(s.substring(i + 1), x);
                } else {
                    return problem17(s.substring(0, i), x) - problem17(s.substring(i + 1), x);
                }
            }
            i--;
        }

        i = s.length() - 1;
        while (i >= 0) {
            if (s.charAt(i) == '*' || s.charAt(i) == '/') {
                if (s.charAt(i) == '*') {
                    return problem17(s.substring(0, i), x) * problem17(s.substring(i + 1), x);
                } else {
                    return problem17(s.substring(0, i), x) / problem17(s.substring(i + 1), x);
                }
            }
            i--;
        }
        return 0;
    }


}