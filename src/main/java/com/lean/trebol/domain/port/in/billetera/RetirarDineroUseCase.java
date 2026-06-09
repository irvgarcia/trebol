package com.lean.trebol.domain.port.in;

import com.lean.trebol.domain.valueobject.Dinero;
import java.util.UUID;

/**
 * Puerto de entrada para el caso de uso de retiro de dinero.
 */
public interface RetirarDineroUseCase {

    /**
     * Ejecuta la lógica para retirar fondos de la billetera de un usuario.
     * 
     * @param usuarioId ID del usuario dueño de la billetera.
     * @param monto Cantidad de dinero a retirar.
     * @throws com.lean.trebol.domain.exception.SaldoInsuficienteException si no hay fondos suficientes.
     */
    void ejecutar(UUID usuarioId, Dinero monto);
}
