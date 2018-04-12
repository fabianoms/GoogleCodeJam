package _2017.qualification.problemD;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		PrintWriter writer = new PrintWriter("D-large-practice.out");
		int tests = sc.nextInt();
		for (int i = 0; i < tests; i++) {
			int grid = sc.nextInt();
			Stage stage = new Stage(grid);
			int models = sc.nextInt();
			for (int j = 0; j < models; j++) {
				char modelTpChar = sc.next().charAt(0);
				stage.addModel(modelTpChar, sc.nextInt(), sc.nextInt());
			}
			stage.optimize();
			List<Model> changedModels = stage.getChangedModels();
			writer.print("Case #" + (i + 1) + ": ");
			writer.println(stage.stylePoints() + " " + changedModels.size());
			Iterator<Model> modelsIt = changedModels.iterator();
			while (modelsIt.hasNext()) {
				writer.println(modelsIt.next());
			}
//			writer.print(stage);
		}
		sc.close();
		writer.close();
	}
}
