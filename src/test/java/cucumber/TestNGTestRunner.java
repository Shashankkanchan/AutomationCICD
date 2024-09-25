package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

public class TestNGTestRunner {
   @CucumberOptions(features="src/test/java/cucumber",glue="rahulshettyacedemy.stepDefinitions",
		   monochrome=true,tags= " @ErrorValidation" ,plugin= {"html:target/cucumber.html"})
   public class TestNGRunner extends AbstractTestNGCucumberTests {
	   
   }
}
