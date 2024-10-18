import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public static void print (String s , int [] a){

        if (a.length == 1){
            System.out.println(s);
            return;
        }
        int first = a[0];

        int[] remaining = Arrays.copyOfRange(a,1,a.length);

        print(s+first , remaining);

        print(s,remaining);


    }
}