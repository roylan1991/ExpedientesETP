package com.roylan.expedientes_etp.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Este clase gestiona la encriptación de contraseñas.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Component
public class EncriptarPassword {

    private BCryptPasswordEncoder bCryp;

    public EncriptarPassword() {
        this.bCryp = new BCryptPasswordEncoder(10);
    }

    /**
     * Esta funcionalidad permite encriptar una contraseña en texto plano.
     *
     * @param passwordTextoPlano Contraseña en texto plano.
     * @return <code>String</code> Contraseña encriptada.
     */
    public String encriptar(String passwordTextoPlano) {
        return bCryp.encode(passwordTextoPlano);
    }

    /**
     * Esta funcionalidad comprueba que una contraseña en texto plano coincide con una encriptada.
     *
     * @param passwordTextoPlano Contraseña en texto plano.
     * @param passwordEncriptado Contraseña encriptada.
     * @return <code>true</code> Si coinciden.
     */
    public boolean passwordMatch(String passwordTextoPlano, String passwordEncriptado) {
        if (getBCryptPasswordEncoder().matches(passwordTextoPlano, passwordEncriptado)) {
            return true;
        }
        return false;
    }

    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return bCryp;
    }
}