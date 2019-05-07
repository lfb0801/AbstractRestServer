package com.abstractrestapplication.components;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationComponent {

    public String hashPassword(final String plaintext) {
        return BCrypt.hashpw(plaintext, BCrypt.gensalt());
    }

    public boolean checkPassword(final String plaintext, final String hashed) {
        return BCrypt.checkpw(plaintext, hashed);
    }
}
