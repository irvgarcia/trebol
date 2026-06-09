package com.lean.trebol.domain.port.in.evento;

import java.util.UUID;

/**
 * Puerto de entrada para el caso de uso de inicio de un evento.
 * Generalmente llamado por un proceso automático (Scheduler).
 */
public interface IniciarEventoUseCase {

    /**
     * Cambia el estado del evento a EN_CURSO.
     * 
     * @param eventoId ID del evento a iniciar.
     */
    void ejecutar(UUID eventoId);
}
