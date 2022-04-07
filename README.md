# praktikum-gruppe-06

# Einleitung und Ziele

## Überblick über die Anforderungen

Webbasiertes Selbstabrufsystem, mit dem sich Studierende selbst für Urlaubstage abmelden und für Prüfungen anmelden können

## Qualitätsziele

- Effiziente und zuverlässige Umsetzung der Kernfunktionen
- Unterschiedliche Berechtigungskontrollen für Benutzer mit unterschiedlichen Identitäten
- Nutzung der dauerhaften Speicherung
- Webbasierte Benutzeroberfläche
- Zwiebelstrukturiertes System
- Hinzufügen der Protokollfunktion für die Nachvollziehbarkeit
- Vollständige Prüfung

# Umfang und Kontext

## Business-Kontext

![未命名文件 (1).png](praktikum-%20c71de/%E6%9C%AA%E5%91%BD%E5%90%8D%E6%96%87%E4%BB%B6_(1).png)

## Technischer Kontext

Die Benutzeroberfläche basiert auf einer Web-Implementierung mit HTML und css.
Das Back-End verwendet das Spring-Framework und nutzt Spring Date JDBC zum Hinzufügen, Löschen und Überprüfen von MariaDB-Datenbanken, die in Docker ausgeführt werden.

# Lösungen und Strategien

## Kernstrategien

Das Herzstück dieses Systems ist die Überprüfung und Speicherung der Urlaubs- und Prüfungsunterlagen der Studenten. Da die Urlaubsanträge von Studenten einer Reihe von Anforderungen genügen müssen, sind mehrere Beurteilungen über Zeit und Datum der Urlaubsanträge erforderlich. Darüber hinaus wird auch die Kombination mehrerer aufeinander folgender Urlaubsanträge und zweier Urlaubsanträge an einem Tag berücksichtigt. Für Prüfungen können sich die Studierenden vor und nach der Prüfung mehr Zeit nehmen.
Wenn der aktuelle Urlaubssatz jedes Mal gespeichert wird. würden sich komplexe und langwierige Beurteilungen ergeben, wenn mehrere Urlaube zusammengelegt werden, Urlaub und Prüfungszeiten übereinstimmen usw.

Lassen Sie uns also anders denken. Für jeden Schüler stehen täglich 4 Stunden Übungszeit zur Verfügung, und da wir 15 Minuten als Mindestmaßeinheit für Urlaub und Prüfungen verwenden, teilen wir jedes kleine Segment in 15 Minuten auf, so dass die 4 Stunden in 16 kleine Segmente aufgeteilt werden. Wir bezeichnen jedes Segment als "Gitter". Für den Tag eines Schülers kann man sich dies als eine Kettentabelle mit 16 Elementen vorstellen, wie im Diagramm dargestellt (der Standardwert ist weiß und zeigt die erwartete Anwesenheit zu diesem Zeitpunkt an)

![未命名文件 (2).png](praktikum-%20c71de/%E6%9C%AA%E5%91%BD%E5%90%8D%E6%96%87%E4%BB%B6_(2).png)

Wenn wir uns für diese 15 Minuten beurlauben lassen, kann dieses eine Raster als rot markiert werden, während Prüfungen als grün markiert werden können, wenn der Schüler abwesend ist, können sie als grau markiert werden usw. So kann eine 1-stündige Beurlaubung als Markierung der entsprechenden 4 Zellen in Rot gesehen werden, z. B. ein Schüler, der an diesem Tag von 11 Uhr bis 12 Uhr beurlaubt ist, die markierte Kette ist im Diagramm dargestellt

![未命名文件 (3).png](praktikum-%20c71de/%E6%9C%AA%E5%91%BD%E5%90%8D%E6%96%87%E4%BB%B6_(3).png)

Wenn der Schüler zwischen 12:00 und 12:30 Uhr erneut abwesend ist, wird die Kettentabelle wie folgt aussehen

![未命名文件 (4).png](praktikum-%20c71de/%E6%9C%AA%E5%91%BD%E5%90%8D%E6%96%87%E4%BB%B6_(4).png)

