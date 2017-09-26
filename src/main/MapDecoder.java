package main;

import java.util.Map;

public interface MapDecoder {
    Map<String, String> getDecodedMap(String input);
    String[] splitByAmpSymbol(String input);
    String[] splitByEqualsSymbol(String input);
}
