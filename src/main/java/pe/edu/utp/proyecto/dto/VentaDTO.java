package pe.edu.utp.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaDTO {
    private int idVenta;
    private Date fechaVenta;
    private String estado;
    private BigDecimal total;
    private ClienteDTO cliente;
    private List<DetalleVentaDTO> detalles;
}