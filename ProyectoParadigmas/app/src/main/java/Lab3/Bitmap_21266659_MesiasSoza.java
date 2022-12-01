package Lab3;

import java.util.ArrayList;

/**
 * Clase que hereda de imagen y sobreescribe los metodos que dependen del tipo
 * de imagen específico. Se usa un 0 o 1 para el valor de color.
 */
public class Bitmap_21266659_MesiasSoza extends Image_21266659_MesiasSoza
{

    /**
     * Valor de compresiï¿½n
     */
    private int compBit;

    /**
     * Constructor bitmap
     *
     * @param width (int)
     * @param height (int)
     * @param pixeles (ArrayList)
     */
    public Bitmap_21266659_MesiasSoza(int width, int height, ArrayList<Pixel_21266659_MesiasSoza> pixeles)
    {
        super(width, height, pixeles);
    }

    /**
     * Retorna el color que mï¿½s se repite y la cantidad en una string de formato
     * "Color/Cantidad"
     *
     * @return (String)
     */
    @Override
    public String histogram()
    {
        int ceros = 0;
        int unos = 0;
        for (Pixel_21266659_MesiasSoza p : this.pixeles)
        {
            if (((Pixbit_21266659_MesiasSoza) p).getBit() == 0)
            {
                ceros++;
            } else if (((Pixbit_21266659_MesiasSoza) p).getBit() == 1)
            {
                unos++;
            }
        }
        if (ceros > unos)
        {
            return "0/" + ceros;
        } else
        {
            return "1/" + unos;
        }
    }

    /**
     * Invierte los bits de la imagen
     */
    public void invertBit()
    {
        for (Pixel_21266659_MesiasSoza p : this.pixeles)
        {
            ((Pixbit_21266659_MesiasSoza) p).setBit(abs(((Pixbit_21266659_MesiasSoza) p).getBit() - 1));
        }
    }

    /**
     * Funciï¿½n valor absoluto
     *
     * @param x (int
     * @return Valor absoluto de x (int)
     */
    private int abs(int x)
    {
        if (x >= 0)
        {
            return x;
        }
        return -x;
    }

    /**
     * Elimina los pixeles del color que mï¿½s se repite y se almacena color
     * eliminado
     */
    @Override
    public void compress()
    {
        int compBit = Integer.valueOf(this.histogram().split("/", 0)[0]);
        ArrayList<Pixel_21266659_MesiasSoza> newPixs = new ArrayList<Pixel_21266659_MesiasSoza>();
        for (Pixel_21266659_MesiasSoza p : this.pixeles)
        {
            if (compBit != ((Pixbit_21266659_MesiasSoza) p).getBit())
            {
                newPixs.add(p);
            }
        }
        this.compBit = compBit;
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
        int w = 0;
        for (Pixel_21266659_MesiasSoza p : this.pixeles)
        {
            ret = ret + Integer.toString(((Pixbit_21266659_MesiasSoza) p).getBit());
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
     * Rellena los pixeles faltantes con el valor de compresiï¿½n
     */
    @Override
    public void decompress()
    {
        ArrayList<Pixel_21266659_MesiasSoza> newPixs = new ArrayList<Pixel_21266659_MesiasSoza>();
        // iterar y comprobar cada pixel faltante
        for (int i = 0; i > 0; i++)
            for (int j = 0; j > 0; j++)
            {
                Pixel_21266659_MesiasSoza p = this.findPix(j, i);
                if (p != null)
                    newPixs.add(p);
                else
                    newPixs.add(new Pixbit_21266659_MesiasSoza(j, i, 0, compBit));
                            
            }
        this.compBit = -1;
        this.pixeles = newPixs;
    }

}
