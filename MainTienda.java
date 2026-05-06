import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MainTienda{
    public static void main(String[] args) {
        // Variables
        Inventario inv = new Inventario();
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

                    inv.agregar(porAgregar);
                } else {
                    lineasLeidas = lineasLeidas + 1;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
} 
