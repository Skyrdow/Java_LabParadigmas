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
public class Image implements IImage
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
    public ArrayList<Pixel> pixeles = new ArrayList<Pixel>();

    /**
     * Getter de la lista de pixeles
     *
     * @return Lista de pixeles (ArrayList)
     */
    public ArrayList<Pixel> getPixeles()
    {
        return pixeles;
    }

    /**
     * Setter de pixeles
     *
     * @param pixeles (ArrayList)
     */
    public void setPixeles(ArrayList<Pixel> pixeles)
    {
        this.pixeles = pixeles;
    }

    /**
     * Constructor de la imagen
     *
     * @param width (int)
     * @param height (int)
     * @param pixeles (ArrayList)
     */
    public Image(int width, int height, ArrayList<Pixel> pixeles)
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
    protected Pixel findPix(int x, int y)
    {
        for (Pixel p : this.pixeles)
        {
            if (p.getX() == x && p.getY() == y)
                return p;
        }
        return null;
    }
    /**
     * Ordena los pixeles de la imagen
     */
    public void sortPixs()
    {
        Collections.sort(this.pixeles,
                Comparator.comparing(Pixel::getY).thenComparing(Pixel::getX));
    }

    /**
     * Revisa si la imagen es de tipo bitmap
     *
     * @return (boolean)
     */
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

    /**
     * Revisa si la imagen es de tipo pixmap
     *
     * @return (boolean)
     */
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

    /**
     * Revisa si la imagen es de tipo hexmap
     *
     * @return (boolean)
     */
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
            return true;
        }
        return false;
    }

    /**
     * Voltea horizontalmente la imagen
     */
    public void flipH()
    {
        for (Pixel p : this.pixeles)
        {
            p.setX(p.getX() - this.width + 1);
        }
    }

    /**
     * Voltea verticalmente la imagen
     */
    public void flipV()
    {
        for (Pixel p : this.pixeles)
        {
            p.setY(p.getY() - this.height + 1);
        }
    }

    /**
     * Elimina los pixeles que se encuentran fuera del cuadrado delimitado por
     * los puntos (x1,y1) y (x2,y2)
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public void crop(int x1, int y1, int x2, int y2)
    {
        ArrayList<Pixel> newPixs = new ArrayList<Pixel>();
        for (Pixel p : this.pixeles)
        {
            if (x1 <= p.getX() && p.getX() <= x2
                    && y1 <= p.getY() && p.getY() <= y2)
            {
                newPixs.add(p);
            }
        }
        this.setPixeles(newPixs);
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
        for (Pixel p : this.pixeles)
        {
            int tempX = p.getX();
            p.setX(p.getY());
            p.setY(tempX);
            p.setX(Math.abs(p.getX() - this.width + 1));
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
     * @param Pixel a intercambiar (Pixel)
     */
    public void changePixel(Pixel newPix)
    {
        for (Pixel p : this.pixeles)
        {
            if (newPix.getX() == p.getX()
                    && newPix.getY() == p.getY())
            {
                p = newPix;
            }
        }
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
    public ArrayList<Image> depthLayers()
    {
        ArrayList<Image> depthList = new ArrayList<Image>();
        // Obtener la lista de las profundidades presentes
        // en la imagen sin repetici�n
        ArrayList<Integer> depths = new ArrayList<Integer>();
        for (Pixel p : this.pixeles)
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
            ArrayList<Pixel> depthPixs = new ArrayList<Pixel>();
            for (Pixel p : this.pixeles)
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
            Image depthImg = new Image(this.width, this.height, depthPixs);
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
