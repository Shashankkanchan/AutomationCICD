package rahulshettyacedemy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacedemy.Testcomponents.BaseTest;
import rahulshettyacedemy.pageobjects.CartPage;
import rahulshettyacedemy.pageobjects.CheckOutPage;
import rahulshettyacedemy.pageobjects.ConfirmationPage;
import rahulshettyacedemy.pageobjects.LandingPage;
import rahulshettyacedemy.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest {
	public LandingPage landingPage;
	ProductCatalogue productCatalogue;
	CheckOutPage checkOutPage;
	ConfirmationPage confirmationPage;
  @Given("I landed on a ecommmerce page")
  public void I_landed_on_a_ecommmerce_page() throws IOException {
	  landingPage =launchApplication();
  }
  @Given("^Logged in with username (.+) and password (.+)$")
  public void Logged_in_with_username_and_password(String username , String password) {
  productCatalogue=landingPage.loginApplication(username,password);
  
  }
  @When("^I add product (.+) to cart$")
  public void I_add_product_to_cart(String productName) throws InterruptedException {
	  List<WebElement> products =productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
  }
 
  @When("^checkout product (.+) and submit the order$")
  public void checkout_submit_order(String productName) {
	  CartPage cartPage=productCatalogue.goToCartPage();
		Boolean match=cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		// Create an instance of JavascriptExecutor// Scroll down by a specified number of pixels
      JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("window.scrollBy(0, 1000)");
       checkOutPage= cartPage.goToCheckout();
       checkOutPage.selectCountry("india");
       confirmationPage =checkOutPage.submitOrder();
  }
  
  @Then("I verify the {string} message is displayed on the confirmation.")
  public void message_displayed_confirmationPage(String string) {	
	  String confirmmsg=confirmationPage.getConfirmationMsg();
      Assert.assertTrue(confirmmsg.equalsIgnoreCase(string));
      driver.close();
      }
  @Then("{string} message is displayed")
  public void error_msg_is_displayed(String string) {
	  Assert.assertEquals(string,landingPage.getErrorMsg());
	  driver.close();
  }
  
}
