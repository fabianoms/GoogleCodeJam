package _2017.qualification.problemA;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tests = sc.nextInt();
		for (int i = 0; i < tests; i++) {
			if (i == 18) {
				int a = 0;
			}
			char[] pancakes = sc.next().toCharArray();
			int flipper = sc.nextInt();
			int result = flipPancakes(pancakes, 0, pancakes.length -1, flipper);
			System.out.print("Case #" + (i+1) + ": ");
			if (result == -1)
				System.out.println("IMPOSSIBLE");
			else
				System.out.println(result);
		}
	}

	private static int flipPancakes(char[] pancakes, int begin, int end, int flipper) {
		if (begin > end) {
			return 0;
		}
		int flips = 0;
		if (pancakes[begin] == '-') {
			if (end - begin + 1 < flipper)
				return -1;
			for (int i = 0; i < flipper; i++) {
				pancakes[begin+i] = flipPancake(pancakes[begin+i]);
			}
			flips++;
		}
		int flipsAfter = flipPancakes(pancakes, begin + 1, end, flipper);
		if (flipsAfter == -1)
			return -1;
		return flips += flipsAfter; 
		
	}
	
	private static char flipPancake(char pancake) {
		if (pancake == '-')
			return '+';
		return '-';
	}

}
