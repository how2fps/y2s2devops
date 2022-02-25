import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class ValidateAddItemToCartTest {
	// declare Selenium WebDriver
	private WebDriver webDriver;

	@Test
	public void ValidateAddItemByCurrentUser() throws InterruptedException {
		// Register a second account as a dummy account to demonstrate the testing
		webDriver.navigate().to("http://localhost:8090/devopsproject/SignUp.jsp");
		WebElement regemail = webDriver.findElement(By.name("email"));
		WebElement regpassword = webDriver.findElement(By.name("password"));
		WebElement regconfirmPassword = webDriver.findElement(By.name("confirmPassword"));
		WebElement regdisplayName = webDriver.findElement(By.name("displayName"));
		WebElement regphoneNumber = webDriver.findElement(By.name("phoneNumber"));
		regemail.sendKeys("dummytest@email.com");
		regpassword.sendKeys("password");
		regconfirmPassword.sendKeys("password");
		regdisplayName.sendKeys("DummyTest");
		regphoneNumber.sendKeys("99999999");
		webDriver.findElement(By.id("submit")).submit();
		Assert.assertEquals(webDriver.findElement(By.id("alert")).getText(), "Registration Successful!");

		// Login page to access
		webDriver.navigate().to("http://localhost:8090/devopsproject/Login.jsp");
		System.out.println("title: " + webDriver.getTitle());
		Assert.assertEquals(webDriver.getTitle(), "Login");
		WebElement email = webDriver.findElement(By.name("email"));
		WebElement password = webDriver.findElement(By.name("password"));
		email.sendKeys("dummytest@email.com");
		password.sendKeys("password");

		webDriver.findElement(By.id("submit")).submit();
		Assert.assertEquals(webDriver.getTitle(), "Shop-Wijs");

		// Go to Items Shop page
		webDriver.navigate().to("http://localhost:8090/devopsproject/ItemsShopServlet");
		Assert.assertEquals(webDriver.getTitle(), "Items Shop");

		// Discover the item from Items Shop page
		Assert.assertTrue(webDriver.getPageSource().contains("Changed"), "Item is in the Items Shop!");

		// Click the button based on target id of item
		WebElement seeDetailsBtn = webDriver.findElement(By.id("ChangedseeDetailsBtn"));
		seeDetailsBtn.click();

		// Check if we are in the itemview page.
		Assert.assertEquals(webDriver.getTitle(), "Item View");

		// Declare form input for quantity
		WebElement additemquantityofuser = webDriver.findElement(By.name("additemquantityofuser"));

		Thread.sleep(5000);

		// Input a wrong quantity to reserved the item
		additemquantityofuser.sendKeys("0");

		// Attempt to add item of wrong quantity
		webDriver.findElement(By.id("addItemToCartBtn")).submit();

		// Alert popups to indicate the validation of current user reserved quantity
		Assert.assertEquals(webDriver.getTitle(), "Item View");
		Thread.sleep(5000);

		// Delete a dummy account to end the testing
		webDriver.navigate().to("http://localhost:8090/devopsproject/Login.jsp");
		WebElement delemail = webDriver.findElement(By.name("email"));
		WebElement delpassword = webDriver.findElement(By.name("password"));
		delemail.sendKeys("dummytest@email.com");
		delpassword.sendKeys("password");
		webDriver.findElement(By.id("submit")).submit();
		Assert.assertEquals(webDriver.getTitle(), "Shop-Wijs");
		WebElement button = webDriver.findElement(By.id("edit-profile"));
		button.click();
		Assert.assertEquals(webDriver.getTitle(), "Edit Profile");
		webDriver.findElement(By.id("deleteUserButton")).submit();
		Assert.assertEquals(webDriver.getTitle(), "Login");

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

		// Quit the ChromeDriver and close all associated window at the end of test
		webDriver.close();
	}

}
