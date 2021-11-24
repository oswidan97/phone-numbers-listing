#!/bin/bash
mvn -f /app/pom.xml -Dmaven.test.skip=true package
java -jar /app/target/jumia-phone-number-listing-0.0.1-SNAPSHOT.jar