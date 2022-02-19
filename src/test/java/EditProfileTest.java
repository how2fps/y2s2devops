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
	
	@Test
	public void checkEnterLoginPage() {
		webDriver.navigate().to("http://localhost:8090/devopsproject/Login.jsp");
		Assert.assertEquals(webDriver.getTitle(), "Login");
	}

	@Test
	public void editProfileCheck() {
		WebElement email = webDriver.findElement(By.name("email"));
		WebElement password = webDriver.findElement(By.name("password"));
		email.sendKeys("hearthstonesigh@gmail.com");
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
		emailEdit.sendKeys("hearthstonesigh@gmail.com");
		displayNameEdit.sendKeys("Finn Yap Jun Hun");
		phoneNumberEdit.sendKeys("97423488");
		webDriver.findElement(By.id("submit")).submit();
	}
	

	@AfterTest
	public void afterTest() {
	}

}
