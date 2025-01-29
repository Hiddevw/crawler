package org.application.core;

import lombok.Getter;
import org.application.extraction.GlobalKeywordFrequency;
import org.application.extraction.KeywordExtractor;
import org.application.link.LinkHandler;
import org.application.config.CrawlerConfig;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WebCrawler {

    private final CrawlerConfig config;
    @Getter
    private final GlobalKeywordFrequency keywordFrequency;
    private final Set<String> visitedUrls;
    private final Queue<CrawlerNode> toVisit;

    public WebCrawler(CrawlerConfig config) {
        this.config = config;
        this.keywordFrequency = new GlobalKeywordFrequency();
        this.visitedUrls = new HashSet<>();
        this.toVisit = new LinkedList<>();
    }

    public void start() {
        toVisit.add(new CrawlerNode(config.getStartUrl(), 0));

        long startTime = System.currentTimeMillis();

        while (!toVisit.isEmpty()) {
            if (hasReachedTimeLimit(startTime)) {
                System.out.println("Time limit reached. Exiting.");
                break;
            }

            CrawlerNode currentNode = toVisit.poll();
            if (!shouldProcessNode(currentNode)) continue;

            processNode(currentNode);

            if (currentNode.depth() < config.getMaxDepth()) {
                try {
                    Document doc = Jsoup.connect(currentNode.url()).get();
                    String text = doc.body().text();
                    List<String> keywords = KeywordExtractor.extractKeywords(text);
                    keywordFrequency.update(keywords);

                    LinkHandler.handleLinks(doc, currentNode, visitedUrls, toVisit);
                } catch (IOException e) {
                    System.err.println("Failed to fetch page: " + currentNode.url() + ". Error: " + e.getMessage());
                }
            }
        }
        System.out.println("Crawl finished.");
    }

    private boolean hasReachedTimeLimit(long startTime) {
        long elapsed = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - startTime);
        return elapsed >= config.getTimeLimit();
    }

    private boolean shouldProcessNode(CrawlerNode node) {
        return !visitedUrls.contains(node.url()) && node.depth() <= config.getMaxDepth();
    }

    private void processNode(CrawlerNode node) {
        System.out.println("Processing (depth " + node.depth() + "): " + node.url());
        visitedUrls.add(node.url());
    }

    public Set<String> getVisitedUrls() {
        return new HashSet<>(visitedUrls);
    }

}


