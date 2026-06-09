package com.lean.trebol.domain.port.out;

import com.lean.trebol.domain.model.Mercado;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MercadoRepository {
    Mercado save(Mercado mercado);
    Optional<Mercado> findById(UUID id);
    List<Mercado> findByEventoId(UUID eventoId);
}
