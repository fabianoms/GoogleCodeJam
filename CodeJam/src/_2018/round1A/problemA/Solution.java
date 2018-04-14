package _2018.round1A.problemA;

import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tests = sc.nextInt();
		for (int i = 0; i < tests; i++) {
			int R = sc.nextInt();
			int C = sc.nextInt();
			int H = sc.nextInt();
			int V = sc.nextInt();
			sc.nextLine();
			char[][] waffle = new char[R][C];
			for (int j = 0; j < R; j++) {
				waffle[j] = sc.nextLine().toCharArray();
			}
			int chips = countChips(waffle, R, C);
			boolean possibleCuts = makeCuts(waffle, R, C, H, V, chips);
			if (possibleCuts) {
				System.out.println("Case #" + (i + 1) + ": POSSIBLE");
			} else {
				System.out.println("Case #" + (i + 1) + ": IMPOSSIBLE");
			}
		}
		sc.close();
	}

	private static boolean makeCuts(char[][] waffle, int R, int C, int H, int V, int chips) {
		if (chips == 0)
			return true;
		if (chips % (H + 1) != 0 || chips % (V + 1) != 0)
			return false;
		if (chips % ((H + 1) * (V + 1)) != 0)
			return false;

		int chipsH = chips / (H + 1);
		int chipsV = chips / (V + 1);

		int currentChipsH = 0;
		int currentChipsV = 0;
		int vCuts = 0;
		int hCuts = 0;
		int[] hCutsMem = new int[H + 1];
		hCutsMem[H] = R - 1;
		for (int i = 0; i < R; i++) {
			boolean cutThisRow = false;
			for (int j = 0; j < C; j++) {
				if (waffle[i][j] == '@') {
					if (cutThisRow) {
						return false;
					}
					currentChipsH++;
					if (currentChipsH == chipsH) {
						hCutsMem[hCuts] = i;
						hCuts++;
						currentChipsH = 0;
						cutThisRow = true;
					}
				}
			}
			if (hCuts == H)
				break;
		}

		int[] vCutsMem = new int[V + 1];
		vCutsMem[V] = C - 1;
		for (int i = 0; i < C; i++) {
			boolean cutThisCol = false;
			for (int j = 0; j < R; j++) {
				if (waffle[j][i] == '@') {
					if (cutThisCol) {
						return false;
					}
					currentChipsV++;
					if (currentChipsV == chipsV) {
						vCutsMem[vCuts] = i;
						vCuts++;
						currentChipsV = 0;
						cutThisCol = true;
					}
				}
			}
			if (vCuts == V)
				break;
		}

		int lastVCut = 0;
		int lastHCut = 0;
		int chipsPerCell = chips / ((H + 1) * (V + 1));
		for (int i = 0; i < vCutsMem.length; i++) {
			int vCut = vCutsMem[i];
			for (int j = 0; j < hCutsMem.length; j++) {
				int hCut = hCutsMem[j];
				int chipsCounted = 0;
				for (int k = lastVCut; k <= vCut; k++) {
					for (int l = lastHCut; l <= hCut; l++) {
						if (waffle[l][k] == '@')
							chipsCounted++;
					}
				}
				lastHCut = hCut + 1;
				if (chipsCounted != chipsPerCell) {
					return false;
				}
			}
			lastVCut = vCut + 1;
			lastHCut = 0;
		}
		return true;
	}

	private static int countChips(char[][] waffle, int r, int c) {
		int chips = 0;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (waffle[i][j] == '@')
					chips++;
			}
		}
		return chips;
	}

}
