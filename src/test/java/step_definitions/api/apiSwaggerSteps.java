package step_definitions.api;

import io.cucumber.java8.En;
import io.restassured.response.Response;
import org.junit.Assert;
import support.context.ScenarioContext;
import support.context.TestContext;
import support.rest_api.dto.req.AddBookRequest;
import support.rest_api.dto.req.AuthRequest;
import support.rest_api.dto.req.ISBN;
import support.rest_api.dto.req.RemoveBookRequest;
import support.rest_api.dto.resp.Book;
import support.rest_api.dto.resp.Books;
import support.rest_api.dto.resp.Token;
import support.rest_api.dto.resp.UserAccount;
import support.rest_api.test.Endpoints;
import support.rest_api.test.IRestResponse;

public class apiSwaggerSteps implements En {

    private final String USER_ID = "f614361f-bedf-49e9-be3c-df86670285da";
    private final String USER_NAME = "TEST12345";
    private final String USER_PASSWORD = "Bagarov12345!";

    private Token tokenResponse;
    private Response response;
    private IRestResponse<UserAccount> userAccountResponse;
    private final Endpoints endpoints = new Endpoints();
    private Book book;

    public apiSwaggerSteps(TestContext context) {
        ScenarioContext scenarioContext = context.getScenarioContext();


        Given("^I am an authorized user$", () -> {
            AuthRequest authRequest = new AuthRequest(USER_NAME, USER_PASSWORD);
            tokenResponse = endpoints.authenticateUser(authRequest).getBody();
        });


        Given("^A list of books are available$", () -> {
            IRestResponse<Books> booksResponse = endpoints.getBooks();
            book = booksResponse.getBody().books.get(0);
        });


        When("^I add a book to my reading list$", () -> {
            AddBookRequest addBookRequest = new AddBookRequest(USER_ID, new ISBN(book.isbn));
            userAccountResponse = endpoints.addBook(addBookRequest);
        });


        Then("^the book is added$", () -> {
            Assert.assertTrue(userAccountResponse.isSuccessful());
            Assert.assertEquals(201, userAccountResponse.getStatusCode());
            Assert.assertEquals(book.isbn, userAccountResponse.getBody().books.get(0).isbn);
        });


        When("^I remove a book from my reading list$", () -> {
            RemoveBookRequest removeBookRequest = new RemoveBookRequest(USER_ID, book.isbn);
            response = endpoints.removeBook(removeBookRequest);
        });


        Then("^the book is removed$", () -> {
            Assert.assertEquals(204, response.getStatusCode());
            userAccountResponse = endpoints.getUserAccount(USER_ID);

            Assert.assertEquals(200, userAccountResponse.getStatusCode());
            Assert.assertEquals(0, userAccountResponse.getBody().books.size());
        });


    }
}
