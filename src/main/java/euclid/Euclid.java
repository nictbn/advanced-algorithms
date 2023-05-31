package euclid;

public class Euclid {
    public int recursiveGreatestCommonDivisor(int number, int divisor) {
        int remainder = number % divisor;
        if (remainder != 0) {
            return recursiveGreatestCommonDivisor(divisor, remainder);
        }
        return divisor;
    }

    public int iterativeGreatestCommonDivisor(int number, int divisor) {
        while (divisor != 0) {
            int temp = divisor;
            divisor = number % divisor;
            number = temp;
        }
        return number;
    }
}
