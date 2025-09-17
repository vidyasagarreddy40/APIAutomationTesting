package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import jdk.jshell.execution.Util;
import org.junit.Assert;
import pojo.AddPlace;
import pojo.location;
import resources.TestDataBuilder;
import resources.Utils;

import java.util.LinkedList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;
import static org.hamcrest.Matchers.equalTo;

public class AddPlaceStepDef extends Utils {

    RequestSpecification requestSpecification1;
    ResponseSpecification responseSpecification;
    Response response;
    TestDataBuilder testDataBuilder= new TestDataBuilder();
    String place_Id;
    @Given("Add Place Payload")
    public void add_place_payload() {

        RequestSpecification requestSpecification = new RequestSpecBuilder().setRelaxedHTTPSValidation().setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key", "qaclick123").setContentType("application/json")
                .build();

         responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType("application/json")
                .build();

        requestSpecification1 = given().spec(setRequestSpecification(getGlobalProperties().getProperty("baseUrl"),"key","qaclick123"))
                 .body(testDataBuilder.addPlacePayLoad("Vidyasagar","Hindi","India"));

    }

    @When("user calls {string} with Post http request")
    public void user_calls_with_post_http_request(String addPlaceAPI) {
        response = requestSpecification1.when().post(getGlobalProperties().getProperty(addPlaceAPI)).then().spec(responseSpecification).extract().response();
    }

    @Then("API call is success with status code {int}")
    public void api_call_is_success_with_status_code(Integer int1) {
        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String value) {
        String responseJson = response.asString();
        System.out.println(responseJson);
        JsonPath jsonPath = new JsonPath(responseJson);
        String statusResponse = jsonPath.get(key);
        Assert.assertEquals(statusResponse, value);
        System.out.println("Key :" + statusResponse);
        place_Id= jsonPath.get("place_id");

        System.out.println(place_Id);
    }


    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) {
        RequestSpecification requestSpecification = new RequestSpecBuilder().setRelaxedHTTPSValidation().setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key", "qaclick123").setContentType("application/json")
                .build();

        responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType("application/json")
                .build();

        requestSpecification1 = given().spec(setRequestSpecification(getGlobalProperties().getProperty("baseUrl"),"key","qaclick123"))
                .body(testDataBuilder.addPlacePayLoad(name,language,address));

    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String endPoint, String httpMethod) {

        if(httpMethod.equalsIgnoreCase("post")) {
            response = requestSpecification1.when().post(getGlobalProperties().getProperty(endPoint)).then().spec(responseSpecification).extract().response();
        }
        else if(httpMethod.equalsIgnoreCase("get")){
            response = requestSpecification1.when().get(getGlobalProperties().getProperty(endPoint)).then().spec(responseSpecification).extract().response();
        }
    }

    @Then("verify place_id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String name, String placeID) {

        //requestSpecification1.when().get()

    }
}
