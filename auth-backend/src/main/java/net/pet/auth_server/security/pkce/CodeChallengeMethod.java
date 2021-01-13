package net.pet.auth_server.security.pkce;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public enum CodeChallengeMethod {
    S256 {
        @Override
        public String decode(String codeVerifier) {
            try {
                byte[] bytes = codeVerifier.getBytes(StandardCharsets.US_ASCII);
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                digest.update(bytes);
                byte[] hash = digest.digest();
                return Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
            } catch (NoSuchAlgorithmException e) {
                throw new IllegalStateException(e);
            }
        }
    },
    PLAIN {
        @Override
        public String decode(String codeVerifier) {
            return codeVerifier;
        }
    },
    NONE {
        @Override
        public String decode(String codeVerifier) {
            throw new UnsupportedOperationException();
        }
    };

    public abstract String decode(String codeVerifier);
}
