package support.page_objects.pages;

import org.openqa.selenium.By;
import support.page_objects.components.web_store.AuthenticationComponent;
import support.page_objects.components.web_store.Header;
import support.page_objects.components.web_store.MyAccountComponent;
import support.page_objects.components.web_store.OrderHistoryComponent;

public class WebStorePage extends ContentPage {
    public static WebStorePage instance = new WebStorePage("WebStorePage");
    String url = this.buildUrl("http://automationpractice.com/");

    public AuthenticationComponent authenticationComponent = AuthenticationComponent.instance;
    public MyAccountComponent myAccountComponent = MyAccountComponent.instance;
    public OrderHistoryComponent orderHistoryComponent = OrderHistoryComponent.instance;
    public Header header = Header.instance;


    private WebStorePage(String name) {
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
