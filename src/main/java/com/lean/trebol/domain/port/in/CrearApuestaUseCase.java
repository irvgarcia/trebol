package com.lean.trebol.domain.port.in;

import com.lean.trebol.domain.model.Apuesta;
import com.lean.trebol.domain.valueobject.Dinero;
import java.util.UUID;

/**
 * Puerto de entrada para el caso de uso de creación de una apuesta.
 */
public interface CrearApuestaUseCase {
    
    /**
     * Ejecuta la lógica para realizar una nueva apuesta en el sistema.
     * 
     * @param usuarioId ID del usuario que realiza la apuesta.
     * @param mercadoId ID del mercado donde se apuesta.
     * @param cuotaId ID de la cuota seleccionada.
     * @param monto Cantidad de dinero a apostar.
     * @return La apuesta creada y persistida.
     */
    Apuesta ejecutar(UUID usuarioId, UUID mercadoId, UUID cuotaId, Dinero monto);
}
