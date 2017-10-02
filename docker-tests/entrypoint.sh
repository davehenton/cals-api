#!/bin/bash

JAVA_OPT="-Xms128m -Xmx512m"

if ([ -z "$CALS_API_URL" ]); then
  echo "CALS_API_URL variable is required"
  exit 1
fi

if ([ -z "$TEST_TYPE" ]); then
  echo "TEST_TYPE variable is required"
  exit 1
fi

if [ "$TEST_TYPE" = "smoke" ]; then
  echo "Executing the Smoke Test..."
  TEST_CLASS=gov.ca.cwds.cals.SmokeTestSuite
else
  echo "Unexpected TEST_TYPE: '$TEST_TYPE'"
  exit 1
fi

java ${JAVA_OPT} -Dcals.api.url="${CALS_API_URL}" -cp /opt/cals-api-tests/resources:cals-api-tests.jar org.junit.runner.JUnitCore ${TEST_CLASS}
