/*
    Andrew Exton - aexton
    Jett Moy - jlmoy
    October 10th, 2018
    ALGORITHMS - Project 2 Part 2
*/

import java.lang.Math;

public class MatrixProduct {

    // Compute and return the product of A, B matrices using “simple” DAC algorithm
    // presented in class.
    public static int[][] matrixProduct_DAC(int[][] A, int[][] B) throws IllegalArgumentException {
        System.out.println("CHECK: " + validityCheck(A, B));
        if (!validityCheck(A, B))
            throw new IllegalArgumentException();
        return matrixProductRecurrsive(A, 0, 0, B, 0, 0, A.length);
    }

    private static int[][] matrixProductRecurrsive(int[][] A, int startRowA, int startColA, int[][] B, int startRowB,
            int startColB, int n) {
        int[][] C = new int[n][n];
        if (n == 1) {
            int a = A[startRowA][startColA];
            int b = B[startRowB][startColB];
            C[0][0] = a * b;
        } else {
            int midpoint = n / 2;

            System.out.println("MID: " + midpoint);

            // C11 = A11 * B11 + A12 * B21
            int[][] C11 = addMatrices(
                    matrixProductRecurrsive(A, startRowA, startColA, B, startRowB, startColB, midpoint),
                    matrixProductRecurrsive(A, startRowA, startColA + midpoint, B, startRowB + midpoint, startColB,
                            midpoint),
                    midpoint);

            // C12 = A11 * B12 + A12 * B22
            int[][] C12 = addMatrices(
                    matrixProductRecurrsive(A, startRowA, startColA, B, startRowB, startColB + midpoint, midpoint),
                    matrixProductRecurrsive(A, startRowA, startColA + midpoint, B, startRowB + midpoint,
                            startColB + midpoint, midpoint),
                    midpoint);

            System.out.println("C12: ");
            TestMatrixProduct.printMatrix(C12);

            // C21 = A21 * B11 + A22 * B21
            int[][] C21 = addMatrices(
                    matrixProductRecurrsive(A, startRowA + midpoint, startColA, B, startRowB, startColB, midpoint),
                    matrixProductRecurrsive(A, startRowA + midpoint, startColA + midpoint, B, startRowB + midpoint,
                            startColB, midpoint),
                    midpoint);

            System.out.println("C21: ");
            TestMatrixProduct.printMatrix(C21);

            // C22 = A21 * B12 + A22 * B22
            int[][] C22 = addMatrices(
                    matrixProductRecurrsive(A, startRowA + midpoint, startColA, B, startRowB, startColB + midpoint,
                            midpoint),
                    matrixProductRecurrsive(A, startRowA + midpoint, startColA + midpoint, B, startRowB + midpoint,
                            startColB + midpoint, midpoint),
                    midpoint);

            System.out.println("C22: ");
            TestMatrixProduct.printMatrix(C22);

            C = constructMatrixFromQuadrants(C11, C12, C21, C22);

        }

        return C;
    }

