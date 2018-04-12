package _2017.qualification.problemD;

public class Model {

	private ModelType type;
	private Point position;
	private boolean upgraded;
	private boolean wasADot;

	public Model(ModelType type, int row, int column) {
		this.type = type;
		position = new Point(row, column);
		upgraded = false;
		wasADot = type == ModelType.dot;
	}
	
	public Model(Model m) {
		this.type = m.getType();
		position = m.getPosition();
		upgraded = m.upgraded();
		wasADot = m.wasADot();
	}

	public boolean upgrade() {
		if (wasADot) {
			if (type == ModelType.dot) {
				type = ModelType.p;
				upgraded = true;
			} else if (type == ModelType.p) {
				type = ModelType.x;
			} else if (type == ModelType.x) {
				type = ModelType.o;
			} else if (type == ModelType.o) {
				return false;
			}
			return true;
		} else {
			if (type == ModelType.p || type == ModelType.x) {
				type = ModelType.o;
				upgraded = true;
				return true;
			}
		}
		return false;
	}

	public boolean upgraded() {
		return upgraded;
	}

	public int stylePoints() {
		return type.points();
	}

	public int getRow() {
		return position.row();
	}

	public int getColumn() {
		return position.column();
	}

	public Point getPosition() {
		return position;
	}
	
	public char getSymbol() {
		return type.symbol();
	}
	
	public ModelType getType() {
		return type;
	}
	
	private boolean wasADot() {
		return wasADot;
	}
	
	@Override
	public String toString() {
		return getSymbol() + " " + position.row() + " " + position.column();
	}
}
