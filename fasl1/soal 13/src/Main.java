public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
    public static void solveHanoi(int n, char fromRod, char toRod, char auxRod) {
        if (n == 1) {
            System.out.println("Move disk 1 from rod " + fromRod + " to rod " + toRod);
            return;
        }

        solveHanoi(n - 1, fromRod, auxRod, toRod);

        System.out.println("Move disk " + n + " from rod " + fromRod + " to rod " + toRod);

        solveHanoi(n - 1, auxRod, toRod, fromRod);
    }
}