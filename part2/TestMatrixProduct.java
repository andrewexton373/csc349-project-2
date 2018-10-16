public class TestMatrixProduct {
    public static void main(String args[]) {
        testAddMatricesSimple();
        testAddMatricesQuadrant();
        testSubMatricesSimple();
    }

    public static void testAddMatricesQuadrant() {

        boolean passed = true;
        int[][] A = {{1, 2},{3, 4}};
        int[][] B = {{0, 0},{0, 1}};

        // printMatrix(A);
        // printMatrix(B);

        int midpoint = A.length/2;
        int[][] C = MatrixProduct.addMatrices(A, midpoint, midpoint, B, midpoint, midpoint, midpoint);

        // printMatrix(C);

        if (C.length != 1) passed = false;
        if (C[0].length != 1) passed = false;
        if (C[0][0] != 5) passed = false;
        System.out.println("TEST ADD MATRIX QUAD SIMPLE 1: " + (passed ? "PASSED" : "FAILED"));

        int[][] D = MatrixProduct.addMatrices(A, 0, midpoint, B, 0, midpoint, midpoint);
        if (D[0][0] != 3) passed = false;
        System.out.println("TEST ADD MATRIX QUAD SIMPLE 2: " + (passed ? "PASSED" : "FAILED"));
    }




    // public static int[][] addMatrices(
        // int [][] A, int startRowA, int startColA,
        // int[][] B, int startRowB, int startColB, int n)

    public static void testAddMatricesSimple() {

        boolean passed = true;
        int[][] A = {{1, 2},{3, 4}};
        int[][] B = {{4, 3},{2, 1}};

        // printMatrix(A);
        // printMatrix(B);

        int[][] C = MatrixProduct.addMatrices(A, 0, 0, B, 0, 0, A.length);

        // printMatrix(C);

        if (C.length != 2) passed = false;
        if (C[0].length != 2) passed = false;

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                if (C[i][j] != 5) passed = false;
            }
        }

        System.out.println("TEST ADD MATRIX SIMPLE 1: " + (passed ? "PASSED" : "FAILED"));

        int[][] D = MatrixProduct.addMatrices(A, B, A.length);

        if (D.length != 2) passed = false;
        if (D[0].length != 2) passed = false;

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                if (D[i][j] != 5)
                    passed = false;
            }
        }

        System.out.println("TEST ADD MATRIX SIMPLE 2: " + (passed ? "PASSED" : "FAILED"));

    }

    public static void testSubMatricesSimple() {

        boolean passed = true;
        int[][] A = { { 4, 4 }, { 4, 4 } };
        int[][] B = { { 2, 2 }, { 2, 2 } };

        // printMatrix(A);
        // printMatrix(B);

        int[][] C = MatrixProduct.subMatrices(A, 0, 0, B, 0, 0, A.length);

        // printMatrix(C);

        if (C.length != 2)
            passed = false;
        if (C[0].length != 2)
            passed = false;

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                if (C[i][j] != 2)
                    passed = false;
            }
        }

        System.out.println("TEST SUB MATRIX SUMPLE: " + (passed ? "PASSED" : "FAILED"));

    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.printf(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}