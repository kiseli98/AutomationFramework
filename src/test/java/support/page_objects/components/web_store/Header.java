package support.page_objects.components.web_store;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import support.page_objects.webelements.BaseComponent;
import support.page_objects.webelements.Button;

public class Header extends BaseComponent {
//    public static Header instance = new Header(By.xpath(".//nav"), "Header");
    public Button signInButton;


    public Header(By locator, String name, WebDriver driver) {
        super(locator, name, driver);
        this.signInButton = new Button(By.xpath(".//a[@class=\"login\"]"), "Log In button", this, driver);

    }


}
