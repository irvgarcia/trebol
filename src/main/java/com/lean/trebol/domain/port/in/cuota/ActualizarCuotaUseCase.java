package com.lean.trebol.domain.port.in.cuota;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Puerto de entrada para el caso de uso de actualización de una cuota.
 */
public interface ActualizarCuotaUseCase {

    /**
     * Ejecuta la lógica para cambiar el valor de una cuota existente.
     * 
     * @param cuotaId ID de la cuota a actualizar.
     * @param nuevoValor Nuevo valor numérico de la cuota.
     */
    void ejecutar(UUID cuotaId, BigDecimal nuevoValor);
}
