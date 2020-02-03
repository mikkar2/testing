# CI Server

The project is a Maven project using Java 13 and contains a jUnit test suite.
Documentation is generated from the javadoc in the master branch and is
published in the `gh-pages` branch in this repository.

## Running the tests

```bash
# Runs all the jUnit tests
$ mvn test
```

## Building the application

```bash
# The jar will be in target/CI-VERSION.jar
$ mvn package
```

## Running the server
```bash
# Running the server
$ mvn compile exec:java
```
## Generating the documentation

```bash
# This generates HTML from the Javadoc in target/site/apidocs
$ mvn javadoc:javadoc
```
