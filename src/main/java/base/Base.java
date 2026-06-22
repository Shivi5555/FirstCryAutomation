package base;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driverRef) {
        driver.set(driverRef);
    }

    public static void removeDriver() {
        driver.remove();
    }

    public String getUrl() throws Exception {
        Properties prop = new Properties();

        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + "/src/main/java/data.properties");

        prop.load(fis);

        return prop.getProperty("url");
    }

    public String getBrowser() throws Exception {
        Properties prop = new Properties();

        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + "/src/main/java/data.properties");

        prop.load(fis);

        return prop.getProperty("browser");
    }

    public WebDriver initializedDriver() throws Exception {

        String browserName = getBrowser();

        WebDriver localDriver = null;

        if (browserName.equalsIgnoreCase("chrome")) {

            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();

            Map<String, Object> prefs = new HashMap<String, Object>();

            prefs.put("profile.default_content_setting_values.geolocation", 2);
            prefs.put("profile.default_content_setting_values.notifications", 2);

            options.setExperimentalOption("prefs", prefs);

            options.addArguments("--disable-notifications");
            options.addArguments("--disable-geolocation");
            options.addArguments("--deny-permission-prompts");

            localDriver = new ChromeDriver(options);
        }

        localDriver.manage().window().maximize();
        localDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        setDriver(localDriver);

        return getDriver();
    }
}