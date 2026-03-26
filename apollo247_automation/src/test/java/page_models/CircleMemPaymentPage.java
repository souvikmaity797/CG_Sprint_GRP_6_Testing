package page_models;



import java.time.Duration;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CircleMemPaymentPage {

    WebDriver driver;
    WebDriverWait wait;

    public CircleMemPaymentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
    
    // click on credit card option
    @FindBy(xpath="//li[@class=' Juspay_desktopNavBelowActive__XCyuR']")
    WebElement creditCardOption;
      

    // Name on Card
    @FindBy(xpath = "//input[contains(@placeholder,'Name')]")
    WebElement nameOnCard;

    // Card Number
    @FindBy(xpath = "//input[contains(@placeholder,'Enter Card Number')]")
    WebElement cardNumber;

    //Expire Date
    @FindBy(xpath = "//input[@name='card_exp_month']")
    WebElement expiryDate;

    // CVV
    @FindBy(xpath = "//input[contains(@placeholder,'CVV')]")
    WebElement cvv;

    //Pay Button
    @FindBy(xpath = "//span[contains(text(),'Pay ')]")
    WebElement payBtn;
    
    @FindBy(xpath ="//p[contains(text(),'invalid_card_number')]")
    WebElement errorMsg;
    

    // ---------------- ACTION METHODS ----------------
    
    public void clickOnCreditCard() {
    	creditCardOption.click();
    }

    public void enterName(String name) {
       // wait.until(ExpectedConditions.visibilityOf(nameOnCard));
        nameOnCard.clear();
        nameOnCard.sendKeys(name);
    }

    public void enterCardNumber(String number) {
       // wait.until(ExpectedConditions.visibilityOf(cardNumber));
        cardNumber.clear();
        cardNumber.sendKeys(number);
    }

    public void enterExpiry(String expiry) {
      //  wait.until(ExpectedConditions.visibilityOf(expiryDate));
        expiryDate.clear();
        expiryDate.sendKeys(expiry);
    }

    public void enterCVV(String cvvCode) {
      //  wait.until(ExpectedConditions.visibilityOf(cvv));
        cvv.clear();
        cvv.sendKeys(cvvCode);
    }

    public void clickPay() {
      //  wait.until(ExpectedConditions.elementToBeClickable(payBtn));
        payBtn.click();
    }
    
    public String errorMsg() {
    //	wait.until(ExpectedConditions.visibilityOf(errorMsg));
    	return errorMsg.getText();
    }

    // Combined method 
    public void makePayment(String name, String card, String expiry, String cvvCode) {
        enterName(name);
        enterCardNumber(card);
        enterExpiry(expiry);
        enterCVV(cvvCode);
        clickPay();
        
    }
}