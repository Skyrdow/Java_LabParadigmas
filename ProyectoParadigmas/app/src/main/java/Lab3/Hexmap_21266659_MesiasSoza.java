package Lab3;

import java.util.ArrayList;

/**
 * Clase que hereda de imagen y sobreescribe los metodos que dependen del tipo
 * de imagen específico. Se usa una String "#RRGGBB" para el valor de color.
 */
public class Hexmap_21266659_MesiasSoza extends Image_21266659_MesiasSoza
{

    /**
     * Valor de compresiï¿½n
     */
    private String compString;

    /**
     * Constructor hexmap
     *
     * @param width (int)
     * @param height (int)
     * @param pixeles (ArrayList)
     */
    public Hexmap_21266659_MesiasSoza(int width, int height, ArrayList<Pixel_21266659_MesiasSoza> pixeles)
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
        ArrayList<String> stringList = new ArrayList<String>();
        // Obtener lista de colores
        for (Pixel_21266659_MesiasSoza p : this.pixeles)
        {
            stringList.add(((Pixhex_21266659_MesiasSoza) p).getHex());
        }
        stringList.sort(null);	// se ordena la lista
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
        if (tempCont >= maxCont)
        {
            // se actualiza el mï¿½s repetido
            maxCont = tempCont;
            maxStr = tempStr;
        }

        return maxStr + "/" + tempCont;
    }

    /**
     * Elimina los pixeles del color que mï¿½s se repite y se almacena color
     * eliminado
     */
    @Override
    public void compress()
    {
        // Extraer el color
        String compString = this.histogram().split("/", 0)[0];
        ArrayList<Pixel_21266659_MesiasSoza> newPixs = new ArrayList<Pixel_21266659_MesiasSoza>();
        for (Pixel_21266659_MesiasSoza p : this.pixeles)
        {
            // Si no es el color a comprimir, se conserva
            if (!compString.equals(((Pixhex_21266659_MesiasSoza) p).getHex()))
            {
                newPixs.add(p);
            }
        }
        this.compString = compString;
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
            ret = ret + ((Pixhex_21266659_MesiasSoza) p).getHex();
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
        for (int i = 0; i < this.height; i++)
            for (int j = 0; j < this.width; j++)
            {
                Pixel_21266659_MesiasSoza p = this.findPix(j, i);
                if (p != null)
                    newPixs.add(p);
                else
                    newPixs.add(new Pixhex_21266659_MesiasSoza(j, i, 0, compString));
                            
            }
        this.compString = null;
        this.pixeles = newPixs;
    }
}
