import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class provides a method to generate the binary reflected gray code for a given order and outputs the generated code as a list of bit strings.
 */
public class Klutzomaniacs {

  public static void main(String[] args) {
    // order of the gray code
    int n = 5;

    // generate binary reflected gray code
    List<String> grayCode = brgc(n);
    System.out.println("Binary Reflected Gray Code of Order 5: ");

    int index = 0;
    for (String code : grayCode) {
      // print each bit string of gray code
      System.out.println(index + ": " + code);
      index++;
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
}
