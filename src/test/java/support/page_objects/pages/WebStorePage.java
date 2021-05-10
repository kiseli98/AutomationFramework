package support.page_objects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import support.page_objects.components.web_store.AuthenticationComponent;
import support.page_objects.components.web_store.Header;
import support.page_objects.components.web_store.MyAccountComponent;
import support.page_objects.components.web_store.OrderHistoryComponent;

public class WebStorePage extends ContentPage {
    public static WebStorePage instance;
    String url = this.buildUrl("http://automationpractice.com/");

    public AuthenticationComponent authenticationComponent;
    public MyAccountComponent myAccountComponent;
    public OrderHistoryComponent orderHistoryComponent;
    public Header header;


    public WebStorePage(String name, WebDriver driver) {
        super(name, driver);
        instance = this;
        this.header = new Header(By.xpath(".//nav"), "Header", driver);
        this.authenticationComponent = new AuthenticationComponent(By.xpath(".//form[@id=\"login_form\"]"), "Auth", driver);
        this.myAccountComponent = new MyAccountComponent(By.xpath(".//div[@id=\"center_column\" and .//h1[.=\"My account\"]]"), "My account", driver);
        this.orderHistoryComponent = new OrderHistoryComponent(By.xpath(".//div[@id=\"center_column\" and .//h1[.=\"Order history\"]]"), "Order History", driver);
    }


    @Override
    public void navigate() {
        this.open(url);
    }

    @Override
    public boolean isDisplayed() {
//        TODO
        return false;
    }

    @Override
    public void waitPageReady(long timeoutInSeconds) {
        super.waitPageReady(timeoutInSeconds);
//TODO
    }

}
