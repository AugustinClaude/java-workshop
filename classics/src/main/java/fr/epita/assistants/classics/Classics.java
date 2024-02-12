package fr.epita.assistants.classics;

public class Classics {
    /**
     * Computes the factorial of n.
     *
     * @param n the nth value to compute, negative values should return -1
     * @return the long value of n!
     */
    public static long factorial(int n) {
        if (n < 0)
            return -1;
        if (n == 0)
            return 1;

        long res = 1;
        while (n != 1) {
            res *= n;
            n -= 1;
        }

        return res;
    }

    /**
     * Computes the nth value of the tribonacci suite.
     * f(0) = 0, f(1) = 1, f(2) = 1, f(n+3) = f(n) + f(n+1) + f(n+2)
     *
     * @param n the nth sequence to compute
     */
    public static long tribonacci(int n) {
        long u0 = 0;
        long u1 = 1;
        long u2 = 1;

        if (n < 0)
            return -1;
        if (n == 0 || n == 1)
            return n;
        if (n == 2)
            return 1;

        for (int i = 2; i < n; i++) {
            long tmp0 = u0;
            long tmp1 = u1;
            u0 = u1;
            u1 = u2;
            u2 = tmp0 + tmp1 + u2;
        }

        return u2;
    }

    /**
     * Checks if a word is a palindrome.
     *
     * @param word the string to check
     * @return true if the word is a palindrome, false otherwise.
     */
    public static boolean isPalindrome(String word) {
        if (word == null)
            return false;
        if (word.isEmpty())
            return true;

        int i = 0;
        int j = word.length() - 1;
        while (i < j) {
            if (word.charAt(i) == ' ')
                i++;
            if (word.charAt(j) == ' ')
                j--;

            if (word.toLowerCase().charAt(i) != word.toLowerCase().charAt(j))
                return false;

            i++;
            j--;
        }

        return true;
    }

    /**
     * Sorts an array using an insertion sort.
     *
     * @param array the array to sort in place
     */
    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (array[j - 1] <= array[j])
                    break;

                int tmp = array[j];
                array[j] = array[j - 1];
                array[j - 1] = tmp;
            }
        }
    }

    /**
     * Combines two strings by alternating their characters. Must use a StringBuilder.
     * If the strings do not have the same length, appends the remaining characters at the end of the result.
     * For instance, combine("abc", "def") returns "adbecf"
     */
    public static String combine(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i;
        for (i = 0; i < Integer.min(a.length(), b.length()); i++) {
            sb.append(a.charAt(i));
            sb.append(b.charAt(i));
        }

        while (i < a.length()) {
            sb.append(a.charAt(i));
            i++;
        }
        while (i < b.length()) {
            sb.append(b.charAt(i));
            i++;
        }

        return sb.toString();
    }
}
