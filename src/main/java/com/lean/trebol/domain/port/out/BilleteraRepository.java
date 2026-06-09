package com.lean.trebol.domain.port.out;

import com.lean.trebol.domain.model.Billetera;

import java.util.Optional;
import java.util.UUID;

public interface BilleteraRepository {
    Billetera save(Billetera billetera);
    Optional<Billetera> findByUsuarioId(UUID usuarioId);
}