Sie können sehen, dass die beiden unmittelbar benachbarten Feiertage auf natürliche Weise ineinander übergehen werden.

Angenommen, ich muss an einem bestimmten Tag von 13:00 bis 14:00 Uhr eine Offline-Prüfung ablegen, dann würde die Kettentabelle für diesen Tag wie folgt aussehen (da es sich um eine Offline-Prüfung handelt, erhält man 2 zusätzliche Stunden, bevor die Prüfung beginnt)

![未命名文件 (5).png](praktikum-%20c71de/%E6%9C%AA%E5%91%BD%E5%90%8D%E6%96%87%E4%BB%B6_(5).png)

An dieser Stelle sind unsere Grundgedanken erläutert worden, aber wir müssen auch einige Sonderfälle berücksichtigen

Was passiert mit unserer Kettentabelle, wenn ich bereits zwischen 11:00 und 12:30 Uhr frei genommen habe, nun aber auch noch zwischen 13:00 und 14:00 Uhr eine Offline-Prüfung ablegen muss (die beiden oben genannten Situationen finden ja am selben Tag statt)?

![未命名文件 (5).png](praktikum-%20c71de/%E6%9C%AA%E5%91%BD%E5%90%8D%E6%96%87%E4%BB%B6_(5).png)

Bemerkenswert ist nur, dass die sechs ursprünglich roten Blöcke zwischen 11 und 12.30 Uhr wieder grün markiert wurden.

Wir erlauben zwei Beurlaubungen zu Beginn und am Ende des Tages mit einem Abstand von mehr als 90 Minuten, aber sonst nichts. Dies erfordert, dass wir eine Beurteilung der markierten Kettentabelle vornehmen. Betrachten wir die Fälle, in denen der Urlaub zwischen 9.30 und 10.00 Uhr bzw. 13.00 und 13.30 Uhr genommen wurde

![未命名文件 (6).png](praktikum-%20c71de/%E6%9C%AA%E5%91%BD%E5%90%8D%E6%96%87%E4%BB%B6_(6).png)

Wir können zunächst feststellen, ob der Kopf und das Ende mit einem roten Block beginnen, und dann zwei Zeiger von vorne und hinten verwenden, um vom ersten weißen Block am Kopf und Ende in entgegengesetzte Richtungen zu gehen und die Anzahl der weißen Blöcke zu zählen, die wir vor dem roten Block angetroffen haben, wie folgt

![未命名文件 (7).png](praktikum-%20c71de/%E6%9C%AA%E5%91%BD%E5%90%8D%E6%96%87%E4%BB%B6_(7).png)

Denn wenn die Anzahl der weißen Blöcke, die von den beiden Zeigern gezählt werden, gleich, größer oder gleich 6 ist und die Anzahl der roten Blöcke am Anfang und am Ende gleich 16 ist, können wir sagen, dass es sich um eine Auszeit am Anfang und am Ende des Tages mit einem Abstand von größer oder gleich 90 Minuten handelt, was natürlich erlaubt ist.

Was ist, wenn der Urlaub zwischen 10:00 und 10:45 Uhr und zwischen 12:00 und 12:30 Uhr genommen wird?

![未命名文件 (9).png](praktikum-%20c71de/%E6%9C%AA%E5%91%BD%E5%90%8D%E6%96%87%E4%BB%B6_(9).png)

Zuerst zählen wir die Anzahl der roten Blöcke, dann stellen wir fest, dass sie nicht beide rot sind, indem wir den ersten und den letzten Block beurteilen. Also benutzen wir den Kopf- und den Schwanzzeiger, um wieder in entgegengesetzte Richtungen zu reisen, wobei wir jeweils die Anzahl der weißen Blöcke zählen, die wir passieren, bevor wir auf einen roten Block treffen.

![未命名文件 (8).png](praktikum-%20c71de/%E6%9C%AA%E5%91%BD%E5%90%8D%E6%96%87%E4%BB%B6_(8).png)

