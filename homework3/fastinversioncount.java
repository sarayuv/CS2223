import java.util.Scanner;

/**
 * This program counts the number of inversion in an array with a O(n logn) [sorting] algorithm.
 */
public class fastinversioncount {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the length of the array (n): ");
    int n = scanner.nextInt();
    if (n <= 0) {
      System.out.println("Error: Invalid length.");
    }

    int[] A = new int[n];
    for (int i = 0; i < n; i++) {
      System.out.print("Enter element " + (i + 1) + ": ");
      A[i] = scanner.nextInt();
    }

    int numInv = mergeSort(A, 0, A.length - 1);
    System.out.println("Number of inversions: " + numInv);

    scanner.close();
  }

  /**
   * This method sorts the elements of an array using the merge sort algorithm and counts the number of inversions it finds.
   *
   * @param A the array
   * @param start the starting index of the subarray that needs to be sorted
   * @param end the ending index of the subarray that needs to be sorted
   * @return the number of inversions found while this method runs
   */
  public static int mergeSort(int[] A, int start, int end) {
    int invCount = 0;

    // check if there are at least 2 elements in the subarray
    if (end > start) {
      // find the middle index to split the array
      int middle = (end + start) / 2;
      // sort left half
      invCount += mergeSort(A, start, middle);
      // sort right half
      invCount += mergeSort(A, middle + 1, end);
      // merge halves, count number of inversions
      invCount += merge(A, start, middle, end);
    }

    return invCount;
  }

  /**
   * This method merges the 2 sorted subarrays and counts the number of inversions it finds.
   *
   * @param A      the array
   * @param start  the starting index of the first subarray
   * @param middle the ending index of the first subarray
   * @param end    the ending index of the second subarray
   * @return the number of inversions found while this method runs
   */
  public static int merge(int[] A, int start, int middle, int end) {
    // create a copy of the array for merging
    int[] copy = new int[A.length];

    // initialize starting indexes
    int i = start;
    int j = middle + 1;
    int k = start;

    int invCount = 0;

    // merge the 2 subarrays
    while (i <= middle && j <= end) {
      if (A[i] <= A[j]) {
        // copy the element from teh left subarray
        copy[k++] = A[i++];
      } else {
        // copy the element from the right subarray
        copy[k++] = A[j++];
        // increment invCout by the number of elements left in the left subarray
        invCount += (middle - i + 1);
      }
    }

    // copy the rest of the elements in the left subarray
    while (i <= middle) {
      copy[k++] = A[i++];
    }

    // copy the rest of the elements in the right subarray
    while (j <= end) {
      copy[k++] = A[j++];
    }

    // copy the elements from the copy of the array back to the original array
    for (i = start; i <= end; i++) {
      A[i] = copy[i];
    }

    return invCount;
  }
}
