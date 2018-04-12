package _2017.qualification.problemD;

public class Point {
	private int pX;
	private int pY;
	
	public Point() {
		this(0, 0);
	}
	
	public Point(int row, int column) {
		pX = column;
		pY = row;
	}
	
	public int row() {
		return pY;
	}
	
	public int column() {
		return pX;
	}
	
	public void setRow(int y) {
		pY = y;
	}
	
	public void setColumn(int x) {
		pX = x;
	}
}