Wenn die Summe der Anzahl der weißen Blöcke, die von den beiden Zeigern gezählt werden, plus die Anzahl der roten Blöcke nicht 16 beträgt, können wir sagen, dass es weiße Blöcke zwischen den roten Blöcken an beiden Enden geben muss, d.h. es gibt mehr als zwei Enden des Blattes und es ist nicht ein Fall von einem Ende und einem Ende. Dies ist nicht erlaubt.

Unsere Beurteilungsmethode gilt auch für korrekte Urlaubsanträge

![未命名文件 (10).png](praktikum-%20c71de/%E6%9C%AA%E5%91%BD%E5%90%8D%E6%96%87%E4%BB%B6_(10).png)

Die Summe der Anzahl der weißen Blöcke, die von den beiden Zeigern gezählt werden, plus die Anzahl der roten Blöcke ergibt 16, was eine korrekte Urlaubsanfrage ist.

Es ist erwähnenswert, dass wir die erwarteten Urlaubsinformationen in der Kettentabelle markieren müssen, bevor wir ein Urteil über die Kettentabelle fällen können, so dass es Fälle gibt, in denen nicht erlaubte Urlaubsinformationen bereits von uns in die Kettentabelle aufgenommen wurden. Dazu müssen wir eine temporäre Kettentabelle einführen, die aus der aktuell gültigen Kettentabelle kopiert wird. Wir fügen den erwarteten Urlaub direkt in die temporäre Kettentabelle ein und beurteilen dann die temporäre Kettentabelle, wenn das Urteil durchgeht, wird die temporäre Kettentabelle die ursprüngliche Kettentabelle ersetzen und zur aktuell gültigen Kettentabelle werden. Wenn das Urteil nicht Bestand hat, wird es automatisch vom System zerstört.

Das Verfahren ist in der Abbildung dargestellt

![未命名文件 (11).png](praktikum-%20c71de/%E6%9C%AA%E5%91%BD%E5%90%8D%E6%96%87%E4%BB%B6_(11).png)

1 Erstellen Sie eine verknüpfte Tabelle, indem Sie Daten aus der Datenbank lesen

2 Erstellen Sie eine exakte Kopie der Kettentabelle und fügen Sie den gewünschten Urlaub in die kopierte temporäre Kettentabelle ein

3 Entspricht die Kettentabelle nach dem Einfügen des gewünschten Blattes immer noch der Spezifikation (d. h. sie besteht die Beurteilung), wird sie als aktuell gültige Kettentabelle festgelegt

4 Schreiben Sie den Wert der gültigen Verknüpfungstabelle in die Datenbank zurück

5 Wenn die Kette nach dem Einfügen des gewünschten Urlaubs nicht der Spezifikation entspricht (d.h. das Urteil nicht besteht), wird sie automatisch vom System zerstört.

### Strategie der Log

Wir haben in der Server-Schicht eine Protokollierungsfunktion (Log) implementiert, die automatisch den Zeitpunkt, die Identität und die Einzelheiten der folgenden Vorgänge aufzeichnet

- Studenten fügen Feiertage hinzu
- Studenten löschen Ferien
- Studenten fügen Prüfungen hinzu
- Studenten löschen Klausuren
- Studenten fügen eine fakultative Prüfung hinzu
- Studenten werden automatisch für ihre erste Anmeldung registriert

## Strategie der Datenspeicherung

Wir haben vier Tabellen in der Datenbank erstellt, um alle Schülernamen und OAIDs, Urlaubsinformationen für alle Schüler, Informationen zur Prüfungsanmeldung für alle Schüler und eine Tabelle zur Speicherung optionaler Prüfungsinformationen zu speichern.

Informationen über die Tabelle sind wie folgt


**student**

| header | explain |
| --- | --- |
| id |  Automatisch generierte ID |
| oaid | Aus dem Schülernamen generierte ID |
| name | Name des Schülers |

**exam**

