package _2017.round1A.problemB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tests = sc.nextInt();
		for (int i = 0; i < tests; i++) {
			int nIngredients = sc.nextInt();
			int nPackages = sc.nextInt();
			int[] servings = new int[nIngredients];
			for (int j = 0; j < nIngredients; j++) {
				servings[j] = sc.nextInt();
			}
			List<LinkedList<Package>> packages = new ArrayList<LinkedList<Package>>(nIngredients);
			for (int j = 0; j < nIngredients; j++) {
				LinkedList<Package> ingPackages = new LinkedList<Package>();
				packages.add(ingPackages);
				for (int k = 0; k < nPackages; k++) {
					ingPackages.add(new Package(servings[j], sc.nextInt()));
				}
//				Collections.sort(ingPackages);
				ingPackages.sort(Comparator.comparing(Package::quantity));
			}
			int kitsMade = 0;
			boolean canMakeKits = true;
			while (canMakeKits) {
				Package p = packages.get(0).peek();
				if (p == null)
					break;
				int upperBound = p.servesMax();
				int lowerBound = p.servesMin();
				int indexMinUpBound = 0;
				for (int j = 1; j < packages.size(); j++) {
					p = packages.get(j).peek();
					if (p == null) {
						canMakeKits = false;
						break;
					}
					if (upperBound == -1)
						break;
					int servesMax = p.servesMax();
					int servesMin = p.servesMin();
					if (servesMin > upperBound) {
						upperBound = -1;
					} else if (servesMax < lowerBound) {
						upperBound = -1;
						indexMinUpBound = j;
					} else {
						lowerBound = Math.max(lowerBound, servesMin);
						upperBound = Math.min(upperBound, servesMax);
						if (packages.get(indexMinUpBound).peek().servesMax() < upperBound)
							indexMinUpBound = j;
					}
				}
				if (canMakeKits) {
					if (upperBound == -1) {
						LinkedList<Package> packagesList = packages.get(indexMinUpBound);
						int minUpperBound = packagesList.removeFirst().servesMax();
						while ((!packagesList.isEmpty()) && packagesList.peek().servesMax() == minUpperBound) {
							packagesList.removeFirst();
						}
						if (packagesList.isEmpty())
							canMakeKits = false;
					} else {
						for (int j = 0; j < packages.size(); j++) {
							packages.get(j).removeFirst();
						}
						kitsMade++;
					}
				}
			}
			System.out.println("Case #" + (i + 1) + ": " + kitsMade);
		}
		sc.close();

	}
}

class Package implements Comparable<Package> {
	private int oneServingQuantity;
	private int quantity;
	private int servesMin;
	private int servesMax;

	public Package(int oneServing, int quantity) {
		oneServingQuantity = oneServing;
		this.quantity = quantity;
		servesMin = 0;
		servesMax = 0;
		calculateServes();
	}

	private void calculateServes() {
		float realPortion = quantity / (float)oneServingQuantity;
		int K = Math.round(realPortion);
		double delta = 0.00001;
		double a = ((1.1d * oneServingQuantity * K) - quantity) / (1.1d * oneServingQuantity);
		int A = (Math.ceil(a) - a < delta) ? (int)Math.ceil(a) : (int)Math.floor(a); 
		double b = ((quantity - (0.9d * oneServingQuantity * K)) / (0.9d * oneServingQuantity));
		int B = (Math.ceil(b) - b < delta) ? (int)Math.ceil(b) : (int)Math.floor(b);
		servesMin = K - A;
		servesMax = K + B;
		if (servesMin > servesMax || (quantity < oneServingQuantity && 0.9d*oneServingQuantity > quantity)) {
			servesMin = -1;
			servesMax = -1;
		}
	}
	
	public int servesMin() {
		return servesMin;
	}
	
	public int servesMax() {
		return servesMax;
	}
	
	public int quantity() {
		return quantity;
	}

	@Override
	public int compareTo(Package p) {
		if (servesMin < p.servesMin())
			return -1;
		if (servesMin > p.servesMin())
			return 1;
		return Integer.compare(servesMax, p.servesMax());
	}
}
