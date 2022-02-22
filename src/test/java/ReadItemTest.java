import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ReadItemTest {
	// declare Selenium WebDriver
	private WebDriver webDriver;

	@Test
	public void readItem() {
		// we need to firstly login first.
		webDriver.navigate().to("http://localhost:8090/devopsproject/Login.jsp");
		Assert.assertEquals(webDriver.getTitle(), "Login");
		WebElement email = webDriver.findElement(By.name("email"));
		WebElement password = webDriver.findElement(By.name("password"));
		email.sendKeys("test@test.com");
		password.sendKeys("password");

		webDriver.findElement(By.id("submit")).submit();

		// go to the itemsListed page.
		webDriver.navigate().to("http://localhost:8090/devopsproject/ItemsListedServlet");
		Assert.assertEquals(webDriver.getTitle(), "Items Listed");
		// find the item that we have newly entered from the add item test
		Assert.assertTrue(webDriver.getPageSource().contains("KELL Keyboard"), "Item that was added is not found!");

	}

	@BeforeTest
	public void beforeTest() {
		String chromeDriverDir = "C:\\Program Files\\Google\\Chrome\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromeDriverDir);
		webDriver = new ChromeDriver();
	}

	@AfterTest
	public void afterTest() {
		// Quit the ChromeDriver and close all associated window at the end of test
		webDriver.close();
	}

}
