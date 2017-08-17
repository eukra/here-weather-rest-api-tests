# Example of REST API test automation using Rest Assured + Java + Maven

To run Tests from Jenkins or console use command:

    mvn -Dendpoint="${endpoint} -Dtest.xml=${test.xml} -Dapp_id="${app_id} -Dapp_code="${app_code}" clean test
    e.g. mvn -Dendpoint=https://weather.cit.api.here.com/weather/1.0/report.json -Dtest.xml=here_weather_tests.xml -Dapp_id=DemoAppId01082013GAL -Dapp_code=AJKnXv84fjrb0KIHawS0Tg clean test