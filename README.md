# Lab 5: Linear-Time Grouping by Digit Count

## Project Overview
This project implements an efficient, stable grouping algorithm for large sets of integers represented as base-128 byte arrays. The goal is to rearrange an array of byte arrays such that they are ordered by their number of digits (array length) in $O(n)$ time.

The project demonstrates an understanding of **Non-Comparison Sorting** and **Stability** in algorithm design, which is critical for processing data where relative order must be preserved.

## Technical Problem
Given a 2D byte array (`byte[][] arr`), where each inner array represents an integer:
1. Integers must be grouped by their digit count (length) in increasing order.
2. All integers with fewer digits must appear before those with more digits.
3. **Stability Requirement:** Integers with the same number of digits must maintain their original relative order from the input.
4. **Efficiency Requirement:** The algorithm must run in $O(n)$ time and $O(n)$ space, where $n$ is the total number of digits.



## Implementation Details

### Algorithm: Stable Counting Sort
The implementation uses a variant of Counting Sort that focuses on the `length` property of the sub-arrays rather than their numerical values.

1. **Find Maximum Length:** A linear pass through the array identifies the maximum number of digits present to size the auxiliary arrays correctly.
2. **Frequency Count:** A second pass counts the occurrences of each length.
3. **Prefix Sum (Cumulative Frequency):** The counts are transformed into prefix sums. This process defines the "boundaries" for each length group in the final sorted array.
4. **Stable Placement:** A final pass iterates **backwards** through the original array. By placing elements into their designated "bucket" starting from the end boundary and decrementing the boundary index, stability is guaranteed.
5. **In-Place Update:** Uses `System.arraycopy` to update the original array reference, ensuring the sorted data persists outside the function scope.



## Constraints
- **Non-Comparison:** No comparison operators or library sorting methods (like `Arrays.sort()`) are used in the core logic.
- **No Helper Functions:** All logic is contained within the `problem` method to adhere to the lab's strict implementation rules.
- **Memory Efficiency:** Uses jagged array initialization to handle varying integer lengths without wasting space.



## How to Run
1. Ensure you have the Java Development Kit (JDK) installed.
2. Compile the project:
   ```bash
   javac Lab5.java
