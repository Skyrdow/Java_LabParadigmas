package Lab3;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu
{

    Scanner sc;
    private String menu1 = "\nOpciones:\n" + "1.Crear Imagen\n" + 
            "2.Modificar Imagen\n" + "3.Visualizar Imagen\n";
    private String preg = "Introduzca su opción:";
    private String menuCrearTam = "Introduzca el tamaño:";
    private String menuCrear = "Seleccione el tipo de imagen:\n1.Bitmap\n2.Pixmap\n3.Hexmap\n";
    private String imgError1 = "Imagen no creada\n";
    private String imgCreada = "Imagen creada:\n";
    private String menuMod = "1.Flip Horizontal\n" + "2.Flip Horizontal\n" +
            "3.Recortar\n" + "4.Rotar 90 grados\n" +
            "5.Histograma (Color que más se reptite)\n" + "6.Comprimir\n" +
            "7.Cambiar Pixel\n" + "8.Capas de Profundidad\n" + 
            "9.Descomprimir\n" + "10.Invertir Bits\n" + "11.Invertir RGB\n" +
            "12.Comprobar Bitmap\n" + "13.Comprobar Pixmap\n" +
            "14.Comprobar Hexmap\n";
    private String imgError2 = "No es posible aplicar la modificación\n";

    public Menu()
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

    public void menu()
    {
        IImage img = null;
        while (true)
        {
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
                            print(((Bitmap) img).imageToString());
                        }
                        if (img.isPixmap())
                        {
                            print(((Pixmap) img).imageToString());
                        }
                        if (img.isHexmap())
                        {
                            print((img).imageToString());
                        }
                    }
            }
        }
    }

    private IImage menuCrearImg()
    {
        ArrayList<Pixel> pixeles = new ArrayList<Pixel>();
        print(menuCrearTam);
        int height = input("Introducir Alto:");
        int width = input("Introducir Ancho:");
        int depth;
        print("\n" + menuCrear);
        switch (input(preg))
        {
            case 1: // Bitmap
                int bit;
                for (int i = 0; i < height; i++)
                {
                    for (int j = 0; j < width; j++)
                    {
                        pedirPixs(j, i);
                        bit = input("Introducir bit:");
                        depth = input("Introducir depth:");
                        pixeles.add(new Pixbit(j, i, depth, bit));
                    }
                }
                return new Bitmap(width, height, pixeles);
            case 2: // Pixmap
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
                        pixeles.add(new Pixrgb(j, i, depth, rgb));
                    }
                }
                return new Pixmap(width, height, pixeles);
            case 3: // Hexmap
                String hex;
                for (int i = 0; i < height; i++)
                {
                    for (int j = 0; j < width; j++)
                    {
                        pedirPixs(j, i);
                        System.out.print("Introducir hex (incluyendo \"#\"): ");
                        hex = sc.nextLine();
                        depth = input("Introducir depth:");
                        pixeles.add(new Pixhex(j, i, depth, hex));
                    }
                }
                return new Hexmap(width, height, pixeles);
            default:
                return null;
        }
    }

    private IImage menuModificar(IImage img)
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
                    histo = ((Bitmap) img).histogram().split("/", 0);
                }
                if (img.isPixmap())
                {
                    histo = ((Pixmap) img).histogram().split("/", 0);
                }
                if (img.isHexmap())
                {
                    histo = ((Hexmap) img).histogram().split("/", 0);
                }
                print(histo[0] + " con un total de " + histo[1]
                        + " repeticiones");
                return img;
            case 6:
                if (img.isCompressed())
                {
                    return img;
                } else
                {
                    img.compress();
                    return img;
                }
            case 7:
                print("Ingrese el pixel nuevo:");
                int x = input("x:");
                int y = input("y:");
                int depth = input("depth:");
                if (img.isBitmap())
                {
                    int bit = input("Ingresar bit:");
                    img.changePixel(new Pixbit(x, y, depth, bit));
                    return img;
                }
                if (img.isPixmap())
                {
                    int[] rgb = new int[3];
                    rgb[0] = input("Introducir R:");
                    rgb[1] = input("Introducir G:");
                    rgb[2] = input("Introducir B:");
                    img.changePixel(new Pixrgb(x, y, depth, rgb));
                }
                if (img.isHexmap())
                {
                    print("Introducir hex (incluyendo \"#\"):");
                    String hex = sc.nextLine();
                    img.changePixel(new Pixhex(x, y, depth, hex));
                    return img;
                }
                break;
            case 8:
                if (img.isBitmap())
                {
                    for (IImage imgD : img.depthLayers())
                    {
                        Bitmap bitD = new Bitmap(imgD.width,
                                imgD.height, imgD.getPixeles());
                        print((bitD).imageToString());
                    }
                }
                if (img.isPixmap())
                {
                    for (IImage imgD : img.depthLayers())
                    {
                        print(((Pixmap) imgD).imageToString());
                    }
                }
                if (img.isHexmap())
                {
                    for (Image imgD : img.depthLayers())
                    {
                        print(((Hexmap) imgD).imageToString());
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
                    ((Bitmap) img).invertBit();
                } else
                {
                    print(imgError2);
                }
                return img;
            case 11:
                if (img.isPixmap())
                {
                    ((Pixmap) img).invertRgb();
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
            default:
                break;
        }
        return img;

    }

    public Image imagen1()
    {
        
        ArrayList<Pixel> pixs = new ArrayList<Pixel>();

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                pixs.add(new Pixhex(j, i, 0, "#FFAA"+i+j));
            }
        }
        return new Hexmap(3,3,pixs);
    }
}
