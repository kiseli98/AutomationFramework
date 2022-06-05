package support.rest_api.test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import support.rest_api.dto.req.AddBookRequest;
import support.rest_api.dto.req.AuthRequest;
import support.rest_api.dto.req.RemoveBookRequest;
import support.rest_api.dto.resp.Books;
import support.rest_api.dto.resp.Token;
import support.rest_api.dto.resp.UserAccount;

public class Endpoints {
    private static final String BASE_URL = "https://bookstore.toolsqa.com";
    private final RequestSpecification request;

    public Endpoints() {
        RestAssured.baseURI = BASE_URL;
        this.request = RestAssured.given()
                .contentType(ContentType.JSON)
                .log().all();
    }

    public IRestResponse<Token> authenticateUser(AuthRequest authRequest) {
        Response response = request.body(authRequest).post(Routes.generateToken());
        IRestResponse<Token> tokenIRestResponse = new RestResponse<>(Token.class, response);

        this.request.header("Authorization", "Bearer " + tokenIRestResponse.getBody().tokenString);
        return tokenIRestResponse;
    }

    public IRestResponse<Books> getBooks() {
        Response response = request.get(Routes.books());
        return new RestResponse<>(Books.class, response);
    }

    public IRestResponse<UserAccount> addBook(AddBookRequest addBooksRequest) {
        Response response = request.body(addBooksRequest).post(Routes.books());
        return new RestResponse<>(UserAccount.class, response);
    }

    public Response removeBook(RemoveBookRequest removeBookRequest) {
        return request.body(removeBookRequest).delete(Routes.book());
    }

    public IRestResponse<UserAccount> getUserAccount(String userId) {
        Response response = request.get(Routes.userAccount(userId));
        return new RestResponse<>(UserAccount.class, response);
    }
}
