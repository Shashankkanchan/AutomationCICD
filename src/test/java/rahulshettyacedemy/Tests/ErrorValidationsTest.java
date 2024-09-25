package rahulshettyacedemy.Tests;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import rahulshettyacedemy.Testcomponents.BaseTest;
import rahulshettyacedemy.pageobjects.CartPage;
import rahulshettyacedemy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {
	@Test(groups = {"ErrorHandling"},retryAnalyzer = rahulshettyacedemy.Testcomponents.Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException  {
		launchApplication();
        landingPage.loginApplication("seleniumpractice4m5@gmail.com",
				"Tester@3");

		landingPage.getErrorMsg();
		System.out.println(landingPage.getErrorMsg());
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMsg());

	}
	@Test(groups = {"ErrorHandling"})
	public void ProductErrorValidation() throws IOException, InterruptedException  {
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue=landingPage.loginApplication("seleniumpractice46@gmail.com",
				"Tester@123");
		List<WebElement> products =productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage=productCatalogue.goToCartPage();
		Boolean match=cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		tearDown();
	}
}
