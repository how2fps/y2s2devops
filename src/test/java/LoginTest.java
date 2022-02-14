import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest {
	 private WebDriver webDriver;		
	  
	  @Test
	  public void checkTitle() {
		  //Load website as a new page
		  webDriver.navigate().to("http://localhost:8090/devopsproject/Login.jsp");
		  
		  //Assert the title to check that we are indeed in the correct website
		  Assert.assertEquals(webDriver.getTitle(), "Login");
		  
		  System.out.println("title: "+webDriver.getTitle());
		  Assert.assertTrue(webDriver.getTitle().contains("Login"));
		  
//		  //Retrieve link using it's class name and click on it
//		  webDriver.findElement(By.className("link")).click();
//
//		  //Assert the new title to check that the title contain Wikipedia and the button had successfully bring us to the new page
//		  Assert.assertTrue(webDriver.getTitle().contains("Wikipedia"));
//		  System.out.println("new title: "+webDriver.getTitle());
	  }
	  
	  @Test
	  public void fillLoginForm() {
		  webDriver.navigate().to("http://localhost:8090/devopsproject/Login.jsp");
		  WebElement email = webDriver.findElement(By.name("email"));
		  WebElement password = webDriver.findElement(By.name("password"));
		  email.sendKeys("hearthstonesigh@gmail.com");
		  password.sendKeys("password");
		  //add press button login
		  webDriver.findElement(By.id("submit")).submit();
		  Assert.assertEquals(webDriver.getTitle(), "Shop-Wijs");
	  }
	  
	  @BeforeTest
	  public void beforeTest() {
		  //Setting system properties of ChromeDriver
		  //to amend directory path base on your local file path
		  String chromeDriverDir = "C:\\Program Files\\Google\\Chrome\\chromedriver.exe";
		  System.setProperty("webdriver.chrome.driver", chromeDriverDir);
		  //initialize FirefoxDriver at the start of test
		  webDriver = new ChromeDriver();  
	  }

	  @AfterTest
	  public void afterTest() {
		  //Quit the ChromeDriver and close all associated window at the end of test
	  }
}
