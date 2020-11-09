package support.page_objects.pages.eliteShoppy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import step_definitions.hooks.Hooks;

public class SignInModal {
//    public static SignInModal instance = new SignInModal(Hooks.getDriver());
    private final String xpath = ".//div[@class=\"modal-content\" and contains(.,\"Sign In\")]";


    @FindBy(how = How.XPATH, using = xpath + "//input[@name=\"Name\"]")
    public WebElement username;

    @FindBy(how = How.XPATH, using = xpath + "//input[@name=\"Email\"]")
    public WebElement email;

    @FindBy(how = How.XPATH, using = xpath + "//input[@value=\"Sign In\"]")
    public WebElement signInBtn;


    public SignInModal(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}