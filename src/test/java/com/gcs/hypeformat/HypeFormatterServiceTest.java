package com.gcs.hypeformat;

import com.intellij.testFramework.fixtures.BasePlatformTestCase;
import org.junit.Test;

public class HypeFormatterServiceTest extends BasePlatformTestCase {

    private HypeFormatterService service;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        service = new HypeFormatterService(getProject());
    }

    @Test
    public void testFormatTextWithSimpleAssignment() {
        String input = "int x=5;";
        String expected = "int x = 5;";
        String result = service.formatText(input);
        assertEquals(expected, result);
    }

    @Test
    public void testFormatTextWithArithmeticOperators() {
        String input = "int result=a+b*c-d/e;";
        String expected = "int result = a + b * c - d / e;";
        String result = service.formatText(input);
        assertEquals(expected, result);
    }

    @Test
    public void testFormatTextWithBraces() {
        String input = "if(condition){doSomething();}";
        String expected = "if(condition) {doSomething();}";
        String result = service.formatText(input);
        assertEquals(expected, result);
    }

    @Test
    public void testFormatTextWithIndentation() {
        String input = "        int x = 5;";
        String expected = "        int x = 5;";
        String result = service.formatText(input);
        assertEquals(expected, result);
    }

    @Test
    public void testFormatTextWithMultipleLines() {
        String input = "int x=5;\nint y=10;";
        String expected = "int x = 5;\nint y = 10;";
        String result = service.formatText(input);
        assertEquals(expected, result);
    }

    @Test
    public void testFormatTextWithEmptyLines() {
        String input = "int x = 5;\n\nint y = 10;";
        String expected = "int x = 5;\n\nint y = 10;";
        String result = service.formatText(input);
        assertEquals(expected, result);
    }

    @Test
    public void testFormatTextWithCommas() {
        String input = "method(a,b,c);";
        String expected = "method(a, b, c);";
        String result = service.formatText(input);
        assertEquals(expected, result);
    }

    @Test
    public void testFormatTextWithParentheses() {
        String input = "result = ( a + b ) * c;";
        String expected = "result =(a + b)* c;";
        String result = service.formatText(input);
        assertEquals(expected, result);
    }
}