package org.application.extraction;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class KeywordExtractorTest {

    @Test
    void givenValidText_WhenExtractKeywordsIsCalled_ThenReturnsTopKeywords() {
        String text = "Java is a programming language. Java is widely used in software development.";
        List<String> result = KeywordExtractor.extractKeywords(text);

        assertTrue(result.containsAll(List.of("java", "development", "software")));
        assertEquals(7, result.size());
    }

    @Test
    void givenTextWithStopWords_WhenExtractKeywordsIsCalled_ThenFiltersOutStopWords() {
        String text = "The cat and the dog are playing in the garden.";
        List<String> result = KeywordExtractor.extractKeywords(text);

        assertTrue(result.containsAll(List.of("cat", "playing", "garden")));
        assertEquals(4, result.size());
    }

    @Test
    void givenShortWords_WhenExtractKeywordsIsCalled_ThenFiltersOutShortWords() {
        String text = "A big dog ran. It is a small dog.";
        List<String> result = KeywordExtractor.extractKeywords(text);

        assertTrue(result.containsAll(List.of("dog", "small", "big")));
        assertEquals(4, result.size());
    }

    @Test
    void givenEmptyText_WhenExtractKeywordsIsCalled_ThenReturnsEmptyList() {
        String text = "";
        List<String> result = KeywordExtractor.extractKeywords(text);

        assertTrue(result.isEmpty());
    }

    @Test
    void givenNullText_WhenExtractKeywordsIsCalled_ThenThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> KeywordExtractor.extractKeywords(null));
    }

    @Test
    void givenTextWithTopNMoreThanUniqueWords_WhenExtractKeywordsIsCalled_ThenReturnsAllUniqueKeywords() {
        String text = "Java and Kotlin are programming languages.";
        List<String> result = KeywordExtractor.extractKeywords(text);

        assertTrue(result.containsAll(List.of("java", "kotlin", "programming", "languages")));
        assertEquals(4, result.size());
    }
}