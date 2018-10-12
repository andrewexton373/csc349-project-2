/*
    Andrew Exton - aexton
    Jett Moy - jlmoy
    October 10th, 2018
    ALGORITHMS - Project 2 Part 1
*/

public class MatrixProduct {

    //Compute and return the product of A, B matrices using “simple” DAC algorithm presented in class.
   public static int[][] matrixProduct_DAC(int[][] A, int[][] B) {
      if (validityCheck(A, B)) throw new IllegalArgumentException();

      return matrixProductRecurrsive(A, B, 0, 0, 0, 0, A.length);
   }

   private int[][] matrixProductRecurrsive(int[][] A, int startRowA, int startColA, int[][] B, int startrowB, int startcolB, int n) {
      int[][] C = new int[][];
      if (n == 1)
         C[1,1] = A[startrowA, startColA] * B[startrowoB, startcolB] // C has 1 element
      else {
         // C11 = A11 * B11 + A12 * B21
         int[][] c11 = matrixProductRecurrsive();
         // C12 = A11 * B12 + A12 * B22
         int[][] c12 = matrixProductRecurrsive();
         // C21 = A21 * B21 + A22 * B21
         int[][] c21 = matrixProductRecurrsive();
         // C22 = A21 * B22 + A22 * B22
         int[][] c22 = matrixProductRecurrsive();
      }
      return C;
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
