package utilizations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.Dimension;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class WebDriverUtilizations {

    public static WebDriver getDriver() {

        // Setup ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();

        // Create ChromeOptions (optional, if needed for other configurations)
        ChromeOptions options = new ChromeOptions();

        // Optional for running headless
        options.addArguments("--headless");

        // Initialize the ChromeDriver
        WebDriver driver = new ChromeDriver(options);

        // Implicit Wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        // Set the window size to a specific resolution (e.g., 1920x1080)
        driver.manage().window().setSize(new Dimension(1920, 1080));

        // Return the WebDriver instance
        return driver;
    }
}