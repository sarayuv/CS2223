CS 2223 Homework 4
Sarayu Vijayanagaram

QUESTION 1:
Explain why the (repaired) ForwardElimination algorithm on page 210 of Levitin fails to provide a solution for:
    x_1 + x_2 + x_3 = 6
    x_1 + x_2 + 2x_3 = 9
    x_1 + 2x_2 + 3x_3 = 14
despite the fact that x = (1, 2, 3) or x_1 = 1, x_2 = 2, x_3 = 3 can be easily verified as a solution to the system.
>> The algorithm fails to provide a solution for the system because of the division inside the nested loop. For the line "A[j, k] / A[i, i]", it is assumed the A[i, i] is always nonzero, but it is not necessarily. When trying to eliminate the second row, there will end up being a division by zero.

How does the BetterForwardElimination algorithm on page 211 of Levitin remedy this?
>> Before starting to eliminate, the function iterates through the rows below the pivot to find the row with the largest absolute value in the column with the pivot. This reduces the likelihood of division by zero because the pivot element chosen for each elimination step is nonzero.

QUESTION 2:
Explain in some detail why the BetterForwardElimination algorithm on page 211 of Levitin fails to provide a solution for:
    x_1 + x_2 + x_3 = 6
    x_1 + x_2 + 2x_3 = 9
    2x_1 + 2x_2 + 3x_3 = 15

despite the fact that x = (1, 2, 3) or x_1 = 1, x_2 = 2, x_3 = 3 can be easily veriﬁed as a solution to the system.
>> Partial pivoting before Gaussian Elimination is done and there is an iteration through the row below the current pivot to find the row with the largest absolute value in the column with the pivot. However, a solution cannot be found this way because of the third equation. The coefficients "2" are twice the corresponding coefficients in the first two equations. This means that the equations are not independent and we cannot find a unique solution using Gaussian elimination.

What can be done to remedy this shortcoming in the algorithm?
>> A different algorithm can be used such as LU decomposition. This method factors the coefficient matrix into the product of a lower and upper triangular matrix. Partial pivoting is still included, and we still have to ensure that the pivot elements are nonzero and have the largest absolute value in their columns. Using the decomposition, we have to solve the system of equations with forward and backward substition (Ly = b and Ux = y).
