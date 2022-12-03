package Lab3;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Clase que hereda de imagen y sobreescribe los metodos que dependen del tipo
 * de imagen específico. Se usan 3 valores enteros para el valor de color.
 */
public class Pixmap_21266659_MesiasSoza extends Image_21266659_MesiasSoza
{

    /**
     * Valor de compresiï¿½n
     */
    private int[] compRgb;

    /**
     * Constructor pixmap
     *
     * @param width (int)
     * @param height (int)
     * @param pixeles (ArrayList)
     */
    public Pixmap_21266659_MesiasSoza(int width, int height, ArrayList<Pixel_21266659_MesiasSoza> pixeles)
    {
        super(width, height, pixeles);
    }

    /**
     * Transforma el pixmap a hexmap
     *
     * @return (Hexmap_21266659_MesiasSoza)
     */
    public Image_21266659_MesiasSoza imgRGBtoHex()
    {
        ArrayList<Pixel_21266659_MesiasSoza> newPixs = new ArrayList<>();
        int rgb[];
        String hex = "#";
        for (Pixel_21266659_MesiasSoza p : this.pixeles)
        {
            rgb = ((Pixrgb_21266659_MesiasSoza) p).getRgb();
            for (int val : rgb)
            {
                if (val < 10)
                {
                    hex += "0" + Integer.toHexString(val);
                } else
                {
                    hex += Integer.toHexString(val);
                }
            }
            newPixs.add(new Pixhex_21266659_MesiasSoza(p.getX(), p.getY(), p.getDepth(), hex));
        }
        return new Hexmap_21266659_MesiasSoza(this.width, this.width, newPixs);
    }

    /**
     * Retorna el color que mï¿½s se repite y la cantidad en una string de
     * formato "Color/Cantidad"
     *
     * @return (String)
     */
    @Override
    public String histogram()
    {
        ArrayList<String> stringList = new ArrayList<String>();
        for (Pixel_21266659_MesiasSoza p : this.pixeles)
        {
            stringList.add(Arrays.toString(((Pixrgb_21266659_MesiasSoza) p).getRgb()));
        }
        stringList.sort(null);	// se ordena la lista
        // se aï¿½ade un null, ya que el algoritmo de histograma compara 
        // cuando el elemento de la lista cambia, sin este elemento
        // extra, el elemento final de la lista no serï¿½ procesado
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
            } else // si cambiï¿½ el valor con respecto al anterior:
            {
                // si el valor se repite mï¿½s que el anterior mï¿½s repetido
                if (tempCont >= maxCont)
                {
                    // se actualiza el mï¿½s repetido
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
        for (Pixel_21266659_MesiasSoza p : this.pixeles)
        {
            int[] rgb = ((Pixrgb_21266659_MesiasSoza) p).getRgb();
            rgb[0] = 255 - rgb[0];
            rgb[1] = 255 - rgb[1];
            rgb[2] = 255 - rgb[2];
            ((Pixrgb_21266659_MesiasSoza) p).setRgb(rgb);
        }
    }

    /**
     * Elimina los pixeles del color que mï¿½s se repite y se almacena color
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

        ArrayList<Pixel_21266659_MesiasSoza> newPixs = new ArrayList<Pixel_21266659_MesiasSoza>();
        for (Pixel_21266659_MesiasSoza p : this.pixeles)
        {
            if (!Arrays.equals(compRgb, ((Pixrgb_21266659_MesiasSoza) p).getRgb()))
            {
                newPixs.add(p);
            }
        }
        this.compRgb = compRgb;
        this.pixeles = newPixs;
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
        for (Pixel_21266659_MesiasSoza p : this.pixeles)
        {
            ret = ret + Arrays.toString(((Pixrgb_21266659_MesiasSoza) p).getRgb());
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
     * Rellena los pixeles faltantes con el valor de compresiï¿½n
     */
    @Override
    public void decompress()
    {
        ArrayList<Pixel_21266659_MesiasSoza> newPixs = new ArrayList<Pixel_21266659_MesiasSoza>();
        // iterar y comprobar cada pixel faltante
        for (int i = 0; i < this.height; i++)
        {
            for (int j = 0; j < this.width; j++)
            {
                Pixel_21266659_MesiasSoza p = this.findPix(j, i);
                if (p != null)
                {
                    newPixs.add(p);
                } else
                {
                    newPixs.add(new Pixrgb_21266659_MesiasSoza(j, i, 0, compRgb));
                }

            }
        }
        this.compRgb = null;
        this.pixeles = newPixs;
    }
}
