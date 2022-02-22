import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DeleteItemTest {
	// declare Selenium WebDriver
	private WebDriver webDriver;

	@Test
	public void deleteItem() {
		// we need to firstly login.
		WebElement newItemImage = webDriver.findElement(By.id("This should make the code fail."));
		webDriver.navigate().to("http://localhost:8090/devopsproject/Login.jsp");
		WebElement email = webDriver.findElement(By.name("email"));
		WebElement password = webDriver.findElement(By.name("password"));
		email.sendKeys("test@test.com");
		password.sendKeys("password");

		webDriver.findElement(By.id("submit")).submit();

		// check if we have successfully login as user
		Assert.assertEquals(webDriver.getTitle(), "Shop-Wijs");

		// go to the ItemsListedPage
		webDriver.navigate().to("http://localhost:8090/devopsproject/ItemsListedServlet");
		Assert.assertEquals(webDriver.getTitle(), "Items Listed");
		// Locate the "See Details" button that is in the same as the newly edited item
		// from EditItemTest
		Assert.assertTrue(!webDriver.findElements(By.id("ChangedseeDetailsBtn")).isEmpty(),
				"Newly editted item is not found!");

		WebElement seeDetailsBtn = webDriver.findElement(By.id("ChangedseeDetailsBtn"));
		seeDetailsBtn.click();

		// Check if we are in the itemview page.
		Assert.assertEquals(webDriver.getTitle(), "Item View");

		// Click on the delete item button
		WebElement deleteItemBtn = webDriver.findElement(By.id("deleteItemBtn"));
		deleteItemBtn.click();
		// Need to click on the confirm button on the alert popup.
		webDriver.switchTo().alert().accept();

		// Check if we are directed to the correct page or not
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
	}

}
