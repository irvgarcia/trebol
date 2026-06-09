package com.lean.trebol.domain.port.in;

import com.lean.trebol.domain.model.Billetera;
import java.util.UUID;

/**
 * Puerto de entrada para el caso de uso de consulta de billetera/saldo.
 */
public interface ConsultarBilleteraUseCase {

    /**
     * Obtiene el estado actual de la billetera de un usuario.
     * 
     * @param usuarioId ID del usuario.
     * @return El modelo de la billetera con sus saldos.
     */
    Billetera ejecutar(UUID usuarioId);
}
