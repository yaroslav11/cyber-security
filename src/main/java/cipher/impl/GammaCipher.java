package cipher.impl;

import cipher.Cipher;

public class GammaCipher implements Cipher {
    private String text;
    private String key;
    private int keySize;

    public GammaCipher(String text, String key) {
        this.text = text;
        this.key = key;
        keySize = key.length();
        verifyKey(key);
    }

    private String bitwiseOr(String src, String pattern) {
        if (src == null || src.isEmpty()) {
            return src;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < src.length(); i++) {
            stringBuilder.append((char) (src.charAt(i) + pattern.charAt(i % keySize)));
        }
        return stringBuilder.toString();
    }

    private String generateAntiKey() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0; i<keySize; i++){
            stringBuilder.append((char)(-1*key.charAt(i)));
        }
        String antiKey = stringBuilder.toString();
        verifyKey(antiKey);
        return antiKey;
    }

    void verifyKey(String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalStateException("Invalid key");
        }
    }

    @Override
    public String code() {
        return bitwiseOr(text, key);
    }

    @Override
    public String decode() {
        return bitwiseOr(text, generateAntiKey());
    }
}
