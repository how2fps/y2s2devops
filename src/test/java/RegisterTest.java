import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RegisterTest {

	private WebDriver webDriver;

	@Test
	public void checkTitle() {
		webDriver.navigate().to("http://localhost:8090/devopsproject/SignUp.jsp");
		System.out.println("title: " + webDriver.getTitle());
		Assert.assertEquals(webDriver.getTitle(), "Sign Up");
	}

	@Test(priority = 1)
	public void checkPasswordsDoNotMatch() {
		webDriver.navigate().to("http://localhost:8090/devopsproject/SignUp.jsp");
		WebElement email = webDriver.findElement(By.name("email"));
		WebElement password = webDriver.findElement(By.name("password"));
		WebElement confirmPassword = webDriver.findElement(By.name("confirmPassword"));
		WebElement displayName = webDriver.findElement(By.name("displayName"));
		WebElement phoneNumber = webDriver.findElement(By.name("phoneNumber"));
		email.sendKeys("test@test.com");
		password.sendKeys("password");
		confirmPassword.sendKeys("password2");
		displayName.sendKeys("testName123");
		phoneNumber.sendKeys("99999999");
		webDriver.findElement(By.id("submit")).submit();
		Assert.assertEquals(webDriver.findElement(By.id("alert")).getText(), "Passwords are not the same!");
	}

	@Test(priority = 2)
	public void checkSuccessfulSignUp() {
		webDriver.navigate().to("http://localhost:8090/devopsproject/SignUp.jsp");
		WebElement email = webDriver.findElement(By.name("email"));
		WebElement password = webDriver.findElement(By.name("password"));
		WebElement confirmPassword = webDriver.findElement(By.name("confirmPassword"));
		WebElement displayName = webDriver.findElement(By.name("displayName"));
		WebElement phoneNumber = webDriver.findElement(By.name("phoneNumber"));
		email.sendKeys("test@test.com");
		password.sendKeys("password");
		confirmPassword.sendKeys("password");
		displayName.sendKeys("testName123");
		phoneNumber.sendKeys("99999999");
		webDriver.findElement(By.id("submit")).submit();
		Assert.assertEquals(webDriver.findElement(By.id("alert")).getText(), "Registration Successful!");
	}

	@Test(priority = 3)
	public void checkEmailAlreadyExists() {
		webDriver.navigate().to("http://localhost:8090/devopsproject/SignUp.jsp");
		WebElement email = webDriver.findElement(By.name("email"));
		WebElement password = webDriver.findElement(By.name("password"));
		WebElement confirmPassword = webDriver.findElement(By.name("confirmPassword"));
		WebElement displayName = webDriver.findElement(By.name("displayName"));
		WebElement phoneNumber = webDriver.findElement(By.name("phoneNumber"));
		email.sendKeys("test@test.com");
		password.sendKeys("password");
		confirmPassword.sendKeys("password");
		displayName.sendKeys("testName1234");
		phoneNumber.sendKeys("99999999");
		webDriver.findElement(By.id("submit")).submit();
		Assert.assertEquals(webDriver.findElement(By.id("alert")).getText(),
				"The email test@test.com is already in use!");
	}

	@Test(priority = 4)
	public void checkDisplayNameAlreadyExists() {
		webDriver.navigate().to("http://localhost:8090/devopsproject/SignUp.jsp");
		WebElement email = webDriver.findElement(By.name("email"));
		WebElement password = webDriver.findElement(By.name("password"));
		WebElement confirmPassword = webDriver.findElement(By.name("confirmPassword"));
		WebElement displayName = webDriver.findElement(By.name("displayName"));
		WebElement phoneNumber = webDriver.findElement(By.name("phoneNumber"));
		email.sendKeys("test2@test.com");
		password.sendKeys("password");
		confirmPassword.sendKeys("password");
		displayName.sendKeys("testName123");
		phoneNumber.sendKeys("99999999");
		webDriver.findElement(By.id("submit")).submit();
		Assert.assertEquals(webDriver.findElement(By.id("alert")).getText(),
				"The display name testName123 is already in use!");
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
