package Lab3;

/**
 * Clase pixrgb, subclase de pixel que almacena 3 valores de 0 a 255
 *
 */
public class Pixrgb_21266659_MesiasSoza extends Pixel_21266659_MesiasSoza
{
    /**
     * Valor de color almacenado
     */
    private int[] rgb = new int[3];

    /**
     * Getter del valor de color
     *
     * @return (int[3]) 3 ints con valores del 0 al 255
     */
    public int[] getRgb()
    {
        return rgb;
    }

    /**
     * Setter del valor de color
     *
     * @param rgb (int[3]) con valores del 0 al 255
     */
    public void setRgb(int[] rgb)
    {
        this.rgb[0] = rgb[0];
        this.rgb[1] = rgb[1];
        this.rgb[2] = rgb[2];
    }

    /**
     * Constructor de Pixrgb
     *
     * @param x Coordenada X del pixel
     * @param y Coordenada Y del pixel
     * @param depth Valor de profundidad del pixel
     * @param rgb (int[3]) Valores de color de 0 a 255
     */
    public Pixrgb_21266659_MesiasSoza(int x, int y, int depth, int[] rgb)
    {
        super(x, y, depth);
        this.rgb[0] = rgb[0];
        this.rgb[1] = rgb[1];
        this.rgb[2] = rgb[2];
    }

    /**
     * Retorna el tipo del pixel
     *
     * @return 1 (int) para pixrgb
     */
    @Override
    public int getTipo()
    {
        return 1;
    }

    /**
     * Retorna un pixel idéntico, pero en color blanco
     *
     * @return Pixbit
     */
    @Override
    public Pixrgb_21266659_MesiasSoza copyToWhite()
    {
        int[] a =
        {
            255, 255, 255
        };
        Pixrgb_21266659_MesiasSoza newP = new Pixrgb_21266659_MesiasSoza(this.getX(), this.getY(), this.getDepth(), a);
        return newP;
    }
}
