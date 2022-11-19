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
	
	public void flipH()
	{
		for (Pixel p : this.pixeles)
		{
			p.setX(p.getX() - this.width + 1);
		}
	}
	
	public void flipV()
	{
		for (Pixel p : this.pixeles)
		{
			p.setY(p.getY() - this.height + 1);
		}
	}
	
	public void crop(int x1, int y1, int x2, int y2)
	{
		ArrayList<Pixel> newPixs = new ArrayList<Pixel>();
		for (Pixel p : this.pixeles)
		{
			if (x1 <= p.getX() && p.getX() <= x2 &&
				y1 <= p.getY() && p.getY() <= y2)
				newPixs.add(p);
		}
		this.setPixeles(newPixs);
		this.setWidth(x2 - x1 + 1);
		this.setHeight(y2 - y1 + 1);
	}
	
	public void histogram()
	{
		return;
	}
}
