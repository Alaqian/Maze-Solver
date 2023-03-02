package Maze;

//Inner class PairInt
public class PairInt {
	private int x;
	private int y;

	public PairInt(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof PairInt)) {
			return false;
		}
		PairInt other = (PairInt) obj;
		return x == other.x && y == other.y;
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

	public PairInt copy() {
		return new PairInt(x, y);
	}
}