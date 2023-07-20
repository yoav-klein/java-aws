#!/bin/bash

if [ ! -f classpath.txt ]; then
    mvn dependency:build-classpath -Dmdep.outputFile=classpath.txt
    echo -n ":./target/my-app-1.0-SNAPSHOT.jar" >> classpath.txt
fi

java -cp $(cat classpath.txt) com.mycompany.app.App
