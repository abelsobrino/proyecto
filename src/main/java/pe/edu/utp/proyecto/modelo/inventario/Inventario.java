package pe.edu.utp.proyecto.modelo.inventario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un inventario que agrupa productos.
 */
@Entity
@Table(name = "inventario")
@Getter
@Setter
@NoArgsConstructor
@Slf4j
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

    /**
     * Agrega un producto al inventario.
     * @param producto Producto a agregar.
     */
    public void agregarProducto(Producto producto) {
        this.productos.add(producto);
        log.info("Producto agregado: {}", producto.getNombre());
    }

    /**
     * Elimina un producto del inventario.
     * @param producto Producto a eliminar.
     */
    public void quitarProducto(Producto producto) {
        this.productos.remove(producto);
        log.info("Producto removido: {}", producto.getNombre());
    }
}