package rahulshettyacedemy.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacedemy.pageobjects.LandingPage;
//cicd integration comment
public class standAloneTest {

	public static void main(String[] args)  {
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");

		LandingPage landingPage = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("seleniumpractice45@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Tester@123");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		List<WebElement> products =driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//ng-animation
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match=cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		 // Create an instance of JavascriptExecutor// Scroll down by a specified number of pixels
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 1000)");
        // Find the element you want to click// Use JavaScriptExecutor to click on the element
        WebElement element = driver.findElement(By.cssSelector(".totalRow button"));
         js.executeScript("arguments[0].click();", element);
       driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("india");
         wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
         driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
         driver.findElement(By.cssSelector(".action__submit")).click();
         String confirmmsg =driver.findElement(By.cssSelector(".hero-primary")).getText();
         Assert.assertTrue(confirmmsg.equalsIgnoreCase("Thankyou for the order."));
         driver.close();


	}

}
