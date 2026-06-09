package com.lean.trebol.domain.port.out;

import com.lean.trebol.domain.model.Cuota;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CuotaRepository {
    Cuota save(Cuota cuota);
    Optional<Cuota> findById(UUID id);
    List<Cuota> findByMercadoId(UUID mercadoId);
}
