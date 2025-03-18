package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;

    

    private By acceptCookiesButton = By.cssSelector("a#wt-cli-accept-all-btn");
    private By acceptOnlyNecessaryCookiesButton = By.cssSelector("a#wt-cli-accept-btn");
    private By declineCookiesButton = By.cssSelector("a#wt-cli-reject-btn");

    
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }


    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }
    
    public List<WebElement> findElements(By locator) {
        // Return the list of WebElements matching the locator
        return driver.findElements(locator);
    }


    public void clickOnObject(By locator) {
        findElement(locator).click();
    }


    public void waitForElementToBeVisible(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    public boolean isElementDisplayed(By locator) {
        return findElement(locator).isDisplayed();
    }
    
    public static void scrollIntoElement(WebElement element, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    
    public void acceptCookies()
    {
    	clickOnObject(acceptCookiesButton);
    }
    public void acceptOnlyNecessaryCookies()
    {
    	clickOnObject(acceptOnlyNecessaryCookiesButton);
    }
    public void declineCookies()
    {
    	clickOnObject(declineCookiesButton);
    }
}