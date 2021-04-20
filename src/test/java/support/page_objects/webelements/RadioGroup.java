package support.page_objects.webelements;

import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RadioGroup {
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
        System.out.println("Selecting " + " radio option");
        PlainRadioElement el = getElement(name);
        if (el != null) {
            el.element(By.xpath(".//input")).click();
        } else {
            throw new Error("Element " + name + " is not part of radio group");
        }
    }


}
