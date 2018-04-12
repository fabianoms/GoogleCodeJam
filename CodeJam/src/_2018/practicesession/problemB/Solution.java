package _2018.practicesession.problemB;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tests = sc.nextInt();
		for (int i = 0; i < tests; i++) {
			int parties = sc.nextInt();
			List<Party> partiesList = new ArrayList<Party>(parties);
			int remaining = 0;
			for (int j = 0; j < parties; j++) {
				int partyRemaining = sc.nextInt();
				partiesList.add(new Party((char) ('A' + j), partyRemaining));
				remaining += partyRemaining;
			}
			partiesList.sort(Comparator.comparingInt(Party::remaining).reversed());
			System.out.print("Case #" + (i + 1) + ": ");
			evacuateSenators(partiesList, 0, 0, parties, remaining);
			System.out.println();

		}
		sc.close();
	}

	private static int evacuateSenators(List<Party> partiesList, int pivot, int remainingToWait, int parties,
			int remainingSenators) {
		int evacuated = 0;
		if (pivot >= parties && remainingToWait >= 0)
			return evacuated;
		while (partiesList.get(pivot).remaining() > remainingToWait) {
			int evacOneThreshold = (remainingSenators - evacuated - 1) / 2;
			int evacTwoThreshold = (remainingSenators - evacuated - 2) / 2;
			int nRemoves = 2;
			boolean hasNext = pivot + 1 < parties;
			if (hasNext) {
				if (partiesList.get(pivot + 1).remaining() > evacTwoThreshold) {
					nRemoves--;
					if (partiesList.get(pivot + 1).remaining() > evacOneThreshold) {
						nRemoves--;
					}
				}
			}
			if (nRemoves > 0) {
				for (int i = 0; i < nRemoves; i++) {
					if (partiesList.get(pivot).hasSenators() && partiesList.get(pivot).remaining() > remainingToWait) {
						System.out.print(partiesList.get(pivot).evacuateSenator());
						evacuated++;
					}
				}
				System.out.print(' ');
			} else {
				if (partiesList.get(pivot).hasSenators() && partiesList.get(pivot).remaining() > remainingToWait) {
					System.out.print(partiesList.get(pivot).evacuateSenator());
					evacuated++;
				}
				if (partiesList.get(pivot + 1).hasSenators()) {
					System.out.print(partiesList.get(pivot + 1).evacuateSenator());
					evacuated++;
				}
				System.out.print(' ');
				evacuated += evacuateSenators(partiesList, pivot + 1, partiesList.get(pivot).remaining(), parties,
						remainingSenators - evacuated);
			}
		}
		evacuated += evacuateSenators(partiesList, pivot + 1, partiesList.get(pivot).remaining(), parties,
				remainingSenators - evacuated);
		return evacuated;
	}
}

class Party {
	private char name;
	private int senators;

	public Party(char name, int senatorsInTheRoom) {
		this.name = name;
		senators = senatorsInTheRoom;
	}

	public int remaining() {
		return senators;
	}

	public boolean hasSenators() {
		return senators > 0;
	}

	public char evacuateSenator() {
		senators--;
		return name;
	}
}