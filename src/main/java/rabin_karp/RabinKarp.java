package rabin_karp;

public class RabinKarp {
    private final int prime = 3;

    public int search(char[] array, char[] pattern) {
        if (array == null || pattern == null) {
            return -1;
        }
        int n = array.length;
        int m = pattern.length;
        int lastChar = n - m;

        long patternHash = calculateHash(pattern, m);
        long arrayHash = calculateHash(array, m);
        for (int i = 0; i <= lastChar; i++) {
            if (patternHash == arrayHash && match(array, pattern, i)) {
                return i;
            }
            if (i < lastChar) {
                arrayHash = recalculateHash(arrayHash, array[i], array[i + m], m);
            }
        }
        return -1;
    }

    public long recalculateHash(long oldHash, char oldChar, char newChar, int hashSize) {
        long hash = oldHash - shiftCharacterValue(oldChar);
        hash = hash / prime;
        hash += shiftCharacterValue(newChar) * Math.pow(prime, hashSize - 1);
        return hash;
    }

    public boolean match(char[] array, char[] pattern, int index) {
        for (int i = 0; i < pattern.length; i++) {
            if (array[index + i] != pattern[i]) {
                return false;
            }
        }
        return true;
    }

    public long calculateHash(char[] text, int hashSize) {
        long hash = 0;
        for (int i = 0; i < hashSize; i++) {
            hash += shiftCharacterValue(text[i]) * Math.pow(prime, i);
        }
        return hash;
    }

    int shiftCharacterValue(char c) {
        return c - 96;
    }
}
