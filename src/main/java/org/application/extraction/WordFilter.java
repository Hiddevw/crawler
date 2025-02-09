package org.application.extraction;


import java.util.Set;

public class WordFilter {

    private WordFilter() {
    }

    static boolean isStopWord(String word) {
        Set<String> stopWords = Set.of(
                "'ll", "'tis", "'twas", "'ve", "a", "a's", "able", "about", "above", "after", "again", "against",
                "all", "am", "an", "and", "any", "are", "aren't", "as", "at", "be", "because", "been", "before",
                "being", "below", "between", "both", "but", "by", "can", "can't", "cannot", "could", "couldn't",
                "did", "didn't", "do", "does", "doesn't", "doing", "don't", "down", "during", "each", "few",
                "for", "from", "further", "had", "hadn't", "has", "hasn't", "have", "haven't", "having", "he",
                "he'd", "he'll", "he's", "her", "here", "here's", "hers", "herself", "him", "himself", "his",
                "how", "how's", "i", "i'd", "i'll", "i'm", "i've", "if", "in", "into", "is", "isn't", "it",
                "it's", "its", "itself", "let's", "me", "more", "most", "mustn't", "my", "myself", "no", "nor",
                "not", "of", "off", "on", "once", "only", "or", "other", "ought", "our", "ours", "ourselves",
                "out", "over", "own", "same", "shan't", "she", "she'd", "she'll", "she's", "should", "shouldn't",
                "so", "some", "such", "than", "that", "that's", "the", "their", "theirs", "them", "themselves",
                "then", "there", "there's", "these", "they", "they'd", "they'll", "they're", "they've", "this",
                "those", "through", "to", "too", "under", "until", "up", "very", "was", "wasn't", "we", "we'd",
                "we'll", "we're", "we've", "were", "weren't", "what", "what's", "when", "when's", "where",
                "where's", "which", "while", "who", "who's", "whom", "why", "why's", "with", "won't", "would",
                "wouldn't", "you", "you'd", "you'll", "you're", "you've", "your", "yours", "yourself",
                "yourselves",

                "https", "http", "ftp", "tcp", "ip", "css", "javascript", "php", "url", "json", "xml", "log",
                "pdf", "cookie", "wikipedia", "www", "www2", "biz", "org", "com",

                "privacy", "policy", "search", "community", "tools", "content", "menu", "contact", "special",
                "create", "projects", "main", "download", "account", "mobile", "edit", "portal", "navigation",
                "contributions", "topic", "retrieved", "statistics", "developers", "donate", "link", "disclaimers",
                "links", "jump", "actions", "version", "read", "history", "file", "logged", "events",
                "appearance", "random", "hide", "commons"
        );

        return stopWords.contains(word.toLowerCase());
    }
}





