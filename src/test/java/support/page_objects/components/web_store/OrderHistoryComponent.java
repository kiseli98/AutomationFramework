package support.page_objects.components.web_store;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import support.page_objects.webelements.BaseComponent;
import support.page_objects.webelements.Button;
import support.page_objects.webelements.WebElementX;

public class OrderHistoryComponent extends BaseComponent {
    public WebElementX ordersTable;


    public OrderHistoryComponent(By locator, String name) {
        super(locator, name);
        this.ordersTable = new WebElementX(By.xpath(".//table[@id=\"order-list\"]"), "Orders table", this);

    }

    @Override
    public void expectToBeDisplayed() {
        super.expectToBeDisplayed();
        this.ordersTable.waitTillIsVisible(10);
        this.ordersTable.expectToBeDisplayed();
    }
}