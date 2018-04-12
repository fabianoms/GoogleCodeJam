package _2017.qualification.problemD;

public enum ModelType {
	dot('.', 0), p('+', 1), x('x', 1), o('o', 2);
	
	public static ModelType getModelType(char charType) {
		switch (charType) {
		case '.':
			return ModelType.dot;
		case '+':
			return ModelType.p;
		case 'x':
			return ModelType.x;
		case 'o':
			return ModelType.o;
		default:
			return ModelType.dot;
		}
	}
	
	private char symbol;
	private int modelPoints;
	
	private ModelType(char symbol, int modelPoints) {
		this.symbol = symbol;
		this.modelPoints = modelPoints;
	}
	
	public int points() {
		return modelPoints;
	}
	
	public char symbol() {
		return symbol;
	}
}
