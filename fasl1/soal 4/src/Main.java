public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
    public static void dectobi(int a , String s){
        if (a/2 == 0){
            System.out.println("1" + s);
        } else if (a%2 == 0) {
            dectobi(a/2,"0" + s);
        } else if (a%2 == 1) {
            dectobi(a/2, "1" + s);
        }
    }
}