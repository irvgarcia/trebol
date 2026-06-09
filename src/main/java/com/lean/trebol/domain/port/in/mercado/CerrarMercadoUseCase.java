package com.lean.trebol.domain.port.in.mercado;

import java.util.UUID;

/**
 * Puerto de entrada para el caso de uso de cierre de un mercado.
 */
public interface CerrarMercadoUseCase {

    /**
     * Cierra el mercado para dejar de aceptar apuestas.
     * 
     * @param mercadoId ID del mercado a cerrar.
     */
    void ejecutar(UUID mercadoId);
}
