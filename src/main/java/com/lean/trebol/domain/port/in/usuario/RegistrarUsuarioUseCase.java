package com.lean.trebol.domain.port.in.usuario;

import com.lean.trebol.domain.model.Usuario;

/**
 * Puerto de entrada para el caso de uso de registro de un nuevo usuario.
 */
public interface RegistrarUsuarioUseCase {

    /**
     * Registra un nuevo usuario en el sistema. 
     * Nota: La billetera asociada se crea automáticamente en la implementación.
     * 
     * @param email Correo electrónico del usuario.
     * @param password Contraseña en texto plano (será hasheada en la aplicación).
     * @return El usuario creado.
     */
    Usuario ejecutar(String email, String password);
}
