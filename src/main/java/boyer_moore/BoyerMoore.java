package boyer_moore;

public class BoyerMoore {
    private final int ASCII_TABLE_SIZE = 256;

    public int search(char[] array, char[] pattern) {
        if (pattern == null || pattern.length == 0) {
            return 0;
        }
        if (array == null) {
            return -1;
        }
        int[] badCharacterTable = preComputeBadCharacterTable(pattern);
        int[] suffixTable = preComputeSuffixTable(pattern);
        for (int i = pattern.length - 1, j; i < array.length;) {
            for (j = pattern.length - 1; pattern[j] == array[i]; i--, j--) {
                if (j == 0) {
                    return i;
                }
            }
            i += Math.max(suffixTable[pattern.length - 1 - j], badCharacterTable[array[i]]);
        }
        return -1;
    }

    public int[] preComputeSuffixTable(char[] pattern) {
        int[] table = new int[pattern.length];
        computePrefix(pattern, table);
        computeSuffix(pattern, table);
        return table;
    }

    public int[] preComputeBadCharacterTable(char[] pattern) {
        int[] table = new int[ASCII_TABLE_SIZE];
        for (int i = 0; i < ASCII_TABLE_SIZE; i++) {
            table[i] = pattern.length;
        }
        for (int t = 0; t < pattern.length - 1; t++) {
            table[pattern[t]] = Math.max(1, pattern.length - t - 1);
        }
        if (table[pattern[pattern.length - 1]] < pattern.length) {
            table[pattern[pattern.length - 1]] = 1;
        }
        return table;
    }

    public void computePrefix(char[] pattern, int[] table) {
        int lastPrefixPosition = pattern.length;
        for (int i = pattern.length; i > 0; i--) {
            if (isPrefix(pattern, i)) {
                lastPrefixPosition = i;
            }
            table[pattern.length - i] = lastPrefixPosition - i + pattern.length;
        }
    }

    public void computeSuffix(char[] pattern, int[] table) {
        for (int i = 0; i < pattern.length - 1; i++) {
            int length = suffixLength(pattern, i);
            table[length] = pattern.length - 1 - i + length;
        }
    }
    public boolean isPrefix(char[] pattern, int index) {
        for (int i = index, j = 0; i < pattern.length; i++, j++) {
            if (pattern[i] != pattern[j]) {
                return false;
            }
        }
        return true;
    }

    public int suffixLength(char[] pattern, int index) {
        int length = 0;
        int j = pattern.length - 1;
        for (int i = index; i >= 0 && pattern[i] == pattern[j]; i--, j--) {
            length++;
        }
        return length;
    }
}
