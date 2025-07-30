package com.gcs.hypeformat.formatter;

import com.gcs.hypeformat.formatter.language.AbstractBasicFormatter;
import com.gcs.hypeformat.formatter.language.markup.HtmlFormatter;
import com.intellij.testFramework.fixtures.BasePlatformTestCase;
import org.junit.Test;

public class HtmlFormatterTest extends BasePlatformTestCase {

    private AbstractBasicFormatter formatter;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        formatter = new AbstractBasicFormatter();
        formatter.setNext(new HtmlFormatter());
    }

    @Test
    public void testFormatBasicHtmlTag() {
        String input = "<div>content</div>";
        String expected = "<div>content</div>";
        String result = formatter.format(input);
        assertEquals(expected, result);
    }

    @Test
    public void testFormatHtmlTagWithAttributes() {
        String input = "<div   class = \"container\"   id = \"main\" >content</div>";
        String expected = "<div class=\"container\" id=\"main\">content</div>";
        String result = formatter.format(input);
        assertEquals(expected, result);
    }

    @Test
    public void testFormatSelfClosingTag() {
        String input = "<img   src = \"image.jpg\"   alt = \"description\"   />";
        String expected = "<img src=\"image.jpg\" alt=\"description\" />";
        String result = formatter.format(input);
        assertEquals(expected, result);
    }

    @Test
    public void testFormatDoctype() {
        String input = "<!DOCTYPE   html>";
        String expected = "<!DOCTYPE html>";
        String result = formatter.format(input);
        assertEquals(expected, result);
    }

    @Test
    public void testFormatHtmlComments() {
        String input = "<!--   This is a comment   -->";
        String expected = "<!-- This is a comment -->";
        String result = formatter.format(input);
        assertEquals(expected, result);
    }

    @Test
    public void testFormatNestedHtmlTags() {
        String input = "<div class=\"outer\"><span   class=\"inner\">content</span></div>";
        String expected = "<div class=\"outer\"><span class=\"inner\">content</span></div>";
        String result = formatter.format(input);
        assertEquals(expected, result);
    }

    @Test
    public void testFormatHtmlWithMultipleAttributes() {
        String input = "<input   type = \"text\"   name = \"username\"   placeholder = \"Enter username\"   required   />";
        String expected = "<input type=\"text\" name=\"username\" placeholder=\"Enter username\" required />";
        String result = formatter.format(input);
        assertEquals(expected, result);
    }

    @Test
    public void testFormatHtmlTable() {
        String input = "<table><tr><td   class = \"cell\" >data</td></tr></table>";
        String expected = "<table><tr><td class=\"cell\">data</td></tr></table>";
        String result = formatter.format(input);
        assertEquals(expected, result);
    }

    @Test
    public void testFormatHtmlForm() {
        String input = "<form   action = \"/submit\"   method = \"post\" ><input type=\"submit\" value=\"Submit\" /></form>";
        String expected = "<form action=\"/submit\" method=\"post\"><input type=\"submit\" value=\"Submit\" /></form>";
        String result = formatter.format(input);
        assertEquals(expected, result);
    }

    @Test
    public void testFormatHtmlWithBooleanAttributes() {
        String input = "<input   type = \"checkbox\"   checked   disabled   />";
        String expected = "<input type=\"checkbox\" checked disabled />";
        String result = formatter.format(input);
        assertEquals(expected, result);
    }
}