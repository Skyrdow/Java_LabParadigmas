package Lab3;

import java.util.ArrayList;

public class Bitmap extends Image
{

    /**
     * Valor de compresión
     */
    private int compBit;

    /**
     * Constructor bitmap
     *
     * @param width (int)
     * @param height (int)
     * @param pixeles (ArrayList)
     */
    public Bitmap(int width, int height, ArrayList<Pixel> pixeles)
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
        int ceros = 0;
        int unos = 0;
        for (Pixel p : this.getPixeles())
        {
            if (((Pixbit) p).getBit() == 0)
            {
                ceros++;
            } else if (((Pixbit) p).getBit() == 1)
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
        for (Pixel p : this.pixeles)
        {
            ((Pixbit) p).setBit(abs(((Pixbit) p).getBit() - 1));
        }
    }

    /**
     * Función valor absoluto
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
     * Elimina los pixeles del color que más se repite y se almacena color
     * eliminado
     */
    @Override
    public void compress()
    {
        int compBit = Integer.valueOf(this.histogram().split("/", 0)[0]);
        ArrayList<Pixel> newPixs = new ArrayList<Pixel>();
        for (Pixel p : this.getPixeles())
        {
            if (compBit != ((Pixbit) p).getBit())
            {
                newPixs.add(p);
            }
        }
        this.compBit = compBit;
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
            ret = ret + Integer.toString(((Pixbit) p).getBit());
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
                    newPixs.add(new Pixbit(j, i, 0, compBit));
                            
            }
        this.compBit = -1;
        this.setPixeles(newPixs);
    }

}
