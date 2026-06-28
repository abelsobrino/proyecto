package pe.edu.utp.proyecto.modelo.inventario;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "inventario")
@Getter
@Setter
@NoArgsConstructor
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInventario;

    private String nombre;
    private String descripcion;

    @OneToMany
    @JoinColumn(name = "idInventario")
    private List<Producto> productos = new ArrayList<>();

    public Inventario(int idInventario, String nombre, String descripcion) {
        this.idInventario = idInventario;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        this.productos.add(producto);
        System.out.println("[Inventario] ProductoService agregado: " + producto.getNombre());
    }

    public void quitarProducto(Producto producto) {
        this.productos.remove(producto);
        System.out.println("[Inventario] ProductoService removido: " + producto.getNombre());
    }
}