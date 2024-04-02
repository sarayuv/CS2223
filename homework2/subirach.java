import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class incorporates tasks relating to a specific magic square.
 */
public class subirach {
  // need this global variable to initialize count for some methods
  private static int count = 0;

  /**
   * Calculates and prints counts for combinations within the magic square.
   * 
   * @param args command-line arguments
   */
  public static void main(String args[]) {
    // recreate magic square
    int[] magicSquare = { 1, 14, 14, 4, 11, 7, 6, 9, 8, 10, 10, 5, 13, 2, 3, 15 };

    // sum all elements in magicSquare
    int maxSum = 0;
    for (int num : magicSquare) {
      maxSum += num;
    }

    // outer loop iterates over all sums from 0 to maxSum
    for (int i = 0; i <= maxSum; i++) {
      // inner loop iterates over all sizes of combinations (1-33)
      for (int j = 1; j <= 33; j++) {
        // call countCombos with the current sum (i) and size of combination (j)
        int numOfSum = countCombos(magicSquare, i, j);
        // skip printing if the number of a sum is zero
        if (numOfSum != 0) {
          System.out.println("Number of " + j + "-element combinations that sum up to " + i + " : " + numOfSum);
        }
      }
    }
  }

  /**
   * Counts the number of 4-element combinations in the magic square that sum up
   * to 33.
   * 
   * @param magicSquare Magic Square array
   * @return Number of 4-element combinations that sum up o 33
   */
  public static int count4Combos(int[] magicSquare) {
    int numCombos = 0;

    // iterate through all positive combinations of 4 elements in the magic square
    // first element
    for (int i = 0; i < 4; i++) {
      // second element
      for (int j = 0; j < 4; j++) {
        // third element
        for (int k = i; k < 4; k++) {
          // fourth element
          for (int l = j; l < 4; l++) {
            // check if the current combination of elements are adjacent or diagonal to each
            // other

            // calculate row difference from linear index to see how far apart rows are
            int rowDiff = Math.abs(i / 4 - k / 4);
            // calculate column difference from linear index to see how far apart columns
            // are
            int colDiff = Math.abs(j % 4 - l % 4);

            // if adjacent or diagonal, check if sum == 33
            if ((rowDiff <= 1 && colDiff == 0) || (rowDiff == 0 && colDiff <= 1)) {
              if (magicSquare[i] + magicSquare[j] + magicSquare[k] + magicSquare[l] == 33) {
                // increment the number of combinations
                numCombos++;
              }
            }
          }
        }
      }
    }

    return numCombos;
  }

  /**
   * Counts all combinations within the magic square for each sum and
   * number of elements.
   *
   * @param magicSquare Magic square array
   * @return Total number of combinations
   */
  public static int countAllCombos(int[] magicSquare) {
    int numCombos = 0;

    // calculate sum of all elements in magicSquare
    int maxSum = 0;
    for (int num : magicSquare) {
      maxSum += num;
    }

    // outer loop iterates from 0 to maxSum for the number of sums
    for (int i = 0; i <= maxSum; i++) {
      // inner loop iterates from 1 to 33 for the number of elements in a combination
      for (int j = 1; j <= 33; j++) {
        numCombos += countCombos(magicSquare, i, j);
      }
    }

    return numCombos;
  }

  /**
   * Counts combinations within the magic square that sum up to a given value with
   * a specified number of elements.
   *
   * @param magicSquare Magic square array
   * @param sum         Target sum
   * @param k           Number of elements in the combination
   * @return Number of combinations that meet the criteria
   */
  public static int countCombos(int[] magicSquare, int sum, int k) {
    // initialize number of combinations to 0
    count = 0;

    // create a list to store elements of each combination
    ArrayList<Integer> sequence = new ArrayList<>();

    // call findCombos function
    findCombos(magicSquare, sum, 0, sequence, k, 0);

    return count;
  }

  /**
   * Uses backtracking to find combinations within the magic square.
   *
   * @param magicSquare Magic square array
   * @param sum         Target sum
   * @param start       Starting index
   * @param sequence    Current sequence of elements
   * @param k           Number of elements in the combination
   * @param currentSum  Current sum of elements
   */
  public static void findCombos(int[] magicSquare, int sum, int start, List<Integer> sequence, int k, int currentSum) {
    // check if the sequence list size is == k
    if (sequence.size() == k) {
      // check if currentSum == target sum
      if (currentSum == sum) {
        // increment count variable
        count++;
      }
      return;
    }

    // if target number of elements isn't the same, enter the below loop
    for (int i = start; i < magicSquare.length; i++) {
      // add current element to sequence list
      sequence.add(magicSquare[i]);
      // recurively call with next index
      findCombos(magicSquare, sum, i + 1, sequence, k, currentSum + magicSquare[i]);
      // remove last element from sequence list
      sequence.remove(sequence.size() - 1);
    }
  }

  /**
   * Counts the frequencies of different sums within all possible combinations of
   * the magic square.
   *
   * @param magicSquare Magic square array
   * @return HashMap containing sums and their counts
   */
  public static HashMap<Integer, Integer> countNumOfSum(int[] magicSquare) {
    // initialize hashmap
    HashMap<Integer, Integer> numOfSum = new HashMap<>();

    // iterate over all combinations of 4 elements
    for (int i = 0; i < magicSquare.length; i++) {
      for (int j = i + 1; j < magicSquare.length; j++) {
        for (int k = j + 1; k < magicSquare.length; k++) {
          for (int l = k + 1; l < magicSquare.length; l++) {
            // calculate sum of 4 elements
            // the calculated sum is used as the key for numOfSum
            int sum = magicSquare[i] + magicSquare[j] + magicSquare[k] + magicSquare[l];
            // increments frequency count for current sum, if sum doesn't exist, 0 is used
            numOfSum.put(sum, numOfSum.getOrDefault(sum, 0) + 1);
          }
        }
      }
    }

    return numOfSum;
  }
}
