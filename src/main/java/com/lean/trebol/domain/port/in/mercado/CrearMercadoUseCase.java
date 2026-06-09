package com.lean.trebol.domain.port.in.mercado;

import com.lean.trebol.domain.model.Mercado;
import java.util.UUID;

/**
 * Puerto de entrada para el caso de uso de creación de un mercado.
 */
public interface CrearMercadoUseCase {

    /**
     * Añade un nuevo mercado a un evento.
     * 
     * @param eventoId ID del evento al que pertenece el mercado.
     * @param nombre Nombre del mercado (ej: "Resultado Final", "Ambos marcan").
     * @return El mercado creado.
     */
    Mercado ejecutar(UUID eventoId, String nombre);
}
