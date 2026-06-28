package pe.edu.utp.proyecto.service.impl.inventario_impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.modelo.inventario.LoteProducto;
import pe.edu.utp.proyecto.repository.inventario_repository.LoteProductoRepository;
import pe.edu.utp.proyecto.service.inventario_service.LoteProductoService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class LoteProductoServiceImpl implements LoteProductoService {

    @Autowired
    private LoteProductoRepository loteProductoRepository;

    @Override
    @Transactional
    public LoteProducto guardarLote(LoteProducto lote) {
        log.info("Guardando nuevo lote");
        return loteProductoRepository.save(lote);
    }

    @Override
    public Optional<LoteProducto> obtenerLotePorId(Integer id) {
        log.debug("Buscando lote con ID: {}", id);
        return loteProductoRepository.findById(id);
    }

    @Override
    public List<LoteProducto> obtenerTodosLosLotes() {
        log.info("Obteniendo todos los lotes");
        return loteProductoRepository.findAll();
    }

    @Override
    @Transactional
    public LoteProducto actualizarLote(Integer id, LoteProducto lote) {
        log.info("Actualizando lote con ID: {}", id);

        LoteProducto existente = loteProductoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lote no encontrado con ID: " + id));

        existente.setCodigoLote(lote.getCodigoLote());
        existente.setCantidad(lote.getCantidad());
        existente.setFechaIngreso(lote.getFechaIngreso());
        existente.setFechaVencimiento(lote.getFechaVencimiento());
        existente.setProducto(lote.getProducto());
        existente.setAlmacen(lote.getAlmacen());

        return loteProductoRepository.save(existente);
    }

    @Override
    @Transactional
    public void eliminarLote(Integer id) {
        log.info("Eliminando lote con ID: {}", id);
        loteProductoRepository.deleteById(id);
    }

    @Override
    public List<LoteProducto> obtenerLotesPorCodigo(String codigoLote) {
        log.info("Buscando lotes por código: {}", codigoLote);
        return loteProductoRepository.findByCodigoLote(codigoLote);
    }

    @Override
    public List<LoteProducto> obtenerLotesPorFechaVencimiento(Date fechaVencimiento) {
        log.info("Buscando lotes por fecha de vencimiento: {}", fechaVencimiento);
        return loteProductoRepository.findByFechaVencimiento(fechaVencimiento);
    }
}