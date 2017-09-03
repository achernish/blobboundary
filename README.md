# Concordance

A Java-based application which generates a concordance, i.e. an alphabetical list of all word occurrences, labeled with word frequencies.

## Getting started

NOTE: The application requires that Java 8, Maven 3 are installed.

To build the application:

    $ mvn clean package

To start the application:

    $ java -jar target/concordance-0.0.1-SNAPSHOT-jar-with-dependencies.jar 'path to text file'