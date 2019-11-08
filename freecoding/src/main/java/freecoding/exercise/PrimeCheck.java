package freecoding.exercise;

public class PrimeCheck {

    private static final int SOURCE = 51;

    public static boolean isPrime(int source) {
        if (source < 2) {
            return false;
        }
        for (int i = 2; i <= (int) Math.sqrt(source); i++) {
            if (source % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isOdd(int source) {
        return (source & 1) == 1;
    }

    public static boolean isPalindrome(int source) {
        String original = Integer.toString(source);
        String reversed = new StringBuilder(Integer.toString(source)).reverse().toString();
        return original.equals(reversed);
    }


    public static void main(String[] args) {
        System.out.println("is the number " + SOURCE + " Prime? : " + isPrime(SOURCE));
        System.out.println("is the number " + SOURCE + " Odd? : " + isOdd(SOURCE));
        System.out.println("is the number " + 123455432 + " Palindrome? : " + isPalindrome(123455432));
        System.out.println("is the number " + 1234554321 + " Palindrome? : " + isPalindrome(1234554321));
    }
}
