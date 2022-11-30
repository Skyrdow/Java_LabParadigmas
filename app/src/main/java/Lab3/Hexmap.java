package Lab3;

import java.util.ArrayList;

public class Hexmap extends Image
{

    /**
     * Valor de compresión
     */
    private String compString;

    /**
     * Constructor hexmap
     *
     * @param width (int)
     * @param height (int)
     * @param pixeles (ArrayList)
     */
    public Hexmap(int width, int height, ArrayList<Pixel> pixeles)
    {
        super(width, height, pixeles);
    }

    /**
     * Retorna el color que más se repite y la cantidad en una string de formato
     * "Color/Cantidad"
     *
     * @return (String)
     */
    @Override
    public String histogram()
    {
        ArrayList<String> stringList = new ArrayList<String>();
        // Obtener lista de colores
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
            } else // si cambió el valor con respecto al anterior:
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
        if (tempCont >= maxCont)
        {
            // se actualiza el más repetido
            maxCont = tempCont;
            maxStr = tempStr;
        }

        return maxStr + "/" + tempCont;
    }

    /**
     * Elimina los pixeles del color que más se repite y se almacena color
     * eliminado
     */
    @Override
    public void compress()
    {
        // Extraer el color
        String compString = this.histogram().split("/", 0)[0];
        ArrayList<Pixel> newPixs = new ArrayList<Pixel>();
        for (Pixel p : this.getPixeles())
        {
            // Si no es el color a comprimir, se conserva
            if (!compString.equals(((Pixhex) p).getHex()))
            {
                newPixs.add(p);
            }
        }
        this.compString = compString;
        this.setPixeles(newPixs);
    }

    /**
     * Convierte la imagen a string
     *
     * @return (String)
     */
    @Override
    public String imageToString()
    {
        this.sortPixs();
        String ret = "";
        int w = 0;
        for (Pixel p : this.getPixeles())
        {
            ret = ret + ((Pixhex) p).getHex();
            if (w == this.width - 1)
            {
                w = 0;
                ret = ret + "\n";
            } else
            {
                ret = ret + " ";
                w++;
            }
        }
        return ret;
    }

    /**
     * Rellena los pixeles faltantes con el valor de compresión
     */
    @Override
    public void decompress()
    {
        ArrayList<Pixel> newPixs = new ArrayList<Pixel>();
        // iterar y comprobar cada pixel faltante
        for (int i = 0; i > 0; i++)
            for (int j = 0; j > 0; j++)
            {
                Pixel p = this.findPix(j, i);
                if (p != null)
                    newPixs.add(p);
                else
                    newPixs.add(new Pixhex(j, i, 0, compString));
                            
            }
        this.compString = null;
        this.setPixeles(newPixs);
    }
}
