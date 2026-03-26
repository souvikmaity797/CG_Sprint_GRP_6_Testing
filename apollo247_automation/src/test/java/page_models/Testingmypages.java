package page_models;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Testingmypages {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Open website
        driver.get("https://www.apollo247.com/");
		Thread.sleep(5000);
	LabTestPage hp=new LabTestPage(driver);
	System.out.println(hp.searchResultText());

	}

}
