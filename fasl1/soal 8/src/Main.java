public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
    public static void print(int a , String s){
        if (s.length() == a){
            System.out.println(s);
            return;
        }
        print(a,s+"1");
        print(a,s+"0");
    }
}