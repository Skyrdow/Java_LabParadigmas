package interfaz;

import java.util.ArrayList;

public class Bitmap extends Image {

	private int compBit;
	
	public Bitmap(int width, int height, ArrayList<Pixel> pixeles)
	{
		super(width, height, pixeles);
	}

	@Override
	public String histogram() {
		int ceros = 0;
		int unos = 0;
		for (Pixel p : this.getPixeles())
		{
			if (((Pixbit) p).getBit() == 0)
				ceros++;
			else if(((Pixbit) p).getBit() == 1)
				unos++;
		}
		if (ceros > unos)
			return "0/" + ceros;
		else
			return "1/" + unos;	
	}

	public void invertBit()
	{
		for (Pixel p : this.pixeles)
		{
			((Pixbit)p).setBit(Math.abs(((Pixbit)p).getBit() - 1));
		}
	}
}
