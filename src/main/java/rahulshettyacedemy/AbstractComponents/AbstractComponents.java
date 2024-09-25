package rahulshettyacedemy.AbstractComponents;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacedemy.pageobjects.CartPage;
import rahulshettyacedemy.pageobjects.OrdersPage;

public class AbstractComponents {
	WebDriver driver ;
	public AbstractComponents(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	By cartHeader=By.cssSelector("[routerlink*='cart']");

	@FindBy (css="[routerlink*='myorders']" )
	WebElement orderHeader;

	public void waitForElementToAppear(By FindBy) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));

    }
	public void waitForWebElementToAppear(WebElement FindBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(FindBy));

	    }
	public void waitForWebElementsToAppear(List<WebElement> FindBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElements(FindBy));

	    }
	public CartPage goToCartPage() {
		driver.findElement(cartHeader).click();
		CartPage cartPage =new CartPage(driver);
		return cartPage;
		}
	public  OrdersPage goToOredrsPage() {
        orderHeader.click();
		OrdersPage ordersPage =new OrdersPage(driver);
		return ordersPage;
		}
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException {
		Thread.sleep(1000);
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.invisibilityOf(ele));
		}

}
