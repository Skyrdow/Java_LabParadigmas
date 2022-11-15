package interfaz;

public class Pixrgb extends Pixel
{
	private int[] rgb = new int[3];
	
	public int[] getRgb() {
		return rgb;
	}

	public void setRgb(int[] rgb) {
		this.rgb[0] = rgb[0];
		this.rgb[1] = rgb[1];
		this.rgb[2] = rgb[2];
	}

	public Pixrgb(int x, int y, int depth, int[] rgb) {
		super(x, y, depth);
		this.rgb[0] = rgb[0];
		this.rgb[1] = rgb[1];
		this.rgb[2] = rgb[2];
	}

	@Override
	public int getTipo() {
		return 1;
	}
}
