package boyer_moore;

public class BoyerMoore {
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
