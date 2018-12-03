package cipher.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CesarCipherTest {
    private String plainText;
    private String cipherText;
    private int key;

    @BeforeEach
    void setUpEasy() {
        plainText = "abc123";
        cipherText = "cde345";
        key = 2;
    }

    @Test
    void code() {
        assertEquals(cipherText, new CesarCipher(plainText, key).code());
    }

    @Test
    void decode() {
        assertEquals(plainText, new CesarCipher(cipherText, key).decode());
    }

    @Test
    void adequate() {
        String coded = (new CesarCipher(plainText, key)).code();
        String decoded = (new CesarCipher(coded, key)).decode();
        assertEquals(plainText, decoded);
    }
}