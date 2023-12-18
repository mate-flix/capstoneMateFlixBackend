package com.mteflix.capstonemateflixbackend.mail;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

public class PasswordGenerator {
    @Component
    public class Utils {
        public static String generateDefaultPassword(int length) {
            String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            SecureRandom random = new SecureRandom();

            StringBuilder password = new StringBuilder();
            for (int i = 0; i < length; i++) {
                int randomIndex = random.nextInt(allowedChars.length());
                password.append(allowedChars.charAt(randomIndex));
            }
            return password.toString();
        }
    }
}
