/*
    Andrew Exton - aexton
    Jett Moy - jlmoy
    October 10th, 2018
    ALGORITHMS - Project 2 Part 1
*/

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

public class MatrixWork {
	public static void main(String[] args) {

		// runTests();

		try {

			// Using Scanner for Getting Input from User
			System.out.printf("Input File: ");
			Scanner in = new Scanner(System.in);

			String filename = in.nextLine();
			in.close();

			System.out.println();

			Scanner fileIn = new Scanner(new File(filename));
			List<int[][]> matrices = parseMatrices(fileIn);
			fileIn.close();

			int A[][] = matrices.get(0);
			int B[][] = matrices.get(1);

			try {

				int C[][] = matrixProduct(A, B);
				printMatrix(C);

			} catch (IllegalArgumentException e) {
				System.out.println("The given two matrices are incompatible!");
			}

		} catch (Exception e) {
			System.out.println("ERROR: " + e);
		}


	}

	// int[col][row]
	public static int[][] matrixProduct(int[][] A, int[][] B) throws IllegalArgumentException {
      	int Arows = A[0].length, Bcols = B.length;
		if (Arows != Bcols) throw new IllegalArgumentException();

		int n = A.length;
		int m = B[0].length;
		int p = A[0].length;

		int resultMatrix[][] = new int[n][m];
		for (int i=0; i < n; i++) Arrays.fill(resultMatrix[i], 0);

		for (int i=0; i < n; i++) {
			for (int j=0; j < m; j++) {
				for (int k=0; k < p; k++) {
					resultMatrix[i][j] = resultMatrix[i][j] + (A[i][k] * B[k][j]);
				}
			}
		}

		return resultMatrix;
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

	public static List<int[][]> parseMatrices(Scanner in) {
		List<int[][]> matrices = new ArrayList<int[][]>(2);

		for (int i = 0; i < 2; i++) {
			int rows = in.nextInt();
			int cols = in.nextInt();

			int matrix[][] = new int[rows][cols];

			for (int j = 0; j < rows; j++) {
				for (int k = 0; k < cols; k++) {
					matrix[j][k] = in.nextInt();
				}
			}
			matrices.add(i, matrix);
		}
      printMatrix(matrices.get(0));
      printMatrix(matrices.get(1));
		return matrices;
	}

	public static boolean areMatricesEqual(int[][] A, int[][] B) {
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[0].length; j++) {
				if (A[i][j] != B[i][j]) return false;
			}
		}
		return true;
	}

	public static void runTests() {
		for (int testNum = 1; testNum <= 9; testNum++) {

			try {

				System.out.println("TEST " + testNum + " OUTPUT:");

				Scanner fileIn = new Scanner(new File("tests/test" + testNum));
				List<int[][]> matrices = parseMatrices(fileIn);
				fileIn.close();

				int A[][] = matrices.get(0);
				int B[][] = matrices.get(1);

				try {

					int C[][] = matrixProduct(A, B);
					printMatrix(C);

				} catch (IllegalArgumentException e) {
					System.out.println("The given two matrices are incompatible!");
				}

			} catch (Exception e) {
				System.out.println(e);
			}

		}
	}

}
