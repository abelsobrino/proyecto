package pe.edu.utp.proyecto.modelo.inventario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer idProducto;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "precio", precision = 10, scale = 2)
    private Double precio;

    @Column(name = "stock")
    private Integer stock;

    // Relación con CategoriaProducto (descomentar cuando CategoriaProducto tenga @Entity)
    // @ManyToOne
    // @JoinColumn(name = "id_categoria")
    // private CategoriaProducto categoria;
}