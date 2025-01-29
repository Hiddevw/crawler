package org.application.link;

import org.application.core.CrawlerNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Queue;
import java.util.Set;

public class LinkHandler {

    private LinkHandler() {
    }

    public static void handleLinks(Document doc, CrawlerNode currentNode, Set<String> visitedUrls,
                                   Queue<CrawlerNode> toVisit) {

        Elements links = doc.select("a[href]");
        for (Element link : links) {
            String href = link.attr("abs:href");
            if (!visitedUrls.contains(href)) {
                toVisit.add(new CrawlerNode(href, currentNode.depth() + 1));
            }
        }
    }
}


