package support.managers;

import lombok.experimental.UtilityClass;
import org.openqa.selenium.WebDriver;

@UtilityClass
public class DriverManager {

	private final ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>() {
		@Override
		public void remove() {
			WebDriver driver = get();
			if (driver != null) {
				driver.close();
				driver.quit();
			}
			super.remove();
		}
	};

	WebDriver getWebDriver() {
		return webDriver.get();
	}

	void setWebDriver(WebDriver driver) {
		webDriver.set(driver);
	}

	void quitWebDriver() {
		webDriver.remove();
	}

}
