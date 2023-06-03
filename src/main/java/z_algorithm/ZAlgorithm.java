package z_algorithm;

public class ZAlgorithm {
    private char SEPARATOR = '$';

    public int search(char[] pattern, char[] array) {
        int z[] = createZTable(pattern, array);
        for (int i = pattern.length + 1; i < z.length; i++) {
            if (z[i] == pattern.length) {
                return i - pattern.length - 1;
            }
        }
        return -1;
    }

    public int[] searchAll(char[] pattern, char[] array) {
        int[] result = new int[array.length];
        int z[] = createZTable(pattern, array);
        for (int i = pattern.length + 1; i < z.length; i++) {
            if (z[i] == pattern.length) {
                result[i - pattern.length - 1] = z[i];
            }
        }
        return result;
    }
    public int[] createZTable(char[] pattern, char[] array) {
        int[] z = new int[pattern.length + array.length + 1];
        char[] longArray = createLongArray(pattern, array);
        int left = 0;
        int right = 0;
        for (int i = 1; i < longArray.length; i++) {
            if (i > right) {
                left = right = i;
                while (right < longArray.length && longArray[right - left] == longArray[right]) {
                    right++;
                }
                z[i] = right - left;
                right--;
            } else {
                int k = i - left;
                if (z[k] < right - i + 1) {
                    z[i] = z[k];
                } else {
                    left = i;
                    while (right < longArray.length && longArray[right - left] == longArray[right]) {
                        right++;
                    }
                    z[i] = right - left;
                    right--;
                }
            }
        }
        return z;
    }

    public char[] createLongArray(char[] pattern, char[] array) {
        char[] longArray = new char[pattern.length + array.length + 1];
        for (int i = 0; i < pattern.length; i++) {
            longArray[i] = pattern[i];
        }
        longArray[pattern.length] = SEPARATOR;
        for (int i = 0; i < array.length; i++) {
            longArray[i + pattern.length + 1] = array[i];
        }
        return longArray;
    }
}
