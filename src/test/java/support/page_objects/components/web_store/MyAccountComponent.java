package support.page_objects.components.web_store;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import support.page_objects.webelements.BaseComponent;
import support.page_objects.webelements.Button;

public class MyAccountComponent extends BaseComponent {
    public static MyAccountComponent instance;

    public Button ordersBtn;
    public Button creditBtn;
    public Button addressesBtn;
    public Button infoBtn;
    public Button wishlistBtn;


    public MyAccountComponent(By locator, String name) {
        super(locator, name);
        instance = this;

        this.ordersBtn = new Button(By.xpath(".//a[@title=\"Orders\"]"), "Orders", this);
        this.creditBtn = new Button(By.xpath(".//a[@title=\"Credit slips\"]"), "Credit", this);
        this.addressesBtn = new Button(By.xpath(".//a[@title=\"Addresses\"]"), "Addresses", this);
        this.infoBtn = new Button(By.xpath(".//a[@title=\"Information\"]"), "Information", this);
        this.wishlistBtn = new Button(By.xpath(".//a[@title=\"My wishlists\"]"), "My wishlists", this);

    }


}