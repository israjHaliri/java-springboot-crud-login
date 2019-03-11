package com.haliri.israj.appservice.config;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Base64;

/**
 * Created by israjhaliri on 12/10/17.
 */
@Component
public class CustomPasswordEncoderConfig implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        String hashed = BCrypt.hashpw(decodeValue(rawPassword), BCrypt.gensalt(12));
        return hashed;
    }
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return BCrypt.checkpw(decodeValue(rawPassword), encodedPassword);
    }

    private String decodeValue(CharSequence rawPassword){
        String decoded = new String(Base64.getDecoder().decode(rawPassword.toString()));
        return new String(decoded);
    }
}
