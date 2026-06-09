package com.lean.trebol.domain.port.in.evento;

import java.util.UUID;

/**
 * Puerto de entrada para el caso de uso de finalización de un evento.
 */
public interface FinalizarEventoUseCase {

    /**
     * Cambia el estado del evento a FINALIZADO.
     * 
     * @param eventoId ID del evento a finalizar.
     */
    void ejecutar(UUID eventoId);
}
