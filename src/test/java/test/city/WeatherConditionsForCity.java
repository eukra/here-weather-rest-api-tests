package test.city;

import com.jayway.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.BaseTest;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;

/**
 * Created by Kravchenko on 7/24/2017.
 */
public class WeatherConditionsForCity extends BaseTest {

        /*
         * Invoke getWeatherForCity API method, url and response to log
         */

    @Test(priority = 0)
    public void getWeatherForCity() {

        String url = ENDPOINT;

        String response =
                given().
                        relaxedHTTPSValidation().
                        headers(headers).
                        queryParam("product", "observation").
                        queryParam("name", "Barcelona").
                        queryParam("app_id", APP_ID).
                        queryParam("app_code", APP_CODE).
                when().
                        get(url).
                then().
                        log().ifValidationFails().
                        statusCode(200).
                        contentType(JSON).
                        extract().asString();

        writeToLog(url, null, response);
        System.out.println(response);

        JsonPath jsonPath = new JsonPath(response);
        String city = jsonPath.getString("observations.location.city[0]");
        Assert.assertEquals(city, "Barcelona");
    }
}
