# noScrabble

Ein Scrabble-ähnliches Brettspiel mit JavaFX-Oberfläche und einem serverseitigen Backend. Zwei Spieler legen abwechselnd Buchstaben auf ein Spielfeld, bilden Wörter und sammeln Punkte.

## Features

- Lokales 2-Spieler-Spiel im Runden-System
- JavaFX-GUI mit FXML-Oberflächen (Startmenü, Spieler-Eingabe, Spielfeld, Leaderboard)
- Buchstaben-Pool und Punktemultiplikatoren (z. B. Doppelbuchstabenwert)
- Deutsche Wortvalidierung anhand einer lokalen Wortliste
- Leaderboard mit sortierter Endstand-Anzeige
- JUnit-Tests für Spielbrett, Punkteberechnung und Wort-Service

## Technologien

- **Sprache**: Java 21
- **Build-Tool**: Gradle 8.5
- **GUI**: JavaFX  (Module `controls`, `fxml`)
- **Backend**: Jakarta Servlet API, Jersey (JAX-RS), SQLite, Jackson
- **Tests**: JUnit 5

## Installation

### Voraussetzungen

- JDK 21 oder neuer
- Gradle-Wrapper wird mitgeliefert (`gradlew` / `gradlew.bat`)

### Repository klonen

```bash
git clone https://github.com/FishMacc/noScrabble.git
cd noScrabble
```

### Projekt bauen

```bash
./gradlew build
```

Unter Windows:

```bash
gradlew.bat build
```

## Nutzung

### GUI starten

```bash
./gradlew :ns-gui:run
```

Startklasse: `de.nocompany.noscrabble.gui.StarterApp`

### Server starten (Backend)

```bash
./gradlew :ns-server:run
```

Für einen Tomcat-Deploy kann die WAR-Datei über den `deploy`-Task kopiert werden (erfordert `tomcat.home` in `gradle.properties`).

### Tests ausführen

```bash
./gradlew test
```

## Projektstruktur

```
noScrabble/
├── ns-gui/              # JavaFX-Frontend
│   ├── src/main/java/de/nocompany/noscrabble/gui/
│   │   ├── StarterApp.java
│   │   ├── SpielfeldController.java
│   │   ├── SpielerEingabeController.java
│   │   └── ...
│   └── src/main/resources/fxml/
│       ├── startseiteMenu.fxml
│       ├── spielerEingabe.fxml
│       ├── Spielfeld.fxml
│       └── ...
├── ns-server/           # Backend & Spiellogik
│   ├── src/main/java/de/nocompany/noscrabble/
│   │   ├── service/serviceSpiel/        # Spielablauf
│   │   ├── service/serviceSpielbrett/   # Spielfeld-Logik
│   │   ├── service/serviceWoerter/      # Wortvalidierung
│   │   ├── model/                       # Spieler, Pool, Punkte, Leaderboard
│   │   └── dao/                         # Datenzugriff (Wörter, Spielbrett)
│   └── src/main/resources/dictionary/
│       └── deu_woerter_raw.txt          # Deutsche Wortliste
├── settings.gradle
└── README.md            # Diese Datei
```

## Beispiel: Spielablauf

1. `StarterApp` starten.
2. Spielernamen eingeben.
3. Der erste Spieler erhält Buchstaben aus dem `SpielsteinPool`.
4. Buchstaben per Mausklick auf dem Spielfeld platzieren.
5. Gültige Wörter werden anhand der deutschen Wortliste geprüft und Punkte berechnet.
6. Nach jeder Runde wechselt der aktive Spieler.
7. Am Ende zeigt das Leaderboard den Gewinner an.

## Lizenz

Keine Lizenzdatei im Repository hinterlegt.
