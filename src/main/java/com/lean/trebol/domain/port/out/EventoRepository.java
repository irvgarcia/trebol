package com.lean.trebol.domain.port.out;

import com.lean.trebol.domain.model.Evento;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventoRepository {
    Evento save(Evento evento);
    Optional<Evento> findById(UUID id);
    List<Evento> findAll();
}
