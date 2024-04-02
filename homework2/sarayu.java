import java.util.Scanner;

/**
 * This class incorporates the computation and outputting of my own sequence of
 * numbers.
 */
public class sarayu {

  public static void main(String args[]) {
    // initialize time variables
    long prevTime = 0;
    double prevSarayuNum = 0;

    // accept and read input value
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter a value to compute the Sarayu Numbers sequence:");
    int n = scanner.nextInt();

    // calculate and output Sarayu numbers
    System.out.println("Sarayu Numbers:");
    for (int i = 0; i <= n; i++) {
      int nextSarayuNum = compute(i + 1);

      // measure time
      long start = System.currentTimeMillis();
      int sarayuNum = compute(i);
      long end = System.currentTimeMillis();
      long timePassed = end - start;

      // print Sarayu number and time taken for its calculation
      System.out.println(
          "L(" +
              i +
              ") = " +
              sarayuNum +
              ", Time: " +
              timePassed +
              " milliseconds");

      // calculate ratios (have to cast)
      if (i > 0) {
        double sarayuRatio = (double) nextSarayuNum / sarayuNum;
        double timeRatio = (double) timePassed / prevTime;

        System.out.println("Ratio of Successive Calculations: " + sarayuRatio);
        System.out.println(
            "Ratio of Successive Calculation Times: " + timeRatio);
      }

      // update variables
      prevSarayuNum = sarayuNum;
      prevTime = timePassed;
    }

    scanner.close();
  }

  /**
   * This method recursively calculates the Sarayu number for a given input "n".
   * 
   * @param n index of the Sarayu number to compute
   * @return the Sarayu number at the specified index
   */
  public static int compute(int n) {
    if (n == 0) {
      return 4;
    } else if (n == 1) {
      return 15;
    } else {
      return (compute(n - 2) + compute(n - 1));
    }

    // order of growth is O(2^n)
  }
}
