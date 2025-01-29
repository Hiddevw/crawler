package org.application.config;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CrawlerConfig {
    private String startUrl;
    private int maxDepth;
    private int timeLimit;

    public CrawlerConfig(String startUrl, int maxDepth, int timeLimit) {
        this.startUrl = startUrl;
        this.maxDepth = maxDepth;
        this.timeLimit = timeLimit;
    }

}

