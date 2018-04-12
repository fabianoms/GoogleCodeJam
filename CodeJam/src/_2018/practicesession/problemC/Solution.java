package _2018.practicesession.problemC;

import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tests = sc.nextInt();
		for (int i = 0; i < tests; i++) {
			long destination = sc.nextLong();
			int nHorses = sc.nextInt();
			double slowerHorseTime = 0;
			int slowerHorseSpeed = 0;
			long slowerHorsePosition = 0;
			for (int j = 0; j < nHorses; j++) {
				long horsePosition = sc.nextLong();
				int horseSpeed = sc.nextInt();
				double horseTime = (destination - horsePosition) / (double)horseSpeed;
				if (horseTime > slowerHorseTime) {
					slowerHorseTime = horseTime;
					slowerHorseSpeed = horseSpeed;
					slowerHorsePosition = horsePosition;
				}
			}
			System.out.print("Case #" + (i + 1) + ": ");
			double annieSpeed = (destination * (double) slowerHorseSpeed) / (destination - slowerHorsePosition);
			System.out.println(annieSpeed);
			
		}
		sc.close();
	}

}