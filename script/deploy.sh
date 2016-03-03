#! /bin/sh
rm local.properties
./gradlew clean build groovydoc uploadArchives uploadGroovydoc
