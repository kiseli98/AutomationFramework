package support.page_objects.webelements;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;

@Log4j
public class SearchComponent extends CustomElement {
    public TextInput searchInput;
    public Button searchButton;

    public SearchComponent(By locator, String name, CustomElement parentElement) {
        super(locator, name != null ? name + " Search Component" : null, parentElement);
        this.searchInput = new TextInput(By.xpath(".//input"), null, this);
        this.searchButton = new Button(By.xpath(".//*"), null, this);
    }

    public void search(String val) {
        log.info("Searching for:: " + val);
        this.searchInput.waitTillIsEnabled(10);
        this.searchInput.appendKeys(val);
    }

    public void searchWithClick(String val) {
        log.info("Searching for:: " + val);
        this.searchInput.waitTillIsEnabled(10);
        this.searchInput.appendKeys(val);
        this.searchButton.waitTillIsEnabled(10);
        this.searchButton.click();
    }

}
