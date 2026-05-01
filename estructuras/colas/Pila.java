package estructuras.colas;
import estructuras.tdas.PiCoLa;

/**
 * Clase concreta {@code Pila<T>}
 * Implementa la interfaz {@link PiCola<T>}
 * 
 * @author Luis Fernando Quintana López
 * @author Erick Xavier Martinez Briones
 * @version 1.0.0
 * @since 2026
 * 
 */

public class Pila<T> implements PiCoLa<T> {

    /**
     * Clase privada {@code Nodo}
     * Nodo para poder usar en la clase Pila
     */
    private class Nodo {
        /**
         * Elemento almacenado en el nodo
         */
        public T elemento;

        /**
         * Apuntador al siguiente nodo en la lista.
         */
        public Nodo siguiente;

        /**
         * Crea un nuevo nodo con el elemento proporcionado.
         *
         * @param elemento El elemento a almacenar en el nodo.
         */
        public Nodo(T elemento) {
            this.elemento = elemento;
            this.siguiente = null;
        }
    }

    /** Atributos */

    /** Nodo tope que representa el tope de la pila */
    protected Nodo tope;

    /** Natural tamanio que representa el tamanio de la pila */
    private int tamanio;

    /**
     * Constructor de la clase Pila
     * Inicializa el tope como null
     * Inicializa el tamanio de la pila como 0
     */
    public Pila() {
        this.tope = null;
        this.tamanio = 0;
    }

    /**
     * Metodo para agregar un elemento a una pila
     * Implementa el metodo meter(T elemento) de la interfaz {@link PiCola<T>}
     */
    @Override
    public void meter(T elemento) {
        Nodo n = new Nodo(elemento);
        n.siguiente = this.tope;
        this.tope = n;
        this.tamanio++;
    }

    /**
     * Metodo para sacar un elemento de una pila
     * Implementa el metodo sacar() de la interfaz {@link PiCola<T>}
     * 
     * @return el elemento T en el tope de la pila
     */
    @Override
    public T sacar() {
        if (this.estaVacia()) {
            throw new IllegalArgumentException("No hay elementos para sacar");
        }
        T elemento = this.tope.elemento;
        this.tope = this.tope.siguiente;
        this.tamanio--;
        return elemento;
    }

    /**
     * Metodo para mirar un elemento de una pila
     * Implementa el metodo mira() de la interfaz {@link PiCola<T>}
     * 
     * @return el elemento T en el tope de la pila sin modificar la pila
     */
    @Override
    public T mira() {
        return this.tope.elemento;
    }

    /**
     * Metodo para determinar si la pila se encuentra vacia
     * Implementa el metodo estaVacia() de la interfaz {@link PiCola<T>}
     * 
     * @return {true} si el tamanio de la pila es igual a 0, {false} en otro caso
     */
    @Override
    public boolean estaVacia() {
        return this.tamanio == 0;
    }

    /**
     * Metodo para determinar el tamanio de la pila
     * Implementa el metodo devolverTamanio() de la interfaz {@link PiCola<T>}
     * 
     * @return el tamanio de la pila llamando al atributo
     */
    @Override
    public int devolverTamanio() {
        return this.tamanio;
    }

    /**
     * Compara esta pila con otro objeto para verificar si son iguales.
     *
     * @param o objeto con el que se compara la pila
     * @return true si ambas pilas tienen los mismos elementos en el mismo orden,
     *         false en caso contrario
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        @SuppressWarnings("unchecked")
        Pila<T> m = (Pila<T>) o;
        Nodo n1 = this.tope;
        Nodo n2 = m.tope;
        while (n1 != null && n2 != null) {
            if (!n1.elemento.equals(n2.elemento))
                return false;
            n1 = n1.siguiente;
            n2 = n2.siguiente;
        }
        return (n1 == null && n2 == null);
    }

    /**
     * Regresa una representación en forma de cadena de la pila.
     * Recorre los elementos desde el tope hasta el fondo y los concatena.
     *
     * @return cadena con los elementos de la pila en orden
     */
    @Override
    public String toString() {
        String resultado = "[";
        Nodo actual = tope;

        while (actual != null) {
            resultado += actual.elemento;
            if (actual.siguiente != null) {
                resultado += ",\n ";
            }
            actual = actual.siguiente;
        }

        resultado += "]";
        return resultado;
    }

}