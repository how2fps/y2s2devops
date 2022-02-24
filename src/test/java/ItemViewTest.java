import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class ItemViewTest {
	// declare Selenium WebDriver
	private WebDriver webDriver;

	@Test
	public void ItemViewInformationByItemId() {
		// Login page to access
		webDriver.navigate().to("http://localhost:8090/devopsproject/Login.jsp");
		System.out.println("title: " + webDriver.getTitle());
		Assert.assertEquals(webDriver.getTitle(), "Login");
		WebElement email = webDriver.findElement(By.name("email"));
		WebElement password = webDriver.findElement(By.name("password"));
		email.sendKeys("testuser1@email.com");
		password.sendKeys("password1");

		webDriver.findElement(By.id("submit")).submit();
		Assert.assertEquals(webDriver.getTitle(), "Shop-Wijs");

		// Go to Items Shop page
		webDriver.navigate().to("http://localhost:8090/devopsproject/ItemsShopServlet");
		Assert.assertEquals(webDriver.getTitle(), "Items Shop");
		// Discover the item from Items Shop page
		Assert.assertTrue(webDriver.getPageSource().contains("Bag"), "Item is in the Items Shop!");
		
		webDriver.findElement(By.xpath("//a[@href='ItemViewServlet?itemId=73']")).click();

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
	public void afterTest() throws InterruptedException {
		// Delay to get to the end result of the test
		Thread.sleep(3000);

		// Quit the ChromeDriver and close all associated window at the end of test
		webDriver.close();
	}

}
