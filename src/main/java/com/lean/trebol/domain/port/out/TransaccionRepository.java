package com.lean.trebol.domain.port.out;

import com.lean.trebol.domain.model.Transaccion;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransaccionRepository {
    Transaccion save(Transaccion transaccion);
    Optional<Transaccion> findById(UUID id);
    List<Transaccion> findByBilleteraId(UUID billeteraId);
}
