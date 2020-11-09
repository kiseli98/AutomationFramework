package step_definitions.ui;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import support.page_objects.pages.*;
import support.ui_dto.User;
import support.utils.BaseUtil;

import java.util.Map;

public class UserCredentialsSteps extends BaseUtil {
    private BaseUtil base;
    private EliteShoppyPage eliteShoppyPage;

    @DataTableType
    public User defineUser(Map<String, String> entry) {
        return new User(entry.get("Username"), entry.get("Email"), entry.get("Password"));
    }

    public UserCredentialsSteps(BaseUtil base) {
        this.base = base;
        eliteShoppyPage = new EliteShoppyPage(base.driver, "https://adoring-pasteur-3ae17d.netlify.app/index.html/");
    }


    @When("I login with the following credentials")
    public void iLoginWithTheFollowingCredentials(DataTable table) {
        User newUser = new User(table);
        eliteShoppyPage.signInBtn.click();
        eliteShoppyPage.login(newUser.getUsername(), newUser.getEmail());

//        // dataTableConfigurer or @DataTableType
//        List<User> users = table.asList(User.class);
//
//        System.out.println("Username is: " + users.get(0).getUsername());
//        System.out.println("Password is: " + users.get(0).getPassword());
    }


    @Then("I assure I am logged in")
    public void iAssureIAmLoggedIn() {
        throw new Error("Test fails here...");
    }

    @When("I register a new account with the following credentials")
    public void iRegisterANewAccountWithTheFollowingCredentials(DataTable table) {
        User newUser = new User(table);
        eliteShoppyPage.signUpBtn.click();
        eliteShoppyPage.register(newUser.getUsername(), newUser.getEmail(), newUser.getPassword());
    }
}
