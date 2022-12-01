package Lab3;

import java.util.ArrayList;

/**
 * Interfaz para imagen, requerimiento del laboratorio
 */
public interface IImage_21266659_MesiasSoza
{
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

    public void changePixel(Pixel_21266659_MesiasSoza newPix);

    public String imageToString();

    public ArrayList<IImage_21266659_MesiasSoza> depthLayers();

    public void decompress();
}
