package com.lean.trebol.domain.port.in.evento;

import com.lean.trebol.domain.model.Evento;
import java.time.LocalDateTime;

/**
 * Puerto de entrada para el caso de uso de creación de un evento.
 */
public interface CrearEventoUseCase {

    /**
     * Ejecuta la lógica para registrar un nuevo evento deportivo.
     * 
     * @param nombre Nombre del evento (ej: "Real Madrid vs Barcelona").
     * @param fechaInicio Fecha y hora programada para el inicio.
     * @return El evento creado.
     */
    Evento ejecutar(String nombre, LocalDateTime fechaInicio);
}
