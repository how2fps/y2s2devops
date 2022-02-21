import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class DeleteUserTest {

	private WebDriver webDriver;

	@Test(priority = 1)
	public void checkDeleteAccount() {
		webDriver.navigate().to("http://localhost:8090/devopsproject/Login.jsp");
		WebElement email = webDriver.findElement(By.name("email"));
		WebElement password = webDriver.findElement(By.name("password"));
		email.sendKeys("test@test.com");
		password.sendKeys("password");
		webDriver.findElement(By.id("submit")).submit();
		Assert.assertEquals(webDriver.getTitle(), "Shop-Wijs");
		WebElement button = webDriver.findElement(By.id("edit-profile"));
		button.click();
		Assert.assertEquals(webDriver.getTitle(), "Edit Profile");
		webDriver.findElement(By.id("deleteUserButton")).submit();
		Assert.assertEquals(webDriver.getTitle(), "Login");
	}
	
	@Test(priority = 2)
	public void checkExistenceOfDeletedAccount() {
		webDriver.navigate().to("http://localhost:8090/devopsproject/Login.jsp");
		WebElement email = webDriver.findElement(By.name("email"));
		WebElement password = webDriver.findElement(By.name("password"));
		email.sendKeys("test@test.com");
		password.sendKeys("password");
		webDriver.findElement(By.id("submit")).submit();
		Assert.assertEquals(webDriver.findElement(By.id("alert")).getText(),
				"The email test@test.com is not registered!");
	}

	@BeforeTest
	public void beforeTest() {
		String chromeDriverDir = "C:\\Program Files\\Google\\Chrome\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromeDriverDir);
		webDriver = new ChromeDriver();
	}

	@AfterTest
	public void afterTest() {
		webDriver.close();
	}

}
