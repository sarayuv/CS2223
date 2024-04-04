import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class generates the binary reflected gray code for a given order and outputs the generated code as a list of bit strings.
 */
public class Klutzomaniacs {

  public static void main(String[] args) {
    String[] names = { "Axel", "Boxo", "Crunchy", "Doofus", "Enzo" };

    // order of the gray code
    int n = 5;

    // generate binary reflected gray code
    List<String> grayCode = brgc(n);

    // print table header
    System.out.println("Index\tGray Code\tKlutzomaniacs Riding\tAction");
    for (int i = 0; i < grayCode.size(); i++) {
      String subset = generateSubset(grayCode.get(i), names);
      String action = generateAction(grayCode.get(i));
      printRow(i, grayCode.get(i), subset, action);
    }
  }

  /**
   * This method generates the binary reflected gray code of order n
   * @param n the order of gray code
   * @return a list of all bit strings of length n coposing the gray code
   */
  public static List<String> brgc(int n) {
    // create a list to store the gray code bit strings
    List<String> grayCode = new ArrayList<String>();

    if (n == 1) {
      // make list L containing bit strings 0 and 1 in this order
      grayCode.add("0");
      grayCode.add("1");
    } else {
      // generate list L1 of bit strings of size n-1 by calling BRCC(n-1)
      List<String> L1 = brgc(n - 1);

      // copy list L1 to list L2 in reversed order
      List<String> L2 = new ArrayList<String>(L1);
      Collections.reverse(L2);

      for (int i = 0; i < L1.size(); i++) {
        // add 0 in front of each bit string in list L1
        L1.set(i, "0" + L1.get(i));
        // add 1 in front of each bit string in list L2
        L2.set(i, "1" + L2.get(i));
      }
      // append L2 to L1 to get list L
      grayCode.addAll(L1);
      grayCode.addAll(L2);
    }

    return grayCode;
  }

  /**
   * This method generates the subset of Klutzomaniacs riding based on the binary code.
   * @param code the binary code representing the Klutzomaniacs riding
   * @param names the array of performers' names
   * @return the subset of Klutzomaniacs riding
   */
  public static String generateSubset(String code, String[] names) {
    // initialize a string to store the subset
    String subset = "";

    // add the performers' names
    if (code.charAt(0) == '1') {
      subset += names[0] + " & ";
    }

    for (int i = 1; i < code.length(); i++) {
      // if the current bit is '1', add the corresponding Klutzomaniac name to the subset
      if (code.charAt(i) == '1') {
        subset += names[i] + " & ";
      }
    }

    // if the subset isn't empty,
    if (!subset.isEmpty()) {
      // remove "&" and return
      subset = subset.substring(0, subset.length() - 3);
    } else {
      // set it to "empty tricycle"
      subset = "Empty Tricycle";
    }

    return subset;
  }

  /**
   * This method generates the action based on the binary code.
   * @param code the binary code representing the action
   * @return the action that is being performed
   */
  public static String generateAction(String code) {
    // if the first bit of the code is 0,
    if (code.charAt(0) == '0') {
      // make the action spotlight
      return "Spotlight";
    } else {
      return "Joins";
    }
  }

  /**
   * This method builds and prints the string of the row for the table.
   * @param index the index of the row
   * @param grayCode the binary code
   * @param subset the subset of Klutzomaniacs riding
   * @param action the action that is being performed
   */
  public static void printRow(
    int index,
    String grayCode,
    String subset,
    String action
  ) {
    System.out.printf(
      "%-6d\t%-10s\t%-30s\t%-20s%n",
      index,
      grayCode,
      subset,
      action
    );
  }
}
