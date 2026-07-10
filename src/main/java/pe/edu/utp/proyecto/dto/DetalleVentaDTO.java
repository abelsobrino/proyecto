package pe.edu.utp.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVentaDTO {
    private int idDetalle;
    private int cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal descuento;
    private BigDecimal subtotal;
    private ProductoDTO producto;
}