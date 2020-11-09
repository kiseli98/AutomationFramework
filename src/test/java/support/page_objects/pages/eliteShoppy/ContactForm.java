package support.page_objects.pages.eliteShoppy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import step_definitions.hooks.Hooks;
import support.ui_dto.ContactMessage;

public class ContactForm {
//    public static ContactForm instance = new ContactForm(Hooks.getDriver());
    private final String xpath = ".//div[contains(@class, \"contact-form\")]";


    @FindBy(how = How.XPATH, using = xpath + "//input[@name=\"Name\"]")
    public WebElement name;

    @FindBy(how = How.XPATH, using = xpath + "//input[@name=\"Email\"]")
    public WebElement email;

    @FindBy(how = How.XPATH, using = xpath + "//input[@name=\"Subject\"]")
    public WebElement subject;

    @FindBy(how = How.XPATH, using = xpath + "//textarea[@name=\"Message\"]")
    public WebElement message;


    public ContactForm(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void fillAndSubmit(ContactMessage msg) {
        this.name.sendKeys(msg.getName());
        this.email.sendKeys(msg.getEmail());
        this.subject.sendKeys(msg.getSubject());
        this.message.sendKeys(msg.getMessage());
        this.message.submit();
    }

}
