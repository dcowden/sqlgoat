#!/bin/sh
java -javaagent:../appfense_new/target/appfense2-1.0.jar -jar  target/sqlgoat-1.0-SNAPSHOT-war-exec.jar -httpPort 8090
