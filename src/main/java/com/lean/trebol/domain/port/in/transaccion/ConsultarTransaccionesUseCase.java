package com.lean.trebol.domain.port.in;

import com.lean.trebol.domain.model.Transaccion;
import java.util.List;
import java.util.UUID;

/**
 * Puerto de entrada para consultar el historial de transacciones.
 */
public interface ConsultarTransaccionesUseCase {

    /**
     * Obtiene todas las transacciones asociadas a la billetera de un usuario.
     * 
     * @param usuarioId ID del usuario.
     * @return Lista de transacciones ordenadas por fecha.
     */
    List<Transaccion> ejecutar(UUID usuarioId);
}
