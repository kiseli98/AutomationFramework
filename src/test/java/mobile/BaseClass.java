//package mobile;
//
//import io.appium.java_client.AppiumDriver;
//import io.appium.java_client.MobileElement;
//import io.appium.java_client.remote.MobileCapabilityType;
//import org.apache.log4j.Logger;
//import org.openqa.selenium.Platform;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
//import support.page_objects.webelements.WebElementX;
//
//import java.net.URL;
//
//public class BaseClass {
//    final Logger logger = Logger.getLogger(WebElementX.class);
//    AppiumDriver<MobileElement> driver;
//
//    @BeforeTest
//    public void setup() {
//        try {
//            DesiredCapabilities caps = new DesiredCapabilities();
//
//            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
//            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");
//            caps.setCapability(MobileCapabilityType.DEVICE_NAME, "ONEPLUS A6003");
//            caps.setCapability(MobileCapabilityType.UDID, "b43d527e");
//            caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
////        caps.setCapability(MobileCapabilityType.APP, "");  //for native
//            caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");  //for web/hybrid
//
//            URL url = new URL("http://127.0.0.1:4723/wd/hub");
//            driver = new AppiumDriver<MobileElement>(url, caps);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void sampleTest() {
//        logger.info("Inside sample test");
//    }
//
//
//    @AfterTest
//    public void teardown() {
//
//    }
//}
