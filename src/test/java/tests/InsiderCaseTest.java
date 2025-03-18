package tests;

import pageobjects.CareersPage;
import pageobjects.HomePage;
import pageobjects.CareersQAPage;
import utilizations.WebDriverUtilizations;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class InsiderCaseTest {

	    private WebDriver driver;
	    private HomePage homePage;
	    private CareersPage careersPage;
	    private CareersQAPage qaJobsPage;

	    @BeforeClass
	    public void setUp() {
	        driver = WebDriverUtilizations.getDriver();
	        homePage = new HomePage(driver);
	        careersPage = new CareersPage(driver);
	        qaJobsPage = new CareersQAPage(driver);
	        
	        driver.get("https://useinsider.com/");
	        homePage.declineCookies();
	    }

	    @Test
	    public void IsHomePageOpened() {
	        Assert.assertTrue(homePage.isHomePageOpened(), "Home page is not opened correctly");
	    }

	    @Test(dependsOnMethods = "IsHomePageOpened")
	    public void AreCareersPageBlocksVisible() {

	        homePage.clickCompanyMenu();
	        homePage.clickCareersMenu();
	        Assert.assertTrue(careersPage.isLocationsBlockVisible(), "Locations block is not visible");
	        Assert.assertTrue(careersPage.isTeamsBlockVisible(), "Teams block is not visible");
	        Assert.assertTrue(careersPage.isLifeAtInsiderBlockVisible(), "Life at Insider block is not visible");
	    }

	    @Test(dependsOnMethods = "AreCareersPageBlocksVisible")
	    public void AreQAJobsPresent() {
	        driver.get("https://useinsider.com/careers/quality-assurance/");
	        qaJobsPage.clickSeeAllQAJobs();
	        qaJobsPage.filterJobsByLocation("Istanbul, Turkiye");
	        qaJobsPage.filterJobsByDepartment("Quality Assurance");
	        Assert.assertTrue(qaJobsPage.isJobListVisible(), "No jobs listed after filtering");
	    }

	    @Test(dependsOnMethods = "AreQAJobsPresent")
	    public void CheckQAJobPositions() {
	    	Assert.assertTrue(qaJobsPage.isJobsContainsRequiredQATexts(), "Not all jobs contains Quality Assurance or Istanbul, Turkiye texts");
	    }

	    @Test(dependsOnMethods = "CheckQAJobPositions")
	    public void IsViewRoleRedirecting() {
	    	
	    	qaJobsPage.clickLeftArrow();
	    	qaJobsPage.scrollIntoJobBoxSection();
	    	qaJobsPage.clickLeftArrow();
	    	qaJobsPage.viewRole();
	    	
	        Set<String> windowHandles = driver.getWindowHandles();
	        String currentWindowHandle = driver.getWindowHandle(); 
	        for (String handle : windowHandles) {
	            if (!handle.equals(currentWindowHandle)) {
	                driver.switchTo().window(handle);
	                break;
	            }
	        }
	    	Assert.assertTrue(driver.getCurrentUrl().contains("jobs.lever.co/useinsider"), "View role button did not direct to lever page");
	    	
	    }

	    @AfterClass
	    public void tearDown() {
	        driver.quit();
	    }
}
