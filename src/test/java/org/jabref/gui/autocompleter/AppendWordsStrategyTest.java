package org.jabref.gui.autocompleter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppendWordsStrategyTest {

    private AppendWordsStrategy aws;

    @BeforeEach
    public void setUp() { aws = new AppendWordsStrategy(); }

    @Test
    void testEmptyString() {
        String input = "";
        AutoCompletionInput result = aws.analyze(input);
        assertEquals("", result.getPrefix());
        assertEquals("", result.getUnfinishedPart());
    }

    @Test
    void testWithNoDelimiter() {
        String input = "NomeArquivo";
        AutoCompletionInput result = aws.analyze(input);
        assertEquals("", result.getPrefix());
        assertEquals(input, result.getUnfinishedPart());
    }


    @Test
    void testWithDelimiterInBeginOfInput() {
        String input = " NomeArquivo";
        AutoCompletionInput result = aws.analyze(input);
        assertEquals(" ", result.getPrefix());
        assertEquals("NomeArquivo", result.getUnfinishedPart());
    }

    @Test
    void testWithDelimiterBetweenTwoStrings() {
        String input = "prefix rest";
        AutoCompletionInput result = aws.analyze(input);
        assertEquals("prefix ", result.getPrefix());
        assertEquals("rest", result.getUnfinishedPart());
    }

    @Test
    void testWithMultipleWhiteSpaces() {
        String input = "that is the prefix - rest";
        AutoCompletionInput result = aws.analyze(input);
        assertEquals("that is the prefix - ", result.getPrefix());
        assertEquals("rest", result.getUnfinishedPart());
    }

    @Test
    void testWithDelimiterInEndOfInput() {
        String input = "NomeArquivo ";
        AutoCompletionInput result = aws.analyze(input);
        assertEquals(input, result.getPrefix());
        assertEquals("", result.getUnfinishedPart());
    }

}
