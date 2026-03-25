package page_models;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	WebDriver driver;
	WebDriverWait wait;
	
	public LoginPage (WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	public void closePopup() {

        WebElement shadowHost = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.cssSelector("ct-web-popup-imageonly"))
        );

        SearchContext shadowRoot = shadowHost.getShadowRoot();

        WebElement closeBtn = shadowRoot.findElement(By.cssSelector(".close"));

        closeBtn.click();
    }
	
	@FindBy(xpath = "//span[text()=\"Login\"]")
	WebElement login;

	@FindBy(name = "mobileNumber")
	WebElement mob;
	
	public void clickLogin() {
		login.click();
	}
	
	public void enterMob(String num) {
		mob.sendKeys(num+Keys.ENTER);
	}
}