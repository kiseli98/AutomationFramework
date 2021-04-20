package step_definitions.ui;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.context.TestContext;

import java.time.Duration;
import java.util.Map;
import java.util.function.Function;

public class OldApproachSteps {
    private TestContext testContext;
    private WebDriver driver;
    private String webStoreUrl = "http://automationpractice.com/";

    public OldApproachSteps(TestContext context) {
        this.testContext = context;
        this.driver = context.getWebDriverManager().getDriver();
    }


    @Given("I am on the store page - old")
    public void iAmOnTheStorePageOld() {
        driver.get(webStoreUrl);
        Function<WebDriver, Boolean> waitCondition = driver -> {
            Boolean isPageLoaded = (Boolean) ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            if (!isPageLoaded) System.out.println("Document is loading");
            return isPageLoaded;
        };
        WebDriverWait webDriverWait = new WebDriverWait(driver, 15);
        webDriverWait.withTimeout(Duration.ofSeconds(15));
        try {
            webDriverWait.until(waitCondition);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @When("I login with the following credentials - old")
    public void iLoginWithTheFollowingCredentialsOld(DataTable dataTable) {
        Map<String, String> rowData = dataTable.asMaps().get(0);
        System.out.println("Waiting till element Sign In btn is enabled and clickable");
        WebDriverWait wait = new WebDriverWait(this.driver, 30);
        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("#header > div.nav > div > div > nav > div.header_user_info > a")));
        signInButton.click();

        System.out.println("Waiting till authenticationComponent is visible");
        WebElement auth = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#login_form")));

        WebElement login = driver.findElement(By.cssSelector("#email"));
        WebElement password = driver.findElement(By.cssSelector("#passwd"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", login);
        login.sendKeys(rowData.get("Username"));
        password.sendKeys(rowData.get("Password"));
        signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#SubmitLogin")));
        signInButton.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#login_form")));

    }

    @And("^I open (order history|my credit|my addresses|my info|my wishlist) - old$")
    public void iOpenOrderHistoryOld(String param) {
        By btnLocator;
        switch (param) {
            case "order history":
                btnLocator = By.cssSelector("#center_column > div > div:nth-child(1) > ul > li:nth-child(1) > a");
                break;
            case "my credit":
                btnLocator = By.cssSelector("#center_column > div > div:nth-child(1) > ul > li:nth-child(2) > a");
                break;
            case "my addresses":
                btnLocator = By.cssSelector("#center_column > div > div:nth-child(1) > ul > li:nth-child(3) > a");
                break;
            default:
                throw new Error("Wrong option");
        }
        WebDriverWait wait = new WebDriverWait(this.driver, 30);
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(btnLocator));
        btn.click();
    }

    @Then("I see order history table is displayed -old")
    public void iSeeOrderHistoryTableOld() {
        WebDriverWait wait = new WebDriverWait(this.driver, 30);
        WebElement orderHistoryComponent = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("#center_column")));
        WebElement orderHistoryTable = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("#order-list")));
        Assert.assertTrue("Order history table is not visible", orderHistoryTable.isDisplayed());
    }
}
