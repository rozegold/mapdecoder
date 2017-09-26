package main;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class MapDecoderImplTest {


    private MapDecoder subject;
    private String inputNull = null;
    private String inputEmpty = "";
    private String input = "A=apple&B=ball&C=cat";
    private String inputWithKeyValue = "A=apple";

    private String inputWithEmptyKey = "=value";
    private String inputWithEmptyValue = "key=";
    private String inputWithInvalidFormatNoAmp = "A=appleB=ballC=cat";
    private String inputWithInvalidFormatNoEqual = "A=apple&Bball&C=cat";

    @org.junit.Before
    public void setUp() throws Exception {
        subject = new MapDecoderImpl();
    }


    @Test
    public void shouldReturnNullForNullInput() {
        assertEquals(null, subject.getDecodedMap(inputNull));

    }
    @Test
    public void shouldReturnEmptyMapForEmptyInput() {
        assertEquals(0, subject.getDecodedMap(inputEmpty).size());
        assertEquals(new HashMap<>(), subject.getDecodedMap(inputEmpty));
    }

    @Test
    public void shouldSplitStringByAmpSymbol(){
        assertEquals("A=apple", subject.splitByAmpSymbol(input)[0]);
    }

    @Test
    public void shouldSplitStringByEqualsSymbol(){
        assertEquals("A", subject.splitByEqualsSymbol(inputWithKeyValue)[0]);
        assertEquals("apple", subject.splitByEqualsSymbol(inputWithKeyValue)[1]);
    }

    @Test
    public void shouldSplitStringByEqualsSymbolForInputWithEmptyKey(){
        assertEquals("", subject.splitByEqualsSymbol(inputWithEmptyKey)[0]);
        assertEquals("value", subject.splitByEqualsSymbol(inputWithEmptyKey)[1]);
    }

    @Test
    public void shouldSplitStringByEqualsSymbolForInputWithEmptyValue(){
        assertEquals("key", subject.splitByEqualsSymbol(inputWithEmptyValue)[0]);
        assertEquals("", subject.splitByEqualsSymbol(inputWithEmptyValue)[1]);
    }

    @Test
    public void shouldReturnMapForInput() {
        assertEquals(3, subject.getDecodedMap(input).size());
        assertEquals("apple", subject.getDecodedMap(input).get("A"));
        assertEquals("ball", subject.getDecodedMap(input).get("B"));
        assertEquals("cat", subject.getDecodedMap(input).get("C"));
    }

    @Test
    public void shouldReturnMapForInputWithEmptyKey() {
        assertEquals(1, subject.getDecodedMap(inputWithEmptyKey).size());
        assertEquals("value", subject.getDecodedMap(inputWithEmptyKey).get(""));
    }

    @Test
    public void shouldReturnMapForInputWithEmptyValue() {
        assertEquals(1, subject.getDecodedMap(inputWithEmptyKey).size());
        assertEquals("", subject.getDecodedMap(inputWithEmptyValue).get("key"));
    }

    /*@Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForInvalidInputNoAmp(){
        subject.getDecodedMap(inputWithInvalidFormatNoAmp);
    }*/

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForInvalidInputNoEquals(){
        subject.getDecodedMap(inputWithInvalidFormatNoEqual);
    }


}