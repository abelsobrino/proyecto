package pe.edu.utp.proyecto.modelo.ventas;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.utp.proyecto.modelo.empresa.Cliente;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "venta")
@Getter
@Setter
@NoArgsConstructor
public class Venta {

    @Id
    private int idVenta;

    private Date fechaVenta;
    private String estado;
    private double total;
    private Cliente cliente;
    private List<DetalleVenta> detalles = new ArrayList<>();

    public Venta(int idVenta, Date fechaVenta, String estado, Cliente cliente) {
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.estado = estado;
        this.cliente = cliente;
        this.detalles = new ArrayList<>();
        this.total = 0.0;
    }

    public void agregarDetalle(DetalleVenta detalle) {
        this.detalles.add(detalle);
        this.total += detalle.getSubtotal();
    }

    public double calcularTotal() {
        this.total = detalles.stream()
                .mapToDouble(DetalleVenta::getSubtotal)
                .sum();
        return this.total;
    }
}