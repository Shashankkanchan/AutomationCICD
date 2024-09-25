package rahulshettyacedemy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacedemy.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

    WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);

	}

	//driver.findElement(By.id("userEmail")).sendKeys("seleniumpractice45@gmail.com");
	//Page Factory
	@FindBy(id = "userEmail")
	WebElement userEmail;
	//driver.findElement(By.id("userPassword")).sendKeys("Tester@123");
	@FindBy(id = "userPassword")
	WebElement passwordEle;
	//driver.findElement(By.id("login")).click();
	@FindBy(id = "login")
	WebElement submit ;

	//.ng-tns-c4-3.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMsg ;

	public String getErrorMsg() {
		waitForWebElementToAppear(errorMsg);
		return errorMsg.getText();
	}

	public ProductCatalogue loginApplication(String email , String password) {
	//	waitForWebElementToAppear(userEmail);

		userEmail.clear();
		userEmail.sendKeys(email);
		passwordEle.clear();
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue =new ProductCatalogue(driver);
		return productCatalogue;

	}
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");

	}
}
