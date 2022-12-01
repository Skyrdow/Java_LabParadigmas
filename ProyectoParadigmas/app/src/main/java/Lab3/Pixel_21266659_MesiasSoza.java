package Lab3;

/**
 * Clase principal pixel, de la cual heredan los diferentes tipos de pixeles
 *
 */
abstract class Pixel_21266659_MesiasSoza
{

    /**
     * Coordenada X del pixel, entero positivo
     */
    private int x;
    /**
     * Coordenada Y del pixel, entero positivo
     */
    private int y;
    /**
     * Valor de profundidad del pixel, va del 0 al 255
     */
    private int depth;

    /**
     * Constructor de la clase pixel
     *
     * @param x Coordenada X del pixel, entero positivo
     * @param y Coordenada Y del pixel, entero positivo
     * @param depth Valor de profundidad del pixel, va del 0 al 255
     */
    public Pixel_21266659_MesiasSoza(int x, int y, int depth)
    {
        this.x = x;
        this.y = y;
        this.depth = depth;
    }

    /**
     * Getter de la coordenada X del pixel
     *
     * @return Coordenada X del pixel
     */
    public int getX()
    {
        return x;
    }

    /**
     * Setter de la coordenada X del pixel
     *  @param x Coordenada x del pixel (int)
     */
    public void setX(int x)
    {
        this.x = x;
    }

    /**
     * Getter de la coordenada Y del pixel
     *
     * @return Coordenada Y del pixel
     */
    public int getY()
    {
        return y;
    }

    /**
     * Setter de la coordenada y del pixel
     * @param y Coordenada y del pixel
     */
    public void setY(int y)
    {
        this.y = y;
    }

    /**
     * Getter de la profundidad del pixel
     *
     * @return profundidad del pixel
     */
    public int getDepth()
    {
        return depth;
    }

    /**
     * Setter de la profundidad del pixel
     *  @param depth Profundidad
     */
    public void setDepth(int depth)
    {
        this.depth = depth;
    }

    /**
     * Retorna el tipo del pixel
     *
     * @return -1 (int) para pixel sin tipo
     */
    public int getTipo()
    {
        return -1; // pixel sin tipo
    }

    /**
     * Función especifica de cada pixel, implementada en cada clase. Retorna un
     * pixel idéntico, pero en color blanco
     *
     * @return (null) pixel sin tipo no tiene color
     */
    public Pixel_21266659_MesiasSoza copyToWhite()
    {
        return null;
    }
}
