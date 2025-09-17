package TestSuites;

import PayLoads.AddPlacePayload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matcher.*;
import static org.hamcrest.Matchers.equalTo;

public class TestCaseOne {

    @Test
    public void TestCase1() {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"location\": {\n" +
                        "    \"lat\": -38.383494,\n" +
                        "    \"lng\": 33.427362\n" +
                        "  },\n" +
                        "  \"accuracy\": 50,\n" +
                        "  \"name\": \"Frontline house\",\n" +
                        "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                        "  \"address\": \"29, side layout, cohen 09\",\n" +
                        "  \"types\": [\n" +
                        "    \"shoe park\",\n" +
                        "    \"shop\"\n" +
                        "  ],\n" +
                        "  \"website\": \"http://google.com\",\n" +
                        "  \"language\": \"French-IN\"\n" +
                        "}\n").when().post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200).
                body("scope", equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)");
    }

    @Test
    public void Testcase2() {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(AddPlacePayload.AddPlace())
                .when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200)
                .body("scope", equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)")
                .extract().response().asString();

        JsonPath jsonPath = new JsonPath(response);
        String placeId = jsonPath.get("place_id");

        System.out.println(placeId);

        String getPlaceResponse = given().log().all()
                .queryParam("key", "qaclick123").queryParam("place_id", placeId)
                .when().get("/maps/api/place/get/json").then().log().all().statusCode(200).extract().response().asString();

        JsonPath jsonPath1 = new JsonPath(getPlaceResponse);
        String address = jsonPath1.get("address");
        System.out.println(address);
    }
}
