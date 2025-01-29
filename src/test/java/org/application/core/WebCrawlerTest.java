package org.application.core;

import org.application.config.CrawlerConfig;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WebCrawlerTest {


    private WebCrawler webCrawler;
    private CrawlerConfig mockConfig;

    @BeforeEach
    void setUp() {
        mockConfig = mock(CrawlerConfig.class);
        when(mockConfig.getStartUrl()).thenReturn("https://example.com");
        when(mockConfig.getMaxDepth()).thenReturn(2);
        when(mockConfig.getTimeLimit()).thenReturn(10);

        webCrawler = new WebCrawler(mockConfig);
    }

    @Test
    void givenStartUrl_WhenStartIsCalled_ThenUrlIsProcessed() throws IOException {
        Connection mockConnection = mock(Connection.class);
        Document mockDocument = mock(Document.class);

        when(mockDocument.body()).thenReturn(mock(Element.class));
        when(mockDocument.body().text()).thenReturn("Sample text for testing.");
        when(mockDocument.select("a[href]")).thenReturn(new Elements());
        when(mockConnection.get()).thenReturn(mockDocument);

        try (MockedStatic<Jsoup> mockedJsoup = mockStatic(Jsoup.class)) {
            mockedJsoup.when(() -> Jsoup.connect("https://example.com")).thenReturn(mockConnection);

            webCrawler.start();

            assertTrue(webCrawler.getVisitedUrls().contains("https://example.com"));
            assertNotNull(webCrawler.getKeywordFrequency());
        }
    }


    @Test
    void givenReachedTimeLimit_WhenStartIsCalled_ThenStopsCrawling() {
        when(mockConfig.getTimeLimit()).thenReturn(0); // Simulate immediate time limit
        webCrawler.start();

        assertTrue(webCrawler.getVisitedUrls().isEmpty());
    }
}