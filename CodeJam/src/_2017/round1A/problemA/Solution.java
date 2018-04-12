package _2017.round1A.problemA;

import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tests = sc.nextInt();
		for (int i = 0; i < tests; i++) {
			int R = sc.nextInt();
			int C = sc.nextInt();
			sc.nextLine();
			char[][] cake = new char[R][C];
			for (int j = 0; j < R; j++) {
				char[] cakeLine = sc.nextLine().toCharArray();
				for (int k = 0; k < cakeLine.length; k++) {
					cake[j][k] = cakeLine[k];
				}
			}
			splitCake(cake, R, C);
			System.out.println("Case #" + (i + 1) + ":");
			for (int j = 0; j < R; j++) {
				for (int k = 0; k < C; k++) {
					System.out.print(cake[j][k]);
				}
				System.out.println();
			}
		}
		sc.close();
	}

	private static void splitCake(char[][] cake, int R, int C) {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				makeHPieceOfCake(cake, R, C, i, j);
			}
		}
		for (int i = 1; i < R; i++) {
			if (cake[i][0] == '?') {
				for (int j = 0; j < C; j++) {
					cake[i][j] = cake[i-1][j];
				}
			}
		}
		for (int i = R - 1; i >= 0; i--) {
			if (cake[i][0] == '?') {
				for (int j = 0; j < C; j++) {
					cake[i][j] = cake[i+1][j];
				}
			}
		}
	}

	private static void makeHPieceOfCake(char[][] cake, int R, int C, int i, int j) {
		for (int c = j + 1; c < C; c++) {
			if (cake[i][c] == '?') {
				cake[i][c] = cake[i][j];
			} else
				break;
		}
		for (int c = j - 1; c >= 0; c--) {
			if (cake[i][c] == '?') {
				cake[i][c] = cake[i][j];
			} else
				break;
		}
	}
}
