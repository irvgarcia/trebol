package com.lean.trebol.domain.model;

import com.lean.trebol.domain.model.enums.Rol;
import com.lean.trebol.domain.valueobject.Email;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Usuario {

    private final UUID id;
    private final Email email;
    private final String passwordHash;
    private final Rol rol;
    private final LocalDateTime createdAt;

    private Usuario(UUID id, Email email, String passwordHash, Rol rol, LocalDateTime createdAt) {
        this.id = Objects.requireNonNull(id);
        this.email = Objects.requireNonNull(email);
        this.passwordHash = Objects.requireNonNull(passwordHash);
        this.rol = Objects.requireNonNull(rol);
        this.createdAt = Objects.requireNonNull(createdAt);
    }

    public static Usuario crear(String email, String passwordHash) {
        return new Usuario(
                UUID.randomUUID(),
                new Email(email),          // El VO valida el formato
                Objects.requireNonNull(passwordHash, "El passwordHash no puede ser null"),
                Rol.USER,            // Todo usuario nuevo es USER por defecto
                LocalDateTime.now()
        );
    }

    public static Usuario reconstituir(UUID id, String email, String passwordHash, Rol rol, LocalDateTime createdAt) {
        return new Usuario(id, new Email(email), passwordHash, rol, createdAt);
    }

    public boolean esAdmin() {
        return this.rol == Rol.ADMIN;
    }


    public UUID getId() { return id; }
    public Email getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public Rol getRol() { return rol; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        return id.equals(((Usuario) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Usuario{id=" + id + ", email=" + email + ", rol=" + rol + "}";
    }
}
