import estructuras.listas.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MainTienda {

    public static void obtenerInfo() {
        ListaDoblementeLigada<Producto> productosDisponibles = new ListaDoblementeLigada<>();

        try (BufferedReader br = new BufferedReader((new FileReader("Productos.txt")))) {

            String linea;

            br.readLine();

            // Producto,Precio,Categoria,Marca,Existencia

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");

                String productoNombre = datos[0].trim();

                String precio = datos[1].trim();

                String categoria = datos[2].trim();

                String marca = datos[3].trim();

                String existencia = datos[4].trim();

                Producto producto = new Producto(productoNombre, Double.parseDouble(precio), categoria,
                        marca, Integer.parseInt(existencia));
                productosDisponibles.agregarFinal(producto);
            }

            // estudiantes en el curso
            System.out.println("======== Productos  ========");
            System.out.println(productosDisponibles.toString());
            System.out.println(" ");

        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
        }

    }


    public static void main(String[] args) {
        
        obtenerInfo();

    }

}
