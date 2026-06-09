package com.lean.trebol.domain.port.in.billetera;

import com.lean.trebol.domain.valueobject.Dinero;
import java.util.UUID;

/**
 * Puerto de entrada para el caso de uso de depósito de dinero.
 */
public interface DepositarDineroUseCase {

    /**
     * Ejecuta la lógica para añadir fondos a la billetera de un usuario.
     * 
     * @param usuarioId ID del usuario dueño de la billetera.
     * @param monto Cantidad de dinero a depositar.
     */
    void ejecutar(UUID usuarioId, Dinero monto);
}
