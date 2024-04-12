/**
 * This class incorporates Gauss-Jordan Elimination to solve a system of linear equations.
 */
public class GaussJordan {

  public static void main(String[] args) {
    double[][] A = {
      { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 364 },
      { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4 },
      { 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 16 },
      { 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 36 },
      { 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 64 },
      { 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 100 },
      { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 79 },
      { 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 61 },
      { 0, 0, 0, 0, 0, 4, -3, 0, 0, 0, 0, 0, 0 },
      { 0, 0, 0, 3, -2, 0, 0, 0, 0, 0, 0, 0, 0 },
      { 0, 0, 0, 1, 0, 0, 0, 0, 1, -1, 0, 0, 0 },
      { 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, -42 },
    };

    // call helper function to solve to system of linear equations
    int[] answer = helper(A);

    // print solution
    System.out.println("Solution: ");
    for (int i = 0; i < answer.length; i++) {
      System.out.println("x[" + i + "] = " + answer[i]);
    }
  }

  /**
   * This method solves a system of linear equations using Gauss-Jordan elimination.
   * @param A an augmented coefficient matrix
   */
  public static int[] helper(double[][] A) {
    // forward elimination
    for (int i = 0; i < A.length; i++) {
      // find the pivot element with the largest absolute value
      int pivot = i;
      for (int j = i + 1; j < A.length; j++) {
        if (Math.abs(A[j][i]) > Math.abs(A[pivot][i])) {
          pivot = j;
        }
      }

      if (pivot != i) {
        // swap rows (pivot with current)
        double[] tempRow = A[i];
        A[i] = A[pivot];
        A[pivot] = tempRow;
      }

      double pivot2 = A[i][i];

      // divide all elements in current row by the pivot
      for (int k = i; k < A.length + 1; k++) {
        A[i][k] /= pivot2;
      }

      // eliminate other entries in the column for the current row
      for (int k = 0; k < A.length; k++) {
        if (k != i) {
          double temp = A[k][i];
          for (int j = i; j < A.length + 1; j++) {
            A[k][j] -= A[i][j] * temp;
          }
        }
      }
    }

    // add answer from the last column of the matrix
    int[] answer = new int[A.length];
    for (int i = 0; i < A.length; i++) {
      answer[i] = (int) A[i][A.length];
    }

    return answer;
  }
}
