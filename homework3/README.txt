CS 2223 Homework 3
Sarayu Vijayanagaram

Palindrome Check:
This program was created to determine whether an input string from the keyboard in a palindrome or not. 
It is case insensitive and ignores white space and puncuation. 
It also uses a recursive function verifyPalindrome() to check the first and last characters over and 
over again.

Easy Inversion Count:
This program was created to count the number of inversions in an array with a naive O(n^2) sorting 
algorithm. It accepts user input for the elements of the array. 

Fast Inversion Count:
This program was created to count the number of inversions in an array with a O(n logn) sorting algorithm. 
For this, I used the merge sort algorithm. This also accepts user input for the elements of the array. 
I create subarrays from the original array by creating variables for the start, middle, and end of the 
original array. Once sorted, I merge the two subarrays.

Klutzomaniacs:
This program was created to generate the binary reflected gray code for a given order and outputs the 
generated code as a list of bit strings. The order of the gray code is 5. In my main method, I initialize 
the order, call for the binary reflected code to be generated, print the table header, and call of the 
subsets and actions to be printed in rows. The gray code prints nicely, however the subsets of performers 
and the actions don't seem to be working properly.
