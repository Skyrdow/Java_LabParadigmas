package Lab3;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase men�, maneja la interacci�n con el usuario
 */
public class Menu_21266659_MesiasSoza
{

    Scanner sc;
    private String menu1 = "\nOpciones:\n" + "1.Crear Imagen\n"
            + "2.Modificar Imagen\n" + "3.Visualizar Imagen\n"
            + "4.Imagen Bitmap Predeterminada\n"
            + "5.Imagen Pixmap Predeterminada\n"
            + "6.Imagen Hexmap Predeterminada\n";
    private String preg = "Introduzca su opción:";
    private String menuCrearTam = "Introduzca el tamaño:";
    private String menuCrear = "Seleccione el tipo de imagen:\n1.Bitmap\n"
            + "2.Pixmap\n3.Hexmap\n";
    private String imgError1 = "Imagen no creada\n";
    private String imgCreada = "Imagen creada:\n";
    private String menuMod = "1.Flip Horizontal\n" + "2.Flip Vertical\n"
            + "3.Recortar\n" + "4.Rotar 90 grados\n"
            + "5.Histograma (Color que más se reptite)\n" + "6.Comprimir\n"
            + "7.Cambiar Pixel\n" + "8.Capas de Profundidad\n"
            + "9.Descomprimir\n" + "10.Invertir Bits\n" + "11.Invertir RGB\n"
            + "12.Comprobar Bitmap\n" + "13.Comprobar Pixmap\n"
            + "14.Comprobar Hexmap\n" + "13.Transformar Pixmap a Hexmap\n";
    private String imgError2 = "No es posible aplicar la modificación\n";

    /**
     *  Constructor del menu, inicializa el scanner
     */
    public Menu_21266659_MesiasSoza()
    {
        this.sc = new Scanner(System.in);
    }

    private void print(String str)
    {
        System.out.println(str);
    }

    private int input(String str)
    {
        System.out.print(str);
        int ret = sc.nextInt();
        // debido a que nextInt() no lee el \n, crea problemas si no se quita
        sc.nextLine(); // quitar el \n
        return ret;
    }

    private void pedirPixs(int x, int y)
    {
        System.out.println("Introduzca el pixel x:" + x + ", y:" + y);
    }

    /**
     *  Metodo principal que ejecuta el menu
     */
    public void menu()
    {
        IImage_21266659_MesiasSoza img = null;
        while (true)
        {
            if (img != null && img.isCompressed())
            {
                print("\n--IMAGEN COMPRIMIDA--");
            }
            print(menu1);
            switch (input(preg))
            {
                case 1:
                    img = menuCrearImg();
                    print(imgCreada);
                    break;
                case 2:
                    if (img == null)
                    {
                        print(imgError1);
                    } else
                    {
                        img = menuModificar(img);
                    }
                    break;
                case 3:
                    if (img == null)
                    {
                        print(imgError1);
                    } else
                    {
                        if (img.isBitmap())
                        {
                            print(((Bitmap_21266659_MesiasSoza) img).imageToString());
                        }
                        if (img.isPixmap())
                        {
                            print(((Pixmap_21266659_MesiasSoza) img).imageToString());
                        }
                        if (img.isHexmap())
                        {
                            print(((Hexmap_21266659_MesiasSoza) img).imageToString());
                        }
                    }
                    break;
                case 4:
                    img = imagenBit();
                    break;
                case 5:
                    img = imagenRgb();
                    break;
                case 6:
                    img = imagenHex();
                    break;
            }
        }
    }

    private IImage_21266659_MesiasSoza menuCrearImg()
    {
        ArrayList<Pixel_21266659_MesiasSoza> pixeles = new ArrayList<Pixel_21266659_MesiasSoza>();
        print(menuCrearTam);
        int height = input("Introducir Alto:");
        int width = input("Introducir Ancho:");
        int depth;
        print("\n" + menuCrear);
        switch (input(preg))
        {
            case 1: // Bitmap_21266659_MesiasSoza
                int bit;
                for (int i = 0; i < height; i++)
                {
                    for (int j = 0; j < width; j++)
                    {
                        pedirPixs(j, i);
                        bit = input("Introducir bit:");
                        depth = input("Introducir depth:");
                        pixeles.add(new Pixbit_21266659_MesiasSoza(j, i, depth, bit));
                    }
                }
                return new Bitmap_21266659_MesiasSoza(width, height, pixeles);
            case 2: // Pixmap_21266659_MesiasSoza
                int[] rgb = new int[3];
                for (int i = 0; i < height; i++)
                {
                    for (int j = 0; j < width; j++)
                    {
                        pedirPixs(j, i);
                        rgb[0] = input("Introducir R:");
                        rgb[1] = input("Introducir G:");
                        rgb[2] = input("Introducir B:");
                        depth = input("Introducir depth:");
                        pixeles.add(new Pixrgb_21266659_MesiasSoza(j, i, depth, rgb));
                    }
                }
                return new Pixmap_21266659_MesiasSoza(width, height, pixeles);
            case 3: // Hexmap_21266659_MesiasSoza
                String hex;
                for (int i = 0; i < height; i++)
                {
                    for (int j = 0; j < width; j++)
                    {
                        pedirPixs(j, i);
                        System.out.print("Introducir hex (incluyendo \"#\"): ");
                        hex = sc.nextLine();
                        depth = input("Introducir depth:");
                        pixeles.add(new Pixhex_21266659_MesiasSoza(j, i, depth, hex));
                    }
                }
                return new Hexmap_21266659_MesiasSoza(width, height, pixeles);
            default:
                return null;
        }
    }

