package pe.edu.utp.proyecto.repository.inventario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.inventario.LoteProducto;

import java.util.Date;
import java.util.List;

/**
 * Repositorio para la entidad LoteProducto.
 */
@Repository
public interface LoteProductoRepository extends JpaRepository<LoteProducto, Integer> {

    /**
     * Busca lotes por su codigo de lote.
     * @param codigoLote Codigo del lote.
     * @return Lista de lotes con ese codigo.
     */
    List<LoteProducto> findByCodigoLote(String codigoLote);

    /**
     * Busca lotes por fecha de vencimiento.
     * @param fechaVencimiento Fecha de vencimiento.
     * @return Lista de lotes que vencen en esa fecha.
     */
    List<LoteProducto> findByFechaVencimiento(Date fechaVencimiento);
}