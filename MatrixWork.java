import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

public class MatrixWork {

	public static void main(String[] args) {

		try {

			// Using Scanner for Getting Input from User 
			System.out.printf("Input File: ");
			Scanner in = new Scanner(System.in);
	
			String filename = in.nextLine();
			Scanner fileIn = new Scanner(new File(filename));
			List<int[][]> matrices = parseMatrices(fileIn);
			
			int A[][] = matrices.get(0);
			int B[][] = matrices.get(1);

			printMatrix(A);
			printMatrix(B);

			try {

				int C[][] = matrixProduct(A, B);
				printMatrix(C);

			} catch (IllegalArgumentException e) {
				System.out.println("ERROR" + e);
			}

		} catch (Exception e) {
			System.out.println("ERROR: " + e);
		}

		
	}

	// int[col][row]
	public static int[][] matrixProduct(int[][] A, int[][] B) throws IllegalArgumentException {
		if (A[0].length != B.length) throw new IllegalArgumentException();
		int n = A.length;

		int resultMatrix[][] = new int[n][n];

		return A;
	}

	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			System.out.printf("| ");
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.printf(matrix[i][j] + " ");
			}
			System.out.printf("|\n");
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
		return matrices;
	}

}
