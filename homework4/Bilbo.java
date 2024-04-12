public class Bilbo {

  static int[][] vault = {
    { 96, 33, 44, 98, 75, 68, 99, 84 },
    { 10, 41, 1, 86, 46, 24, 53, 93 },
    { 83, 97, 94, 27, 65, 51, 30, 7 },
    { 56, 70, 47, 64, 22, 88, 67, 12 },
    { 91, 11, 77, 48, 13, 71, 92, 15 },
    { 32, 59, 17, 25, 31, 4, 16, 63 },
    { 79, 5, 14, 23, 78, 37, 40, 74 },
    { 35, 89, 52, 66, 82, 20, 95, 21 },
  };

  public static void main(String[] args) {
    // store the most precious path for this vault
    int[] optimalPath = findMPP(vault);

    // initialize collected gems variable
    int gems = 0;

    for (int i = 0; i < optimalPath.length; i++) {
      gems += vault[i][optimalPath[i]];
    }

    // output Bilbo's starting square
    System.out.println(
      "Starting square is Row 1, Vault " + (optimalPath[0] + 1)
    );

    // output a representation of Bilbo's path
    System.out.println("Most Precious Path:");
    for (int i = 0; i < optimalPath.length; i++) {
      System.out.println(
        "Row " +
        (i + 1) +
        ", Vault " +
        (optimalPath[i] + 1) +
        " (" +
        vault[i][optimalPath[i]] +
        " Gems)"
      );
    }

    // ouput the total number of gems Bilbo collected on the way
    System.out.println("Total number of gems: " + gems);

    // output the number of the vault wherein the King has secreted the Arkenstone
    System.out.println(
      "The vault containing the Arkenstone is Vault " + (optimalPath[0] + 1)
    );
  }

  /**
   * This method finds the most precious path for Bilbo to collect maximum gems.
   * @param vault a 2D array where each cell represents the number of gems
   * @return an array representing the most precious path
   */
  public static int[] findMPP(int[][] vault) {
    // create a dynamic programming table - represents the maximum number of gems that can be collected from the top to cell (i, j)
    int[][] dpTable = new int[vault.length][vault.length];

    // reverse the vault values bottom-up
    for (int i = 0; i < vault.length / 2; i++) {
      int[] temp = vault[i];
      vault[i] = vault[vault.length - 1 - i];
      vault[vault.length - 1 - i] = temp;
    }

    // initialize first row of dp table with the number of gems in the first row of vault
    for (int i = 0; i < vault.length; i++) {
      dpTable[0][i] = vault[0][i];
    }

    // update dp table row by row - for each cell, find the maximum number of gems that can be collected from the top to this cell
    for (int i = 1; i < vault.length; i++) {
      for (int j = 0; j < vault.length; j++) {
        // initialize maxPrev, the maximum number of gems that can be collected from the top to the previous row
        int maxPrevious = dpTable[i - 1][j];

        // check if moving left is better than staying in this column
        if (j > 0) {
          maxPrevious = Math.max(maxPrevious, dpTable[i - 1][j - 1]);
        }

        // check if moving right is better than staying in this column
        if (j < vault.length - 1) {
          maxPrevious = Math.max(maxPrevious, dpTable[i - 1][j + 1]);
        }

        // calculate the maximum gems and update table
        dpTable[i][j] = maxPrevious + vault[i][j];
      }
    }

    // find the max number of gems that can be collected
    int maxGems = 0;

    // find the corresponding column in the last row for maxGems
    int maxColumn = 0;
    for (int i = 0; i < vault.length; i++) {
      if (dpTable[vault.length - 1][i] > maxGems) {
        maxGems = dpTable[vault.length - 1][i];
        maxColumn = i;
      }
    }

    // figure out the path that Bilbo took
    // initialize an array to store the path
    int[] path = new int[vault.length];

    // set the last element to column index of the maximum gems in the last row
    path[vault.length - 1] = maxColumn;

    // iterate through the rows of the vault
    for (int i = vault.length - 2; i >= 0; i--) {
      // get the column index of the previous row
      int current = path[i + 1];

      // initialize the maximum gems collected from the top to the current cell
      int maxPrevious = dpTable[i][current];

      // initialize the next column as the left one
      int nextColumn = current;

      // check if moving left is better than staying in this column
      if (current > 0 && dpTable[i][current - 1] > maxPrevious) {
        // update the maximum gems collected
        maxPrevious = dpTable[i][current - 1];

        // update the next column as the left one
        nextColumn = current - 1;
      }

      // check if moving right is better than staying in this column
      if (current < vault.length - 1 && dpTable[i][current + 1] > maxPrevious) {
        // update the next column as the right one
        nextColumn = current + 1;
      }

      // store the next column in the path array
      path[i] = nextColumn;
    }

    return path;
  }
}