    public static int[][] constructMatrixFromQuadrants(int[][] Q11, int[][] Q12, int[][] Q21, int[][] Q22) {
        int quadN = Q11.length;
        int[][] result = new int[quadN * 2][quadN * 2];
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

    // Compute and return the product of A, B matrixes using Strassen’s algorithm
    // presented in class.
    public static int[][] matrixProduct_Strassen(int[][] A, int[][] B) throws IllegalArgumentException {
        if (!validityCheck(A, B))
            throw new IllegalArgumentException();
        // int[][] C = strassenAdds(A, B, A.length);
        System.out.println("N:" + A.length);
        int[][] C = strassenSP(A, 0, 0, B, 0, 0, A.length);

        // strassenP();
        // strassenC();
        return C;
    }

    private static int[][] strassenSP(int[][] A, int startRowA, int startColA, int[][] B, int startRowB, int startColB, int n) {
        int[][] C = new int[n][n];
        if (n == 1) {
            int a = A[startRowA][startColA];
            int b = B[startRowB][startColB];
            C[0][0] = a * b;
        } else {
            int mid = n / 2;
            System.out.println(mid);
            // s1 = B12 - B22
            int[][] s1 = subMatrices(B, startRowB, startColB + mid, B, startRowB + mid, startColB + mid, mid);            
            // s2 = A11 + A12
            int[][] s2 = addMatrices(A, startRowA, startColA, A, startRowA, startColA + mid, mid);
            // s3 = A21 + A22
            int[][] s3 = addMatrices(A, startRowA + mid, startColA, A, startRowA + mid, startColA + mid, mid);
            // S4 = B21 - B11
            int[][] s4 = subMatrices(B, startRowB + mid, startColB, B, startRowB, startColB, mid);
            // s5 = A11 + A22
            int[][] s5 = addMatrices(A, startRowA, startColA, A, startRowA + mid, startColA + mid, mid);
            // s6 = B11 + B22
            int[][] s6 = addMatrices(B, startRowB, startColB, B, startRowB + mid, startColB + mid, mid);
            // s7 = A12 - A22
            int[][] s7 = subMatrices(A, startRowA, startColA + mid, A, startRowA + mid, startColA + mid, mid);
            // s8 = B21 + B22
            int[][] s8 = addMatrices(B, startRowB + mid, startColB, B, startRowB + mid, startColB + mid, mid);
            // s9 = A11 - A21
            int[][] s9 = subMatrices(A, startRowA, startColA, A, startRowA + mid, startColA, mid);
            // s10 = B11 + B12
            int[][] s10 = addMatrices(B, startRowB, startColB, B, startRowB, startColB + mid, mid);

            // System.out.println("Ss");
            // TestMatrixProduct.printMatrix(s1);
            // TestMatrixProduct.printMatrix(s2);
            // TestMatrixProduct.printMatrix(s3);
            // TestMatrixProduct.printMatrix(s4);
            // TestMatrixProduct.printMatrix(s5);
            // TestMatrixProduct.printMatrix(s6);
            // TestMatrixProduct.printMatrix(s7);
            // TestMatrixProduct.printMatrix(s8);           
            // TestMatrixProduct.printMatrix(s9);
            // TestMatrixProduct.printMatrix(s10);

            // p1 = A11 * S1
            int[][] p1 = strassenSP(A, startRowA, startColA, s1, 0, 0, mid);
            // p2 = s2 * B22
            int[][] p2 = strassenSP(s2, 0, 0, B, startRowB + mid, startColB + mid, mid);
            // p3 = s3 * B11
            int[][] p3 = strassenSP(s3, 0, 0, B, startRowB, startColB, mid);
            // p4 = A22 * s4
            int[][] p4 = strassenSP(A, startRowA + mid, startColA + mid, s4, 0, 0, mid);
            // p5 = s5 * s6
            int[][] p5 = strassenSP(s5, 0, 0, s6, 0, 0, mid);
            // p6 = s7 * s8
            int[][] p6 = strassenSP(s7, 0, 0, s8, 0, 0, mid);
            // p7 = s9 * s10
            int[][] p7 = strassenSP(s9, 0, 0, s10, 0, 0, mid);

            int[][] c111 = addMatrices(p5, p4, mid);
            int[][] c112 = subMatrices(c111, p2, mid);
            int[][] c113 = addMatrices(c112, p6, mid);
            int[][] C11 = c113;

            int[][] C12 = addMatrices(p1, p2, mid);

            int[][] C21 = addMatrices(p3, p4, mid);

            int[][] c221 = addMatrices(p5, p1, mid);
            int[][] c222 = subMatrices(c221, p3, mid);
            int[][] c223 = subMatrices(c222, p7, mid);
            int[][] C22 = c223;

            C = constructMatrixFromQuadrants(C11, C12, C21, C22);
        }

        return C;
    }

    private static int[][] strassenAdds(int[][] A, int[][] B, int n) {
        int mid = A.length / 2;
        // s1 = B12 - B22
        int[][] s1 = addMatrices(B, 0, mid, B, mid, mid, mid);
        // s2 = A11 + A12
        int[][] s2 = addMatrices(A, 0, 0, B, 0, mid, mid);
        // s3 = A21 + A22
        int[][] s3 = addMatrices(A, mid, 0, A, mid, mid, mid);
        // S4 = B21 - B11
        int[][] s4 = subMatrices(B, mid, 0, B, 0, 0, mid);
        // s5 = A11 + A22
        int[][] s5 = addMatrices(A, 0, 0, A, mid, mid, mid);
        // s6 = B11 + B22
        int[][] s6 = addMatrices(B, 0, 0, B, mid, mid, mid);
        // s7 = A12 - A22
        int[][] s7 = subMatrices(A, 0, mid, A, mid, mid, mid);
        // s8 = B21 + B22
        int[][] s8 = addMatrices(B, mid, 0, B, mid, mid, mid);
        // s9 = A11 - A21
        int[][] s9 = subMatrices(A, 0, 0, A, mid, 0, mid);
        // s10 = B11 + B12
        int[][] s10 = addMatrices(B, 0, 0, B, 0, mid, mid);

        return s1;
    }

    private int[][] strassenP(int[][] A, int[][] B, int n) {
        int[][] C = new int[n][n];
        // // p1 = A11 * S1
        // int[][] p1 = matrixProduct_Strassen();
        // // p2 = s2 * B22
        // int[][] p1 = matrixProduct_Strassen();
        // // p3 = s3 * B11
        // int[][] p1 = matrixProduct_Strassen();
        // // p4 = A22 * s4
        // int[][] p1 = matrixProduct_Strassen();
        // // p5 = s5 * s6
        // int[][] p1 = matrixProduct_Strassen();
        // // p6 = s7 * s8
        // int[][] p1 = matrixProduct_Strassen();
        // // p7 = s9 * s10
        // int[][] p1 = matrixProduct_Strassen();
        return C;
    }

    private int[][] strassenC(int[][] A, int[][] B, int n) {
        int[][] C = new int[n][n];
        // C11 = p5 + p4 - p2 + p6
        // C12 = p1 + p2
        // C21 = p3 + p4
        // C22 = p5 + p1 - p3 - p7
        return C;
    }

    private static boolean validityCheck(int[][] A, int[][] B) {
        if (A.length == B.length && A[0].length == B[0].length && A.length == A[0].length && isPow2(A.length)) {
            return true; // is Valid
        } else {
            return false; // is inValid
        }
    }

    // For adding whole matrices
    public static int[][] addMatrices(int[][] A, int[][] B, int n) {
        return addMatrices(A, 0, 0, B, 0, 0, n); // refactor to use other method
    }

    // For adding 1 quadrant from each of 2 matrices
    public static int[][] addMatrices(int[][] A, int startRowA, int startColA, int[][] B, int startRowB, int startColB,
            int n) {
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

    // For subtracting whole matrices
    public static int[][] subMatrices(int[][] A, int[][] B, int n) {
        return subMatrices(A, 0, 0, B, 0, 0, n); // refactor to use other method
    }

    public static int[][] subMatrices(int[][] A, int startRowA, int startColA, int[][] B, int startRowB, int startColB,
            int n) {
        int[][] negatedB = negateMatrix(B);
        int[][] result = addMatrices(A, startRowA, startColA, negatedB, startRowB, startColB, n);
        return result;
    }

    public static int[][] negateMatrix(int[][] matrix) {
        int n = matrix.length;
        int[][] negatedMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                negatedMatrix[i][j] = -matrix[i][j]; // negate value
            }
        }
        return negatedMatrix;
    }

    public static boolean isPow2(int n) {
        if (n == 0)
            return false;

        while (n != 1) {
            if (n % 2 != 0)
                return false;
            n = n / 2;
        }

        return true;
    }

}
