package org.application;

import lombok.extern.slf4j.Slf4j;
import org.application.config.CrawlerConfig;
import org.application.core.WebCrawler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Slf4j
@SpringBootApplication
public class WebCrawlerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(WebCrawlerApplication.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("Starting web crawler...");
        CrawlerConfig config = new CrawlerConfig(
                "https://en.wikipedia.org/wiki/Open-source_intelligence",
                5,
                300
        );

        WebCrawler webCrawler = new WebCrawler(config);
        webCrawler.start();

        log.info("\nFinal aggregated keyword frequencies:");
        webCrawler.getKeywordFrequency().printTopKeywords(50);
    }
}
