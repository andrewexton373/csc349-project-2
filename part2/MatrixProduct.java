/*
    Andrew Exton - aexton
    Jett Moy - jlmoy
    October 10th, 2018
    ALGORITHMS - Project 2 Part 1
*/

public class MatrixProduct {

    //Compute and return the product of A, B matrices using “simple” DAC algorithm presented in class.
   public static int[][] matrixProduct_DAC (int[][] A, int[][] B) throws IllegalArgumentException {
      if (!validityCheck(A, B)) throw new IllegalArgumentException();
      return matrixProductRecurrsive(A, 0, 0, B, 0, 0, A.length);
   }

   private static int[][] matrixProductRecurrsive(int[][] A, int startRowA, int startColA, int[][] B, int startRowB, int startColB, int n) {
      int[][] C = new int[A.length][A.length];
      if (n == 1)
         C[0][0] = A[startRowA][startColA] * B[startRowB][startColB]; // C has 1 element
      else {
         int midpoint = n/2;
         // C11 = A11 * B11 + A12 * B21
         int[][] C11 = addMatrices(matrixProductRecurrsive(A, 0, 0, B, 0, 0, midpoint), matrixProductRecurrsive(A, 0, midpoint , B, midpoint , 0, midpoint), midpoint);
         // C12 = A11 * B12 + A12 * B22
         int[][] C12 = addMatrices(matrixProductRecurrsive(A, 0, 0, B, 0, midpoint , midpoint), matrixProductRecurrsive(A, 0, midpoint , B, midpoint , midpoint , midpoint), midpoint);
         // C21 = A21 * B21 + A22 * B21
         int[][] C21 = addMatrices(matrixProductRecurrsive(A, 0, midpoint , B, 0, 0, midpoint), matrixProductRecurrsive(A, midpoint , midpoint , B, midpoint , 0, midpoint), midpoint);
         // C22 = A21 * B22 + A22 * B22
         int[][] C22 = addMatrices(matrixProductRecurrsive(A, midpoint , 0, B, 0, midpoint , midpoint), matrixProductRecurrsive(A, midpoint , midpoint , B, midpoint , midpoint , midpoint), midpoint);
      
        C = constructMatrixFromQuadrants(C11, C12, C21, C22);

    } 

    return C;
   }

   public static int[][] constructMatrixFromQuadrants(int[][] Q11, int[][] Q12, int[][] Q21, int[][] Q22) {
        int quadN = Q11.length;
        int[][] result  = new int[quadN*2][quadN*2];
        for (int i = 0; i < quadN; i++) {
            for (int j = 0; j < quadN; j++) {
                result[i][j] = Q11[i][j];
                result[i + quadN][j] = Q21[i][j];
                result[i][j + quadN] = Q12[i][j];
                result[i + quadN][j + quadN] = Q22[i][j];
            }
        }
        return result;
   }

   public static int[][] addMatrices(int [][] A, int[][] B, int n) {
       return addMatrices(A, 0, 0, B, 0, 0, n); // refactor to use other method
   }

   private static boolean validityCheck(int[][] A, int[][]B) {
      if (A.length == B.length &&
         A[0].length == B[0].length &&
         A.length == A[0].length &&
         isSquare(A.length) &&
         A.length % 2 == 0)
         return false;
      return true;
   }
   public static boolean isSquare(int n) {
        for (int i = 0; i < n / 2 + 2; i++) {
            if (i * i == n) {
                return true;
            }
        }
        return false;
   }

    //Compute and return the product of A, B matrixes using Strassen’s algorithm presented in class.
   public static int[][] matrixProduct_Strassen(int[][] A, int[][] B) throws IllegalArgumentException {
      if (validityCheck(A, B)) throw new IllegalArgumentException();
      int[][] C = strassenAdds(A, B, A.length);
      return C;
   }

   public static int[][] strassenAdds(int[][] A, int[][] B, int n) {
      int mid = A.length/2;
    //   s1 = B12 - B22
      int[][] s1 = addMatrices(B, 0, mid, B, mid, mid, n);
    //   s2 = A11 + A12
      int[][] s2 = addMatrices(A, 0, 0, B, 0, mid, n);
    //   s3 = A21 + A22
      int[][] s3 = addMatrices(A, mid, 0, A, mid, mid, n);
    //   S4 = B21 - B11
      int[][] s4 = subMatrices(B, mid, 0, B, 0, 0, n);
    //   s5 = A11 + A22
      int[][] s5 = addMatrices(A, 0, 0, A, mid, mid, n);
    //   s6 = B11 + B22
      int[][] s6 = addMatrices(B, 0, 0, B, mid, mid, n);
    //   s7 = A12 - A22
      int[][] s7 = subMatrices(A, 0, mid, A, mid, mid, n);
    //   s8 = B21 + B22
      int[][] s8 = addMatrices(B, mid, 0, B, mid, mid, n);
    //   s9 = A11 - A21
      int[][] s9 = subMatrices(A, 0, 0, A, mid, 0, n);
    //   s10 = B11 + B12
      int[][] s10 = addMatrices(B, 0, 0, B, 0, mid, n);

    return s1; // for now...
   }

   public static int[][] subMatrices(int [][] A, int startRowA, int startColA, int[][] B, int startRowB, int startColB, int n) {
        int[][] negatedB = new int[n][n];
        // negate B
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                negatedB[i][j] = -B[i][j]; // negate value
            }
        }
        int[][] result = addMatrices(A, startRowA, startColA, negatedB, startRowB, startColB, n);
        return result;
   }

   public static int[][] addMatrices(int [][] A, int startRowA, int startColA, int[][] B, int startRowB, int startColB, int n) {
        int[][] result = new int[n][n];  
        int colA, colB, rowA, rowB;
        colA = startColA;
        colB = startColB;
        for (int i = 0; i < n; i++) {
                rowA = startRowA;
                rowB = startRowB;
                for (int j = 0; j < n; j++) {
                    result[i][j] = A[colA][rowA] + B[colB][rowB];
                    rowA++;
                    rowB++;
                }
                colA++;
                colB++;
        }
        return result;
   }

}
