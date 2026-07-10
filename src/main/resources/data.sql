-- =============================================
-- DATOS DE PRUEBA - SISTEMA DE VENTAS
-- =============================================

-- 1. ROLES
INSERT INTO rol (id_rol, nombre_rol, descripcion) VALUES
                                                      (1, 'ADMIN', 'Administrador del sistema'),
                                                      (2, 'VENDEDOR', 'Vendedor de tienda')
    ON CONFLICT (id_rol) DO NOTHING;

-- 2. USUARIOS
INSERT INTO usuario (id_usuario, nombre, correo, contrasena, estado, id_rol) VALUES
                                                                                 (1, 'Administrador', 'admin@utp.edu.pe', 'admin123', true, 1),
                                                                                 (2, 'Vendedor', 'vendedor@utp.edu.pe', 'vendedor123', true, 2)
    ON CONFLICT (id_usuario) DO NOTHING;

-- 3. CLIENTES
INSERT INTO cliente (id_cliente, nombres, documento, telefono) VALUES
                                                                   (1, 'Jose Bustamante Romero', '40597166', '987654321'),
                                                                   (2, 'Juan Perez Garcia', '30495722', '987654322')
    ON CONFLICT (id_cliente) DO NOTHING;

-- 4. EMPLEADOS
INSERT INTO empleado (id_empleado, nombres, apellidos, cargo) VALUES
                                                                  (1, 'Jose', 'Bustamante Romero', 'Gerente General'),
                                                                  (2, 'Maria', 'Lopez Torres', 'Vendedor')
    ON CONFLICT (id_empleado) DO NOTHING;

-- 5. EMPRESA
INSERT INTO empresa (ruc, razon_social, direccion, telefono) VALUES
    ('20123456789', 'UTP S.A.C.', 'Av. Principal 123, Lima', '987654321')
    ON CONFLICT (ruc) DO NOTHING;

-- 6. SUCURSAL
INSERT INTO sucursal (id_sucursal, nombre, direccion) VALUES
    (1, 'Sucursal Central', 'Av. Principal 123, Lima')
    ON CONFLICT (id_sucursal) DO NOTHING;

-- 7. PROVEEDORES
INSERT INTO proveedor (id_proveedor, razon_social, ruc, telefono) VALUES
    (1, 'Distribuciones SAC', '20567890123', '987654323')
    ON CONFLICT (id_proveedor) DO NOTHING;

-- 8. CATEGORIAS
INSERT INTO categoria_producto (id_categoria, nombre, descripcion) VALUES
                                                                       (1, 'Electronicos', 'Productos electronicos y tecnologia'),
                                                                       (2, 'Ropa', 'Prendas de vestir')
    ON CONFLICT (id_categoria) DO NOTHING;

-- 9. ALMACENES
INSERT INTO almacen (id_almacen, nombre, direccion, responsable, capacidad_maxima) VALUES
    (1, 'Almacen Central', 'Av. Principal 123', 'Juan Perez', 1000.0)
    ON CONFLICT (id_almacen) DO NOTHING;

-- 10. PRODUCTOS
INSERT INTO producto (id_producto, nombre, precio, stock) VALUES
                                                              (1, 'Laptop HP', 2500.00, 50),
                                                              (2, 'Mouse USB', 25.00, 200),
                                                              (3, 'Teclado Inalambrico', 45.00, 150)
    ON CONFLICT (id_producto) DO NOTHING;

-- 11. INVENTARIO
INSERT INTO inventario (id_inventario, nombre, descripcion) VALUES
    (1, 'Inventario General', 'Inventario de todos los productos')
    ON CONFLICT (id_inventario) DO NOTHING;

-- 12. LOTES
INSERT INTO lote_producto (id_lote, codigo_lote, cantidad, fecha_ingreso, fecha_vencimiento, producto_id_producto, almacen_id_almacen) VALUES
    (1, 'LOTE-001', 50, '2024-01-01', '2025-12-31', 1, 1)
    ON CONFLICT (id_lote) DO NOTHING;

-- 13. IMPUESTOS
INSERT INTO impuesto (tipo_impuesto, porcentaje, monto) VALUES
                                                            ('IGV', 18.0, 0.0),
                                                            ('ISC', 5.0, 0.0)
    ON CONFLICT (tipo_impuesto) DO NOTHING;

-- 14. METODOS DE PAGO
INSERT INTO metodo_pago (id_metodo, nombre_metodo, descripcion) VALUES
                                                                    (1, 'EFECTIVO', 'Pago en efectivo'),
                                                                    (2, 'TARJETA', 'Pago con tarjeta')
    ON CONFLICT (id_metodo) DO NOTHING;

