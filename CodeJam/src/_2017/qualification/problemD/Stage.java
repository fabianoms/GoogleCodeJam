package _2017.qualification.problemD;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class Stage {

	private boolean[][] bishopGrid;
	private boolean[][] rookGrid;

	private boolean[][] canPutBishop;
	private boolean[][] canPutRook;

	private boolean[][] modelsChanged;

	private int gridDimension;

	private List<Point> newModels;

	public Stage(int n) {
		if (n > 0) {
			gridDimension = n;
			bishopGrid = new boolean[n][n];
			rookGrid = new boolean[n][n];
			canPutBishop = new boolean[n][n];
			canPutRook = new boolean[n][n];
			modelsChanged = new boolean[n][n];
			addModelsToTheGrid();
		} else {
			throw new InvalidParameterException("N should be bigger than zero.");
		}
	}

	private void addModelsToTheGrid() {
		for (int i = 0; i < gridDimension; i++) {
			for (int j = 0; j < gridDimension; j++) {
				bishopGrid[i][j] = false;
				rookGrid[i][j] = false;
				canPutBishop[i][j] = true;
				canPutRook[i][j] = true;
				modelsChanged[i][j] = false;
			}
		}
	}

	public void addModel(char modelTypeChar, int row, int column) {
		if (modelTypeChar == 'o' || modelTypeChar == 'x') {
			rookGrid[row - 1][column - 1] = true;
			for (int i = 0; i < dimension(); i++) {
				canPutRook[i][column - 1] = false;
				canPutRook[row - 1][i] = false;
			}
		}
		if (modelTypeChar == 'o' || modelTypeChar == '+') {
			bishopGrid[row - 1][column - 1] = true;
			for (int i = 0; i < dimension(); i++) {
				int vertDistance = (i + 1) - row;
				int horizPosMainDiag = vertDistance + column;
				int horizPosSecDiag = column - vertDistance;
				if (horizPosMainDiag > 0 && horizPosMainDiag <= dimension()) {
					canPutBishop[i][horizPosMainDiag - 1] = false;
				}
				if (horizPosSecDiag > 0 && horizPosSecDiag <= dimension()) {
					canPutBishop[i][horizPosSecDiag - 1] = false;
				}
			}
		}
	}

	public int stylePoints() {
		int total = 0;
		for (int i = 0; i < gridDimension; i++) {
			for (int j = 0; j < gridDimension; j++) {
				total += (bishopGrid[i][j]) ? 1 : 0;
				total += (rookGrid[i][j]) ? 1 : 0;
			}
		}
		return total;
	}

	public int dimension() {
		return gridDimension;
	}

	public void optimize() {
		optimizeGrids();
	}

	private void optimizeGrids() {
		for (int j = 1; j < dimension() - 1; j++) {
			if (canPutBishop[0][j]) {
				addModel('+', 1, j + 1);
				modelsChanged[0][j] = true;
			}
			if (canPutBishop[dimension() - 1][j]) {
				addModel('+', dimension(), j + 1);
				modelsChanged[dimension() - 1][j] = true;
			}
		}
		if (canPutBishop[dimension() - 1][0]) {
			addModel('+', dimension(), 1);
			modelsChanged[dimension() - 1][0] = true;
		}
		if (canPutBishop[dimension() - 1][dimension() - 1]) {
			addModel('+', dimension(), dimension());
			modelsChanged[dimension() - 1][dimension() - 1] = true;
		}		
		for (int i = 0; i < dimension(); i++) {
			for (int j = 0; j < dimension(); j++) {
				if (canPutRook[i][j]) {
					addModel('x', i + 1, j + 1);
					modelsChanged[i][j] = true;
				}
			}
		}
	}

	public List<Model> getChangedModels() {
		List<Model> models = new ArrayList<Model>();
		for (int i = 0; i < gridDimension; i++) {
			for (int j = 0; j < gridDimension; j++) {
				if (modelsChanged[i][j]) {
					char modelSymbol = getModelSymbol(i + 1, j + 1);
					models.add(new Model(ModelType.getModelType(modelSymbol), i + 1, j + 1));
				}
			}
		}
		return models;
	}

	private char getModelSymbol(int row, int column) {
		int i = row - 1;
		int j = column - 1;
		if (bishopGrid[i][j] && rookGrid[i][j]) {
			return 'o';
		} else if (bishopGrid[i][j]) {
			return '+';
		} else if (rookGrid[i][j]) {
			return 'x';
		} else {
			return '.';
		}
	}

	@Override
	public String toString() {
		StringBuilder gridStr = new StringBuilder();
		for (int i = 0; i < gridDimension; i++) {
			for (int j = 0; j < gridDimension; j++) {
				char modelSymbol = getModelSymbol(i + 1, j + 1);
				gridStr.append(modelSymbol);
				gridStr.append(' ');
			}
			gridStr.deleteCharAt(gridStr.length() - 1);
			gridStr.append(System.lineSeparator());
		}
		return gridStr.toString();
	}
}
