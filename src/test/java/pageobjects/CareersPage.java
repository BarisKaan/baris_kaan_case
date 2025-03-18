package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CareersPage extends BasePage {

    // Page-specific locators
    private By locationsBlock = By.xpath("//h3[contains(text(), 'calling')]/..");
    private By teamsBlock = By.xpath("//h3[contains(text(), 'Locations')]/..");
    private By lifeAtInsiderBlock = By.xpath("//h2[text()='Life at Insider']/..");
    
    
//    private By locationsBlock = By.cssSelector("#locations");
//    private By teamsBlock = By.cssSelector("#teams");
//    private By lifeAtInsiderBlock = By.cssSelector("#life-at-insider");


    public CareersPage(WebDriver driver) {
        super(driver); 
    }

    public boolean isLocationsBlockVisible() {
    	boolean result = false;
        try {
        	 result = isElementDisplayed(locationsBlock);
        } catch (Exception e) {
        	System.out.println("Can't find Locations Block");
        }
        return result;
    }

    public boolean isTeamsBlockVisible() {
    	boolean result = false;
        try {
        	 result = isElementDisplayed(teamsBlock);
        } catch (Exception e) {
        	System.out.println("Can't find Teams Block");
        }
        return result;
    }

    public boolean isLifeAtInsiderBlockVisible() {
    	boolean result = false;
        try {
        	 result = isElementDisplayed(lifeAtInsiderBlock);
        } catch (Exception e) {
        	System.out.println("Can't find Life At Insiders Block");
        }
        return result;
    }
}