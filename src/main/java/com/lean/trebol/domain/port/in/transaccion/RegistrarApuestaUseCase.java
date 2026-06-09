package com.lean.trebol.domain.port.in.transaccion;

import com.lean.trebol.domain.model.Transaccion;
import com.lean.trebol.domain.valueobject.Dinero;
import java.util.UUID;

/**
 * Caso de uso para registrar una transacción vinculada a una apuesta colocada.
 */
public interface RegistrarApuestaUseCase {
    Transaccion ejecutar(UUID billeteraId, Dinero monto, UUID apuestaId);
}
