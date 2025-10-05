package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {
	public static WebDriver driver;
	public Logger logger;
	public Properties prop;

	@SuppressWarnings("deprecation")
	@BeforeClass(groups = { "Sanity", "Regression", "Master" })
	@Parameters({ "os", "browser" })
	public void setup(String os, String br) throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//test//resources//config.properties");
		prop.load(fis);
		String url = prop.getProperty("appURL");

		logger = LogManager.getLogger(this.getClass());

		// GRID SetUp
		if (prop.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities cap = new DesiredCapabilities();

			if (os.equalsIgnoreCase("windows"))
				cap.setPlatform(Platform.WIN11);
			else if (os.equalsIgnoreCase("mac"))
				cap.setPlatform(Platform.MAC);
			else if(os.equalsIgnoreCase("linux"))
				cap.setPlatform(Platform.LINUX);
			else {
				System.out.print("No matching OS...");
				return;
			}

			switch (br.toLowerCase()) {
			case "chrome":
				cap.setBrowserName("chrome");
				break;

			case "edge":
				cap.setBrowserName("MicrosoftEdge");
				break;

			case "firefox":
				cap.setBrowserName("firefox");
				break;

			default:
				System.out.println("No matching browser");
				break;
			}
			
			driver = new RemoteWebDriver(new URL("http://192.168.1.7:4444/wd/hub"), cap);
		}

		if (prop.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("Invalid browser name!!!");
				return;
			}
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();

		driver.get(url);
	}

	@AfterClass(groups = { "Sanity", "Regression", "Master" })
	public void tearDown() {
		driver.quit();
	}

	public String captureScreenshot(String fileName) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + fileName + "_" + timeStamp
				+ ".png";
		File dest = new File(targetFilePath);

		src.renameTo(dest);
		return targetFilePath;
	}

	@SuppressWarnings("deprecation")
	public String randomString() {
		return RandomStringUtils.randomAlphabetic(5);
	}

	@SuppressWarnings("deprecation")
	public String randomNumber() {
		return RandomStringUtils.randomNumeric(10);
	}

	@SuppressWarnings("deprecation")
	public String randomPassword() {
		return RandomStringUtils.randomAlphabetic(5) + "@" + RandomStringUtils.randomNumeric(6);
	}
}
