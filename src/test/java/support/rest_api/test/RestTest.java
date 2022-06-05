package support.rest_api.test;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class RestTest {

    @Test
    public void RegistrationSuccessful() {
        RestAssured.baseURI = "https://bookstore.toolsqa.com";
        RequestSpecification request = RestAssured.given();

        JsonObject requestParams = new JsonObject();
        requestParams.addProperty("userName", "TEST12345");
        requestParams.addProperty("password", "Bagarov12345!");

        Response response = request
                .log().all()
                .contentType(ContentType.JSON)
                .body(requestParams.toString())
                .post("/Account/v1/User");

        Assert.assertEquals(201, response.getStatusCode());
        String userID = response.getBody().jsonPath().getString("userID");
    }
}
