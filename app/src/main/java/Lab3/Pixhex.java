package Lab3;

/**
 * Clase pixhex, subclase de pixel que guarda una String
 *
 */
public class Pixhex extends Pixel
{

    /**
     * Valor de color almacenado
     */
    private String hex;

    /**
     * Getter del valor hexadecimal
     *
     * @return (String) que almacena el pixhex
     */
    public String getHex()
    {
        return hex;
    }

    /**
     * Setter del valor hexadecimal
     *
     * @param hex (String) de formato "#RRGGBB"
     */
    public void setHex(String hex)
    {
        this.hex = hex;
    }

    /**
     * Constructor de Pixhex
     *
     * @param x Coordenada X del pixel
     * @param y Coordenada Y del pixel
     * @param depth Valor de profundidad del pixel
     * @param hex Valor de color (String) de formato "#RRGGBB"
     */
    public Pixhex(int x, int y, int depth, String hex)
    {
        super(x, y, depth);
        this.hex = hex;
    }

    /**
     * Retorna el tipo del pixel
     *
     * @return 2 (int) para pixhex
     */
    public int getTipo()
    {
        return 2;
    }

    /**
     * Retorna un pixel idéntico, pero en color blanco
     *
     * @return Pixhex
     */
    public Pixhex copyToWhite()
    {
        Pixhex newP = new Pixhex(this.getX(), this.getY(), this.getDepth(), "#FFFFFF");
        return newP;
    }

}
