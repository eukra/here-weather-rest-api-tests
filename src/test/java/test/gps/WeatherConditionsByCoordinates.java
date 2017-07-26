package test.gps;

import com.jayway.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.BaseTest;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;

/**
 * Created by Kravchenko on 7/24/2017.
 */
public class WeatherConditionsByCoordinates extends BaseTest {

        /*
         * Invoke getWeatherConditions API method at a specified Latitude and Longitude with One observation, url and response to log
         */

    @Test(priority = 0)
    public void getWeatherAtSpecifiedCoordinatesOneObservation() {

        String url = ENDPOINT;

        String response =
                given().
                        relaxedHTTPSValidation().
                        headers(headers).
                        queryParam("product", "observation").
                        queryParam("latitude", "48.8534").
                        queryParam("longitude", "2.3486").
                        queryParam("oneobservation", true).
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
        String latitude = jsonPath.getString("observations.location.observation[0].latitude[0]");
        String longitude = jsonPath.getString("observations.location.observation[0].longitude[0]");
        Assert.assertEquals(city, "Paris");
        Assert.assertEquals(latitude, "48.8534");
        Assert.assertEquals(longitude, "2.3486");
    }

      /*
         * Invoke getWeatherConditions API method at a specified Latitude and Longitude with Multiple observations, url and response to log
         */

    @Test(priority = 1)
    public void getWeatherAtSpecifiedCoordinatesMultipleObservations() {

        String url = ENDPOINT;

        String response =
                given().
                        relaxedHTTPSValidation().
                        headers(headers).
                        queryParam("product", "observation").
                        queryParam("latitude", "41.3888").
                        queryParam("longitude", "2.159").
                        queryParam("oneobservation", false).
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
        String latitude = jsonPath.getString("observations.location.observation[0].latitude[0]");
        String longitude = jsonPath.getString("observations.location.observation[0].longitude[0]");
        Assert.assertEquals(city, "Barcelona");
        Assert.assertEquals(latitude, "41.3888");
        Assert.assertEquals(longitude, "2.159");
    }
}
