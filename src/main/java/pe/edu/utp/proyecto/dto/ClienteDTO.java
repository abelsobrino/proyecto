package pe.edu.utp.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private int idCliente;
    private String nombres;
    private String documento;
    private String telefono;
}