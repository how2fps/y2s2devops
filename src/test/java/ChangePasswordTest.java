import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class ChangePasswordTest {
	private WebDriver webDriver;

	@BeforeTest
	public void beforeTest() {
		String chromeDriverDir = "C:\\Program Files\\Google\\Chrome\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromeDriverDir);
		webDriver = new ChromeDriver();
	}

	@Test(priority=1)
	public void login() {
		webDriver.navigate().to("http://localhost:8090/devopsproject/Login.jsp");
		WebElement email = webDriver.findElement(By.name("email"));
		WebElement password = webDriver.findElement(By.name("password"));
		email.sendKeys("test@test.com");
		password.sendKeys("password");
		webDriver.findElement(By.id("submit")).submit();
		Assert.assertEquals(webDriver.getTitle(), "Shop-Wijs");
	}
	
	@Test(priority=2)
	public void checkChangePasswordPage() {
		webDriver.findElement(By.id("edit-profile")).click();
		webDriver.findElement(By.id("changePasswordButton")).click();
		Assert.assertEquals(webDriver.getTitle(), "Change Password");
	}


	@Test(priority=3)
	public void checkPasswordsDoNotMatch() {
		webDriver.navigate().to("http://localhost:8090/devopsproject/ChangePasswordServlet");
		WebElement oldPassword = webDriver.findElement(By.name("oldPassword"));
		WebElement newPassword = webDriver.findElement(By.name("newPassword"));
		WebElement confirmNewPassword = webDriver.findElement(By.name("confirmNewPassword"));
		oldPassword.sendKeys("password");
		newPassword.sendKeys("password123");
		confirmNewPassword.sendKeys("password1234");
		webDriver.findElement(By.id("submit")).submit();
		Assert.assertEquals(webDriver.findElement(By.id("alert")).getText(), "New password and confirm new password is not the same!");
	}
	
	@Test(priority=4)
	public void checkOldPasswordIsWrong() {
		WebElement oldPassword = webDriver.findElement(By.name("oldPassword"));
		WebElement newPassword = webDriver.findElement(By.name("newPassword"));
		WebElement confirmNewPassword = webDriver.findElement(By.name("confirmNewPassword"));
		oldPassword.sendKeys("password1");
		newPassword.sendKeys("password123");
		confirmNewPassword.sendKeys("password123");
		webDriver.findElement(By.id("submit")).submit();
		Assert.assertEquals(webDriver.findElement(By.id("alert")).getText(), "Old password is wrong!");
	}

	@Test(priority=5)
	public void checkSuccessfulPasswordChange() {
		WebElement oldPassword = webDriver.findElement(By.name("oldPassword"));
		WebElement newPassword = webDriver.findElement(By.name("newPassword"));
		WebElement confirmNewPassword = webDriver.findElement(By.name("confirmNewPassword"));
		oldPassword.sendKeys("password");
		newPassword.sendKeys("password123");
		confirmNewPassword.sendKeys("password123");
		webDriver.findElement(By.id("submit")).submit();
		Assert.assertEquals(webDriver.findElement(By.id("alert")).getText(), "Password successfully changed!");
	}
	
	@Test(priority = 6)
	public void checkChangeBackToPreviousPassword() {
		WebElement oldPassword = webDriver.findElement(By.name("oldPassword"));
		WebElement newPassword = webDriver.findElement(By.name("newPassword"));
		WebElement confirmNewPassword = webDriver.findElement(By.name("confirmNewPassword"));
		oldPassword.sendKeys("password123");
		newPassword.sendKeys("password");
		confirmNewPassword.sendKeys("password");
		webDriver.findElement(By.id("submit")).submit();
		Assert.assertEquals(webDriver.findElement(By.id("alert")).getText(), "Password successfully changed!");
	}

	@AfterTest
	public void afterTest() {
		webDriver.close();
	}
}
