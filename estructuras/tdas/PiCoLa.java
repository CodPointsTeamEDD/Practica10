package estructuras.tdas;

/**
 * Interfaz {@code PiCola<T>}
 * Implementa la interfaz {@link PiCola<T>}
 * 
 * @author Luis Fernando Quintana López
 * @author Erick Xavier Martinez Briones
 * @version 1.0.0
 * @since 2026
 * 
 */
public interface PiCoLa<T> {

    /**
     * Metodo para agregar un elemento a la estructura de datos
     * 
     * @param elemento elemento a agregar
     */
    public void meter(T elemento);

    /**
     * Metodo para quitar un elemento de la estructura de datos
     * 
     * @return elemento T
     */
    public T sacar();

    /**
     * Metodo para observar un elemento de la etsructura de datos
     * 
     * @return elemento T
     */
    public T mira();

    /**
     * Metodo para determinar si la estructura de datos esta vacia
     * 
     * @return {true} si esta vacia, {false} en otro caso
     */
    public boolean estaVacia();

    /**
     * Metodo que devuelve el tamanio de la estuctura de datos
     * 
     * @return
     */
    public int devolverTamanio();

    /**
     * Metodo que determina si dos objetos de la estructura de datos son iguales
     * 
     * @param obj objeto a comparar
     * @return {true} si son iguales, {false} en otro caso
     */
    @Override
    public boolean equals(Object obj);

    /**
     * Metodo que devuelve una cadena que es una representacion visual de la
     * estructura de datos
     * 
     * @return la cadena que representa la estructura de datos
     */
    @Override
    public String toString();
}