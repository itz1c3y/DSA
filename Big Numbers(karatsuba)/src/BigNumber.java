import java.util.Arrays;

public class BigNumber {
    public int[] digits;
    private boolean isNegative;

    public BigNumber(String number) {
        isNegative = number.startsWith("-");
        if (isNegative) {
            number = number.substring(1);
        }
        digits = new int[number.length()];
        for (int i = 0; i < number.length(); i++) {
            digits[i] = number.charAt(i) - '0';
        }
    }



    public BigNumber add(BigNumber other) {
        if (this.isNegative == other.isNegative) {
            BigNumber result = addAbsoluteValues(other);
            result.isNegative = this.isNegative;
            return result;
        } else {
            if (compareAbsolute(other) >= 0) {
                BigNumber result = subtractAbsoluteValues(other);
                result.isNegative = this.isNegative;
                return result;
            } else {
                BigNumber result = other.subtractAbsoluteValues(this);
                result.isNegative = other.isNegative;
                return result;
            }
        }
    }

    public BigNumber subtract(BigNumber other) {
        if (this.isNegative != other.isNegative) {
            BigNumber result = addAbsoluteValues(other);
            result.isNegative = this.isNegative;
            return result;
        } else {
            if (compareAbsolute(other) >= 0) {
                BigNumber result = subtractAbsoluteValues(other);
                result.isNegative = this.isNegative;
                return result;
            } else {
                BigNumber result = other.subtractAbsoluteValues(this);
                result.isNegative = !this.isNegative;
                return result;
            }
        }
    }

    public BigNumber multiply(BigNumber other) {
        BigNumber result = multiplyAbsoluteValues(other);
        result.isNegative = this.isNegative != other.isNegative;
        return result;
    }

    public BigNumber divide(BigNumber other) {
        if (other.compareAbsolute(new BigNumber("0")) == 0) {
            throw new ArithmeticException("Division by zero");
        }

        BigNumber quotient = new BigNumber("0");
        BigNumber remainder = new BigNumber(this.toString());

        while (remainder.compareAbsolute(other) >= 0) {
            remainder = remainder.subtractAbsoluteValues(other);
            quotient = quotient.add(new BigNumber("1"));
        }

        quotient.isNegative = this.isNegative != other.isNegative;
        return quotient;
    }

    public BigNumber factorial() {
        BigNumber result = new BigNumber("1");
        BigNumber counter = new BigNumber("1");
        BigNumber one = new BigNumber("1");
        while (counter.compareAbsolute(this) <= 0) {
            result = result.multiply(counter);
            counter = counter.add(one);
        }
        return result;
    }

    private BigNumber addAbsoluteValues(BigNumber other) {
        int maxLength = Math.max(this.digits.length, other.digits.length);
        int[] result = new int[maxLength + 1];
        int carry = 0;

        for (int i = 0; i < maxLength; i++) {
            int sum = getDigitFromRight(i) + other.getDigitFromRight(i) + carry;
            result[result.length - 1 - i] = sum % 10;
            carry = sum / 10;
        }
        result[0] = carry;
        return new BigNumber(arrayToString(result));
    }

    private BigNumber subtractAbsoluteValues(BigNumber other) {
        int[] result = new int[digits.length];
        int borrow = 0;

        for (int i = digits.length - 1; i >= 0; i--) {
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

    private BigNumber multiplyAbsoluteValues(BigNumber other) {
        int[] result = new int[digits.length + other.digits.length];
        for (int i = digits.length - 1; i >= 0; i--) {
            int carry = 0;
            for (int j = other.digits.length - 1; j >= 0; j--) {
                int product = result[i + j + 1] + getDigit(i) * other.getDigit(j) + carry;
                result[i + j + 1] = product % 10;
                carry = product / 10;
            }
            result[i] += carry;
        }
        return new BigNumber(arrayToString(result));
    }

    private int getDigit(int index) {
        return index < digits.length ? digits[index] : 0;
    }

    private int getDigitFromRight(int index) {
        int reversedIndex = digits.length - 1 - index;
        return getDigit(reversedIndex);
    }

    private int compareAbsolute(BigNumber other) {
        if (this.digits.length != other.digits.length) {
            return this.digits.length - other.digits.length;
        }
        for (int i = 0; i < digits.length; i++) {
            if (this.digits[i] != other.digits[i]) {
                return this.digits[i] - other.digits[i];
            }
        }
        return 0;
    }

    private String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();
        int start = 0;
        while (start < array.length && array[start] == 0) {
            start++;
        }
        for (int i = start; i < array.length; i++) {
            sb.append(array[i]);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (isNegative) {
            sb.append("-");
        }
        for (int digit : digits) {
            sb.append(digit);
        }
        return sb.toString();
    }
    // Method to left shift by adding 'times' number of zeros to the right
    public BigNumber leftShift(int times) {
        if (times <= 0) return new BigNumber(this.toString());

        // Create a new array with extra space for the shift
        int[] shiftedDigits = new int[this.digits.length + times];

        // Copy the current digits to the new array
        System.arraycopy(this.digits, 0, shiftedDigits, times, this.digits.length);

        return new BigNumber(arrayToString(shiftedDigits));
    }

    // Method to right shift by removing 'times' number of digits from the end
    public BigNumber rightShift(int times) {
        if (times <= 0 || times >= this.digits.length) return new BigNumber("0");

        // Create a new array with the remaining digits after the shift
        int[] shiftedDigits = Arrays.copyOfRange(this.digits, times, this.digits.length);

        return new BigNumber(arrayToString(shiftedDigits));
    }

    public BigNumber karatsuba(BigNumber other){
        if (this.digits.length < 10 && other.digits.length < 10 ){
            return this.multiply(other);
        }
        int[] left_a = new int[this.digits.length / 2];
        int[] left_b = new int[other.digits.length / 2];
        int[] right_b = new int[other.digits.length / 2];
        int[] right_a = new int[this.digits.length / 2];


        for (int i =0 ; i < this.digits.length / 2 ; i++) {
            left_a[i] = this.digits[i];
        }

        for (int i =0 ; i < other.digits.length / 2 ; i++) {
            left_b[i] = other.digits[i];
        }

        for (int i = 0; i < other.digits.length/2; i++) {
            right_b[i] = other.digits[i+other.digits.length/2];
        }

        for (int i = 0; i < this.digits.length/2; i++) {
            right_a[i] = this.digits[i + this.digits.length/2];
        }

        BigNumber a = new BigNumber(arrayToString(left_a));
        BigNumber c = new BigNumber(arrayToString(left_b));
        BigNumber b = new BigNumber(arrayToString(right_a));
        BigNumber d = new BigNumber(arrayToString(right_b));

        BigNumber ac = a.karatsuba(c);
        BigNumber bd = b.karatsuba(d);
        BigNumber mid = (a.add(b)).karatsuba(c.add(d));
        BigNumber flag = (ac.subtract(bd)).subtract(mid);

        return (ac.leftShift(this.digits.length).add( flag.leftShift(this.digits.length/2))).add(bd);



    }
}
