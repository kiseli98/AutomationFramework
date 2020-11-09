package support.page_objects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import step_definitions.hooks.Hooks;
import support.page_objects.pages.eliteShoppy.ContactForm;
import support.page_objects.pages.eliteShoppy.SignInModal;
import support.page_objects.pages.eliteShoppy.SignUpModal;
import support.utils.BaseUtil;

public class EliteShoppyPage extends BasePage {
//    public static EliteShoppyPage instance = new EliteShoppyPage(Hooks.getDriver(), "https://adoring-pasteur-3ae17d.netlify.app/index.html/");
    public SignInModal signInModal;
    public SignUpModal signUpModal;
    public ContactForm contactForm;


    public EliteShoppyPage(WebDriver driver, String url) {
        super(driver, url);
        PageFactory.initElements(driver, this);
        signInModal = new SignInModal(driver);
        signUpModal = new SignUpModal(driver);
        contactForm = new ContactForm(driver);
    }

    @FindBy(how = How.NAME, using = "search")
    public WebElement searchbar;

    @FindBy(how = How.XPATH, using = ".//a[@role = \"button\" and contains(.,\"Men's wear\")]")
    public WebElement menSWearBtn;

    @FindBy(how = How.XPATH, using = ".//a[@href= \"mens.html\" and .=\"Clothing\"]")
    public WebElement menSWearClothing;

    @FindBy(how = How.XPATH, using = ".//a[contains(.,\"Sign In\")]")
    public WebElement signInBtn;

    @FindBy(how = How.XPATH, using = ".//a[contains(.,\"Sign Up\")]")
    public WebElement signUpBtn;

    @FindBy(how = How.XPATH, using = ".//a[@href=\"contact.html\"]")
    public WebElement contactBtn;

    @FindBy(how = How.XPATH, using = ".//div[contains(@class, \"page-head\")]//h3")
    public WebElement pageHeadInfo;



    public void searchFor(String query) {
        this.searchbar.sendKeys(query);
        this.searchbar.submit();
    }

    public void takePoll (String answer) {
        WebElement elem = driver.findElement(By.xpath(".//div[@class=\"radio\" and contains(.,\"" + answer + "\")]"));
        elem.click();
        elem.submit();
    }

    public void login (String username, String email) {
        signInModal.username.sendKeys(username);
        signInModal.email.sendKeys(email);
        signInModal.email.submit();
    }

    public void register (String username, String email, String password) {
        signUpModal.username.sendKeys(username);
        signUpModal.email.sendKeys(email);
        signUpModal.password.sendKeys(password);
        signUpModal.passwordConfirm.sendKeys(password);
        signUpModal.passwordConfirm.submit();
    }

    public void clickOnCarouselBanner(int num) {
        int carouselNum = num - 1;
        WebElement carouselBtn = driver.findElement(By.xpath(".//*[@data-target=\"#myCarousel\" and @data-slide-to=\"" + carouselNum + "\"]"));
        carouselBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, 3);
        WebElement shopBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//a[contains(.,\"Shop Now\")])[" + num + "]")));
        shopBtn.click();
    }
}