package pages_buy_medicines_system;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
	WebDriver driver;

	// Initialize driver and PageFactory elements
	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Quantity dropdown arrow in cart
	@FindBy(xpath = "//span[contains(@class,'arrowIcon')]")
	WebElement quantityDropdownBtn;

	// First quantity option from dropdown
	@FindBy(xpath = "(//div[@class=\"MedicineProductCard_list__Wd7VW \"])[1]")
	WebElement quantity;

	// Coupon related elements
	@FindBy(xpath = "//span[contains(text(),'Apply Coupon')]")
	WebElement applyCouponBtn;

	@FindBy(xpath = "//input[@placeholder=\"Enter Coupon Code\"]")
	WebElement inputBoxCoupon;

	@FindBy(xpath = "(//span[text()=\"Apply\"])[1]")
	WebElement applyBtn;

	@FindBy(xpath = "//span[text()=\"Cancel\"]")
	WebElement cancelBtn;

	// Checkout related elements
	@FindBy(xpath = "//button[contains(text(),'Proceed')]")
	WebElement proceedBtn;

	@FindBy(xpath = "//span[text()=\"Skip Savings\"]")
	WebElement skipBtn;

	// Delete button for removing item from cart
	@FindBy(xpath = "//div[contains(@class,'deleteIcon')]")
	WebElement deleteBtn;

	// Open quantity dropdown using explicit wait
	public void clickOnQuantityDropdown() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			WebElement btn = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class,'arrowIcon')]")));

			btn.click();

		} catch (Exception e) {
			System.out.println("Quantity dropdown not found - skipping");
		}
	}

	// Select quantity after dropdown is opened
	public void selectQuantity() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(quantity)).click();
	}

	// Click on Apply Coupon button
	public void applyCoupon() {
		applyCouponBtn.click();
	}

	// Enter coupon code in input field
	public void enterCouponCode(String coupon) {
		inputBoxCoupon.sendKeys(coupon);
	}

	// Apply entered coupon
	public void clickApply() {
		applyBtn.click();
	}

	// Close coupon popup
	public void exitCouponBox() {
		cancelBtn.click();
	}

	// Proceed to payment page
	public void proceedToPaymentPage() {
		proceedBtn.click();
	}

	// Click delete icon to remove product from cart
	public void clickDeleteBtn() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		WebElement btn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'deleteIcon')]")));

		btn.click();
	}

	// Confirm product removal if confirmation popup appears
	public void confirmDelete() {

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			WebElement removeBtn = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Remove']")));

			removeBtn.click();

		} catch (Exception e) {
			System.out.println("No confirmation popup");
		}
	}

	// Skip savings popup if displayed during checkout
	public void skip() {
		skipBtn.click();
	}
}