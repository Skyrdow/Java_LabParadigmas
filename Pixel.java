package interfaz;

public class Pixel
{
	private int x;
	private int y;
	private int depth;
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	public Pixel(int x, int y, int depth) {
		this.x = x;
		this.y = y;
		this.depth = depth;
	}

	public int getTipo() {
		return -1; // pixel sin tipo
	}
	
	public Pixel copyToWhite() {
		return null;
	}
}
