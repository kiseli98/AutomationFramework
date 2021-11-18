package support.page_objects.webelements;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;

@Log4j
public class FileUpload extends CustomElement {

    public FileUpload(By locator, String name, CustomElement parentElement) {
        super(locator, name != null ? name + " Upload Button" : null, parentElement);
    }

    public void uploadFile(String file) {
//        TODO
//        String absolutePath = file + smth
        log.info("Uploading File: " + file);
        this.getRawElement().sendKeys(file);
    }
}
