public class Main {
    public static void main(String[] args) {
        BigNumber num1 = new BigNumber("1234");
        BigNumber num2 = new BigNumber("9876");

        System.out.println("Addition: " + num1.add(num2));
        System.out.println("Subtraction: " + num1.subtract(num2));
        System.out.println("Multiplication: " + num1.multiply(num2));
        System.out.println("Division: " + num1.divide(new BigNumber("2")));
        System.out.println("Left Shift: " + num1.leftShift());
        System.out.println("Right Shift: " + num1.rightShift());
        System.out.println("Factorial: " + new BigNumber("5").factorial());
    }
}