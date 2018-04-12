package _2017.qualification.problemB;

import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tests = sc.nextInt();
		for (int i = 0; i < tests; i++) {
			long number = sc.nextLong();
			number = findCloserTidyNumber(number);
			System.out.print("Case #" + (i + 1) + ": ");
			System.out.println(number);
		}
	}

	private static long findCloserTidyNumber(long number) {
		char[] digits = Long.toString(number).toCharArray();
		boolean tidyNumber = true;
		int firstDecreaseIndex = -1;
		for (int i = 0; i < digits.length; i++) {
			if (tidyNumber) {
				if ((i + 1 < digits.length) && (digits[i] > digits[i + 1])) {
					digits[i] = decreaseDigit(digits[i]);
					firstDecreaseIndex = i;
					tidyNumber = false;
				}
			} else {
				digits[i] = '9';
			}
		}
		if (!tidyNumber) {
			for (int i = firstDecreaseIndex; i > 0; i--) {
				if (digits[i] < digits[i-1]) {
					digits[i] = '9';
					digits[i-1] = decreaseDigit(digits[i-1]);
				}
			}
		}
		return Long.parseLong(String.valueOf(digits));
	}

	private static char decreaseDigit(char digit) {
		if (digit == '0')
			return '9';
		return (char) (digit - 1);
	}

}
