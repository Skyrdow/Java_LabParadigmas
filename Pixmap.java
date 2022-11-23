package interfaz;

import java.util.ArrayList;

public class Pixmap extends Image {

	public Pixmap(int width, int height, ArrayList<Pixel> pixeles) {
		super(width, height, pixeles);
	}
	
	
	public void imgRGBtoHex()
	{
		
	}

	@Override
	public String histogram() 
	{
		ArrayList<String> stringList = new ArrayList<String>();
		for (Pixel p : this.getPixeles())
		{
			stringList.add(Arrays.toString(((Pixrgb) p).getRgb()));
		}
		stringList.sort(null);	// se ordena la lista
		// se añade un null, ya que el algoritmo de histograma compara 
		// cuando el elemento de la lista cambia, sin este elemento
		// extra, el elemento final de la lista no será procesado
		stringList.add("NULL"); 
		String tempStr = stringList.get(0);
		int tempCont = 0;
		String maxStr = null;
		int maxCont = -1;
		// se itera en la lista de strings ordenada
		for (String s : stringList)	
		{
			if (s.equals(tempStr)) // si el valor no cambia aumenta el contador
			{
				tempCont++;
			}
			else // si cambió el valor con respecto al anterior:
			{
				// si el valor se repite más que el anterior más repetido
				if (tempCont >= maxCont)
				{
					// se actualiza el más repetido
					maxCont = tempCont;
					maxStr = tempStr;
				}
				// se comienzan a contar las repeticiones del valor nuevo
				tempCont = 1;  
			}
			tempStr = s; // se guarda el valor anterior para comparar
		}
		char[] rgbChars = maxStr.toCharArray();
		String maxRgb = "";
		for (char c : rgbChars)
		{
			if (Character.isDigit(c))
				maxRgb = maxRgb+c;
			else if (c == ',')
			{
				maxRgb = maxRgb + "-";
			}
			
		}
		
		String h = maxRgb + "/" + maxCont;
		return h;
	}
	
	public void invertRgb()
	{
		for (Pixel p : this.pixeles)
		{
			int[] rgb =  ((Pixrgb) p).getRgb();
			rgb[0] = Math.abs(rgb[0] - 255);
			rgb[1] = Math.abs(rgb[1] - 255);
			rgb[2] = Math.abs(rgb[2] - 255);
			((Pixrgb) p).setRgb(rgb);
		}
	}
}
