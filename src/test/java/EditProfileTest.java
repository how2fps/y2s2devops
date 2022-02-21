import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class EditProfileTest {

	private WebDriver webDriver;

	@BeforeTest
	public void beforeTest() {
		String chromeDriverDir = "C:\\Program Files\\Google\\Chrome\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromeDriverDir);
		webDriver = new ChromeDriver();
	}


	@Test(priority=1)
	public void editProfileCheck() {
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
		WebElement emailEdit = webDriver.findElement(By.name("email"));
		WebElement displayNameEdit = webDriver.findElement(By.name("displayName"));
		WebElement phoneNumberEdit = webDriver.findElement(By.name("phoneNumber"));
		emailEdit.clear();
		displayNameEdit.clear();
		phoneNumberEdit.clear();
		emailEdit.sendKeys("test@test.com");
		displayNameEdit.sendKeys("testName1234");
		phoneNumberEdit.sendKeys("99999998");
		webDriver.findElement(By.id("submit")).submit();
		Assert.assertEquals(webDriver.getTitle(), "Shop-Wijs");
	}

	@Test(priority=2)
	public void checkSuccessfulEdit() {
		WebElement emailDisplay = webDriver.findElement(By.id("emailDisplay"));
		WebElement displayNameDisplay = webDriver.findElement(By.id("displayNameDisplay"));
		WebElement phoneNumberDisplay = webDriver.findElement(By.id("phoneNumberDisplay"));
		Assert.assertEquals(emailDisplay.getText(), "test@test.com");
		Assert.assertEquals(displayNameDisplay.getText(), "testName1234");
		Assert.assertEquals(phoneNumberDisplay.getText(), "99999998");
	}

	@AfterTest
	public void afterTest() {
		webDriver.close();
	}

}
