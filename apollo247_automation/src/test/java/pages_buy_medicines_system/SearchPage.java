package pages_buy_medicines_system;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {

    WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(text(),'Pain')]")
    WebElement cateFilterChkbox;

    @FindBy(xpath = "//span[contains(text(),'View Cart')] | //a[contains(@href,'cart')]")
    WebElement viewCartBtn;

    // Apply category filter to refine search results
    public void clickCategoryFilter() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement filter = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//label[contains(text(),'Pain Relief')]")
            )
        );

        filter.click();
    }

    // Select first product from search results and navigate to product page
    public void clickOnProduct() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//div[contains(@class,'ProductCard')]")
        ));

        By firstProduct = By.xpath("(//div[contains(@class,'ProductCard')]//a)[1]");

        WebElement product = wait.until(
            ExpectedConditions.elementToBeClickable(firstProduct)
        );

        // Scroll to avoid header overlap issues
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});", product
        );

        // Use JS click to avoid click interception
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].click();", product
        );

        wait.until(ExpectedConditions.urlContains("medicine"));
    }

    // Click Add to Cart (handles both product page and listing page)
    public void clickOnAddToCart() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        List<WebElement> addToCart = driver.findElements(
            By.xpath("//button[contains(.,'Add to Cart')]")
        );

        if (addToCart.size() > 0) {

            WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(addToCart.get(0))
            );

            // Scroll slightly above to avoid sticky header overlap
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true); window.scrollBy(0, -120);",
                btn
            );

            // JS click ensures stability when normal click fails
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", btn
            );

        } else {

            // Fallback: add product directly from listing page
            By addBtn = By.xpath("(//div[contains(@class,'ProductCard')]//button)[1]");

            WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(addBtn)
            );

            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", btn
            );
        }
    }

    // Navigate to cart page after adding product
    public void clickOnViewCart() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        By viewCartLocator = By.xpath(
            "//span[contains(text(),'View Cart')] | //a[contains(@href,'cart')]"
        );

        WebElement viewCart = wait.until(
            ExpectedConditions.visibilityOfElementLocated(viewCartLocator)
        );

        // Scroll to avoid overlay issues
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});", viewCart
        );

        // Attempt normal click, fallback to JS click
        try {
            viewCart.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", viewCart
            );
        }

        wait.until(ExpectedConditions.urlContains("cart"));
    }
}