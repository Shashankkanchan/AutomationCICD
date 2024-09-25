package rahulshettyacedemy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacedemy.AbstractComponents.AbstractComponents;



public class CartPage extends AbstractComponents  {
    WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css= ".cartSection h3")
	List<WebElement> cartProducts;

	By checkoutEle = By.cssSelector(".totalRow button");


	public Boolean verifyProductDisplay(String productName) {
		waitForWebElementsToAppear(cartProducts);
		Boolean match=cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	public CheckOutPage goToCheckout() {
		//driver.findElement(checkoutEle).click();
		 WebElement element = driver.findElement(checkoutEle);
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
		return new CheckOutPage(driver);
	}





}
