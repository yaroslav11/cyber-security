package cipher.impl;

import cipher.Cipher;

import java.util.Arrays;
import java.util.List;

public class ShuffleCipher implements Cipher {

    private String text;
    private int[] key;
    private int keySize;

    public ShuffleCipher(String text, int[] key) {
        this.text = text;
        this.key = key;
        keySize = key.length;
        verifyKey(key);
    }

    private String shuffle(String src, int[] pattern) {
        if (src == null || src.isEmpty() || keySize == 0) {
            return src;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < src.length() / keySize; i++) {
            stringBuilder.append(fullShuffle(src.substring(i * keySize, (i + 1) * keySize), pattern));
        }
        stringBuilder.append(partialShuffle(src.substring(src.length() - src.length() % keySize), pattern));
        return stringBuilder.toString();
    }

    private char[] fullShuffle(String smallSrc, int[] pattern) {
        char[] dst = new char[keySize];
        for (int i = 0; i < keySize; i++) {
            dst[pattern[i]] = smallSrc.charAt(i);
        }
        return dst;
    }

    private char[] partialShuffle(String smallSrc, int[] pattern) {
        char[] dst = new char[smallSrc.length()];
        int dstIndex = 0;
        for (int newLetterPlace = 0; newLetterPlace < keySize; newLetterPlace++) {
            for (int i = 0; i < keySize; i++) {
                if (pattern[i] == newLetterPlace && i < smallSrc.length()) {
                    dst[dstIndex++] = smallSrc.charAt(i);
                }
            }
        }
        return dst;
    }

    private int[] generateAntiKey() {
        int[] antiKey = new int[keySize];
        for (int i = 0; i < keySize; i++) {
            int newIndex = key[i];
            antiKey[newIndex] = i;
        }
        verifyKey(antiKey);
        return antiKey;
    }

    void verifyKey(int[] key) {
        List listKey = Arrays.asList(key);
        for (int i = 0; i < listKey.size(); i++) {
            if (!listKey.contains(i)) {
                throw new IllegalStateException("Invalid key");
            }
        }
    }

    @Override
    public String code() {
        return shuffle(text, key);
    }

    @Override
    public String decode() {
        return shuffle(text, generateAntiKey());
    }
}
