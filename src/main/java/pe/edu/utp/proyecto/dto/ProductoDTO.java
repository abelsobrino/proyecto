package pe.edu.utp.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {
    private Integer idProducto;
    private String nombre;
    private BigDecimal precio;
    private Integer stock;
}