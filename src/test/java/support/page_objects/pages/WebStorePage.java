package support.page_objects.pages;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import support.page_objects.components.web_store.AuthenticationComponent;
import support.page_objects.components.web_store.Header;
import support.page_objects.components.web_store.MyAccountComponent;
import support.page_objects.components.web_store.OrderHistoryComponent;

@Page
@Log4j
public class WebStorePage extends ContentPage {
    String url = this.buildUrl("http://automationpractice.com/");

    public AuthenticationComponent authenticationComponent =
        new AuthenticationComponent(By.xpath(".//form[@id=\"login_form\"]"), "Auth");
    public MyAccountComponent myAccountComponent =
        new MyAccountComponent(By.xpath(".//div[@id=\"center_column\" and .//h1[.=\"My account\"]]"), "My account");
    public OrderHistoryComponent orderHistoryComponent =
        new OrderHistoryComponent(By.xpath(".//div[@id=\"center_column\" and .//h1[.=\"Order history\"]]"), "Order History");
    public Header header =
        new Header(By.xpath(".//nav"), "Header");



    public WebStorePage(String name) {
        super(name);
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
