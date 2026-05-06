import estructuras.listas.ListaDoblementeLigada;

public class Inventario{
    private ArbolBinarioOrdenado productos;
    public Inventario(){
        productos = new ArbolBinarioOrdenado<Producto>();
    }

    public void agregar(Producto p){
        productos.agregar(p);
    }
    public void eliminar(Producto p){
        productos.eliminar(p);
    }
    public void buscar(Producto p){
        productos.buscar(p);
    }
    public void productosRangoDePrecio(double minimo, double maximo){
        ListaDoblementeLigada<Producto> recorrido = productos.devolverRecorrido();
        ListaDoblementeLigada<Producto> productosEnElRango = new ListaDoblementeLigada<>();

        for (Producto pr : recorrido) {
            if (pr.getPrecio() >= minimo && pr.getPrecio() <= maximo) {
                productosEnElRango.agregar(pr);
            }
        }

        for (Producto p : productosEnElRango) {
            System.out.println(p);
        }
    }
    public String toString(){
        ListaDoblementeLigada<Producto> recorrido = productos.devolverRecorrido();
        String cadena = "";
        for (Producto p : recorrido) {
            cadena = cadena + p.toString();
        }
        return cadena;
    }
}