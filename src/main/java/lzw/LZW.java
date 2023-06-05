package lzw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LZW {
    public List<Integer> compress(String text) {
        int dictionarySize = 256;
        if (text == null) {
            return null;
        }
        List<Integer> result = new ArrayList<>();
        Map<String, Integer> dictionary = new HashMap<>();
        for (int i = 0; i < dictionarySize; i++) {
            dictionary.put(Character.toString(i), i);
        }

        String previous = "";
        for (char c : text.toCharArray()) {
            String combined = previous + c;
            if (dictionary.containsKey(combined)) {
                previous = combined;
            } else {
                result.add(dictionary.get(previous));
                dictionary.put(combined, dictionarySize++);
                previous = Character.toString(c);
            }
        }
        if (!previous.equals("")) {
            result.add(dictionary.get(previous));
        }
        return result;
    }

    public String decompress(List<Integer> compressed) {
        int dictionarySize = 256;
        if (compressed == null) {
            return null;
        }
        Map<Integer, String> dictionary = new HashMap<>();

        for (int i = 0; i < dictionarySize; i++) {
            dictionary.put(i, Character.toString(i));
        }
        String previous = Character.toString(compressed.remove(0));
        StringBuilder result = new StringBuilder(previous);
        for (int j : compressed) {
            String combined;
            if (dictionary.containsKey(j)) {
                combined = dictionary.get(j);
            } else if(j == dictionarySize) {
                combined = previous + previous.charAt(0);
            } else {
                return "-1";
            }
            result.append(combined);
            dictionary.put(dictionarySize++, previous + combined.charAt(0));
            previous = combined;
        }
        return result.toString();
    }
}
