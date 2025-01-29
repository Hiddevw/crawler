package org.application.extraction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeywordExtractor {

    private KeywordExtractor() {
    }

    /**
     * Extracts the top N most frequent keywords from the given text.
     *
     * @param text The input text to analyze.
     * @return A list of keywords sorted by frequency in descending order.
     * Filters out words shorter than 3 characters and stop words defined in {@link WordFilter}.
     * Splits text into words, counts their frequency, and returns the top {@code topN}.
     * @throws NullPointerException     If {@code text} is null.
     * @throws IllegalArgumentException If {@code topN} is negative.
     */
    public static List<String> extractKeywords(String text) {
        String[] words = text.toLowerCase().split("\\W+");
        Map<String, Integer> frequencyMap = new HashMap<>();

        for (String word : words) {
            if (word.length() > 2 && !WordFilter.isStopWord(word)) {
                frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
            }
        }

        return frequencyMap.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .map(Map.Entry::getKey)
                .toList();
    }
}

