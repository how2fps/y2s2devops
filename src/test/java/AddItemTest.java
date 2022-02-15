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
		// we need to firstly login first.
		webDriver.navigate().to("http://localhost:8090/devopsproject/Login.jsp");
		WebElement email = webDriver.findElement(By.name("email"));
		WebElement password = webDriver.findElement(By.name("password"));
		email.sendKeys("user@mail.com");
		password.sendKeys("Password123");

		webDriver.findElement(By.id("submit")).submit();

		// check if we have successfully login as user
		Assert.assertEquals(webDriver.getTitle(), "Shop-Wijs");

		// Load the designated page
		webDriver.navigate().to("http://localhost:8090/devopsproject/AddItem.jsp");

		// Declares the form inputs.
		WebElement itemName = webDriver.findElement(By.name("itemName"));
		WebElement itemImage = webDriver.findElement(By.name("itemImage"));
		WebElement itemDescription = webDriver.findElement(By.name("itemDescription"));
		WebElement itemPricing = webDriver.findElement(By.name("itemPricing"));
		WebElement itemQuantity = webDriver.findElement(By.name("itemQuantity"));
		WebElement addItemButton = webDriver.findElement(By.name("confirmListingBtn"));

		// Adds things in the form.
		itemName.sendKeys("KELL Keyboard");
		itemImage.sendKeys("C:\\Users\\User\\Pictures\\aesthetically pleasing background.png");
		itemDescription.sendKeys("This is a very nice keyboard. 75% layout. Membrane Switches");
		itemPricing.sendKeys("12.89");
		itemQuantity.sendKeys("3");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("got interrupted!");
		}

		// Add the item by pressing the Add Item button.
		addItemButton.click();

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			System.out.println("got interrupted!");
		}

		// we need to have the servlet functionality.

		// After adding the item, we want to check if we are redirected back to the
		// correct page.
		// Assert the title to check that we are indeed in the correct page
		Assert.assertEquals(webDriver.getTitle(), "Items Listed");
	}

	@BeforeTest
	public void beforeTest() {
		String chromeDriverDir = "C:\\Program Files\\Google\\Chrome\\chromedriver.exe";

		System.setProperty("webdriver.chrome.driver", chromeDriverDir);

		// initialize FirefoxDriver at the start of test
		webDriver = new ChromeDriver();
	}

	@AfterTest
	public void afterTest() {
		// Quit the ChromeDriver and close all associated window at the end of test
		webDriver.close();
		webDriver.quit();
	}

}
