#!/bin/bash

#The file should have UNIX-style EOL

if [ -z "$CALS_API_CONFIG" ]
then
  CALS_API_CONFIG="cals-api.yml"
fi

echo "config file: $CALS_API_CONFIG"

java -jar cals-api.jar server $CALS_API_CONFIG
