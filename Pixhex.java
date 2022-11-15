package interfaz;

public class Pixhex extends Pixel
{
	private String hex;
	
	public String getHex() {
		return hex;
	}

	public void setHex(String hex) {
		this.hex = hex;
	}

	public Pixhex(int x, int y, int depth, String hex) {
		super(x, y, depth);
		this.hex = hex;
	}

	public int getTipo() {
		return 2;
	}

}
