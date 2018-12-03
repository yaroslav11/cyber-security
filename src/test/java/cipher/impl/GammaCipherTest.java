package cipher.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GammaCipherTest {
    private String plainText;
    private String cipherText;
    private String key;

    @BeforeEach
    void setUp() {
        plainText = "abc123";
        cipherText = "def456";
        key = String.valueOf((char)((int)(3)));
    }

    @Test
    void code() {
        assertEquals(cipherText, (new GammaCipher(plainText, key)).code());
    }

    @Test
    void decode() {
        assertEquals(plainText, (new GammaCipher(cipherText, key)).decode());
    }

    @Test
    void adequate() {
        String coded = (new GammaCipher(plainText, key)).code();
        String decoded = (new GammaCipher(coded, key)).decode();
        assertEquals(plainText, decoded);
    }
}