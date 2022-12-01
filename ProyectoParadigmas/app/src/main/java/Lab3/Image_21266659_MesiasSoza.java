package Lab3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Clase principal, contiene todas las funciones genericas de imagen
 *
 * @author skyrdow
 *
 */
public class Image_21266659_MesiasSoza implements IImage_21266659_MesiasSoza
{

    /**
     * Ancho de la imagen
     */
    public int width;
    /**
     * Alto de la imagen
     */
    public int height;
    /**
     * Lista de pixeles de la imagen
     */
    public ArrayList<Pixel_21266659_MesiasSoza> pixeles = new ArrayList<Pixel_21266659_MesiasSoza>();

    /**
     * Constructor de la imagen
     *
     * @param width (int)
     * @param height (int)
     * @param pixeles (ArrayList)
     */
    public Image_21266659_MesiasSoza(int width, int height, ArrayList<Pixel_21266659_MesiasSoza> pixeles)
    {
        this.width = width;
        this.height = height;
        this.pixeles = pixeles;
    }

    /**
     * Encuentra el pixel de las coordenadas de entrada
     * @param x Coordenada X
     * @param y Coordenada Y
     * @return Si existe el pixel lo retorna, sino retorna null
     */
    protected Pixel_21266659_MesiasSoza findPix(int x, int y)
    {
        for (Pixel_21266659_MesiasSoza p : this.pixeles)
        {
            if (p.getX() == x && p.getY() == y)
                return p;
        }
        return null;
    }
    
    private int abs(int x)
    {
        if (x >= 0)
            return x;
        return -x;
    }
    /**
     * Ordena los pixeles de la imagen
     */
    public void sortPixs()
    {
        Collections.sort(this.pixeles,
                Comparator.comparing(Pixel_21266659_MesiasSoza::getY).thenComparing(Pixel_21266659_MesiasSoza::getX));
    }

    /**
     * Revisa si la imagen es de tipo bitmap
     *
     * @return (boolean)
     */
    public boolean isBitmap()
    {
        for (Pixel_21266659_MesiasSoza p : this.pixeles)
        {
            if (p.getTipo() != 0)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Revisa si la imagen es de tipo pixmap
     *
     * @return (boolean)
     */
    public boolean isPixmap()
    {
        for (Pixel_21266659_MesiasSoza p : this.pixeles)
        {
            if (p.getTipo() != 1)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Revisa si la imagen es de tipo hexmap
     *
     * @return (boolean)
     */
    public boolean isHexmap()
    {
        for (Pixel_21266659_MesiasSoza p : this.pixeles)
        {
            if (p.getTipo() != 2)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Revisa si la imagen esta comprimida seg�n la cantidad de pixeles que
     * contiene
     *
     * @return (boolean)
     */
    public boolean isCompressed()
    {
        if (this.pixeles.size() == (this.height * this.width))
        {
            return false;
        }
        return true;
    }

    /**
     * Voltea horizontalmente la imagen
     */
    public void flipH()
    {
        for (Pixel_21266659_MesiasSoza p : this.pixeles)
        {
            p.setX(abs(p.getX() - this.width + 1));
        }
    }

    /**
     * Voltea verticalmente la imagen
     */
    public void flipV()
    {
        for (Pixel_21266659_MesiasSoza p : this.pixeles)
        {
            p.setY(abs(p.getY() - this.height + 1));
        }
    }

    /**
     * Elimina los pixeles que se encuentran fuera del cuadrado delimitado por
     * los puntos (x1,y1) y (x2,y2)
     *
     * @param x1 Coordenada x del punto 1
     * @param y1 Coordenada y del punto 1
     * @param x2 Coordenada x del punto 2
     * @param y2 Coordenada y del punto 2
     */
    public void crop(int x1, int y1, int x2, int y2)
    {
        ArrayList<Pixel_21266659_MesiasSoza> newPixs = new ArrayList<Pixel_21266659_MesiasSoza>();
        for (Pixel_21266659_MesiasSoza p : this.pixeles)
        {
            if (x1 <= p.getX() && p.getX() <= x2
                    && y1 <= p.getY() && p.getY() <= y2)
            {
                newPixs.add(p);
            }
        }
        this.pixeles = newPixs;
        this.width = (x2 - x1 + 1);
        this.height = (y2 - y1 + 1);
    }

    /**
     * Funci�n que debe ser implementada en las clases que la hereden
     *
     * @return String de formato "Color/Cantidad"(String)
     */
    public String histogram()
    {
        return "";
    }

    /**
     * Rota en 90 grados la imagen
     */
    public void rotate90()
    {
        for (Pixel_21266659_MesiasSoza p : this.pixeles)
        {
            int tempX = p.getX();
            p.setX(p.getY());
            p.setY(tempX);
            p.setX(abs(p.getX() - this.width + 1));
        }
        int temp = this.width;
        this.width = this.height;
        this.height = temp;
    }

    /**
     * Funci�n que debe ser implementada en las clases heredadas
     */
    public void compress()
    {
    }

    /**
     * Intercambia un pixel de la lista con el pixel de entrada
     *
     * @param newPix Pixel_21266659_MesiasSoza a intercambiar (Pixel_21266659_MesiasSoza)
     */
    public void changePixel(Pixel_21266659_MesiasSoza newPix)
    {
        ArrayList<Pixel_21266659_MesiasSoza> newPixs = new ArrayList<>();
        for (Pixel_21266659_MesiasSoza p : this.pixeles)
        {
            if (newPix.getX() == p.getX() && newPix.getY() == p.getY())
                newPixs.add(newPix);
            else
                newPixs.add(p);
        }
        this.pixeles = newPixs;
    }

    /**
     * Funci�n que debe ser implementada en las clases heredadas
     */
    public String imageToString()
    {
        return "";
    }

    /**
     * Retorna las capas de profundidad de la imagen, dejando en blanco los
     * pixeles que no pertenecen a la profundidad
     *
     * @return Lista de imagenes (ArrayList)
     */
    public ArrayList<IImage_21266659_MesiasSoza> depthLayers()
    {
        ArrayList<IImage_21266659_MesiasSoza> depthList = new ArrayList<IImage_21266659_MesiasSoza>();
        // Obtener la lista de las profundidades presentes
        // en la imagen sin repetici�n
        ArrayList<Integer> depths = new ArrayList<Integer>();
        for (Pixel_21266659_MesiasSoza p : this.pixeles)
        {
            depths.add(p.getDepth());
        }
        depths.sort(null);
        ArrayList<Integer> depthsNoClone = new ArrayList<Integer>();
        int temp = -1;
        for (Integer i : depths)
        {
            if (i != temp)
            {
                temp = i;
                depthsNoClone.add(i);
            }
        }

        // Generar las capas
        for (Integer i : depthsNoClone)
        {
            ArrayList<Pixel_21266659_MesiasSoza> depthPixs = new ArrayList<Pixel_21266659_MesiasSoza>();
            for (Pixel_21266659_MesiasSoza p : this.pixeles)
            {
                // Si el pixel es de la capa se almacena, sino
                // se cambia por uno blanco
                if (i == p.getDepth())
                {
                    depthPixs.add(p);
                } else
                {
                    depthPixs.add(p.copyToWhite());
                }
            }
            Image_21266659_MesiasSoza depthImg = new Image_21266659_MesiasSoza(this.width, this.height, depthPixs);
            depthList.add(depthImg);
        }
        return depthList;
    }

    /**
     * Funci�n que debe ser implementada en las clases heredadas
     */
    public void decompress()
    {
    }
}
