package estructuras.colas;
import estructuras.tdas.PiCoLa;

/**
 * Clase concreta {@code Cola<T>}
 * Implementa la interfaz {@link PiCola<T>}
 * 
 * @author Luis Fernando Quintana López
 * @author Erick Xavier Martinez Briones
 * @version 1.0.0
 * @since 2026
 * 
 */

public class Cola<T> implements PiCoLa<T> {

    /**
     * Clase privada {@code Nodo}
     * Nodo para poder usar en la clase Cola
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

    /** Nodo tope que representa el tope de la cola */
    protected Nodo tope;

    /** Nodo tope que representa el rabo de la cola */
    protected Nodo rabo;

    /** Natural tamanio que representa el tamanio de la cola */
    private int tamanio;

    /**
     * Constructor de la clase Cola
     * Inicializa el tope como null
     * Inicialza el rabo como null
     * Inicializa el tamanio de la pila como 0
     */
    public Cola() {
        this.tope = null;
        this.rabo = null;
        this.tamanio = 0;
    }

    /**
     * Metodo para agregar un elemento a una Cola
     * La estructura de la cola sigue FIFO (First In, First Out),
     * por lo que los elementos se agregan al final.
     * Implementa el metodo meter(T elemento) de la interfaz {@link PiCola<T>}
     */
    @Override
    public void meter(T elemento) {
        Nodo n = new Nodo(elemento);

        if (this.estaVacia()) {
            this.tope = n;
            this.rabo = n;
        } else {
            this.rabo.siguiente = n;
            this.rabo = n;
        }

        this.tamanio++;

    }

    /**
     * Metodo para sacar un elemento de una cola
     * La estructura de la cola sigue FIFO (First In, First Out),
     * por lo que siempre se elimina el elemento que se encuentra al tope.
     * Implementa el metodo sacar() de la interfaz {@link PiCola<T>}
     * 
     * @return el elemento T en el tope de la cola
     */
    @Override
    public T sacar() {
        if (this.estaVacia()) {
            throw new IllegalArgumentException("No hay elementos para sacar");
        }

        T elemento = this.tope.elemento;
        this.tope = this.tope.siguiente;

        if (this.tope == null) {
            this.rabo = null;
        }

        this.tamanio--;
        return elemento;
    }

    /**
     * Metodo para mirar un elemento de una COla
     * Implementa el metodo mira() de la interfaz {@link PiCola<T>}
     * 
     * @return el elemento T en el tope de la pila sin modificar la cola
     */
    @Override
    public T mira() {
        return this.tope.elemento;
    }

    /**
     * Metodo para determinar si la cola se encuentra vacia
     * Implementa el metodo estaVacia() de la interfaz {@link PiCola<T>}
     * 
     * @return {true} si el tamanio de la cola es igual a 0, {false} en otro caso
     */
    @Override
    public boolean estaVacia() {
        return this.tamanio == 0;
    }

    /**
     * Metodo para determinar el tamanio de la cola
     * Implementa el metodo devolverTamanio() de la interfaz {@link PiCola<T>}
     * 
     * @return el tamanio de la cola llamando al atributo
     */
    @Override
    public int devolverTamanio() {
        return this.tamanio;
    }

    /**
     * Compara esta cola con otro objeto para verificar si son iguales.
     *
     * @param o objeto con el que se compara la cola
     * @return true si ambas colas tienen los mismos elementos en el mismo orden,
     *         false en caso contrario
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        @SuppressWarnings("unchecked")
        Cola<T> m = (Cola<T>) o;
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
     * Metodo que devuelve una representación en cadena de la cola.
     *
     * @return una cadena con los elementos de la cola en orden FIFO
     */
    @Override
    public String toString() {
        String resultado = "[";
        Nodo actual = tope;

        while (actual != null) {
            resultado += actual.elemento;
            if (actual.siguiente != null) {
                resultado += ", ";
            }
            actual = actual.siguiente;
        }

        resultado += "]";
        return resultado;
    }

}
