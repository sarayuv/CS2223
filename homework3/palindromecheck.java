import java.util.Scanner;

/**
 * This program determines whether an input sequence (string) from the keyboard is a palindrome or not.
 */
public class palindromecheck {

  public static void main(String args[]) {
    boolean answer = true;

    // accept input from user
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter input:");
    String input = scanner.nextLine();

    // makes program case insensitive and ignores white space and puncuation
    input = input.toLowerCase().replaceAll("[^a-z]", "");

    int inputLength = input.length();

    // checks if length is 0 or 1, meaning that it is a palindrome
    if (inputLength <= 1) {
      answer = true;
    } else {
      answer = verifyPalindrome(input, 0, inputLength - 1);
    }

    if (answer == false) {
      System.out.println("The input is not a palindrome.");
    } else if (answer == true) {
      System.out.println("The input is a palindrome.");
    }

    scanner.close();
  }

  /**
   * Checks if the first character is the same as the last character and recurses to check the rest of the word.
   * @param input The user's input
   * @param start The character that the method should check
   * @param end Another character that the method should check
   * @return True if the first character matches with the last
   */
  public static boolean verifyPalindrome(String input, int start, int end) {
    if (start >= end) {
      return true;
    }

    if (input.charAt(start) != input.charAt(end)) {
      return false;
    }

    // recursion
    return verifyPalindrome(input, start + 1, end - 1);
  }
}
