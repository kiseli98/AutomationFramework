package support.page_objects.webelements;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Log4j
public class RadioGroup {
//    protected final Logger logger = Logger.getLogger(RadioGroup.class);
    private PlainRadioElement[] elements;

    public RadioGroup(PlainRadioElement[] elements) {
        this.elements = elements;
    }

    public PlainRadioElement getElement(String name) {
        List<PlainRadioElement> res = Arrays.stream(elements)
                .filter(el -> el.getName().equals(name + " Plain radio button")).collect(Collectors.toList());
        return res.size() > 0 ? res.get(0) : null;
    }

    public void select(String name) {
        log.info("Selecting " + " radio option");
        PlainRadioElement el = getElement(name);
        if (el != null) {
            el.element(By.xpath(".//input")).click();
        } else {
            throw new Error("Element " + name + " is not part of radio group");
        }
    }


}
