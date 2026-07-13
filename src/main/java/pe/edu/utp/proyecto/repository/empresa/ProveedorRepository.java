package pe.edu.utp.proyecto.repository.empresa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.empresa.Proveedor;

import java.util.List;

/**
 * Repositorio para la entidad Proveedor.
 */
@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

    /**
     * Busca un proveedor por su RUC.
     * @param ruc RUC del proveedor.
     * @return Proveedor encontrado o null.
     */
    Proveedor findByRuc(String ruc);

    /**
     * Busca proveedores cuya razon social contenga un texto.
     * @param razonSocial Texto a buscar en la razon social.
     * @return Lista de proveedores que coinciden.
     */
    List<Proveedor> findByRazonSocialContaining(String razonSocial);
}