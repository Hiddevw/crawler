package org.application.link;

import org.application.core.CrawlerNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LinkHandlerTest {

    private Document mockDocument;
    private CrawlerNode currentNode;
    private Set<String> visitedUrls;
    private Queue<CrawlerNode> toVisit;

    @BeforeEach
    void setUp() {
        mockDocument = mock(Document.class);
        currentNode = new CrawlerNode("http://example.com", 1);
        visitedUrls = Set.of("http://example.com");
        toVisit = new LinkedList<>();
    }

    @Test
    void givenNewLinks_WhenHandleLinksIsCalled_ThenLinksAreAddedToQueue() {
        Element mockLink1 = mock(Element.class);
        Element mockLink2 = mock(Element.class);

        when(mockLink1.attr("abs:href")).thenReturn("http://example.com/page1");
        when(mockLink2.attr("abs:href")).thenReturn("http://example.com/page2");

        Elements mockElements = new Elements(mockLink1, mockLink2);
        when(mockDocument.select("a[href]")).thenReturn(mockElements);

        LinkHandler.handleLinks(mockDocument, currentNode, visitedUrls, toVisit);

        assertEquals(2, toVisit.size());
        assertEquals("http://example.com/page1", toVisit.poll().url());
        assertEquals("http://example.com/page2", toVisit.poll().url());
    }

    @Test
    void givenVisitedLinks_WhenHandleLinksIsCalled_ThenLinksAreNotAddedToQueue() {
        Element mockLink1 = mock(Element.class);
        Element mockLink2 = mock(Element.class);

        when(mockLink1.attr("abs:href")).thenReturn("http://example.com");
        when(mockLink2.attr("abs:href")).thenReturn("http://example.com/page1");

        Elements mockElements = new Elements(mockLink1, mockLink2);
        when(mockDocument.select("a[href]")).thenReturn(mockElements);

        LinkHandler.handleLinks(mockDocument, currentNode, visitedUrls, toVisit);

        assertEquals(1, toVisit.size());
        assertEquals("http://example.com/page1", toVisit.poll().url());
    }

}