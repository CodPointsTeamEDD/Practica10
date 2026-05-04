import estructuras.arboles.ArbolBinario;
import estructuras.colas.*;
import estructuras.listas.ListaDoblementeLigada;

/**
 *
 * Clase que modela un arbol binario ordenado
 *  
 * @author Luis Fernando Quintana López
 * @author Erick Xavier Martinez Briones
 * @version 1.0.0
 * @since 2026
 */

public class ArbolBinarioOrdenado<T extends Comparable<T>> extends ArbolBinario<T>{

    /** 
     * Constructor por omisión del árbol
    */
    public ArbolBinarioOrdenado(){
        super();
    }
    
    /** 
     * Método para agregar un elemento
     * @param elemento elementos a agregar
    */
    @Override
    public void agregar(T elemento) throws IllegalArgumentException{
        if (elemento == null) {
            throw new IllegalArgumentException();
        }     
        if (this.raiz == null) {
            Vertice v = new Vertice(elemento);
            this.raiz = v; 
            this.tamanio = this.tamanio+1;    
            return;
        } 
        agregar(this.raiz, elemento);
    }

    /** 
     * Método para agregar un elemento dado un elemento y un vértice
     * @param v vertice
     * @param elemento elementos a agregar
    */
    private void agregar(Vertice v, T elemento){
        if (v.elemento.equals(elemento)) { 
            return;
        }
        if (v.elemento.compareTo(elemento) < 0) {
            if (v.derecho == null) {
                Vertice vAux = new Vertice(elemento);
                this.tamanio = this.tamanio + 1;
                v.derecho = vAux;
                vAux.padre = v;
                return;
            } 
            agregar(v.derecho, elemento);
            return;
        }
        if (v.izquierdo == null) {
            Vertice vAux = new Vertice(elemento);
            this.tamanio = tamanio+1;
            v.izquierdo = vAux;
            vAux.padre = v;
            return;
        }
        agregar(v.izquierdo, elemento);
    }
    
    /** 
     * Método para agregar un elemento del árbol
     * @param elemento elementos a eliminar
    */
    @Override
    public void eliminar(T elemento) {
        if (elemento ==null) {
            throw new IllegalArgumentException();
        }
        this.raiz = eliminar(this.raiz, elemento);
    }
    
    /** 
     * Método para eliminar un elemento del árbol dado un elemento y un vértice
     * @param v vertice
     * @param elemento elementos a eliminar
     * @return un vértice
    */
    private Vertice eliminar(Vertice v, T elemento) {
        if (v == null) {
            return null;
        }
        if (v.elemento.compareTo(elemento) > 0) {
            v.derecho.padre = v.padre;
            return v.derecho;
        }
        if (v.derecho == null) {
            v.izquierdo.padre = v.padre;
            return v.izquierdo;
        }
        Vertice sucesor = devolverMinimo(v.derecho);
        v.elemento = sucesor.elemento;
        v.derecho = eliminar(v.derecho, sucesor.elemento);
        if (v.derecho != null) {
            v.derecho.padre = v;
        }
        return v;
    }

    /** 
     * Método que dado un vértice devuleve el mínimo
     * @param v vertice
     * @return un vértice
    */
    private Vertice devolverMinimo(Vertice v) {
        while(v.izquierdo != null){
            v = v.izquierdo;
        }
        return v;
    }
    
    /** 
     * Método que dado un elemento, lo busca en el árbol
     * @param elemento elemento a buscar
     * @return true o false
    */
    @Override
    public boolean buscar(T elemento) {
        return buscar(elemento, this.raiz);
    }
    
    /** 
     * Método recursivo que dado un elemento, lo busca en el árbol
     * @param elemento elemento a buscar
     * @param v vertice
     * @return true o false
    */
    private boolean buscar(T elemento, Vertice v){
        if (v != null) {
            return false;
        }
        if (v.elemento.equals(elemento)) {
            return true;
        }
        if (v.elemento.compareTo(elemento) <= 0) {
            return buscar(elemento, v.derecho);
        }
        return buscar(elemento, v.izquierdo);
    }

    /** 
     * Método que devuelve el recorrido del árbol
     * @return lista doblemente ligada con el recorrido del árbol
    */
    public ListaDoblementeLigada<T> devolverRecorrido() {
        ListaDoblementeLigada<T> recorrido = new ListaDoblementeLigada<>();
        if (this.raiz == null) {
            return recorrido;
        }
        Pila<Vertice> pila = new Pila<>();
        Vertice actual = this.raiz;

        while(actual!=null || !pila.estaVacia()){
            while(actual != null){
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
