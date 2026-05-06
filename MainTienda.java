import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MainTienda{
    public static void main(String[] args) {
        /* Creando el inventario */
        Inventario inv = new Inventario();

        /* Nombre del archivo a leer */
		String nombreArchivo = "participantes.txt";

		/* Lectura del archivo para crear participates */
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
			/* Variable auxiliar que almacena la linea leida */
            String linea;


            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");

                String producto = partes[0];
                double precio = Float.parseFloat(partes[1]);
                String marca = partes[2];
                String categoria = partes[3];
                int existencia = Integer.parseInt(partes[4]);

                Producto porAgregar = new Producto(producto, precio, marca, categoria, existencia);

                inv.agregar(porAgregar);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(inv);

    }
} 
