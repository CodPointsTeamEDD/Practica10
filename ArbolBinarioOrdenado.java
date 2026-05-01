import estructuras.colas.*;
import estructuras.listas.*;

/**
 * Clase concreta {@code ArbolBinarioOrdenado}
 * Extiende de la interfaz {@link Comparable}
 * Extiende de la clase abstracta {@link ArbolBinario}
 * 
 * @author Luis Fernando Quintana López
 * @author Erick Xavier Martinez Briones
 * @version 1.0.0
 * @since 2026
 */
public class ArbolBinarioOrdenado<T extends Comparable<T>> extends ArbolBinario<T> {

    /**
     * Constructor vacío.
     * 
     * Crea un árbol binario ordenado sin elementos.
     */
    public ArbolBinarioOrdenado() {
        super();
    }

    /**
     * Agrega un elemento de tipo T al árbol conservando el orden binario.
     * 
     * @param elemento elemento a agregar.
     * @throws IllegalArgumentException si el elemento es null.
     */
    @Override
    public void agregar(T elemento) throws IllegalArgumentException {
        if (elemento == null) {
            throw new IllegalArgumentException("El elemetno no puede ser null");
        }

        if (this.raiz == null) {
            Vertice v = new Vertice(elemento);
            this.raiz = v;
            this.tamanio++;
        }

        agregar(this.raiz, elemento);
    }

    /**
     * Agrega recursivamente un elemento de tipo T en la posición correspondiente.
     * 
     * @param v        vértice actual.
     * @param elemento elemento a agregar.
     */
    private void agregar(Vertice v, T elemento) {
        if (v.elemento.equals(elemento)) {
            return;
        }

        if (v.elemento.compareTo(elemento) < 0) {

            if (v.derecho == null) {
                Vertice vAux = new Vertice(elemento);
                this.tamanio++;
                v.derecho = vAux;
                vAux.padre = v;
            } else {
                agregar(v.derecho, elemento);
            }
        } else {

            if (v.izquierdo == null) {
                Vertice vAux = new Vertice(elemento);
                this.tamanio++;
                v.izquierdo = vAux;
                vAux.padre = v;
            } else {
                agregar(v.izquierdo, elemento);
            }
        }
    }

    /**
     * Elimina un elemento de tipo T del árbol.
     * 
     * @param elemento elemento a eliminar.
     * @throws IllegalArgumentException si el elemento es null.
     */
    @Override
    public void eliminar(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("El elemento no puede ser nulo");
        }

        eliminar(this.raiz, elemento);
    }

    /**
     * Elimina recursivamente un elemento de tipo T del árbol.
     * 
     * @param v        vértice actual.
     * @param elemento elemento a eliminar.
     * @return la nueva referencia del subárbol.
     */
    private Vertice eliminar(Vertice v, T elemento) {
        if (v == null) {
            return null;
        } else

        if (v.elemento.compareTo(elemento) > 0) {
            v.izquierdo = eliminar(v.izquierdo, elemento);
        } else

        if (v.elemento.compareTo(elemento) < 0) {
            v.derecho = eliminar(v.derecho, elemento);
        } else {

            this.tamanio--;

            if (v.izquierdo == null || v.derecho == null) {
                return null;
            } else

            if (v.izquierdo == null) {
                v.derecho.padre = v.padre;
                return v.derecho;
            } else

            if (v.derecho == null) {
                v.izquierdo.padre = v.padre;
                return v.izquierdo;
            } else {

                Vertice sucesor = devolverMinimo(v.derecho);
                v.elemento = sucesor.elemento;
                v.derecho = eliminar(v.derecho, sucesor.elemento);

                if (v.derecho != null) {
                    v.derecho.padre = v;
                }

            }
        }
        return v;
    }

    /**
     * Obtiene el vértice con el elemento mínimo de un subárbol,
     * recorriendo por la izquierda.
     * 
     * @param v raíz del subárbol.
     * @return vértice con el elemento mínimo.
     */
    private Vertice devolverMinimo(Vertice v) {
        while (v.izquierdo != null) {
            v = v.izquierdo;
        }
        return v;
    }

    /**
     * Busca un elemento de tipo T dentro del árbol.
     * 
     * @param elemento elemento a buscar.
     * @return {true} si el elemento está en el árbol,
     *         {@false} en otro caso.
     */
    @Override
    public boolean buscar(T elemento) {
        return buscar(elemento, this.raiz);
    }

    /**
     * Busca recursivamente un elemento a partir de un vértice.
     * 
     * Dependiendo de la comparación, continúa la búsqueda
     * en el subárbol izquierdo o derecho.
     * 
     * @param elemento elemento a buscar.
     * @param v        vértice actual.
     * @return {true} si el elemento se encuentra,
     *         {false} en otro caso.
     */
    private boolean buscar(T elemento, Vertice v) {
        if (v == null) {
            return false;
        }

        if (v.elemento.equals(elemento)) {
            return true;
        }

        if (v.elemento.compareTo(elemento) < 0) {
            return buscar(elemento, v.derecho);
        }

        return buscar(elemento, v.izquierdo);
    }

    /**
     * Devuelve un recorrido in-order del árbol.
     * 
     * El recorrido se realiza iterativamente usando una pila,
     * visitando primero el subárbol izquierdo, luego la raíz
     * y finalmente el subárbol derecho.
     * 
     * @return lista con los elementos en orden.
     */
    @Override
    public ListaDoblementeLigada<T> devolverRecorrido() {
        ListaDoblementeLigada<T> recorrido = new ListaDoblementeLigada<>();
        if (this.raiz == null) {
            return recorrido;
        }

        Pila<Vertice> pila = new Pila<>();
        Vertice actual = this.raiz;

        while (actual != null || !pila.estaVacia()) {

            while (actual != null) {
                pila.meter(actual);
                actual = actual.izquierdo;
            }

            actual = pila.sacar();
            recorrido.agregarFinal(actual.elemento);
            actual = actual.derecho;
        }

        return recorrido;

    }

}
