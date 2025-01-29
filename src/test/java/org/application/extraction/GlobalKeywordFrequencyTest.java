package org.application.extraction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class GlobalKeywordFrequencyTest {

    GlobalKeywordFrequency globalKeywordFrequency = new GlobalKeywordFrequency();
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void givenMultipleKeywords_WhenPrintTopKeywordsIsCalled_ThenTopNKeywordsArePrinted() {
        globalKeywordFrequency.update(List.of("java", "code", "java", "python", "code", "java"));
        globalKeywordFrequency.printTopKeywords(2);
        String expectedOutput = "java: 3\r\ncode: 2\r\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void givenLimitExceedingKeywords_WhenPrintTopKeywordsIsCalled_ThenAllKeywordsArePrinted() {
        globalKeywordFrequency.update(List.of("java", "code"));
        globalKeywordFrequency.printTopKeywords(5);
        String expectedOutput = "java: 1\r\ncode: 1\r\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void givenNoKeywords_WhenPrintTopKeywordsIsCalled_ThenNoOutputIsPrinted() {
        globalKeywordFrequency.printTopKeywords(5);
        assertEquals("", outContent.toString());
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

}