| header | explain |
| --- | --- |
| id |  Automatisch generierte ID |
| name | Name der Prüfung |
| begin | Startzeit |
| end | Ende der Zeit |
| online | ob die Prüfung online stattfindet |
| studentid | Studentenausweis für diese Prüfung |

**examalreadyexusts**

| header | explain |
| --- | --- |
| id |  Automatisch generierte ID |
| name | Name der Prüfung |
| begin | Startzeit |
| end | Ende der Zeit |
| online | ob die Prüfung online stattfindet |
| lsfid | ID dieser Prüfung auf LSF |

**holiday**

| header | explain |
| --- | --- |
| id |  Automatisch generierte ID |
| begin | Startzeit |
| end | Ende der Zeit |
| studentid | Die Person, zu der dieser Urlaubssatz gehört |

**log**

| header | explain |
| --- | --- |
| id |  Automatisch generierte ID |
| type | Art der Operation |
| studentid | Die Person, zu der dieser Urlaubssatz gehört |
| name | wer |
| action | Ausführliche Erklärung der Bedienung |

# Ansicht der Systemarchitektur

![arch.png](praktikum-%20c71de/arch.png)

# Website-Karte


![未命名文件 (1).png](praktikum-%20c71de/%E6%9C%AA%E5%91%BD%E5%90%8D%E6%96%87%E4%BB%B6_(1)%201.png)

# Aufstellung

## Operatives Umfeld des Projekts

| JDK | 17 |
| --- | --- |
| Springframework | 2.6.4 |
| Mariadb (Docker) | latest(10.5) |

## Passwort für das Datenbankkonto

| root |
| --- |
| iamgroot |

## Erster Start

### GitHub OAuth2
Sie müssen Umgebungsvariablen für GitHub OAuth2 konfigurieren

### Konfigurieren von Praktikumsinformationen

Bitte überprüfen Sie die Standardkonfiguration dieser Klasse (DefaultTime) und ändern Sie sie gegebenenfalls. Sein Weg ist

```java
chicken/src/main/java/hhu/propra2/group6/chicken/domain/time/DefaultTime.java
```

Die Standardkonfiguration sieht wie folgt aus

```java
package hhu.propra2.group6.chicken.domain.time;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DefaultTime {

//    important!
//    Please check the initial value for the first run

//    Set the size of the block, that is, how many minutes is a block. (unit minute)
//    Please note that the time allowed by the system should be divisible by the time size of the block you set
    private static int blockSizeOfMinuten = 15;

//    Set the time when the internship starts
    private static LocalDateTime begin = LocalDateTime.of(2022, 3, 7, 9, 30);

//    Set when the internship ends
    private static LocalDateTime end = LocalDateTime.of(2022, 3, 30, 13, 30);

//    The total length of time a student is allowed to leave (unit minute)
    private static int maxHolidayOfMinuten = 240;

//    The maximum allowable part-day leave in a day (unit minute)
    private static int maxPartHolidayFromOneDayOfMinuten = 150;

//    Allowed breaks for the start and end of online exams (unit minute)
    private static int freeTimeOfMinutenForOLExam = 30;

//    Allowable breaks at the beginning and end of non-online exams (unit minute)
    private static int freeTimeOfMinutenForNotOLExam = 120;
```

| variable name | Erklärung |
| --- | --- |
| blockSizeOfMinuten | block size |
| begin | Zeitpunkt des Abschlusses des Praktikums |
| end | Zeitpunkt des Abschlusses des Praktikums |
| maxHolidayOfMinuten | Maximale Gesamtdauer des Urlaubs für Studenten |
| maxPartHolidayFromOneDayOfMinuten | Höchstdauer des Urlaubs an einem einzigen Tag bei Nicht-Vollzeiturlaub |
| freeTimeOfMinutenForOLExam | Erlaubte Pausen vor und nach Online-Prüfungen |
| freeTimeOfMinutenForNotOLExam | Erlaubte Pausen vor und nach Offline-Prüfungen |

## Starten Sie das Programm

Sobald das Programm gestartet ist, rufen Sie es bitte über [http://localhost:8080/](http://localhost:8080/) auf.
