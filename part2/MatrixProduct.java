/*
    Andrew Exton - aexton
    Jett Moy - jlmoy
    October 10th, 2018
    ALGORITHMS - Project 2 Part 1
*/

public class MatrixProduct {

    //Compute and return the product of A, B matrices using “simple” DAC algorithm presented in class.
   public static int[][] matrixProduct_DAC (int[][] A, int[][] B) throws IllegalArgumentException {
      if (validityCheck(A, B)) throw new IllegalArgumentException();

      return matrixProductRecurrsive(A, B, 0, 0, 0, 0, A.length);
   }

   private int[][] matrixProductRecurrsive(int[][] A, int startRowA, int startColA, int[][] B, int startrowB, int startcolB, int n) {
      int[][] C = new int[][];
      if (n == 1)
         C[0,0] = A[startrowA, startColA] * B[startrowoB, startcolB] // C has 1 element
      else {
         int midpoint = n/2;
         // C11 = A11 * B11 + A12 * B21
         int[][] c11 = addMatrices(matrixProductRecurrsive(A, 0, 0, B, 0, 0, midpoint), matrixProductRecurrsive(A, 0, midpoint , B, midpoint , 0, midpoint), midpoint);
         // C12 = A11 * B12 + A12 * B22
         int[][] c12 = addMatrices(matrixProductRecurrsive(A, 0, 0, B, 0, midpoint , midpoint), matrixProductRecurrsive(A, 0, midpoint , B, midpoint , midpoint , midpoint), midpoint);
         // C21 = A21 * B21 + A22 * B21
         int[][] c21 = addMatrices(matrixProductRecurrsive(A, 0, midpoint , B, 0, 0), matrixProductRecurrsive(A, midpoint , midpoint , B, midpoint , 0, midpoint), midpoint);
         // C22 = A21 * B22 + A22 * B22
         int[][] c22 = addMatrices(matrixProductRecurrsive(A, midpoint , 0, B, 0, midpoint , midpoint), matrixProductRecurrsive(A, midpoint , midpoint , B, midpoint , midpoint , midpoint), midpoint);
      }
      return C;
   }
   private int[]][] addMatrices(int [][] A, int[][] B, int n) {
      int[][] result = new int[n][n];
      for (int i = 0; i < n; i++) {
         for (int j = 0; j < n; j++) {
            result[i][j] = A[i][j] + B[i][j];
         }
      }
      return result;
   }

   private static boolean validityCheck(int[][] A, int[][]B) {
      if (A.length == B.length &&
         A[0].length == B[0].length &&
         isSquare(A.length) &&
         isSquare(A[0].length))
         return false;
      return true;
   }
   private boolean isSquare(int n) {
      double sqrt = Math.sqrt(n);
      return ((sqrt - Math.floor(sqrt)) == 0)
   }

    //Compute and return the product of A, B matrixes using Strassen’s algorithm presented in class.
   public static int[][] matrixProduct_Strassen(int[][] A, int[][] B)

}
