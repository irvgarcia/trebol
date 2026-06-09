package com.lean.trebol.domain.port.in.cuota;

import com.lean.trebol.domain.model.Cuota;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Puerto de entrada para el caso de uso de creación de una cuota.
 */
public interface CrearCuotaUseCase {

    /**
     * Ejecuta la lógica para añadir una nueva cuota (selección) a un mercado.
     * 
     * @param mercadoId ID del mercado al que pertenece la cuota.
     * @param seleccion Texto de la selección (ej: "Local", "Empate", "2.5 goles").
     * @param valor Valor numérico de la cuota (ej: 1.85).
     * @return La cuota creada.
     */
    Cuota ejecutar(UUID mercadoId, String seleccion, BigDecimal valor);
}
