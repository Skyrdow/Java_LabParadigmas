package Lab3;

/**
 * Clase pixbit, subclase de pixel que guarda un bit
 *
 */
public class Pixbit extends Pixel
{
    /**
     * Valor de color almacenado
     */
    private int bit;

    /**
     * Getter del valor bit
     *
     * @return Bit que almacena el pixbit
     */
    public int getBit()
    {
        return bit;
    }

    /**
     * Setter del valor bit
     *
     * @param bit valor 0 o 1
     */
    public void setBit(int bit)
    {
        this.bit = bit;
    }

    /**
     * Constructor de Pixbit
     *
     * @param x Coordenada X del pixel
     * @param y Coordenada Y del pixel
     * @param depth Valor de profundidad del pixel
     * @param bit Valor de color 0 o 1
     */
    public Pixbit(int x, int y, int depth, int bit)
    {
        super(x, y, depth);
        this.bit = bit;
    }

    /**
     * Retorna el tipo del pixel
     *
     * @return 0 (int) para pixbit
     */
    @Override
    public int getTipo()
    {
        return 0;
    }

    /**
     * Retorna un pixel idéntico, pero en color blanco
     *
     * @return Pixbit
     */
    @Override
    public Pixbit copyToWhite()
    {
        Pixbit newP = new Pixbit(this.getX(), this.getY(), this.getDepth(), 1);
        return newP;
    }

}
