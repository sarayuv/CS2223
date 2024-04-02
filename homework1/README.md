CS2223 Homework 1
Sarayu Vijayanagaram

Background:
    The real name of "Double Trouble" is Nim.
    C. L. Bouton was the person who solved it in 1902.
    It shows up in popular culture in the TV show "The Big Bang Theory". The characters play a variation of the game called "Rock, Paper, Scissors, Lizard, Spock".

Notes:
    I split my program up into 3 methods including main(). The number of sticks of each color were stored as separate integer values for simplicity. After each move, the program checks if there are 0 sticks remaining overall to determine which player made the winning move. The computerMove() method handles the winning strategies and random moves for the computer, and it returns an array of integers with the computer's move data including the color and number of sticks it wants to remove. The printBoard() method prints a visual representation of the board to the terminal with colors to enhance user experience. If I had more time on this homework, I would see how I could reorganize my code, because there is still a lot of code that is repetitive.

Running the Program:
    java doubleTrouble.java
