public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");


    }
    static int res = 0;
    public static int divide (int a , int b){
        if (a-b >= 0){
            res++;
            divide(a-b,b);
        } else if (a==0) {
            return res;
        }
        return res;
    }
}