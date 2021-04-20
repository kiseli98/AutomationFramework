package support.page_objects.components.web_store;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import support.page_objects.webelements.BaseComponent;
import support.page_objects.webelements.Button;

public class MyAccountComponent extends BaseComponent {
    public Button ordersBtn;
    public Button creditBtn;
    public Button addressesBtn;
    public Button infoBtn;
    public Button wishlistBtn;


    public MyAccountComponent(By locator, String name, WebDriver driver) {
        super(locator, name, driver);
        this.ordersBtn = new Button(By.xpath(".//a[@title=\"Orders\"]"), "Orders", this, driver);
        this.creditBtn = new Button(By.xpath(".//a[@title=\"Credit slips\"]"), "Credit", this, driver);
        this.addressesBtn = new Button(By.xpath(".//a[@title=\"Addresses\"]"), "Addresses", this, driver);
        this.infoBtn = new Button(By.xpath(".//a[@title=\"Information\"]"), "Information", this, driver);
        this.wishlistBtn = new Button(By.xpath(".//a[@title=\"My wishlists\"]"), "My wishlists", this, driver);

    }


}