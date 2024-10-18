public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        System.out.println(gcd(6,3));
    }

    public static int gcd(int a , int b){
        int x = a;
        int y = b;
        int r = x%y;
        if (r==0){
            return b;
        }
        else {
            x = y;
            y = r;
        }
        return gcd(x,y);


    }
}