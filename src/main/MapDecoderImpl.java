package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MapDecoderImpl implements MapDecoder {
    @Override
    public Map<String, String> getDecodedMap(String input) {

        if (null == input) {
            return null;
        }

        if (input.isEmpty()) {
            return new HashMap<>();
        }

        String[] keyAndValueStr = splitByAmpSymbol(input);

        return Arrays
                .stream(keyAndValueStr)
                .map(this::splitByEqualsSymbol)
                .collect(Collectors.toMap(inputArr -> inputArr[0], inputArr -> inputArr[1]));
    }

    public String[] splitByAmpSymbol(String input) {
        return input.split("&");
    }

    public String[] splitByEqualsSymbol(String input) {
        if (null == input)
            return new String[0];

        String[] split = input.split("=");

        //TODO : Improve this
        if (split.length < 2) {
            if ('=' == input.charAt(input.length() - 1)) {
                String[] emptyVal = new String[2];
                emptyVal[0] = input.substring(0, input.length() - 1);
                emptyVal[1] = new String();
                return emptyVal;
            }
            throw new IllegalArgumentException("Missing = ");
        }


        return split;
    }

}
