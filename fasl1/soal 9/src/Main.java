public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public static int print (int i , int n){
        if (n ==1){
            return 1;
        }
        return 1+(i+1)*print(i+1,n);
    }
}