public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
    public static double print (int n , int i){
        if (n==1){
            return 1;
        }
        else {
            return 1 + (double) 1/(i+1) * print(n,i+1);
        }
    }
}