# WebCrawler 

## Overzicht

Dit project is een webcrawler geschreven in Java. Het crawlt websites, verzamelt sleutelwoorden en verwerkt links op basis van vooraf ingestelde configuraties. Dit project is gemaakt als onderdeel van een sollicitatiegesprek.

## Functionaliteiten

- Crawlt websites en verzamelt sleutelwoorden uit de inhoud.
- Filtert veelvoorkomende stopwoorden uit de sleutelwoordenlijst.
- Beperkt de diepte van het crawlen en respecteert tijdslimieten.
- Houdt een lijst bij van bezochte URL’s om duplicaten te vermijden.
- Unit-tests met JUnit en Mockito voor maximale testcoverage.
- Ondersteunt Spring Boot voor eenvoudigere configuratie en logging.

## Installatie & Setup

### Vereisten

- Java 17+ (of de versie die je gebruikt hebt)
- Maven of Gradle (voor dependency management)
- IntelliJ IDEA, Eclipse of een andere IDE naar keuze

### Builden en Runnen

1. **Clone de repository:**

   ```sh
   git clone https://github.com/Hiddevw/crawler.git
   ```

2. **Compileer het project met Maven:**

   ```sh
   mvn clean install
   ```

3. **Voer het programma uit:**

   ```sh
   mvn spring-boot:run
   ```
## Configuratie

De crawlerconfiguratie kan worden aangepast in `WebCrawlerApplication.java`. Standaard instellingen:

- **Start-URL:** `https://en.wikipedia.org/wiki/Open-source_intelligence`
- **Maximale diepte:** `5`
- **Tijdslimiet:** `300 seconden`

Wil je een andere website crawlen? Pas `WebCrawlerApplication.java` aan of geef parameters mee bij het starten.

## Testen

Unit-tests zijn geschreven in JUnit 5 en Mockito.

Om de tests uit te voeren:

```sh
mvn test
```
## Projectstructuur

📂 **src/main/java/org/application/**

- `WebCrawler.java` → De hoofdklasse voor het crawlen van websites.
- `CrawlerConfig.java` → Beheer van configuratie-instellingen.
- `KeywordExtractor.java` → Analyseert en filtert sleutelwoorden.
- `LinkHandler.java` → Verwerkt links op basis van HTML-documenten.
- `WebCrawlerApplication.java` → Main class voor Spring Boot-integratie.

📂 **src/test/java/org/application/**

- `WebCrawlerTest.java` → Unit-tests voor de WebCrawler-functionaliteit.
- `KeywordExtractorTest.java` → Unit-tests voor sleutelwoordextractie.
- `LinkHandlerTest.java` → Unit-tests voor linkverwerking.
- `GlobalKeywordFrequencyTest.java` → Unit-tests voor de frequentieanalyse van sleutelwoorden.

## Veelgestelde vragen

### ❓ Hoe pas ik de lijst met stopwoorden aan?
De stopwoorden zijn gedefinieerd in `WordFilter.java`. Voeg hier extra woorden toe om ze te filteren.




