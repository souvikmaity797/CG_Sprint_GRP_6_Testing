package page_models;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LabTestPage {

    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public LabTestPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // 🔍 Search box locator
    @FindBy(xpath = "//input[@placeholder='Search for lab tests']")
    WebElement searchBox;
    
    
    @FindBy(xpath="//*[contains(@class, \"QX\")]/*[1]")
    List<WebElement> resultlist;
    
    @FindBy(xpath="//span[text()='Go To Cart']")
    WebElement go_to_cart;

    
    @FindBy(xpath="(//*[@class='cl dl DiagItemAccordionPatient_checkbox__Rz7Wh '])[1]/*[2]")
    WebElement addPatient;
    
  @FindBy(xpath="//button[@class='PatientSelection_nextActionBtn__fLmkL ']")
  WebElement nexttoCart;
  
    
  @FindBy(xpath="//button[@class='SlotSelection_nextActionBtn__2OqHn  ']")
  WebElement reviewCart;
  
  
  
    // Action: enter test name
    public void enterLabTest(String testName) {
        wait.until(ExpectedConditions.visibilityOf(searchBox));
        searchBox.click();
        searchBox.clear();
        searchBox.sendKeys(testName);
    }

    // Action: press Enter to search
    public void searchLabTest(String testName) {
        enterLabTest(testName);
        searchBox.sendKeys(Keys.ENTER);
    }

    
    public void addPatient() {
    	addPatient.click();
    	
    }
    
    public void clickNext() {
    	nexttoCart.click();
    }
    
    public void clickreviewCart() {
    	reviewCart.click();
    }
    
    
    
    
    // Optional: get entered text
    public String getSearchText() {
        return searchBox.getAttribute("value");
    }
}
