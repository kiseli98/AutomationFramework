package support.rest_api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class RestAssuredTest {

    @Test
    void test_01() {
        Response response = get("https://reqres.in/api/users?page=2");

        System.out.println(response.asString());
        System.out.println();
        System.out.println(response.getBody());
        System.out.println();
        System.out.println(response.getHeaders());
        System.out.println();
        System.out.println(response.getStatusCode());
        System.out.println();
        System.out.println(response.getStatusLine());
        System.out.println();
        System.out.println(response.getTime());

    }

    @Test
    void test_get() {
        given().get("https://reqres.in/api/users?page=2")
                .then().statusCode(200)
                .body("data.email[0]", equalTo("michael.lawson@reqres.in"))
                .body("data.id", hasItems(9, 10, 11))
                .log().all();
    }

    @Test
    void test_post() throws JsonProcessingException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Book1");
        map.put("author", "Bob");

        String json = new ObjectMapper().writeValueAsString(map);
        System.out.println(json);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = mapper.createObjectNode();

        rootNode.put("name", "Book1");
        rootNode.put("author", "Bob");

        json = new ObjectMapper().writeValueAsString(rootNode);
        System.out.println(json);

        given().body(json).header("Content-Type", "application/json")
                .when().post("https://reqres.in/api/users")
                .then().statusCode(201)
                .log().all();

    }

    @Test
    void test_put() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = mapper.createObjectNode();

        rootNode.put("name", "Book1");
        rootNode.put("author", "Bob");

        String json = new ObjectMapper().writeValueAsString(rootNode);
        System.out.println(json);

        given().body(json).header("Content-Type", "application/json")
                .when().put("https://reqres.in/api/users/2")
                .then().statusCode(200)
                .log().all();

    }

    @Test
    void test_delete() {
        given().header("Content-Type", "application/json")
                .when().delete("https://reqres.in/api/users/2")
                .then().statusCode(204)
                .log().all();

    }

}
