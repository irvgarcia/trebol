package com.lean.trebol.domain.port.in.evento;

import java.util.UUID;

/**
 * Puerto de entrada para el caso de uso de cancelación de un evento.
 */
public interface CancelarEventoUseCase {

    /**
     * Ejecuta la lógica para cancelar un evento (por suspensión, error, etc.).
     * 
     * @param eventoId ID del evento a cancelar.
     */
    void ejecutar(UUID eventoId);
}
