package pe.edu.utp.proyecto.service.patron.comportamiento_state;

public class EstadoFactory {

    private EstadoFactory() {}

    public static EstadoVenta getEstado(String nombreEstado) {
        if (nombreEstado == null) {
            return new EstadoPendiente();
        }
        return switch (nombreEstado.toUpperCase()) {
            case "PENDIENTE" -> new EstadoPendiente();
            case "PROCESANDO" -> new EstadoProcesando();
            case "COMPLETADA" -> new EstadoCompletada();
            case "CANCELADA" -> new EstadoCancelada();
            default -> new EstadoPendiente();
        };
    }
}