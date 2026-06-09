package com.lean.trebol.domain.port.out;

import com.lean.trebol.domain.model.Apuesta;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ApuestaRepository {
    Apuesta save(Apuesta apuesta);
    Optional<Apuesta> findById(UUID id);
    List<Apuesta> findByUsuarioId(UUID usuarioId);
}
