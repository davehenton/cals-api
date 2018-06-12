#!/bin/bash

#The file should have UNIX-style EOL

if [ -x /paramfolder/parameters.sh ]; then
    source /paramfolder/parameters.sh
fi


JAVA_OPTS="-Xms128m -Xmx512m ${JAVA_OPTS}"
echo  "Java parameters: $JAVA_OPTS"


if [ -z "$CALS_API_CONFIG" ]
then
  CALS_API_CONFIG="cals-api.yml"
fi

echo "config file: $CALS_API_CONFIG"



if [ -f /opt/newrelic/newrelic.yml ]; then
    java -javaagent:/opt/newrelic/newrelic.jar  ${JAVA_OPTS} -jar cals-api.jar server $CALS_API_CONFIG
else
    java  ${JAVA_OPTS} -jar cals-api.jar server $CALS_API_CONFIG
fi
