package lab4;

import java.util.ArrayList;

public class Hexmap extends Image {

	private String compString;
	public Hexmap(int width, int height, ArrayList<Pixel> pixeles) {
		super(width, height, pixeles);
	}

	@Override
	public String histogram() 
	{
		ArrayList<String> stringList = new ArrayList<String>();
		for (Pixel p : this.getPixeles())
		{
			stringList.add(((Pixhex) p).getHex());
		}
		stringList.sort(null);	// se ordena la lista
		System.out.println(stringList.toString());
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

		return maxStr + "/" + tempCont;
	}
	
	@Override
	public void compress()
	{
		String compString = this.histogram().split("/", 0)[0];
		ArrayList<Pixel> newPixs = new ArrayList<Pixel>();
		for(Pixel p : this.getPixeles())
		{
			if (!compString.equals(((Pixhex)p).getHex()))
				newPixs.add(p);
		}
		this.compString = compString;
		this.setPixeles(newPixs);
	}
	
}