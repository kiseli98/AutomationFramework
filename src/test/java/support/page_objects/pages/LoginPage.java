package support.page_objects.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import step_definitions.hooks.Hooks;
import support.utils.BaseUtil;

public class LoginPage extends BasePage {
    public static LoginPage instance = new LoginPage(Hooks.getDriver(), "https://demosite.executeautomation.com/");

    private LoginPage(WebDriver driver, String url) {
        super(driver, url);
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.NAME, using = "UserName")
    WebElement username;
    @FindBy(how = How.NAME, using = "Password")
    WebElement password;
    @FindBy(how = How.XPATH, using = ".//input[@type=\"submit\"]")
    WebElement loginBtn;

    public void login(String username, String password) {

        this.username.sendKeys(username);
        this.password.sendKeys(password);
        this.loginBtn.click();

    }

}
