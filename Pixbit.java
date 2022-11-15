package interfaz;

public class Pixbit extends Pixel
{
	private int bit;

	public int getBit() {
		return bit;
	}

	public void setBit(int bit) {
		this.bit = bit;
	}

	public Pixbit(int x, int y, int depth, int bit) {
		super(x, y, depth);
		this.bit = bit;
	}

	@Override
	public int getTipo() {
		return 0;
	}


}
