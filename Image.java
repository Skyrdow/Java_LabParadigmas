package interfaz;

import java.util.ArrayList;

public class Image {
	public int width;
	public int height;
	private ArrayList<Pixel> pixeles = new ArrayList<Pixel>();

	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public ArrayList<Pixel> getPixeles() {
		return pixeles;
	}
	public void setPixeles(ArrayList<Pixel> pixeles) {
		this.pixeles = pixeles;
	}
	
	public Image(int width, int height, ArrayList<Pixel> pixeles) {
		this.width = width;
		this.height = height;
		this.pixeles = pixeles;
	}
	
	
	public boolean isBitmap()
	{
		for (Pixel p : this.pixeles)
		{
			if (p.getTipo() != 0)
			{
				return false;
			}
		}
		return true;
	}
	
	public boolean isPixmap()
	{
		for (Pixel p : this.pixeles)
		{
			if (p.getTipo() != 1)
			{
				return false;
			}
		}
		return true;
	}

	public boolean isHexmap()
	{
		for (Pixel p : this.pixeles)
		{
			if (p.getTipo() != 2)
			{
				return false;
			}
		}
		return true;
	}
	
	public boolean isCompressed()
	{
		if (this.pixeles.size() == (this.height * this.width))
		{
			return true;
		}
		return false;
	}
	
}
