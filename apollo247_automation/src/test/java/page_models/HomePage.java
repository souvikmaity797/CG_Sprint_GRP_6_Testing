
package page_models;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    WebDriver driver;

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locator for "Lab Tests" menu/button
    @FindBy(xpath = "//a[contains(@href,'lab-tests')]")
    WebElement labTestsLink;

    // Action method to click Lab Tests
    public void clickLabTests() {
        labTestsLink.click();
    }

    // Optional: verify page title
    public String getPageTitle() {
        return driver.getTitle();
    }
}
