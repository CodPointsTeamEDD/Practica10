/**
 * Clase concreta {@code MainArbolCompleto}
 * 
 * @author Luis Fernando Quintana López
 * @author Erick Xavier Martinez Briones
 * @version 1.0.0
 * @since 2026
 * 
 */

public class MainArbolOrdenado {
    public static void main(String[] args) {

        /** Creando un objeto arbol de la clase ArbolBinarioCompleto */
        ArbolBinarioOrdenado<Integer> arbol = new ArbolBinarioOrdenado<>();

        // insertar elementos
        arbol.agregar(50);

        arbol.agregar(30);
        arbol.agregar(70);

        arbol.agregar(20);
        arbol.agregar(40);
        arbol.agregar(60);
        arbol.agregar(80);

        arbol.agregar(10);
        arbol.agregar(25);
        arbol.agregar(35);
        arbol.agregar(45);
        arbol.agregar(55);
        arbol.agregar(65);
        arbol.agregar(75);
        arbol.agregar(90);

        System.out.println(arbol.toString());

        /** Imprime el recorrido despues de insertar los elementos */
        System.out.println("recorrido en amplitud después de insertar los elementos");
        System.out.println(arbol.devolverRecorrido());

        // probando buscar
        System.out.println("\nel árbol tiene como elemento al 5? " + arbol.buscar(5));
        System.out.println("el árbol tiene como elemento al 65? " + arbol.buscar(65));

        // probando eliminar
        System.out.println("\neliminado al 70");
        arbol.eliminar(70);

        System.out.println(arbol.toString());

        System.out.println("recorrido después de eliminar al 70");
        System.out.println(arbol.devolverRecorrido());
    }

}