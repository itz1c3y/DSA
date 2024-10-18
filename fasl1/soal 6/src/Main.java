public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
    public static int multiply(int a, int b) {
        if (b == 0) {
            return 0;
        }

        if (b < 0) {
            return -multiply(a, -b);
        }

        return a + multiply(a, b - 1);
    }
}