CS 2223 Homework 6
Sarayu Vijayanagaram

Usage:
Compile "Queens.java" and run the main method. Some test cases are included.

QUESTION 1
The partial solution (1 6 8 3 7 0 0 0) should have isLegalPosition((1 6 8 3 7 0 0 0 0), 8) return true, while isLegalPosition((1 6 8 3 5 0 0 0), 8) returns false, This is because on the first board, there are no queens attacking each other. On the second board, the queen in column 5 is attacking the queen in column 8 diagonally.

QUESTION 2
The next legal position from a legal position will not always add a Queen to the next rank. This is because it depends on if there is an empty space available in teh next rank. If the current position already has queens in every rank, the positions of the queens already on the board have to be adjusted instead of adding another queen.
