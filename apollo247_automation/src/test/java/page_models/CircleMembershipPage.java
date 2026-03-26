
package page_models;


import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CircleMembershipPage {

    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public CircleMembershipPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //Join Circle
    @FindBy(xpath = "(//span[contains(text(),'Join') or contains(text(),'Buy')])[2]")
    WebElement joinCircleBtn;
    
    
   

    // Proceed / Continue button after selecting plan
    @FindBy(xpath = "//span[contains(text(),'Proceed to pay')]")
    WebElement proceedBtn;


    
  

    // ---------------- ACTION METHODS ----------------

    // Click Join Circle 
    public void clickJoinCircle() {
       // wait.until(ExpectedConditions.elementToBeClickable(joinCircleBtn));
        joinCircleBtn.click();
    }

    // Click Proceed after selecting plan
    public void clickProceed() {
        wait.until(ExpectedConditions.elementToBeClickable(proceedBtn));
        proceedBtn.click();
    }

 

   
}
