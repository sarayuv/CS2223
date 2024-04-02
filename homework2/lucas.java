import java.util.Scanner;

/**
 * This class incorporates tasks relating to the Lucas Numbers section of the
 * homework.
 */
public class lucas {

  public static void main(String args[]) {
    // initialize time variables
    long prevTime = 0;
    double prevLucasNum = 0;

    // accept and read input value
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter a value to compute the Lucas Numbers sequence:");
    int n = scanner.nextInt();

    // calculate and output lucas numbers
    System.out.println("Lucas Numbers:");
    for (int i = 0; i <= n; i++) {
      int nextLucasNum = compute(i + 1);

      // measure time
      long start = System.currentTimeMillis();
      int lucasNum = compute(i);
      long end = System.currentTimeMillis();
      long timePassed = end - start;

      // print lucas number and time taken for its calculation
      System.out.println(
          "L(" + i + ") = " + lucasNum + ", Time: " + timePassed + " milliseconds");

      // calculate ratios (have to cast)
      if (i > 0) {
        double lucasRatio = (double) nextLucasNum / lucasNum;
        double timeRatio = (double) timePassed / prevTime;

        System.out.println("Ratio of Successive Calculations: " + lucasRatio);
        System.out.println(
            "Ratio of Successive Calculation Times: " + timeRatio);
      }

      // update variables
      prevLucasNum = lucasNum;
      prevTime = timePassed;
    }

    scanner.close();
  }

  /**
   * This method recursively calculates the lucas number for a given input "n".
   * 
   * @param n index of the lucas number to compute
   * @return the lucas number at the specified index
   */
  public static int compute(int n) {
    if (n == 0) {
      return 2;
    } else if (n == 1) {
      return 1;
    } else {
      return (compute(n - 1) + compute(n - 2));
    }

    // order of growth is O(2^n)
  }
}
