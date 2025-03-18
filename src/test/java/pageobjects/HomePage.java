package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {


    private By companyMenu = By.xpath("//a[contains(text(),'Company')]");
    private By careersMenu = By.cssSelector("a[href*='careers'].dropdown-sub");
   
    public HomePage(WebDriver driver) {
        super(driver); 
    }


    public void clickCompanyMenu() {
    	
        try {
        	clickOnObject(companyMenu);
        } catch (Exception e) {
        	System.out.println("Can't click on Company dropdown button");
        }
    	
    }


    public void clickCareersMenu() {
        try {
        	clickOnObject(careersMenu);
        } catch (Exception e) {
        	System.out.println("Can't click on Careers button on dropdown button");
        }
    }


    public boolean isHomePageOpened() {
        return driver.getTitle().contains("#1 Leader in Individualized, Cross-Channel CX — Insider");
    }
}