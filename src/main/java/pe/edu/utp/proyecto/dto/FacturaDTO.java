package pe.edu.utp.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturaDTO {
    private Long idFactura;
    private String serie;
    private String numero;
    private Date fechaEmision;
    private double total;
    private String rucCliente;
}