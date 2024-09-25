package rahulshettyacedemy.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacedemy.Testcomponents.BaseTest;
import rahulshettyacedemy.pageobjects.CartPage;
import rahulshettyacedemy.pageobjects.CheckOutPage;
import rahulshettyacedemy.pageobjects.ConfirmationPage;
import rahulshettyacedemy.pageobjects.OrdersPage;
import rahulshettyacedemy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	 String productName = "ZARA COAT 3";

	 @Test(dataProvider ="getData",groups={"purchase"},priority=1)
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException  {
	    launchApplication();
		ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products =productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage=productCatalogue.goToCartPage();
		Boolean match=cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		// Create an instance of JavascriptExecutor// Scroll down by a specified number of pixels
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 1000)");
        CheckOutPage checkOutPage= cartPage.goToCheckout();
        checkOutPage.selectCountry("india");
        ConfirmationPage confirmationPage =checkOutPage.submitOrder();
        String confirmmsg=confirmationPage.getConfirmationMsg();
        Assert.assertTrue(confirmmsg.equalsIgnoreCase("Thankyou for the order."));
       tearDown();

	}
   // to verify zara coat 3 is displaying in orders page
	@Test(dependsOnMethods= {"submitOrder"},priority=2)
	public void OrderHistoryTest() throws IOException {
		launchApplication();
		ProductCatalogue productCatalogue=landingPage.loginApplication("seleniumpractice45@gmail.com",
				"Tester@123");
		OrdersPage ordersPage=productCatalogue.goToOredrsPage();
		Assert.assertTrue(ordersPage.verifyOrdersDisplay(productName));
		 tearDown();

	}
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String,String> >data=getJasonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacedemy\\data\\PurchaseOrder.json");
	 return	new Object[][] {{data.get(0)},{data.get(1)}};

	}

}
