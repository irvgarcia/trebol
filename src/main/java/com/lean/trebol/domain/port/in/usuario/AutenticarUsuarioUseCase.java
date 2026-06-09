package com.lean.trebol.domain.port.in.usuario;

import com.lean.trebol.domain.model.Usuario;

/**
 * Puerto de entrada para el caso de uso de autenticación.
 */
public interface AutenticarUsuarioUseCase {

    /**
     * Valida las credenciales del usuario.
     * 
     * @param email Correo electrónico.
     * @param password Contraseña proporcionada.
     * @return El usuario si las credenciales son válidas.
     * @throws RuntimeException si las credenciales son incorrectas.
     */
    Usuario ejecutar(String email, String password);
}
