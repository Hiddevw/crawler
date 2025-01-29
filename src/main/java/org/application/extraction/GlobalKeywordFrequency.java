package org.application.extraction;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class GlobalKeywordFrequency {

    private final Map<String, Integer> globalFrequency = new HashMap<>();

    public void update(List<String> keywords) {
        for (String keyword : keywords) {
            globalFrequency.put(keyword, globalFrequency.getOrDefault(keyword, 0) + 1);
        }
    }

    public void printTopKeywords(int limit) {
        globalFrequency.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .limit(limit)
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }
}