-- 15. BOLETA
INSERT INTO boleta (id_boleta, serie, numero, fecha_emision, total, dni_cliente) VALUES
    (1, 'B001', '0001', '2024-01-15', 150.00, '40597166')
    ON CONFLICT (id_boleta) DO NOTHING;

-- 16. FACTURA
INSERT INTO factura (id_factura, serie, numero, fecha_emision, total, ruc_cliente) VALUES
    (1, 'F001', '0001', '2024-01-15', 1180.00, '20123456789')
    ON CONFLICT (id_factura) DO NOTHING;

-- 17. VENTA (PARA PROBAR STATE)
INSERT INTO venta (id_venta, fecha_venta, estado, total, cliente_id_cliente) VALUES
    (1, '2024-01-15 10:00:00', 'PENDIENTE', 150.00, 1)
    ON CONFLICT (id_venta) DO NOTHING;

-- 18. DETALLE VENTA
INSERT INTO detalle_venta (id_detalle, cantidad, precio_unitario, descuento, subtotal, id_producto, id_venta) VALUES
                                                                                                                  (1, 2, 25.00, 0.00, 50.00, 2, 1),
                                                                                                                  (2, 2, 45.00, 0.00, 90.00, 3, 1)
    ON CONFLICT (id_detalle) DO NOTHING;

-- 19. COTIZACION
INSERT INTO cotizacion (id_cotizacion, fecha, total_estimado) VALUES
    (1, '2024-01-10', 500.00)
    ON CONFLICT (id_cotizacion) DO NOTHING;

-- 20. CARRITO
INSERT INTO carrito_compra (id_carrito, fecha_creacion, estado) VALUES
    (1, '2024-01-10', 'ACTIVO')
    ON CONFLICT (id_carrito) DO NOTHING;

-- 21. PAGO
INSERT INTO pago (id_pago, monto, fecha_pago) VALUES
    (1, 150.00, '2024-01-15')
    ON CONFLICT (id_pago) DO NOTHING;

-- 22. ENVIO SUNAT
INSERT INTO envio_sunat (codigo_envio, estado_envio, fecha_envio) VALUES
    ('ENV-001', 'PROCESADO_ACEPTADO', '2024-01-15')
    ON CONFLICT (codigo_envio) DO NOTHING;

-- 23. NOTA CREDITO
INSERT INTO nota_credito (id, serie, numero, fecha_emision, total, motivo) VALUES
    (1, 'NC001', '0001', '2024-01-16', 50.00, 'Devolucion de producto')
    ON CONFLICT (id) DO NOTHING;

-- 24. PERMISOS
INSERT INTO permiso (id_permiso, nombre_permiso, descripcion) VALUES
                                                                  (1, 'CREATE_VENTA', 'Permite crear ventas'),
                                                                  (2, 'DELETE_VENTA', 'Permite eliminar ventas')
    ON CONFLICT (id_permiso) DO NOTHING;

-- 25. ROLES_PERMISOS
INSERT INTO roles_permisos (id_rol, id_permiso) VALUES
                                                    (1, 1),
                                                    (1, 2)
    ON CONFLICT (id_rol, id_permiso) DO NOTHING;

-- 26. SESION
INSERT INTO sesion (id_sesion, fecha_inicio, fecha_fin, activa, id_usuario) VALUES
    (1, '2024-01-15 08:00:00', '2024-01-15 17:00:00', false, 1)
    ON CONFLICT (id_sesion) DO NOTHING;

-- 27. MOVIMIENTO FINANCIERO
INSERT INTO movimiento_financiero (id_movimiento, tipo, monto, fecha) VALUES
    (1, 'INGRESO', 150.00, '2024-01-15')
    ON CONFLICT (id_movimiento) DO NOTHING;

-- 28. MOVIMIENTO INVENTARIO
INSERT INTO movimientos_inventario (id_movimiento, tipo_movimiento, cantidad, fecha_movimiento, motivo, id_producto) VALUES
    (1, 'SALIDA', 2, '2024-01-15 10:00:00', 'Venta', 2)
    ON CONFLICT (id_movimiento) DO NOTHING;

-- 29. AUDITORIA
INSERT INTO auditorias (id_auditoria, accion, fecha, descripcion) VALUES
    (1, 'LOGIN', '2024-01-15 08:00:00', 'Usuario admin inicio sesion')
    ON CONFLICT (id_auditoria) DO NOTHING;

-- 30. REPORTE
INSERT INTO reporte (id_reporte, tipo_reporte, fecha_generacion) VALUES
    (1, 'VENTAS', '2024-01-15')
    ON CONFLICT (id_reporte) DO NOTHING;