package knuth_morris_pratt;

public class KnuthMorrisPratt {

    public int search(char[] array, char[] pattern) {
        int[] lsp = computeLongestSuffixPrefixTable(pattern);
        int j = 0;
        for (int i = 0; i < array.length; i++) {
            while (j > 0 && array[i] == pattern[j]) {
                j = lsp[j - 1];
            }
            if (array[i] == pattern[j]) {
                j++;
                if (j == pattern.length) {
                    return i - (j - 1);
                }
            }
        }
        return -1;
    }
    public int[] computeLongestSuffixPrefixTable(char[] pattern) {
        int[] lsp = new int[pattern.length];
        int j = 0;
        for (int i = 1; i < pattern.length; i++) {
            while (j > 0 && pattern[i] != pattern[j]) {
                j = lsp[j - 1];
            }
            if (pattern[i] == pattern[j]) {
                lsp[i] = j + 1;
                j++;
            } else {
                lsp[i] = 0;
            }
        }
        return lsp;
    }
}
