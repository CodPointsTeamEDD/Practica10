import estructuras.listas.ListaDoblementeLigada;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MainTienda{
    public static void main(String[] args) {
        // Variables
        Scanner sc = new Scanner(System.in);
        Inventario inv = new Inventario();
        ListaDoblementeLigada<Producto> productosPosibles = new ListaDoblementeLigada<>();
		String nombreArchivo = "Productos.txt";

		// Lectura del archivo para crear participates
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
			// Variables auxilires
            String linea;
            int lineasLeidas = 0;


            while ((linea = br.readLine()) != null) {
                if (lineasLeidas != 0) {
                    String[] partes = linea.split(",");

                    String producto = partes[0];
                    double precio = Double.parseDouble(partes[1]);
                    String marca = partes[2];
                    String categoria = partes[3];
                    int existencia = Integer.parseInt(partes[4]);

                    Producto porAgregar = new Producto(producto, precio, marca, categoria, existencia);

                    productosPosibles.agregarFinal(porAgregar);
                } else {
                    lineasLeidas = lineasLeidas + 1;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Interactuando con el usuario
        System.out.println("Bienvenido al inventario");
        System.out.println("");
        System.out.println("El inventario está vacío");

        System.out.println("Estos son los posibles productos que pueden haber en el inventario: ");
        System.out.println("");

        int contador = 1;
        for (Producto pr : productosPosibles) {
            System.out.println( contador + ") "+  pr.getProducto()+ ", $" + pr.getPrecio());
            contador++;
        }
        System.out.println("");



        int accion;


        while (true) {
            System.out.println("¿Qué quieres hacer?");
            System.out.println("1) agregar producto");
            System.out.println("2) eliminar producto");
            System.out.println("3) ver productos del inventario");
            System.out.println("4) ver productos dentro de un rango de precio");
            System.out.println("5) salir");

            System.out.print("> ");

            if (!sc.hasNextInt()) {
                System.out.println("Entrada inválida. Debes ingresar un número.");
                sc.nextLine();
                continue;
            }

            accion = sc.nextInt();
            sc.nextLine();

            switch (accion) {
                case 1:
                    System.out.println("¿Qué producto deseas agregar? (1-15)");
                    System.out.print("> ");

                    if (!sc.hasNextInt()) {
                        System.out.println("Entrada inválida.");
                        sc.nextLine();
                        break;
                    }

                    int pPorAgregar = sc.nextInt();
                    sc.nextLine();

                    if (pPorAgregar < 1 || pPorAgregar > 15) {
                        System.out.println("El número debe estar entre 1 y 15.");
                        break;
                    }

                    inv.agregar(productosPosibles.acceder(pPorAgregar - 1));
                    System.out.println("Producto agregado.");
                    break;

                case 2:
                    System.out.println("¿Qué producto deseas eliminar? (1-15)");
                    System.out.print("> ");

                    if (!sc.hasNextInt()) {
                        System.out.println("Entrada inválida.");
                        sc.nextLine();
                        break;
                    }

                    int pPorEliminar = sc.nextInt();
                    sc.nextLine();

                    if (pPorEliminar < 1 || pPorEliminar > 15) {
                        System.out.println("El número debe estar entre 1 y 15.");
                        break;
                    }

                    inv.eliminar(productosPosibles.acceder(pPorEliminar - 1));
                    System.out.println("Producto eliminado.");
                    break;

                case 3:
                    System.out.println(inv);
                    break;

                case 4:
                    System.out.println("Ingresa el valor mínimo:");
                    System.out.print("> ");

                    if (!sc.hasNextDouble()) {
                        System.out.println("Entrada inválida.");
                        sc.nextLine();
                        break;
                    }
                    double minimo = sc.nextDouble();
                    sc.nextLine();

                    System.out.println("Ingresa el valor máximo:");
                    System.out.print("> ");

                    if (!sc.hasNextDouble()) {
                        System.out.println("Entrada inválida.");
                        sc.nextLine();
                        break;
                    }
                    double maximo = sc.nextDouble();
                    sc.nextLine();

                    if (minimo > maximo) {
                        System.out.println("El mínimo no puede ser mayor que el máximo.");
                        break;
                    }

                    inv.productosRangoDePrecio(minimo, maximo);
                    break;

                case 5:
                    System.out.println("Saliendo...");
                    return;

                default:
                    System.out.println("Opción inválida.");
            }
        }


    }
} 
