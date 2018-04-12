package _2018.qualification.problemC;

import java.util.Scanner;

public class Solution {

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int tests = sc.nextInt();
		for (int i = 0; i < tests; i++) {
			int a = sc.nextInt();
			int width = (int) Math.ceil(Math.sqrt(a));
			int height = width;
			if (width * (width - 1) >= a) {
				height = width - 1;
			}
			if (width < 3)
				width = 3;
			if (height < 3)
				height = 3;
//			System.err.println("W = " + width + "; H = " + height);
			boolean[][] grid = new boolean[height][width];
			boolean result = prepareCells(grid/* , clusters */);
			if (!result)
				break;
		}
		sc.close();
	}

	private static boolean prepareCells(
			boolean[][] grid/* , Cluster[][] clusters */) {
//		System.err.println("TEST INIT");
//		int tries = 0;
		int fullClustersH = grid.length / 3;
		int fullClustersW = grid[0].length / 3;
		int partialClusterH = grid.length % 3;
		int partialClusterW = grid[0].length % 3;
		int centerH = 0;
		for (int row = 0; row < fullClustersH; row++) {
			centerH = (3 * row) + 1;
			int centerW = 0;
			for (int col = 0; col < fullClustersW; col++) {
				centerW = (3 * col) + 1;
				int orchardRow = centerH + 2;
				int orchardCol = centerW + 2;
				int preparedInCluster = 0;
				while (preparedInCluster < 9) {
//					tries++;
					Point cell = prepareCell(orchardRow, orchardCol);
					if (cell.row() == -1 && cell.column() == -1)
						return false;
					if (cell.row() == 0 && cell.column() == 0)
						return true;
					if (!grid[cell.row() - 2][cell.column() - 2]) {
						grid[cell.row() - 2][cell.column() - 2] = true;
						preparedInCluster++;
//						System.err.println(" Tries: " + Integer.toString(tries));
					}
				}
//				System.err.println("Cluster filled " + "(" + centerH + "," + centerW + ")");
			}
			if (partialClusterW > 0) {
				centerW += partialClusterW;
				int orchardRow = centerH + 2;
				int orchardCol = centerW + 2;
				int preparedInCluster = 0;
				while (preparedInCluster < 3 * partialClusterW) {
//					tries++;
					Point cell = prepareCell(orchardRow, orchardCol);
					if (cell.row() == -1 && cell.column() == -1)
						return false;
					if (cell.row() == 0 && cell.column() == 0)
						return true;
					if (!grid[cell.row() - 2][cell.column() - 2]) {
						grid[cell.row() - 2][cell.column() - 2] = true;
						preparedInCluster++;
//						System.err.println(" Tries: " + Integer.toString(tries));
					}
				}
//				System.err.println("Cluster filled " + "(" + centerH + "," + centerW + ")");
			}
		}
		if (partialClusterH > 0) {
			centerH += partialClusterH;
			int centerW = 0;
			for (int col = 0; col < fullClustersW; col++) {
				centerW = (3 * col) + 1;
				int orchardRow = centerH + 2;
				int orchardCol = centerW + 2;
				int preparedInCluster = 0;
				while (preparedInCluster < 3 * partialClusterH) {
//					tries++;
					Point cell = prepareCell(orchardRow, orchardCol);
					if (cell.row() == -1 && cell.column() == -1)
						return false;
					if (cell.row() == 0 && cell.column() == 0)
						return true;
					if (!grid[cell.row() - 2][cell.column() - 2]) {
						grid[cell.row() - 2][cell.column() - 2] = true;
						preparedInCluster++;
//						System.err.println(" Tries: " + Integer.toString(tries));
					}
				}
//				System.err.println("Cluster filled " + "(" + centerH + "," + centerW + ")");
			}
			if (partialClusterW > 0) {
				centerW += partialClusterW;
				int orchardRow = centerH + 2;
				int orchardCol = centerW + 2;
				int preparedInCluster = 0;
				while (preparedInCluster < 3 * partialClusterW) {
//					tries++;
					Point cell = prepareCell(orchardRow, orchardCol);
					if (cell.row() == -1 && cell.column() == -1)
						return false;
					if (cell.row() == 0 && cell.column() == 0)
						return true;
					if (!grid[cell.row() - 2][cell.column() - 2]) {
						grid[cell.row() - 2][cell.column() - 2] = true;
						preparedInCluster++;
//						System.err.println(" Tries: " + Integer.toString(tries));
					}
//					System.err.println("Cluster filled " + "(" + centerH + "," + centerW + ")");
				}
			}
		}
		return true;
	}

	private static Point prepareCell(int orchardRow, int orchardCol) {
		System.out.println(Integer.toString(orchardRow) + " " + Integer.toString(orchardCol));
		System.out.flush();
		return new Point(sc.nextInt(), sc.nextInt());
	}
}

class Point {
	private int pX;
	private int pY;

	public Point() {
		this(0, 0);
	}

	public Point(int row, int column) {
		pX = column;
		pY = row;
	}

	public int row() {
		return pY;
	}

	public int column() {
		return pX;
	}

	public void setRow(int y) {
		pY = y;
	}

	public void setColumn(int x) {
		pX = x;
	}
}
