package page_models;



import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestsCartPage {

    WebDriver driver;
    WebDriverWait wait;

    public TestsCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    // 🏷️ Coupon Input Box
    @FindBy(xpath = "//input[@placeholder='Enter Coupon Code']")
    WebElement couponInput;

    // ✅ Apply Coupon Button
    @FindBy(xpath = "//span[contains(text(),'Offers and Coupons')]")
    WebElement applyCouponBtn;

    //Get the span text
    
    @FindBy(xpath = "//div[contains(@class,'Coupon_pinErrorMsg')]")
    WebElement couponErrorMsg;
    // 🗑️ Remove item from cart
    @FindBy(xpath = "//img[contains(@src,'ic_clearinput.svg')]")
    WebElement removeFromCartBtn;

    // 💰 Total Payable Amount
    @FindBy(xpath = "//span[contains(text(),\"Proceed to Pay\")]")
    WebElement toPayAmount;

    // ---------------- ACTION METHODS ----------------



    // Enter coupon
    public String enterCoupon(String coupon) {
        wait.until(ExpectedConditions.visibilityOf(couponInput));
        couponInput.clear();
        couponInput.sendKeys(coupon,Keys.ENTER);
        
        return couponErrorMsg.getText();
        
    }

    // Apply coupon
    public void applyCoupon() {
        wait.until(ExpectedConditions.elementToBeClickable(applyCouponBtn));
        applyCouponBtn.click();
    }

  

    // Remove item from cart
    public void removeItemFromCart() {
        wait.until(ExpectedConditions.elementToBeClickable(removeFromCartBtn));
        removeFromCartBtn.click();
    }

    // Get To Pay amount
    public String getToPayAmount() {
        wait.until(ExpectedConditions.visibilityOf(toPayAmount));
        String text= toPayAmount.getText();
        return text.replaceAll("[^0-9.]", "");

    }
}