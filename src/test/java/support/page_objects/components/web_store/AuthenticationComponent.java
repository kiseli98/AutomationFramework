package support.page_objects.components.web_store;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import support.page_objects.webelements.BaseComponent;
import support.page_objects.webelements.Button;
import support.page_objects.webelements.TextInput;

public class AuthenticationComponent extends BaseComponent {
    public TextInput emailInput;
    public TextInput passwordInput;
    public Button signInButton;


    public AuthenticationComponent(By locator, String name, WebDriver driver) {
        super(locator, name, driver);
        this.emailInput = new TextInput(By.xpath(".//input[@name=\"email\"]"), "Email", this, driver);
        this.passwordInput = new TextInput(By.xpath(".//input[@name=\"passwd\"]"), "Password", this, driver);
        this.signInButton = new Button(By.xpath(".//button[@id=\"SubmitLogin\"]"), "Log in", this, driver);

    }

    public void authenticate(String email, String password) {
        this.emailInput.scrollTo();
        this.emailInput.appendKeys(email);
        this.passwordInput.appendKeys(password);
        this.signInButton.click();
    }


}