package test;

import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import utils.TestStatusListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Kravchenko on 7/24/2017.
 */

@Listeners(TestStatusListener.class)
public class BaseTest {

    public String ENDPOINT;
    public String APP_ID;
    public String APP_CODE;

    public Map<String, String> headers;

    /**
     * To run Tests from Jenkins or console using defined test.xml and endpoint
     * mvn -Dendpoint="${endpoint} -Dtest.xml=${test.xml} -Dapp_id="${app_id} -Dapp_code="${app_code}" clean test
     *
     * e.g. mvn -Dendpoint=https://weather.cit.api.here.com/weather/1.0/report.json -Dtest.xml=here_weather_tests.xml -Dapp_id=DemoAppId01082013GAL -Dapp_code=AJKnXv84fjrb0KIHawS0Tg clean test
     */

    @BeforeTest
    @Parameters("TOKEN")
    public void setUp() {
        initConstants();
        headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        headers.put("Cache-Control", "no-cache");
        System.out.println("\n\n >>> Endpoint: " + this.ENDPOINT);
        System.out.println(" >>> TestSuiteName: " + getClass().getName());
    }

    public void initConstants() {
        Properties prop = new Properties();
        InputStream input;
        if ((new File("local.properties")).exists()) {
            try {
                input = new FileInputStream("local.properties");
                prop.load(input);
                System.setProperty("endpoint", prop.getProperty("endpoint"));
                System.setProperty("app_id", prop.getProperty("app_id"));
                System.setProperty("app_code", prop.getProperty("app_code"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ENDPOINT = System.getProperty("endpoint");
        APP_ID = System.getProperty("app_id");
        APP_CODE = System.getProperty("app_code");
    }

    /**
     * Assert is json equals
     *
     * @param expectedJson
     * @param actualJson
     */
    public void assertIsJsonEquals(String expectedJson, String actualJson) {
        try {
            JSONAssert.assertEquals(expectedJson, actualJson, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write request url, body and responce to log
     *
     * @param requestUrl
     * @param body
     * @param response
     */
    public void writeToLog(String requestUrl, String body, String response) {
        Reporter.log("Request URL: " + requestUrl);
        Reporter.log("*******************************************************************************************************************************");
        if (body != null) {
            Reporter.log("Request Body: " + body);
            Reporter.log("*******************************************************************************************************************************");
        }
        Reporter.log("Response Body: " + response);
        Reporter.log("*******************************************************************************************************************************");
    }
}
