package org.application.extraction;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class WordFilterTest {


    @Test
    void givenStopWord_WhenIsStopWordIsCalled_ThenReturnsTrue() {
        assertTrue(WordFilter.isStopWord("and"));
        assertTrue(WordFilter.isStopWord("the"));
        assertTrue(WordFilter.isStopWord("http"));
        assertTrue(WordFilter.isStopWord("www"));
    }

    @Test
    void givenNonStopWord_WhenIsStopWordIsCalled_ThenReturnsFalse() {
        assertFalse(WordFilter.isStopWord("java"));
        assertFalse(WordFilter.isStopWord("programming"));
        assertFalse(WordFilter.isStopWord("awesome"));
    }

    @Test
    void givenStopWordWithDifferentCase_WhenIsStopWordIsCalled_ThenReturnsTrue() {
        assertTrue(WordFilter.isStopWord("AND"));
        assertTrue(WordFilter.isStopWord("ThE"));
        assertTrue(WordFilter.isStopWord("HtTp"));
    }

    @Test
    void givenNonAlphabeticString_WhenIsStopWordIsCalled_ThenReturnsFalse() {
        assertFalse(WordFilter.isStopWord("123"));
        assertFalse(WordFilter.isStopWord("@#&*"));
        assertFalse(WordFilter.isStopWord("http123"));
    }

    @Test
    void givenEmptyString_WhenIsStopWordIsCalled_ThenReturnsFalse() {
        assertFalse(WordFilter.isStopWord(""));
    }

    @Test
    void givenNullWord_WhenIsStopWordIsCalled_ThenThrowsException() {
        assertThrows(NullPointerException.class, () -> WordFilter.isStopWord(null));
    }
}