    private IImage_21266659_MesiasSoza menuModificar(IImage_21266659_MesiasSoza img)
    {
        print(menuMod);
        switch (input(preg))
        {
            case 1:
                img.flipH();
                return img;
            case 2:
                img.flipV();
                return img;
            case 3:
                print("Introducir coordenadas de recorte:\n");
                int x1,
                 x2,
                 y1,
                 y2;
                x1 = input("Introducir x1:");
                y1 = input("Introducir y1:");
                x2 = input("Introducir x2:");
                y2 = input("Introducir y2:");
                img.crop(x1, y1, x2, y2);
                return img;
            case 4:
                img.rotate90();
                return img;
            case 5:
                String[] histo = null;
                print("El color que más se repite es:");
                if (img.isBitmap())
                {
                    histo = ((Bitmap_21266659_MesiasSoza) img).histogram().split("/", 0);
                }
                if (img.isPixmap())
                {
                    histo = ((Pixmap_21266659_MesiasSoza) img).histogram().split("/", 0);
                }
                if (img.isHexmap())
                {
                    histo = ((Hexmap_21266659_MesiasSoza) img).histogram().split("/", 0);
                }
                print(histo[0] + " con un total de " + histo[1]
                        + " repeticiones");
                return img;
            case 6:
                if (img.isCompressed())
                {
                    return img;
                }
                img.compress();
                return img;
            case 7:
                print("Ingrese el pixel nuevo:");
                int x = input("x:");
                int y = input("y:");
                int depth = input("depth:");
                if (img.isBitmap())
                {
                    int bit = input("Ingresar bit:");
                    img.changePixel(new Pixbit_21266659_MesiasSoza(x, y, depth, bit));
                }
                if (img.isPixmap())
                {
                    int[] rgb = new int[3];
                    rgb[0] = input("Introducir R:");
                    rgb[1] = input("Introducir G:");
                    rgb[2] = input("Introducir B:");
                    img.changePixel(new Pixrgb_21266659_MesiasSoza(x, y, depth, rgb));
                }
                if (img.isHexmap())
                {
                    print("Introducir hex (incluyendo \"#\"):");
                    String hex = sc.nextLine();
                    img.changePixel(new Pixhex_21266659_MesiasSoza(x, y, depth, hex));
                }
                return img;
            case 8:
                if (img.isBitmap())
                {
                    for (IImage_21266659_MesiasSoza imgD : img.depthLayers())
                    {
                        print(((Bitmap_21266659_MesiasSoza) imgD).imageToString());
                    }
                }
                if (img.isPixmap())
                {
                    for (IImage_21266659_MesiasSoza imgD : img.depthLayers())
                    {
                        print(((Pixmap_21266659_MesiasSoza) imgD).imageToString());
                    }
                }
                if (img.isHexmap())
                {
                    for (IImage_21266659_MesiasSoza imgD : img.depthLayers())
                    {
                        print(((Hexmap_21266659_MesiasSoza) imgD).imageToString());
                    }
                }
                return img;
            case 9:
                if (img.isCompressed())
                {
                    img.decompress();
                }
                return img;
            case 10:
                if (img.isBitmap())
                {
                    ((Bitmap_21266659_MesiasSoza) img).invertBit();
                } else
                {
                    print(imgError2);
                }
                return img;
            case 11:
                if (img.isPixmap())
                {
                    ((Pixmap_21266659_MesiasSoza) img).invertRgb();
                } else
                {
                    print(imgError2);
                }
                return img;
            case 12:
                if (img.isBitmap())
                {
                    print("La imagen es de tipo Bitmap");
                } else
                {
                    print("La imagen NO es de tipo Bitmap");
                }
                return img;
            case 13:
                if (img.isPixmap())
                {
                    print("La imagen es de tipo Pixmap");
                } else
                {
                    print("La imagen NO es de tipo Pixmap");
                }
                return img;
            case 14:
                if (img.isHexmap())
                {
                    print("La imagen es de tipo Hexmap");
                } else
                {
                    print("La imagen NO es de tipo Hexmap");
                }
                return img;
            case 15:
                if (img.isPixmap())
                {
                    img = ((Pixmap_21266659_MesiasSoza) img).imgRGBtoHex();
                } else
                {
                    print(imgError2);
                }
            default:
                break;
        }
        return img;

    }

    private IImage_21266659_MesiasSoza imagenHex()
    {

        ArrayList<Pixel_21266659_MesiasSoza> pixs = new ArrayList<Pixel_21266659_MesiasSoza>();

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                pixs.add(new Pixhex_21266659_MesiasSoza(j, i, 0, "#FFAA" + j + i));
            }
        }
        return new Hexmap_21266659_MesiasSoza(3, 3, pixs);
    }

    private IImage_21266659_MesiasSoza imagenBit()
    {

        ArrayList<Pixel_21266659_MesiasSoza> pixs = new ArrayList<Pixel_21266659_MesiasSoza>();

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                pixs.add(new Pixbit_21266659_MesiasSoza(j, i, 0, (i + j) % 2));
            }
        }
        return new Bitmap_21266659_MesiasSoza(3, 3, pixs);
    }

    private IImage_21266659_MesiasSoza imagenRgb()
    {

        ArrayList<Pixel_21266659_MesiasSoza> pixs = new ArrayList<Pixel_21266659_MesiasSoza>();

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                int[] rgb =
                {
                    i, j, i + j
                };
                pixs.add(new Pixrgb_21266659_MesiasSoza(j, i, 0, rgb));
            }
        }
        return new Pixmap_21266659_MesiasSoza(3, 3, pixs);
    }
}
