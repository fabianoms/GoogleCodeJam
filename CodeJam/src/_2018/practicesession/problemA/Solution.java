package _2018.practicesession.problemA;

import java.util.Scanner;

public class Solution {

	private enum JudgesAnswer {
		CORRECT, TOO_SMALL, TOO_BIG, WRONG_ANSWER, OTHER
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tests = sc.nextInt();
		for (int i = 0; i < tests; i++) {
			long a = sc.nextLong() + 1;
			long b = sc.nextLong() + 1;
			sc.nextLine();
			sc.nextLine();
			JudgesAnswer answer = JudgesAnswer.OTHER;
			do {
				long p = nextCandidateBS(a, b);
				System.out.println(p);
				System.out.flush();
				answer = JudgesAnswer.valueOf(sc.nextLine());
				if (answer == JudgesAnswer.TOO_BIG) {
					b = p; 
				} else if (answer == JudgesAnswer.TOO_SMALL) {
					a = p + 1;
				}
			} while (answer != JudgesAnswer.WRONG_ANSWER && answer != JudgesAnswer.CORRECT);
			
			if (answer == JudgesAnswer.WRONG_ANSWER)
				break;
		}
		sc.close();
	}

	private static long nextCandidateBS(long a, long b) {
		return (a + b) / 2;
	}
}