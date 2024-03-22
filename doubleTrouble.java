import java.util.Random;
import java.util.Scanner;

public class doubleTrouble {

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);

    while (true) {
      // set up game
      int green = 3;
      int yellow = 7;
      int red = 5;

      String winner = "";

      // user decides who goes first
      System.out.println("Choose who goes first: (0) You (1) Computer");

      int firstPlayer = scanner.nextInt();

      // if user selects themself to go first:
      if (firstPlayer == 0) {
        System.out.println("You go first.");

        while (true) {
          // player's turn
          printBoard(green, yellow, red);

          System.out.println("Choose a color: (0) Green (1) Yellow (2) Red");
          int chooseColor = scanner.nextInt();

          System.out.println("How many?");
          int howMany = scanner.nextInt();

          if (chooseColor == 0) {
            System.out.println("You chose green.");
            if (green - howMany >= 0) {
              green = green - howMany;
            } else {
              System.out.println("Error: Invalid move");
              continue;
            }
          } else if (chooseColor == 1) {
            System.out.println("You chose yellow.");
            if (yellow - howMany >= 0) {
              yellow = yellow - howMany;
            } else {
              System.out.println("Error: Invalid move");
              continue;
            }
          } else if (chooseColor == 2) {
            System.out.println("You chose red.");
            if (red - howMany >= 0) {
              red = red - howMany;
            } else {
              System.out.println("Error: Invalid move");
              continue;
            }
          } else {
            System.out.println("Error: Chooose a valid color (0, 1, or 2).");
            continue;
          }

          printBoard(green, yellow, red);

          // check if player made a winning move
          if (green + yellow + red == 0) {
            winner = "You";
            break;
          }

          // computer's turn
          int[] cMove = computerMove(green, yellow, red);
          int cColor = cMove[0];
          int cNumber = cMove[1];

          if (cColor == 0) {
            System.out.println("Computer chooses green.");
            green = green - cNumber;
          } else if (cColor == 1) {
            System.out.println("Computer chooses yellow.");
            yellow = yellow - cNumber;
          } else if (cColor == 2) {
            System.out.println("Computer chooses red.");
            red = red - cNumber;
          }

          printBoard(green, yellow, red);

          if (green + yellow + red == 0) {
            winner = "Computer";
            break;
          }
        }
      } else if (firstPlayer == 1) {
        System.out.println("Computer goes first.");

        printBoard(green, yellow, red);

        while (true) {
          // computer's turn
          int[] cMove = computerMove(green, yellow, red);
          int cColor = cMove[0];
          int cNumber = cMove[1];

          if (cColor == 0) {
            System.out.println("Computer chooses green.");
            green = green - cNumber;
          } else if (cColor == 1) {
            System.out.println("Computer chooses yellow.");
            yellow = yellow - cNumber;
          } else if (cColor == 2) {
            System.out.println("Computer chooses red.");
            red = red - cNumber;
          }

          printBoard(green, yellow, red);

          // check if computer made a winning move
          if (green + yellow + red == 0) {
            winner = "Computer";
            break;
          }

          // player's turn
          System.out.println("Choose a color: (0) Green (1) Yellow (2) Red");
          int chooseColor = scanner.nextInt();

          System.out.println("How many?");
          int howMany = scanner.nextInt();

          if (chooseColor == 0) {
            System.out.println("You chose green.");
            green = green - howMany;
          } else if (chooseColor == 1) {
            System.out.println("You chose yellow.");
            yellow = yellow - howMany;
          } else if (chooseColor == 2) {
            System.out.println("You chose red.");
            red = red - howMany;
          } else {
            System.out.println("Error: Chooose a valid color (0, 1, or 2).");
            continue;
          }

          printBoard(green, yellow, red);

          // check if player made a winning move
          if (green + yellow + red == 0) {
            winner = "You";
            break;
          }
        }
      } else {
        System.out.println("Error: Choose (0) or (1)");
      }

      // announce winner
      System.out.println(String.format("The winner is %s!", winner));

      System.out.println("Want to play again? (0) Yes (1) No");
      int again = scanner.nextInt();
      if (again != 0) {
        // terminate program
        break;
      }
    }

    scanner.close();
    System.out.println("Game over.");
  }

  public static int[] computerMove(int green, int yellow, int red) {
    // check that computer is at a winning position
    int xorVariable = green ^ yellow ^ red;

    if (xorVariable != 0) {
      // determine XOR of each combo of 2 piles
      int xorGY = green ^ yellow;
      int xorYR = yellow ^ red;
      int xorGR = green ^ red;

      // if XOR of 2 piles is less than total of 3rd pile, take the difference of the 2 from the 3rd pile
      if (xorGY < red) {
        return new int[] { 2, (red - xorGY) };
      } else if (xorGR < yellow) {
        return new int[] { 1, (yellow - xorGR) };
      } else if (xorYR < green) {
        return new int[] { 0, (green - xorYR) };
      }
    }

    // losing postion
    Random random = new Random();
    int color = random.nextInt(3);
    int number;

    if ((color == 0) && (green > 0)) {
      number = random.nextInt(green) + 1;
    } else if ((color == 1) && (yellow > 0)) {
      number = random.nextInt(yellow) + 1;
    } else if ((color == 2) && (red > 0)) {
      number = random.nextInt(red) + 1;
    } else {
      return computerMove(green, yellow, red);
    }

    return new int[] { color, number };
  }

  public static void printBoard(int green, int yellow, int red) {
    // colors for terminal printing
    String GREEN = "\u001B[32m";
    String YELLOW = "\u001B[33m";
    String RED = "\u001B[31m";
    String COLOR_RESET = "\u001B[0m";

    System.out.println("Current board:");

    System.out.println(GREEN + "Green: " + green + COLOR_RESET);
    String greenSticks = "";
    for (int i = 1; i <= green; i++) {
      greenSticks += " | ";
    }
    System.out.println(GREEN + greenSticks + COLOR_RESET);

    System.out.println(YELLOW + "Yellow: " + yellow + COLOR_RESET);
    String yellowSticks = "";
    for (int i = 1; i <= yellow; i++) {
      yellowSticks += " | ";
    }
    System.out.println(YELLOW + yellowSticks + COLOR_RESET);

    System.out.println(RED + "Red: " + red + COLOR_RESET);
    String redSticks = "";
    for (int i = 1; i <= red; i++) {
      redSticks += " | ";
    }
    System.out.println(RED + redSticks + COLOR_RESET);
  }
}
