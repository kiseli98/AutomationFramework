package support.page_objects.components.web_store;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import support.page_objects.webelements.BaseComponent;
import support.page_objects.webelements.SimpleTable;
import support.page_objects.webelements.WebElementX;

public class OrderHistoryComponent extends BaseComponent {
    public static OrderHistoryComponent instance;

    public SimpleTable ordersTable;


    public OrderHistoryComponent(By locator, String name) {
        super(locator, name);
        instance = this;
        this.ordersTable = new SimpleTable(By.xpath(".//table[@id=\"order-list\"]"), "Orders table", this);

        this.innerElements.add(ordersTable);
    }

    @Override
    public void expectToBeDisplayed() {
        super.expectToBeDisplayed();
        this.ordersTable.waitTillIsVisible(10);
        this.ordersTable.expectToBeDisplayed();
    }
}