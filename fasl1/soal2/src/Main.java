public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
    public static double findAverage(int[] arr, int n) {
        // Base case: if there's only one element, return it
        if (n == 1) {
            return arr[0];
        }
        return ((findAverage(arr, n - 1) * (n - 1)) + arr[n - 1]) / n;
    }
}