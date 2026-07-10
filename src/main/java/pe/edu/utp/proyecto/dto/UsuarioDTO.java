package pe.edu.utp.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private int idUsuario;
    private String nombre;
    private String correo;
    private boolean estado;
    private String rolNombre;
}