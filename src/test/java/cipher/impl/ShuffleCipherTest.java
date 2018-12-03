package cipher.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShuffleCipherTest {
    private String plainText;
    private String cipherText;
    private int[] key;

    @BeforeEach
    void setUp() {
        plainText = "abcdef12";
        cipherText = "cbafed21";
        key = new int[]{2, 1, 0};
    }

    @Test
    void code() {
        assertEquals(cipherText, new ShuffleCipher(plainText, key).code());
    }

    @Test
    void decode() {
        assertEquals(plainText, new ShuffleCipher(cipherText, key).decode());
    }

    @Test
    void adequate() {
        String coded = (new ShuffleCipher(plainText, key)).code();
        String decoded = (new ShuffleCipher(coded, key)).decode();
        assertEquals(plainText, decoded);
    }

}