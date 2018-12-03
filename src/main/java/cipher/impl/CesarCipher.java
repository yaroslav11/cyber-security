package cipher.impl;


import cipher.Cipher;

public class CesarCipher implements Cipher {
    private String text;
    private int key;

    public CesarCipher(String text, int key) {
        this.text = text;
        this.key = key;
    }

    private String shift(String src, int shift) {
        if (src == null || src.isEmpty()) {
            return src;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < src.length(); i++) {
            stringBuilder.append((char) (shift + src.charAt(i)));
        }
        return stringBuilder.toString();
    }

    @Override
    public String code() {
        return shift(text, key);
    }

    @Override
    public String decode() {
        return shift(text, -key);
    }
}
