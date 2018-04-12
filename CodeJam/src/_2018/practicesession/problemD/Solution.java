package _2018.practicesession.problemD;

import java.util.Scanner;
import java.util.TreeMap;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		TreeMap<Long, Long> map = new TreeMap<Long, Long>();
		int tests = sc.nextInt();
		for (int i = 0; i < tests; i++) {
			long n = sc.nextLong();
			long k = sc.nextLong();
			Distances d = null;
			map.put(n, 1L);
			long counter = 0;
			while (counter < k) {
				long highest = map.lastKey();
				long occurrences = map.get(highest);
				d = getDistancesFrom(highest);
				if (!map.containsKey(d.L())) {
					map.put(d.L(), 0L);
				}
				if (!map.containsKey(d.R())) {
					map.put(d.R(), 0L);
				}
				map.put(d.L(), map.get(d.L()) + occurrences);
				map.put(d.R(), map.get(d.R()) + occurrences);
				map.remove(highest);
				counter += occurrences;
			}
			System.out.print("Case #" + (i + 1) + ": ");
			System.out.println(d.max() + " " + d.min());
			map.clear();
		}

	}

	private static Distances getDistancesFrom(long n) {
		Distances d = new Distances();
		if (n % 2 == 0) {
			d.setR(n / 2);
			d.setL(d.R() - 1);
		} else {
			d.setR((n - 1) / 2);
			d.setL(d.R());
		}
		return d;
	}
}

class Distances {
	private long l;
	private long r;

	public Distances() {
		this(0, 0);
	}

	public Distances(long l, long r) {
		this.l = l;
		this.r = r;
	}

	public long max() {
		return (l >= r) ? l : r;
	}

	public long min() {
		return (l <= r) ? l : r;
	}

	public long L() {
		return l;
	}

	public long R() {
		return r;
	}

	public void setL(long l) {
		this.l = l;
	}

	public void setR(long r) {
		this.r = r;
	}
}