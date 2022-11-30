package Lab3;

import java.util.ArrayList;
import java.util.Arrays;

public class Pixmap extends Image
{

    /**
     * Valor de compresión
     */
    private int[] compRgb;

    /**
     * Constructor pixmap
     *
     * @param width (int)
     * @param height (int)
     * @param pixeles (ArrayList)
     */
    public Pixmap(int width, int height, ArrayList<Pixel> pixeles)
    {
        super(width, height, pixeles);
    }

    /**
     * Transforma el pixmap a hexmap
     *
     * @return (Hexmap)
     */
    public Image imgRGBtoHex()
    {
        return this;
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
        String maxStr = "";
        int maxCont = -1;
        // se itera en la lista de strings ordenada
        for (String s : stringList)
        {
            // si el valor no cambia aumenta el contador
            if (s.equals(tempStr))
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
        char[] rgbChars = maxStr.toCharArray();
        String maxRgb = "";
        for (char c : rgbChars)
        {
            if (Character.isDigit(c))
            {
                maxRgb = maxRgb + c;
            } else if (c == ',')
            {
                maxRgb = maxRgb + "-";
            }

        }

        String h = maxRgb + "/" + maxCont;
        return h;
    }

    /**
     * Invierte los valores de color de la imagen
     */
    public void invertRgb()
    {
        for (Pixel p : this.pixeles)
        {
            int[] rgb = ((Pixrgb) p).getRgb();
            rgb[0] = 255 - rgb[0];
            rgb[1] = 255 - rgb[1];
            rgb[2] = 255 - rgb[2];
            ((Pixrgb) p).setRgb(rgb);
        }
    }

    /**
     * Elimina los pixeles del color que más se repite y se almacena color
     * eliminado
     */
    @Override
    public void compress()
    {
        // Transformar de String "RRR-GGG-BBB/COUNT" a int[]
        String histogramStr = this.histogram().split("/", 0)[0];
        String[] rgbStr = histogramStr.split("-", 0);
        int[] compRgb =
        {
            Integer.valueOf(rgbStr[0]),
            Integer.valueOf(rgbStr[1]), Integer.valueOf(rgbStr[2])
        };

        ArrayList<Pixel> newPixs = new ArrayList<Pixel>();
        for (Pixel p : this.getPixeles())
        {
            if (!Arrays.equals(compRgb, ((Pixrgb) p).getRgb()))
            {
                newPixs.add(p);
            }
        }
        this.compRgb = compRgb;
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
        int i = 0;
        for (Pixel p : this.getPixeles())
        {
            ret = ret + Arrays.toString(((Pixrgb) p).getRgb());
            if (i == this.width - 1)
            {
                i = 0;
                ret = ret + "\n";
            } else
            {
                ret = ret + " ";
                i++;
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
                    newPixs.add(new Pixrgb(j, i, 0, compRgb));
                            
            }
        this.compRgb = null;
        this.setPixeles(newPixs);
    }
}
