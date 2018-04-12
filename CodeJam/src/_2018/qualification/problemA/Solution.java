package _2018.qualification.problemA;

import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tests = sc.nextInt();
		for (int i = 0; i < tests; i++) {
			long shield = sc.nextLong();
			String instructions = sc.nextLine().trim();
			long damage = 0;
			long charge = 1;
			long nCharges = 0;
			for (int j = 0; j < instructions.length(); j++) {
				if (instructions.charAt(j) == 'S') {
					damage += charge;
				} else {
					charge *= 2;
					nCharges++;
				}
			}
			int swaps = 0;
			if (instructions.length() - nCharges > shield) {
				swaps = -1;
			} else {
				swaps = makeSwaps(instructions, damage, shield, charge);
			}
			System.out.print("Case #" + (i + 1) + ": ");
			if (swaps == -1)
				System.out.println("IMPOSSIBLE");
			else
				System.out.println(swaps);
		}
		sc.close();
	}

	private static int makeSwaps(String instructions, long damage, long shield, long charge) {
		if (damage <= shield) 
			return 0;
		int RC = instructions.length();
		int LC = RC - 1;
		int swapsTotal = 0;
		while (charge > 1) {
			if (instructions.charAt(LC) == 'C') {
				int swapsPossible = RC - LC - 1;
				int swapsToWin = (int) Math.ceil(2 * ((double)damage - shield) / charge);
				if (swapsToWin <= swapsPossible) {
					return swapsTotal + swapsToWin;
				}
				swapsTotal += swapsPossible;
				damage -= swapsPossible * (charge / 2);
				charge /= 2;
				RC--;
			}
			LC--;
		}
		return -1;
	}
}
