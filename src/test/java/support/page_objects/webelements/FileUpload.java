package support.page_objects.webelements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FileUpload extends WebElementX {

    public FileUpload(By locator, String name, WebElementX parentElement, WebDriver driver) {
        super(locator, name != null ? name + " Upload Button" : null, parentElement, driver);
    }

    public void uploadFile(String file) {
//        TODO
//        String absolutePath = file + smth
        System.out.println("Uploading File: " + file);
        this.getRawElement().sendKeys(file);
    }
}