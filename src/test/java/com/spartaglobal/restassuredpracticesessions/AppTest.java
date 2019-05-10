package com.spartaglobal.restassuredpracticesessions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spartaglobal.restassuredpracticesessions.entities.Address;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private static JsonPath postcodeJSONResponse;
    private static Response fullPostcodeResponse;
    /**
     * Rigorous Test :-)
     */
    @BeforeClass
    public static void setup(){
        baseURI = "http://api.postcodes.io";
        basePath = "/postcodes/";

        fullPostcodeResponse = get("SE58TE");
        postcodeJSONResponse = fullPostcodeResponse.body().jsonPath();
    }

    @Test
    public void PostcodeRequestIsSuccessful()
    {
        fullPostcodeResponse
                .then()
                .statusCode(200)
                .body("status", equalTo(200));
    }

    @Test
    public void PostcodesGetBody() throws IOException {
       /*JsonPath jsonPath = get("SE58TE").jsonPath();
       get("SE58TE").jsonPath().getJsonObject("result");
       String jsonPath2 = get("SE58TE").body().print();

       //Map addy = get("SE58TE").jsonPath().getMap("result");
       //String addys = get("SE58TE").jsonPath().getString("result");
       //HashMap jsonHash = jsonPath.getJsonObject("result");
    */
       ObjectMapper resultMapper = new ObjectMapper();
       String jsonObject = fullPostcodeResponse.body().print();

       Address addressObject = resultMapper.readValue(jsonObject, Address.class);


    }
}
