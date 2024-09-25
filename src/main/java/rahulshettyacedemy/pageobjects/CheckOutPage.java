package rahulshettyacedemy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacedemy.AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents {
    WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	 // driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("india");
	@FindBy(css="input[placeholder='Select Country']")
	WebElement country;
	// driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
	@FindBy(css=".ta-item:nth-of-type(2)")
	WebElement selectCountry;
    //driver.findElement(By.cssSelector(".action__submit")).click();
	@FindBy(css=".action__submit")
	WebElement submit;
	By results=By.cssSelector(".ta-results");


   //  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
    // driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
    // driver.findElement(By.cssSelector(".action__submit")).click();
     public void selectCountry(String countryName) {
     country.sendKeys(countryName);
     waitForElementToAppear(results);
     selectCountry.click();
     }


     public ConfirmationPage submitOrder() {
    	 submit.click();
    	 return new ConfirmationPage(driver);
     }

}
