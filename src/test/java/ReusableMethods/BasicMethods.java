package ReusableMethods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BasicMethods {

	WebDriver driver;

	public String readFromConfig(String key) {
		String value = null;

		try {
			FileInputStream fis = new FileInputStream("config.properties");
			Properties prop = new Properties();
			prop.load(fis);

			value = prop.getProperty(key);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return value;
	}

	public WebDriver launchBrowser(String browserName) {

		if (browserName.equalsIgnoreCase("chrome")) {
			System.out.println("Invoking Chrome");

			ChromeOptions opt = new ChromeOptions();
			opt.addArguments("start-maximized");

			System.setProperty("webdriver.chrome.driver", "res\\drivers\\chromedriver.exe");
			driver = new ChromeDriver(opt);

		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.out.println("Invoking Firefox");

			System.setProperty("webdriver.gecko.driver", "res\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();

		} else {
			System.out.println("Choose valid browser - Chrome or Firefox");
		}

		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		return driver;

	}

	public void navigateToURL(String URL) {
		driver.get(URL);
		System.out.println("Navigating to URL: " + URL);
	}

	public void click(WebDriver driver, WebElement element) {
		element.click();
	}

	public void back(WebDriver driver) {
		driver.navigate().back();
	}

	public void scrollIntoView(WebDriver driver, WebElement element, String objectName) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", element);
		System.out.println("Scrolling into " + objectName);
	}

	public int getStatusCode(WebDriver driver, String url) throws MalformedURLException, IOException {
		HttpURLConnection conn = (HttpURLConnection) new java.net.URL(url).openConnection();
		conn.setRequestMethod("HEAD");
		conn.connect();
		int responseCode = conn.getResponseCode();
		return responseCode;
	}

}
