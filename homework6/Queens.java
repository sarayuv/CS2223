public class Queens {

    public static void main(String[] args) {
        // test cases for isLegalPosition
        int[] board1 = { 1, 6, 8, 3, 7, 4, 2, 5 };
        int[] board2 = { 1, 6, 8, 3, 7, 0, 0, 0 };
        int[] board3 = { 1, 6, 8, 3, 5, 0, 0, 0 };
        int n = 8;

        System.out.println(
                "isLegalPosition((1 6 8 3 7 4 2 5), 8) should return true and returns " + isLegalPosition(board1, n));
        System.out.println(
                "isLegalPosition((1 6 8 3 7 0 0 0), 8) should return true and returns " + isLegalPosition(board2, n));
        System.out.println(
                "isLegalPosition((1 6 8 3 5 0 0 0), 8) should return false and returns " + isLegalPosition(board3, n));

        // test cases for nextLegalPosition
        int[] board4 = { 1, 6, 8, 3, 5, 0, 0, 0 };
        int[] board5 = { 1, 6, 8, 3, 7, 0, 0, 0 };
        int[] board6 = { 1, 6, 8, 3, 7, 4, 2, 5 };

        int[] nextPos1 = nextLegalPosition(board4, n);
        System.out.println(
                "nextLegalPosition((1 6 8 3 5 0 0 0), 8) should return (1, 6, 8, 3, 7, 0, 0, 0) and returns: ");
        printBoard(nextPos1);

        int[] nextPos2 = nextLegalPosition(board5, n);
        System.out.println(
                "nextLegalPosition((1 6 8 3 7 0 0 0), 8) should return (1, 6, 8, 3, 7, 4, 0, 0) and returns: ");
        printBoard(nextPos2);

        int[] nextPos3 = nextLegalPosition(board6, n);
        System.out.println(
                "nextLegalPosition((1 6 8 3 7 4 2 5), 8) should return (1, 6, 8, 5, 0, 0, 0, 0) and returns: ");
        printBoard(nextPos3);

        // solving the board
        // print first solutions from n = 4 to n = 100
        for (int m = 4; m <= 100; m++) {
            int[] board7 = new int[m];
            boolean solution = solveNQueens(board7, 0, m);
            if (solution) {
                System.out.println("First solution for n = " + m + ":");
                printBoard(board7);
            }
        }

        // print all solutions for n = 4 to n = 20
        for (int m = 4; m <= 20; m++) {
            int[] board8 = new int[m];
            int solution2 = numSolutions(board8, 0, m);
            System.out.println(solution2 + " solutions to the " + m + "-Queens Problem");
        }
    }

    /**
     * Tis method checks if the current board layout is a legal position for
     * n-Queens.
     * 
     * @param board The board layout represented as an array
     * @param n     The size of the board
     * @return True if the board layout is legal, false otherwise
     */
    public static boolean isLegalPosition(int[] board, int n) {
        // check each queen on the board
        for (int i = 0; i < n; i++) {
            // skip empty spaces
            if (board[i] == 0) {
                continue;
            }

            // skip empty spaces
            for (int j = i + 1; j < n; j++) {
                if (board[j] == 0) {
                    continue;
                }

                // check if queens are on the same row/diagonal
                if (board[i] == board[j] || Math.abs(board[i] - board[j]) == Math.abs(i - j)) {
                    return false;
                }
            }
        }

        // return TRUE if and only if no two queens attack each other
        return true;
    }

    /**
     * This method finds the successor board layout to the given board.
     * 
     * @param board The current board layout represented as an array
     * @param n     The size of the board
     * @return The successor board layout
     */
    public static int[] Successor(int[] board, int n) {
        int[] newBoard = new int[n];
        System.arraycopy(board, 0, newBoard, 0, n);
        int i = n - 1;

        // find rightmost queen that can be moved
        while ((i >= 0) && (newBoard[i] == n)) {
            newBoard[i] = 0;
            i--;
        }

        // if all queens are at max positions
        if (i < 0) {
            // return board filled with zeros
            return new int[n];
        }

        // move rightmost queen to next position
        newBoard[i]++;

        // make sure queen is within bounds of board
        while ((i < n) && (newBoard[i] > n)) {
            newBoard[i] = 1;
            i--;
            if (i >= 0) {
                newBoard[i]++;
            }
        }

        return newBoard;
    }

    /**
     * This method finds the next legal position from the given board layout.
     * 
     * @param board The current board layout represented as an array.
     * @param n     The size of the board
     * @return The next legal board layout
     */
    public static int[] nextLegalPosition(int[] board, int n) {
        int[] next = Successor(board, n);

        // check if first queen is at its max position
        if (next[0] == 0) {
            // no legal position found
            return new int[n];
        }

        // find next legal position
        while ((!isLegalPosition(next, n)) && (next[0] != 0)) {
            next = Successor(next, n);
        }

        return next;
    }

    /**
     * This method prints the given board layout.
     * 
     * @param board The board layout represented as an array
     */
    public static void printBoard(int[] board) {
        System.out.print("(");
        for (int i = 0; i < board.length; i++) {
            System.out.print(board[i]);
            if (i < board.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(")");
    }

    /**
     * This method solves the n-Queens problem.
     * 
     * @param board The current board layout represented as an array
     * @param col   The current column
     * @param n     The size of the board
     * @return True if a solution is found, false otherwise
     */
    public static boolean solveNQueens(int[] board, int col, int n) {
        // if all columns are filled, a solution is found
        if (col == n) {
            return true;
        }

        // try putting a queen in each row of the current column
        for (int i = 1; i <= n; i++) {
            // place the queen in the current row
            board[col] = i;

            // check if the current board layout is legal and move to the next column
            if ((isLegalPosition(board, col + 1)) && (solveNQueens(board, col + 1, n))) {
                // if a solution is found in the next column
                return true;
            }
        }

        // if no solution is found for the current column, backtrack
        return false;
    }

    /**
     * This method finds the number of solutions to the n-Queens problem.
     * 
     * @param board The current board layout represented as an array
     * @param col   The current column
     * @param n     The size of the board
     * @return The total number of solutions found
     */
    public static int numSolutions(int[] board, int col, int n) {
        int totalSolutions = 0;

        // if all columns are filled, increment the total solutions count
        if (col == n) {
            return 1;
        }

        // try putting a queen in each row of the current column
        for (int i = 1; i <= n; i++) {
            // trying placing queen
            board[col] = i;

            // check if queen can be moved legally
            if (isLegalPosition(board, col + 1)) {
                totalSolutions += numSolutions(board, col + 1, n);
            }

            // reset board to backtrack and try next row
            board[col] = 0;
        }

        return totalSolutions;
    }
}
