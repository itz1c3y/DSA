public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
    public static int findMax(int[] arr, int n) {
        if (n == 1) {
            return arr[0];
        }

        int maxOfRest = findMax(arr, n - 1);

        if (arr[n - 1] > maxOfRest) {
            return arr[n - 1];
        } else {
            return maxOfRest;
        }
    }
}