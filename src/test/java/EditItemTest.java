import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class EditItemTest {
	// declare Selenium WebDriver
	private WebDriver webDriver;

	@Test
	public void editItem() {

		// we need to firstly login.
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
		// Locate the "See Details" button that is in the same as the newly added item
		// from AddItemTest
		Assert.assertTrue(!webDriver.findElements(By.id("KELL KeyboardseeDetailsBtn")).isEmpty(),
				"Newly added item is not found!");

		// ----- Need to add sleep timer here as data is loading

		WebElement seeDetailsBtn = webDriver.findElement(By.id("KELL KeyboardseeDetailsBtn"));
		seeDetailsBtn.click();

		// Check if we are in the itemview page.
		Assert.assertEquals(webDriver.getTitle(), "Item View");

		// Click on the edit item button
		WebElement editItemDetailsBtn = webDriver.findElement(By.id("editItemDetailsBtn"));
		editItemDetailsBtn.click();

		// Check if we are in the edit item details page.
		Assert.assertEquals(webDriver.getTitle(), "Edit Item Details");

		// Fill the edit Items form.
		WebElement itemName = webDriver.findElement(By.name("itemName"));
		WebElement itemImage = webDriver.findElement(By.name("itemImage"));
		WebElement itemDescription = webDriver.findElement(By.name("itemDescription"));
		WebElement itemPricing = webDriver.findElement(By.name("itemPricing"));
		WebElement itemQuantity = webDriver.findElement(By.name("itemQuantity"));
		WebElement editItemButton = webDriver.findElement(By.className("edit-item-btn"));

		// Adds things in the form.
		// we are using very unrealistic changed text to make sure that we will not
		// potentially have other items with either same name, quantity and pricing.
		itemName.clear();
		itemImage.clear();
		itemDescription.clear();
		itemPricing.clear();
		itemQuantity.clear();
		itemName.sendKeys("Changed");
		itemImage.sendKeys("C:\\painting.jpg");
		itemDescription.sendKeys("Changed Description");
		itemPricing.sendKeys("9999.99");
		itemQuantity.sendKeys("9999");
		editItemButton.click();

		// Check if we are in the corrected redirect page.
		Assert.assertEquals(webDriver.getTitle(), "Items Listed");

		// Clicks on the see details button
		WebElement secondSeeDetailsBtn = webDriver.findElement(By.id("ChangedseeDetailsBtn"));
		secondSeeDetailsBtn.click();

		// Check if the changed itemDetails is reflected.
		Assert.assertTrue(webDriver.getPageSource().contains("Changed"), "Item Name was not changed!");

		// Depending on where you run this test, the image path will be different:
		// If ran locally, then the image will be saved on the ecpilse workspace, and
		// the code is below:
		// WebElement newItemImage = webDriver.findElement(By.id(
		// "C:\\Users\\User\\eclipse-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp4\\wtpwebapps\\y2s2devops\\image_upload\\painting.jpg"));

		// If ran using Jenkins, then the image will be saved on the Tomcat 9.0
		// server,and
		// the code is below:
		WebElement newItemImage = webDriver.findElement(By.id(
				"C:\\Users\\User\\Downloads\\apache-tomcat-9.0.58\\apache-tomcat-9.0.58\\webapps\\devopsproject\\\\image_upload\\painting.png"));
		Assert.assertTrue(webDriver.getPageSource().contains("Changed Description"),
				"Item Description was not changed!");
		Assert.assertTrue(webDriver.getPageSource().contains("9999.99"), "Item Pricing was not changed!");
		Assert.assertTrue(webDriver.getPageSource().contains("9999"), "Item Quantity was not changed!");

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
