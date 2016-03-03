#! /bin/sh
rm local.properties
./gradlew dependencies > dependencies.txt
./gradlew clean build groovydoc uploadArchives uploadGroovydoc
