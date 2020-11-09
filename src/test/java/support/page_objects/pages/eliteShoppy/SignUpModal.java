package support.page_objects.pages.eliteShoppy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import step_definitions.hooks.Hooks;

public class SignUpModal {
//    public static SignUpModal instance = new SignUpModal(Hooks.getDriver());
    private final String xpath = ".//div[@class=\"modal-content\" and contains(.,\"Sign Up\")]";


    @FindBy(how = How.XPATH, using = xpath + "//input[@name=\"Name\"]")
    public WebElement username;

    @FindBy(how = How.XPATH, using = xpath + "//input[@name=\"Email\"]")
    public WebElement email;

    @FindBy(how = How.XPATH, using = xpath + "//input[@name=\"password\"]")
    public WebElement password;

    @FindBy(how = How.XPATH, using = xpath + "//input[@name=\"Confirm Password\"]")
    public WebElement passwordConfirm;


    public SignUpModal(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}