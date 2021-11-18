package support.page_objects.components.web_store;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import support.page_objects.webelements.BaseComponent;
import support.page_objects.webelements.Button;

public class MyAccountComponent extends BaseComponent {

    public Button ordersBtn = new Button(By.xpath(".//a[@title=\"Orders\"]"), "Orders", this);
    public Button creditBtn  = new Button(By.xpath(".//a[@title=\"Credit slips\"]"), "Credit", this);
    public Button addressesBtn = new Button(By.xpath(".//a[@title=\"Addresses\"]"), "Addresses", this);
    public Button infoBtn  = new Button(By.xpath(".//a[@title=\"Information\"]"), "Information", this);
    public Button wishlistBtn  = new Button(By.xpath(".//a[@title=\"My wishlists\"]"), "My wishlists", this);


    public MyAccountComponent(By locator, String name) {
        super(locator, name);
    }


}