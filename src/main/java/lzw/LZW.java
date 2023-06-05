package lzw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LZW {
    private int dictionarySize = 256;
    public List<Integer> compress(String text) {
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
}
