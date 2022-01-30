package support.utils;

import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Helpers {

    public static List<Integer> range (int n){
        return IntStream.range(0, n).boxed().collect(Collectors.toList());
    }

    public static List<Integer> rangeFromTo (int from, int to){
        return IntStream.range(from, to).boxed().collect(Collectors.toList());
    }

    // returns first match of p in s for nth group in regular expression
    public static String getRegexMatch(String s, String pattern, int group) {
        Matcher m = Pattern.compile(pattern).matcher(s);
        return m.find() ? m.group(group) : "";
    }

    // returns all matches of p in s for nth group in regular expression
    public static List<String> getRegexMatches(String s, String pattern, int group) {
        List<String> matches = new ArrayList<>();
        Matcher m = Pattern.compile(pattern).matcher(s);
        while(m.find()) {
            matches.add(m.group(group));
        }
        return matches;
    }

    public static String getRegexMatch(String s, String pattern) {
        Matcher m = Pattern.compile(pattern).matcher(s);
        return m.find() ? m.group(0) : "";
    }

    public static List<String> getRegexMatches(String s, String pattern) {
        List<String> matches = new ArrayList<>();
        Matcher m = Pattern.compile(pattern).matcher(s);
        while(m.find()) {
            matches.add(m.group(0));
        }
        return matches;
    }

    @When("I run the test scenario")
    public void iRunTheTestScenario() {
//        DriverFactory.getWebDriver().get("https://dst-fe.dev.whs.adidas.com/dst-collections/default/iframe.html?id=demo-highlights-page--default&globals=backgrounds.grid:false&args=&viewMode=story");
//        productHighlightsPage.waitForPageToLoad();
//        productHighlightsPage.allProductsHaveAttributesProperlyDisplayed();
//        KeyProduct product = productHighlightsPage.getFirstAvailableSection().getFirstAvailableProduct();
//        productHighlightsPage.hoverMouseOnKeyProductHeader(product);
//        Assert.assertEquals(productHighlightsPage.getTooltipText(), product.getHeader().getText());
//        productHighlightsPage.expandProduct(product);
//        productHighlightsPage.sleep(1000);
//
//        product.allAttributesExpandedAreVisible();
//        productHighlightsPage.openVideo(product);
//        productHighlightsPage.sleep(1000);
//        productHighlightsPage.getVideoAssetModal().close();
//        product.changeColorRandomly();
//        productHighlightsPage.sleep(1000);
//        product.changeColorRandomly();
//        productHighlightsPage.collapseProduct(product);
//        product = productHighlightsPage.getSectionByHeader("TUNED FOR CUSHION AND SUPPORT").getFirstAvailableProduct();
//        productHighlightsPage.hoverMouseOnKeyProductHeader(product);
//        Assert.assertEquals(productHighlightsPage.getTooltipText(), product.getHeader().getText());
//        productHighlightsPage.expandProduct(product);
//        product.allAttributesExpandedAreVisible();
//        productHighlightsPage.openVideo(product);
//        productHighlightsPage.sleep(4000);
//        productHighlightsPage.getVideoAssetModal().close();
//
//
//        DriverFactory.getWebDriver().get("https://dst-fe.dev.whs.adidas.com/dst-collections/default/iframe.html?id=demo-concept-page--default&globals=backgrounds.grid:false&args=&viewMode=story");
//        conceptPage.getKeyLooksCarousel().scrollRightForTimes(1);
//        conceptPage.sleep(2000);
//        conceptPage.getKeyLooksCarousel().openItemByIndex(3);
//        conceptPage.sleep(2000);
//        conceptPage.waitUntilWebElementIsVisible(conceptPage.getKeyLooksModal());
//        conceptPage.getKeyLooksModal().scrollLeft();
//        conceptPage.sleep(2000);
//        conceptPage.getKeyLooksModal().scrollRight();

//        DriverFactory.getWebDriver().get("https://dst-fe.dev.whs.adidas.com/dst-collections/default/iframe.html?id=demo-brand-strategy-page--default&viewMode=story");
//        brandStrategyPage.waitForPageToLoad();
//        System.out.println(brandStrategyPage.getPresentation().getCurrentSlideNumber());
//        System.out.println(brandStrategyPage.getPresentation().getSlidesCount());
//        brandStrategyPage.getPresentation().nextSlide();
//        brandStrategyPage.sleep(2000);
//        System.out.println(brandStrategyPage.getPresentation().getCurrentSlideNumber());
//        brandStrategyPage.getPresentation().previousSlide();
//        brandStrategyPage.sleep(2000);
//        brandStrategyPage.getPresentation().enterFullscreen();
//        brandStrategyPage.sleep(2000);
//        brandStrategyPage.getPresentation().nextSlide();
//        System.out.println(brandStrategyPage.getPresentation().getCurrentSlideNumber());
//        brandStrategyPage.getPresentation().previousSlide();

//        assertThat(true).isTrue();
//        System.out.println(1);
//        assertThat(false).isTrue();
//        System.out.println(2);
//        assertThat(true).isTrue();
//        System.out.println(3);
    }
}
