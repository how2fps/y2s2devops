import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddItemTest {
	// declare Selenium WebDriver
	private WebDriver webDriver;

	// I want to enter all form fields to create a new item
	@Test
	public void fillAddItemForm() {
		// Load website as a new page
		webDriver.navigate().to("http://localhost:8081/devopsproject/AddItem.jsp");

		// Declares the form inputs.
		WebElement itemName = webDriver.findElement(By.name("itemName"));
		WebElement itemImage = webDriver.findElement(By.name("itemImage"));
		WebElement itemDescription = webDriver.findElement(By.name("itemDescription"));
		WebElement itemPricing = webDriver.findElement(By.name("itemPricing"));
		WebElement itemQuantity = webDriver.findElement(By.name("itemQuantity"));

		// Adds things in the form.
		itemName.sendKeys("KELL Keyboard");
		itemImage.sendKeys("C:\\Users\\User\\Pictures\\aesthetically pleasing background.png");
		itemDescription.sendKeys("This is a very nice keyboard. 75% layout. Membrane Switches");
		itemPricing.sendKeys("12.89");
		itemQuantity.sendKeys("3");

		// After adding the item, we want to check if we are redirected back to the
		// correct page.
		// Assert the title to check that we are indeed in the correct website
		Assert.assertEquals(webDriver.getTitle(), "Items Listed");
	}

	@BeforeTest
	public void beforeTest() {
		// Setting system properties of ChromeDriver
		// to amend directory path base on your local file path
		String chromeDriverDir = "C:\\Program Files\\Google\\Chrome\\chromedriver.exe";

		System.setProperty("webdriver.chrome.driver", chromeDriverDir);

		// initialize FirefoxDriver at the start of test
		webDriver = new ChromeDriver();
	}

	@AfterTest
	public void afterTest() {
		// Quit the ChromeDriver and close all associated window at the end of test
		webDriver.quit();
	}

}
