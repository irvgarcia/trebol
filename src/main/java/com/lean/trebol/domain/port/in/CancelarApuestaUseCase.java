package com.lean.trebol.domain.port.in;

import java.util.UUID;

/**
 * Puerto de entrada para el caso de uso de cancelación de una apuesta.
 */
public interface CancelarApuestaUseCase {

    /**
     * Ejecuta la lógica para cancelar una apuesta activa.
     * 
     * @param apuestaId ID de la apuesta a cancelar.
     */
    void ejecutar(UUID apuestaId);
}
