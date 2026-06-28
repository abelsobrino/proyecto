package pe.edu.utp.proyecto.repository.inventario_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.inventario.LoteProducto;

import java.util.Date;
import java.util.List;

@Repository
public interface LoteProductoRepository extends JpaRepository<LoteProducto, Integer> {

    List<LoteProducto> findByCodigoLote(String codigoLote);

    List<LoteProducto> findByFechaVencimiento(Date fechaVencimiento);
}