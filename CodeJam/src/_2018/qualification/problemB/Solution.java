package _2018.qualification.problemB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int tests = Integer.parseInt(reader.readLine());
		for (int i = 0; i < tests; i++) {
			int n = Integer.parseInt(reader.readLine());
			int oddsCapacity, evensCapacity;
			if (n % 2 == 0) {
				oddsCapacity = n / 2;
				evensCapacity = oddsCapacity;
			} else {
				oddsCapacity = (n + 1) / 2;
				evensCapacity = oddsCapacity - 1;;
			}
			List<Long> odds = new ArrayList<Long>(oddsCapacity);
			List<Long> evens = new ArrayList<Long>(evensCapacity);
			String[] seqStr = reader.readLine().split(" ");
			for (int j = 0; j < n; j+=2) {
				odds.add(Long.parseLong(seqStr[j]));
			}
			for (int j = 1; j < n; j+=2) {
				evens.add(Long.parseLong(seqStr[j]));
			}
			Collections.sort(odds);
			Collections.sort(evens);
			String result = "OK";
			for (int j = 0; j < odds.size(); j++) {
				if (evens.size() > j) {
					if (odds.get(j) > evens.get(j)) {
						result = Integer.toString(2 * j);
						break;
					}
					if (odds.size() > j + 1 && evens.get(j) > odds.get(j + 1)) {
						result = Integer.toString((2 * j) + 1);
						break;
					}
				}
			}
			System.out.println("Case #" + (i + 1) + ": " + result);
		}
		reader.close();
	}
}
