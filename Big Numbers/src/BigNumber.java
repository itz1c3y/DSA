import java.util.Arrays;

public class BigNumber {
    private int[] digits;
    private boolean isNegative;

    public BigNumber(String number) {
        isNegative = number.startsWith("-");
        if (isNegative) {
            number = number.substring(1);
        }
        digits = new int[number.length()];
        for (int i = 0; i < number.length(); i++) {
            digits[i] = number.charAt(number.length() - i - 1) - '0';
        }
    }

    public BigNumber add(BigNumber other) {
        int maxLength = Math.max(this.digits.length, other.digits.length);
        int[] result = new int[maxLength + 1];

        for (int i = 0; i < maxLength; i++) {
            int sum = getDigit(i) + other.getDigit(i) + result[i];
            result[i] = sum % 10;
            result[i + 1] = sum / 10;
        }
        return new BigNumber(arrayToString(result));
    }

    public BigNumber subtract(BigNumber other) {
        if (compareTo(other) < 0) {
            return other.subtract(this).negate();
        }
        int[] result = new int[digits.length];
        int borrow = 0;
        for (int i = 0; i < digits.length; i++) {
            int diff = getDigit(i) - other.getDigit(i) - borrow;
            if (diff < 0) {
                diff += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
            result[i] = diff;
        }
        return new BigNumber(arrayToString(result));
    }

    public BigNumber multiply(BigNumber other) {
        int[] result = new int[digits.length + other.digits.length];
        for (int i = 0; i < digits.length; i++) {
            int carry = 0;
            for (int j = 0; j < other.digits.length; j++) {
                int product = result[i + j] + getDigit(i) * other.getDigit(j) + carry;
                result[i + j] = product % 10;
                carry = product / 10;
            }
            result[i + other.digits.length] += carry;
        }
        return new BigNumber(arrayToString(result));
    }

    public BigNumber divide(BigNumber other) {
        BigNumber zero = new BigNumber("0");
        if (other.compareTo(zero) == 0) {
            throw new ArithmeticException("Division by zero");
        }
        BigNumber result = zero;
        BigNumber temp = new BigNumber(this.toString());
        while (temp.compareTo(other) >= 0) {
            temp = temp.subtract(other);
            result = result.add(new BigNumber("1"));
        }
        return result;
    }

    public BigNumber leftShift() {
        return this.multiply(new BigNumber("10"));
    }

    public BigNumber rightShift() {
        int[] result = Arrays.copyOfRange(digits, 1, digits.length);
        return new BigNumber(arrayToString(result));
    }

    public BigNumber factorial() {
        BigNumber result = new BigNumber("1");
        BigNumber counter = new BigNumber("1");
        BigNumber one = new BigNumber("1");
        while (counter.compareTo(this) <= 0) {
            result = result.multiply(counter);
            counter = counter.add(one);
        }
        return result;
    }

    private int getDigit(int index) {
        return index < digits.length ? digits[index] : 0;
    }

    private int compareTo(BigNumber other) {
        if (this.digits.length != other.digits.length) {
            return this.digits.length - other.digits.length;
        }
        for (int i = digits.length - 1; i >= 0; i--) {
            if (this.digits[i] != other.digits[i]) {
                return this.digits[i] - other.digits[i];
            }
        }
        return 0;
    }

    private BigNumber negate() {
        BigNumber negated = new BigNumber(this.toString());
        negated.isNegative = !this.isNegative;
        return negated;
    }

    private String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();
        int start = array.length - 1;
        while (start > 0 && array[start] == 0) {
            start--;
        }
        for (int i = start; i >= 0; i--) {
            sb.append(array[i]);
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (isNegative) {
            sb.append("-");
        }
        for (int i = digits.length - 1; i >= 0; i--) {
            sb.append(digits[i]);
        }
        return sb.toString();
    }

}
