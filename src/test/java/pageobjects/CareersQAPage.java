package pageobjects;


import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class CareersQAPage extends BasePage {

   
    private By seeAllQAJobsButton = By.cssSelector("a[href*='qualityassurance']");
    private By jobList = By.cssSelector("#jobs-list");

    private By departmentFilter = By.cssSelector("#select2-filter-by-department-container");
    private By departmentFilterQATitle = By.cssSelector("#select2-filter-by-department-container[title='Quality Assurance']");
    
    private By locationFilter = By.cssSelector("#select2-filter-by-location-container");
    private By noPositionAvailableBox = By.cssSelector("#jobs-list > p");
    
    
    private By jobBox = By.cssSelector("#jobs-list > div> div");
    private By qualityAssuranceJobTitle = By.xpath("//*[@class='position-title font-weight-bold' and (contains(text(), 'Quality Assurance') or contains(text(), 'QA'))]");
    private By qualityAssuranceJobDepartment = By.xpath("//*[@class='position-department text-large font-weight-600 text-primary'and contains(text(), 'Quality Assurance')]");
    private By qualityAssuranceJobLocation = By.xpath("//*[@class='position-location text-large' and contains(text(), 'Istanbul, Turkiye')]");
    
    private By jobsPaginationArrowRight = By.xpath("//div[@class='jobs-pagination']//i[@class = 'icon-arrow-right']");
    private By jobsPaginationArrowLeft = By.xpath("//div[@class='jobs-pagination']//i[@class = 'icon-arrow-left']");
    
    private By viewRoleButton = By.xpath("//a[text()='View Role']");




    public CareersQAPage(WebDriver driver) {
        super(driver); 
    }

    public void clickSeeAllQAJobs() {
    	clickOnObject(seeAllQAJobsButton);
    }

    public void filterJobsByLocation(String location) {
    	waitForElementToBeVisible(departmentFilterQATitle, 20); //Added due to long loading time of job filters.
    	clickOnObject(locationFilter);
    	clickOnObject(By.cssSelector("li[data-select2-id*= '"+location+"' ]"));
    	
    }

    public void filterJobsByDepartment(String department) {
    	waitForElementToBeVisible(departmentFilterQATitle, 20); //Added due to long loading time of job filters.
    	clickOnObject(departmentFilter);
    	clickOnObject(By.cssSelector("li[data-select2-id*= '"+department+"' ]"));
    
    }

    public boolean isJobListVisible() {
    	boolean result = false;
        try {
        	 result = isElementDisplayed(jobList);
        } catch (Exception e) {
        	System.out.println("Can't find job list");
        }
        return result;
    }
    
	public void viewRole() {
        try {
        	clickOnObject(viewRoleButton);
        } catch (Exception e) {
        	System.out.println("Can't click on View Role button");
        }
    }
    
    public void clickRightArrow() {
        try {
        	clickOnObject(jobsPaginationArrowRight);
        } catch (Exception e) {
        	System.out.println("Can't click on Pagination Arrow Right button");
        }
    }
    
    public void clickLeftArrow() {
        try {
        	clickOnObject(jobsPaginationArrowLeft);
        } catch (Exception e) {
        	System.out.println("Can't click on Pagination Arrow Left button");
        }
    }
    
    public void scrollIntoJobBoxSection() {
    	scrollIntoElement(findElement(jobBox), driver);
        try {
        	scrollIntoElement(findElement(jobBox), driver);
        } catch (Exception e) {
        	System.out.println("Can't scroll into Jobs");
        }
    }
    
    
    public boolean isJobsContainsRequiredQATexts() {
        int jobBoxListCount = 0;
        int jobTitlesWQualityAssuranceListSize = 0;
        int jobDepartmentsWQualityAssuranceListSize = 0;
        int jobLocationsWIstanbulListSize = 0;

        scrollIntoJobBoxSection();
        
        try {
        	waitForElementToBeVisible(qualityAssuranceJobTitle, 10);// Check if any Quality Assurance jobs available, wait is long due to high load time
       } 
        catch (Exception e) {
        	System.out.println("No Quality Assurance Jobs");
        	return false;
       }

        while (findElement(jobsPaginationArrowRight).isEnabled()) {
            List<WebElement> jobBoxList = findElements(jobBox);
            List<WebElement> jobTitlesWQualityAssuranceList = findElements(qualityAssuranceJobTitle);
            List<WebElement> jobDepartmentsWQualityAssuranceList = findElements(qualityAssuranceJobDepartment);
            List<WebElement> jobLocationsWIstanbulList = findElements(qualityAssuranceJobLocation);


            if (jobBoxList.isEmpty() && jobTitlesWQualityAssuranceList.isEmpty() &&
                jobDepartmentsWQualityAssuranceList.isEmpty() && jobLocationsWIstanbulList.isEmpty()) 
                break; 

            jobBoxListCount += jobBoxList.size();
            jobTitlesWQualityAssuranceListSize += jobTitlesWQualityAssuranceList.size();
            jobDepartmentsWQualityAssuranceListSize += jobDepartmentsWQualityAssuranceList.size();
            jobLocationsWIstanbulListSize += jobLocationsWIstanbulList.size();

            clickRightArrow();
            
            try {
            	waitForElementToBeVisible(noPositionAvailableBox, 10);// Check if we found end of the list, wait is long due to high load time
           } 
            catch (Exception e) {
            	break;
           }

           

        }

        // Ensure we only return true when all lists match in size
        return jobBoxListCount == jobTitlesWQualityAssuranceListSize &&
               jobBoxListCount == jobDepartmentsWQualityAssuranceListSize &&
               jobBoxListCount == jobLocationsWIstanbulListSize;
    }
    
}