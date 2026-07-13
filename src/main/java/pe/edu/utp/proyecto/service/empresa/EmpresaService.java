package pe.edu.utp.proyecto.service.empresa;

import pe.edu.utp.proyecto.modelo.empresa.Empresa;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestion de empresas.
 * Define las operaciones disponibles para la entidad Empresa.
 */
public interface EmpresaService {

    /**
     * Guarda una nueva empresa en el sistema.
     * @param empresa Datos de la empresa a guardar.
     * @return Empresa guardada.
     */
    Empresa guardarEmpresa(Empresa empresa);

    /**
     * Busca una empresa por su RUC.
     * @param ruc RUC de la empresa.
     * @return Optional con la empresa encontrada o vacio.
     */
    Optional<Empresa> obtenerEmpresaPorId(String ruc);

    /**
     * Obtiene todas las empresas registradas.
     * @return Lista de todas las empresas.
     */
    List<Empresa> obtenerTodasLasEmpresas();

    /**
     * Actualiza los datos de una empresa existente.
     * @param ruc RUC de la empresa a actualizar.
     * @param empresa Datos actualizados de la empresa.
     * @return Empresa actualizada.
     */
    Empresa actualizarEmpresa(String ruc, Empresa empresa);

    /**
     * Elimina una empresa del sistema.
     * @param ruc RUC de la empresa a eliminar.
     */
    void eliminarEmpresa(String ruc);

    /**
     * Busca una empresa por su RUC.
     * @param ruc RUC de la empresa.
     * @return Empresa encontrada o null.
     */
    Empresa buscarPorRuc(String ruc);

    /**
     * Busca empresas cuya razon social contenga un texto.
     * @param razonSocial Texto a buscar en la razon social.
     * @return Lista de empresas que coinciden.
     */
    List<Empresa> buscarPorRazonSocialContaining(String razonSocial);
}