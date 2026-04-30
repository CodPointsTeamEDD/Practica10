package estructuras.arboles;

import estructuras.listas.ListaDoblementeLigada;
import estructuras.tdas.Coleccion;
import java.util.Iterator;

/**
 * Clase asbtracta {@code ArbolBinario}
 * Implementa la interfaz {@link Coleccion}
 * 
 * @author Luis Fernando Quintana López
 * @author Erick Xavier Martinez Briones
 * @version 1.0.0
 * @since 2026
 */
public abstract class ArbolBinario<T> implements Coleccion<T> {


    /**
     * Clase protegida {@code Vertice}
     * Vertice para poder usar en la clase ArbolBinario
     */
    protected class Vertice {

        /**Atributos de la clase Vertice */

        /** Elemento de tipo T del vertice */
        public T elemento;

        /** Vertice que representa el vertice izquierdo del vertice dado */
        public Vertice izquierdo;

        /** Vertice que representa el vertice derecho del vertice dado */
        public Vertice derecho;

        /** Vertice que representa el padre del vertice dado */
        public Vertice padre;

        /**
         * Constrcutor de la clase Vertice
         * @param elemento elemento dentro del vertice a crear
         */
        public Vertice(T elemento) {
            this.elemento = elemento;
            this.izquierdo = null;
            this.derecho = null;
            this.padre = null;
        }

        /**
         * Metodo para determinar si el vertice dado es hijo izquierdo
         * {true} si es hijo izquierdo, {false} en otro caso
         */
        public boolean esHijoIzquierdo() {
            return padre != null && padre.izquierdo == this;
        }

        /**
         * Metodo para determinar si el vertice dado es hijo derecho
         * {true} si es hijo derechi, {false} en otro caso
         */
        public boolean esHijoDerecho() {
            return padre != null && padre.derecho == this;
        }

        /**
         * Agrega un vértice como hijo izquierdo de este vértice
         * @param hijo el vertice que se asignara como hijo izquierdo
         */
        public void agregaIzquierdo(Vertice hijo) {
            izquierdo = hijo;
            if (hijo != null) {
                hijo.padre = this;
            }
        }

        /**
         * Agrega un vértice como hijo derecho de este vértice
         * @param hijo el vertice que se asignara como hijo derecho
         */
        public void agregaDerecho(Vertice hijo) {
            derecho = hijo;
            if (hijo != null)
                hijo.padre = this;
        }
        public String toString(){
            String cadena = AsciiBox.addAccents(elemento.toString());
            cadena = AsciiBox.asciiBox(cadena, false, AsciiBox.length(cadena));
            int type = 2;
            if(izquierdo == null && derecho == null){
                type = 3;
            } else if(padre == null){
                type = 1;
            }
            return AsciiBox.nodify(cadena,type);
        }  
    }

    /** atributos de la clase ArbolBinario */

    /** Vertice raiz que representa la raiz del arbol binario */
    protected Vertice raiz;

    /** Numero natural que representa el tamanio del arbol binario */
    protected int tamanio;

    /**
     * Constructor de la clase ArbolBinario
     * Inicializa la raiz como null
     * Inicializa el tamanio como0
     */
    public ArbolBinario() {
        this.raiz = null;
        this.tamanio = 0;
    }

    /**
     * Metodo abstracto que busca un elemento de tipo T en el arbol binario
     */
    @Override
    public abstract boolean buscar(T elemento);

    /**
     * Metodo acceder que accede a un elemento del arbol binario dado un indice
     * @param indice indice al cual queremos acceder
     * @return el elemento en el indice seleccionado 
     */
    public T acceder(int indice) {
        ListaDoblementeLigada<T> recorrido = this.devolverRecorrido();
        return recorrido.acceder(indice);
    }

    /**
     * Metodo que devuelve el tamanio del arbol
     * @return el atributo del tamanio del arbol
     */
    public int devolverTamanio() {
        return this.tamanio;
    }

    /** 
     * Metodo que determina si el arbol esta vacio 
     * {true} si el arbol esta vacio (con tamanio 0), {false} en otro caso
     */
    public boolean estaVacio() {
        if (this.devolverTamanio() == 0) {
            return true;
        }

        return false;
    }

    /**Metodo abstracto que agrega un elemento de tipo T al arbol binario */
    @Override
    public abstract void agregar(T elemento);

    /** Metodo abstracto que elimina un elemento de tipo T del arbol binario */
    @Override
    public abstract void eliminar(T elemento);

    /** Metodo abstrato que devuelve el recorrido del arbol binario siguiendo la metodologia BFS */
    protected abstract ListaDoblementeLigada<T> devolverRecorrido();
    
    /** Metodo que devuelve un inteardor para recorrer los elementos del arbol
     * Se realiza en el orden definido por devolverRecorrido()
     * @return un iterador sobre los elementos del arbol
     */
    @Override
    public Iterator<T> iterator() {
        return devolverRecorrido().iterator();
    }
        /**
     * Método que convierte un árbol a su representación en ascii art
     * @param root Vértice raíz del árbol.
     * @return Cadena con la representación en ascii art de un árbol n-ario
     */
    public String asciiTree(Vertice root){
        if(root.izquierdo == null && root.derecho == null){
            return root.toString();
        } else {
            String concatVertex = "";
            String cadenaIzquierda = root.izquierdo != null ? asciiTree(root.izquierdo) : "";
            String cadenaDerecha = root.derecho != null ? asciiTree(root.derecho) : "";
            concatVertex = AsciiBox.concatAscii(cadenaIzquierda,cadenaDerecha,1,true);
            return AsciiBox.treeConcat(root.toString(),AsciiBox.addTreeEdge(concatVertex));
        }
    }     

    public String toString(){
        if(estaVacio()){
            return "No hay árbol";
        } else if(raiz.izquierdo == null && raiz.derecho == null) {
            return AsciiBox.asciiBox(raiz.elemento,false,AsciiBox.length(raiz.elemento.toString()));
        } else {
            return asciiTree(raiz);
        }
    }
}
