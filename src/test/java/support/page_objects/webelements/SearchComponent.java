package support.page_objects.webelements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchComponent extends WebElementX {
    public TextInput searchInput;
    public Button searchButton;

    public SearchComponent(By locator, String name, WebElementX parentElement, WebDriver driver) {
        super(locator, name != null ? name + " Search Component" : null, parentElement, driver);
        this.searchInput = new TextInput(By.xpath(".//input"), null, this, driver);
        this.searchButton = new Button(By.xpath(".//*"), null, this, driver);
    }

    public void search(String val) {
        logger.info("Searching for:: " + val);
        this.searchInput.waitTillIsEnabled(10);
        this.searchInput.appendKeys(val);
    }

    public void searchWithClick(String val) {
        logger.info("Searching for:: " + val);
        this.searchInput.waitTillIsEnabled(10);
        this.searchInput.appendKeys(val);
        this.searchButton.waitTillIsEnabled(10);
        this.searchButton.click();
    }

}
