import java.util.Scanner;

/**
 * This program counts the number of inversion in an array with a naive O(n^2) [sorting] algorithm.
 */
public class easyinversioncount {

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the length of the array (n): ");
    int n = scanner.nextInt();
    if (n <= 0) {
      System.out.println("Error: Invalid length.");
    }

    int[] A = new int[n];
    for (int i = 0; i < n; i++) {
      System.out.println("Enter element " + (i + 1) + ": ");
      A[i] = scanner.nextInt();
    }

    int numInv = countInversions(A);
    System.out.println("Number of inversions: " + numInv);

    scanner.close();
  }

  public static int countInversions(int[] A) {
    int n = A.length;
    int numInv = 0;
    for (int i = 0; i < n - 1; i++) {
      for (int j = i + 1; j < n; j++) {
        if (A[i] > A[j]) {
          numInv++;
        }
      }
    }
    return numInv;
  }
}
