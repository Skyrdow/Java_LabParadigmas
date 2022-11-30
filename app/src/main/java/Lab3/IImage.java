package Lab3;

import java.util.ArrayList;

/**
 * Interfaz para imagen, requerimiento del laboratorio
 */
public interface IImage
{

    public int width = 0;
    public int height = 0;
    public ArrayList<Pixel> pixeles = new ArrayList<Pixel>();
    
    public ArrayList<Pixel> getPixeles();
    
    public void setPixeles(ArrayList<Pixel> pixeles);

    public void sortPixs();

    public boolean isBitmap();

    public boolean isPixmap();

    public boolean isHexmap();

    public boolean isCompressed();

    public void flipH();

    public void flipV();

    public void crop(int x1, int y1, int x2, int y2);

    public String histogram();

    public void rotate90();

    public void compress();

    public void changePixel(Pixel newPix);

    public String imageToString();

    public ArrayList<Image> depthLayers();

    public void decompress();
}
