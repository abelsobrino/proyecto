package pe.edu.utp.proyecto.modelo.ventas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.utp.proyecto.modelo.inventario.Producto;

@Entity
@Table(name = "detalles_venta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private int idDetalle;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Column(name = "precio_unitario", precision = 10, scale = 2, nullable = false)
    private double precioUnitario;

    @Column(name = "descuento", precision = 10, scale = 2)
    private double descuento;

    @Column(name = "subtotal", precision = 10, scale = 2)
    private double subtotal;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    public DetalleVenta(int idDetalle, int cantidad, double precioUnitario,
                        double descuento, Producto producto) {
        this.idDetalle = idDetalle;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.descuento = descuento;
        this.producto = producto;
        this.subtotal = calcularSubtotal();
    }

    public double calcularSubtotal() {
        this.subtotal = (precioUnitario * cantidad) - descuento;
        return this.subtotal;
    }
}