public class TestMatrixProduct {
    public static void main(String args[]) {
        testAddMatricesSimple();
        testAddMatricesUnitQuadrant();
        testAddMatrices();
        testAddMatrices2();

        testSubMatricesSimple();

        testConstructMatrixFromQuadrants();

        testMatrixProduct_DAC_Single();
        testMatrixProduct_DAC_UnitMats();
        testMatrixProduct_DAC_UnitMats2();
        testMatrixProduct_DAC2();
        testMatrixProduct_DAC();

    }

    public static void testMatrixProduct_DAC_Single() {
        int[][] A = { { 4 } };

        int[][] B = { { 20 } };

        int[][] expected = { { 80 } };

        int[][] result = MatrixProduct.matrixProduct_DAC(A, B);

        boolean passed = matrixEquality(result, expected);
        System.out.println("TEST MULT MATRIX SINGLE VAL MATRICES: " + (passed ? "PASSED" : "FAILED"));
    }

    public static void testMatrixProduct_DAC_UnitMats() {
        int[][] A = { { 1, 0 }, { 0, 1 } };

        int[][] result = MatrixProduct.matrixProduct_DAC(A, A);

        boolean passed = matrixEquality(result, A);
        System.out.println("TEST MULT MATRIX UNIT MATRICES: " + (passed ? "PASSED" : "FAILED"));
    }

    public static void testMatrixProduct_DAC_UnitMats2() {
        int[][] A = { { 1, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 1 } };

        int[][] result = MatrixProduct.matrixProduct_DAC(A, A);

        printMatrix(result);

        boolean passed = matrixEquality(result, A);
        System.out.println("TEST MULT MATRIX UNIT MATRICES 2: " + (passed ? "PASSED" : "FAILED"));
    }

    public static void testMatrixProduct_DAC() {
        int[][] A = { { 1, 2 }, { 3, 4 } };

        int[][] B = { { 5, 6 }, { 7, 8 } };

        int[][] expected = { { 19, 22 }, { 43, 50 } };

        int[][] result = MatrixProduct.matrixProduct_DAC(A, B);

        printMatrix(result);

        boolean passed = matrixEquality(result, expected);
        System.out.println("TEST MULT MATRIX MATRICES: " + (passed ? "PASSED" : "FAILED"));
    }

    public static void testMatrixProduct_DAC2() {
        int[][] A = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };

        int[][] B = { { 16, 15, 14, 13 }, { 12, 11, 10, 9 }, { 8, 7, 6, 5 }, { 4, 3, 2, 1 } };

        int[][] expected = { { 80, 70, 60, 50 }, { 240, 214, 188, 162 }, { 400, 358, 316, 274 },
                { 560, 502, 444, 386 } };

        int[][] result = MatrixProduct.matrixProduct_DAC(A, B);

        printMatrix(expected);
        printMatrix(result);

        boolean passed = matrixEquality(result, expected);
        System.out.println("TEST MULT MATRIX MATRICES: " + (passed ? "PASSED" : "FAILED"));
    }

    public static void testConstructMatrixFromQuadrants() {
        int[][] Q11 = { { 11, 11 }, { 11, 11 } };

        int[][] Q12 = { { 12, 12 }, { 12, 12 } };

        int[][] Q21 = { { 21, 21 }, { 21, 21 } };

        int[][] Q22 = { { 22, 22 }, { 22, 22 } };

        int[][] expected = { { 11, 11, 12, 12 }, { 11, 11, 12, 12 }, { 21, 21, 22, 22 }, { 21, 21, 22, 22 } };

        int[][] result = MatrixProduct.constructMatrixFromQuadrants(Q11, Q12, Q21, Q22);

        boolean passed = matrixEquality(result, expected);
        System.out.println("TEST CONSTRUCT MATRIX FROM QUADRANTS: " + (passed ? "PASSED" : "FAILED"));
    }

    public static void testAddMatrices() {

        int[][] A = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };

        int[][] B = { { 16, 15, 14, 13 }, { 12, 11, 10, 9 }, { 8, 7, 6, 5 }, { 4, 3, 2, 1 } };

        int[][] expected = { { 17, 17, 17, 17 }, { 17, 17, 17, 17 }, { 17, 17, 17, 17 }, { 17, 17, 17, 17 } };

        int[][] result = MatrixProduct.addMatrices(A, 0, 0, B, 0, 0, A.length);

        boolean passed = matrixEquality(result, expected);
        System.out.println("TEST ADD MATRIX: " + (passed ? "PASSED" : "FAILED"));

    }

    public static void testAddMatrices2() {

        int[][] A = { { 1, 1, 2, 2 }, { 1, 1, 2, 2 }, { 3, 3, 4, 4 }, { 3, 3, 4, 4 } };

        int[][] expected = { { 5, 5 }, { 5, 5 } };

        int mid = A.length / 2;

        int[][] result = MatrixProduct.addMatrices(A, 0, 0, A, mid, mid, A.length / 2);

        // printMatrix(result);

        boolean passed = matrixEquality(result, expected);
        System.out.println("TEST ADD MATRIX 2: " + (passed ? "PASSED" : "FAILED"));

    }

    public static void testAddMatricesUnitQuadrant() {

        boolean passed = true;
        int[][] A = { { 1, 2 }, { 3, 4 } };
        int[][] B = { { 0, 0 }, { 0, 1 } };

        int midpoint = A.length / 2;
        int[][] C = MatrixProduct.addMatrices(A, midpoint, midpoint, B, midpoint, midpoint, midpoint);

        if (C.length != 1)
            passed = false;
        if (C[0].length != 1)
            passed = false;
        if (C[0][0] != 5)
            passed = false;
        System.out.println("TEST ADD MATRIX QUAD SIMPLE 1: " + (passed ? "PASSED" : "FAILED"));

        int[][] D = MatrixProduct.addMatrices(A, 0, midpoint, B, 0, midpoint, midpoint);
        if (D[0][0] != 3)
            passed = false;
        System.out.println("TEST ADD MATRIX QUAD SIMPLE 2: " + (passed ? "PASSED" : "FAILED"));
    }

    public static void testAddMatricesSimple() {

        boolean passed = true;
        int[][] A = { { 1, 2 }, { 3, 4 } };
        int[][] B = { { 4, 3 }, { 2, 1 } };

        int[][] C = MatrixProduct.addMatrices(A, 0, 0, B, 0, 0, A.length);

        if (C.length != 2)
            passed = false;
        if (C[0].length != 2)
            passed = false;

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                if (C[i][j] != 5)
                    passed = false;
            }
        }

        System.out.println("TEST ADD MATRIX SIMPLE 1: " + (passed ? "PASSED" : "FAILED"));

        int[][] D = MatrixProduct.addMatrices(A, B, A.length);

        if (D.length != 2)
            passed = false;
        if (D[0].length != 2)
            passed = false;

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

        int[][] C = MatrixProduct.subMatrices(A, 0, 0, B, 0, 0, A.length);

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

        System.out.println("TEST SUB MATRIX SIMPLE: " + (passed ? "PASSED" : "FAILED"));

    }

    public static boolean matrixEquality(int[][] A, int[][] B) {
        boolean areEqual = true;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j] != B[i][j])
                    areEqual = false;
            }
        }
        return areEqual;
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