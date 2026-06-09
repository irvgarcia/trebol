package com.lean.trebol.domain.port.out;

import com.lean.trebol.domain.model.Usuario;
import com.lean.trebol.domain.valueobject.Email;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository {
    Usuario save(Usuario usuario);
    Optional<Usuario> findById(UUID id);
    Optional<Usuario> findByEmail(Email email);
}
