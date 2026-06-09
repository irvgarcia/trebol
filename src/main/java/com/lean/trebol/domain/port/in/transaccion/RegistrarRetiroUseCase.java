package com.lean.trebol.domain.port.in.transaccion;

import com.lean.trebol.domain.model.Transaccion;
import com.lean.trebol.domain.valueobject.Dinero;
import java.util.UUID;

/**
 * Caso de uso para registrar formalmente un retiro en el historial.
 */
public interface RegistrarRetiroUseCase {
    Transaccion ejecutar(UUID billeteraId, Dinero monto);
}
