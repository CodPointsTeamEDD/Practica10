public class Producto implements Comparable<Producto>{
    private String producto;
    private double precio;
    private String categoria;
    private String marca;
    private int existencia;

    public Producto(String producto, double precio, String categoria, String marca, int existencia){
        this.producto = producto;
        this.precio = precio;
        this.categoria = categoria;
        this.marca = marca;
        this.existencia = existencia;
    }

    public String getProducto(){
        return producto;
    }
    public double getPrecio(){
        return precio;
    }
    public String getCategoria(){
        return categoria;
    }
    public String getMarca(){
        return marca;
    }
    public int getExistencia(){
        return existencia;
    }

    @Override
    public int compareTo(Producto p){
        if (this.precio > p.precio) {
            return 1;
        }
        if (this.precio < p.precio) {
            return -1;
        } else {
            return 0;
        }
    }

    public String toString(){
        String cadena = "";
        cadena = cadena + "Producto: " + getProducto() + "\n";
        cadena = cadena + "Precio: " + getPrecio() + "\n";
        cadena = cadena + "Marca: " + getMarca() + "\n";
        cadena = cadena + "Categoria: " + getCategoria() + "\n";
        cadena = cadena + "Existencia: " + getExistencia() + "\n";
        cadena = cadena + "\n";
        return cadena;
    }

}