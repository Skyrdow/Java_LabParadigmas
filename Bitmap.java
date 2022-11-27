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
	
	@Override
	public void compress()
	{
		int compBit = Integer.valueOf(this.histogram().split("/", 0)[0]);
		ArrayList<Pixel> newPixs = new ArrayList<Pixel>();
		for(Pixel p : this.getPixeles())
		{
			if (compBit != ((Pixbit)p).getBit())
				newPixs.add(p);
		}
		this.compBit = compBit;
		this.setPixeles(newPixs);
	}
	@Override
	public String imageToString()
	{
		this.sortPixs();
		String ret = "";
		int w = 0;
		for(Pixel p : this.getPixeles())
		{
			ret = ret + Integer.toString(((Pixbit) p).getBit());
			if (w == this.width - 1)
			{
				w = 0;
				ret = ret + "\n";
			}
			else
			{
				ret = ret + " ";
				w++;
			}
		}
		return ret;
	}
	
	@Override
	public void decompress()
	{
		ArrayList<Pixel> newPixs = new ArrayList<Pixel>();
		for (int i = 0; i < this.height; i++)
		{
			for (int j = 0; j < this.width; j++)
			{
				Pixel p = findPix(j, i);
				if (p != null)
				{
					Pixbit decompressedPix = new Pixbit(j, i, 0, this.compBit);
					newPixs.add(decompressedPix);
				}
				else
					newPixs.add(p);
			}
		}
		this.compBit = -1;
		this.setPixeles(newPixs);
	}

	
}
