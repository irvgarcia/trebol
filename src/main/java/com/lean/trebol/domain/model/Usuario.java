package com.lean.trebol.domain.model;

import com.lean.trebol.domain.model.enums.Rol;

import java.time.LocalDateTime;
import java.util.UUID;

public class Usuario {

    private UUID id;

    private String email;

    private String passwordHash;

    private Rol rol;

    private LocalDateTime creado_desde;

    public Usuario() {
    }

    public Usuario(String email, String passwordHash, Rol rol) {
        if (email == null || email.isBlank()){
            throw new IllegalArgumentException("El email no puede estar vacío");
        }
        if (passwordHash == null || passwordHash.isBlank()){
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }
        if (rol == null){
            throw new IllegalArgumentException("El rol es obligatorio");
        }
        this.id = UUID.randomUUID();
        this.email = email;
        this.passwordHash = passwordHash;
        this.rol = rol;
        this.creado_desde = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Rol getRol() {
        return rol;
    }

    public LocalDateTime getCreado_desde() {
        return creado_desde;
    }
}
