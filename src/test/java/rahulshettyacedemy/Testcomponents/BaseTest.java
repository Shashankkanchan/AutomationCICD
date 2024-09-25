package rahulshettyacedemy.Testcomponents;


import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacedemy.pageobjects.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;
     public WebDriver intializeDriver() throws IOException {
    	 Properties prop = new Properties();
    	 FileInputStream fis=new FileInputStream(System.getProperty("user.dir")
    			 +"\\src\\main\\java\\rahulshettyacedemy\\resources\\GlobalData.properties");
    	 prop.load(fis);
    	 String browserName= System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
    	//String browserName=prop.getProperty("browser");

    	if(browserName.contains("chrome")) {
    		ChromeOptions options = new ChromeOptions();
    	 WebDriverManager.chromedriver().setup();
    	 if(browserName.contains("headless")) {
    	 options.addArguments("headless");
    	 }
 		 driver = new ChromeDriver();
 		 driver.manage().window().setSize(new Dimension(1440,900));
    	 }
    	 else if (browserName.equalsIgnoreCase("firefox")) {
    		 System.setProperty("webdriver.gecko.driver", "Users\\Lenovo\\Documents\\Firefox\\geckodriver-v0.35.0-win64");
    		 driver = new FirefoxDriver();
    	 }

    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
 		driver.manage().window().maximize();
 		return driver;
     }
     public List<HashMap<String, String>> getJasonDataToMap(String filePath) throws IOException {
    	 //read json to string
    	 String jsonContent = FileUtils.readFileToString(new File(filePath),
    			 StandardCharsets.UTF_8);
         //String to hashmap -> jackson databind dependency -- it will convert string content into hashmap
    	 ObjectMapper mapper = new ObjectMapper();
    	 List<HashMap<String, String>>  data= mapper.readValue(jsonContent,new TypeReference<List<HashMap<String ,String >>>(){
    		 
    	 });
		return data;

     }

     public String getScreenShot(String testCaseName,WebDriver driver) throws IOException {
    	 TakesScreenshot ts =(TakesScreenshot)driver;
    	 File source=ts.getScreenshotAs(OutputType.FILE);
    	 File file= new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
    	 FileUtils.copyFile(source, file);
    	 return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";

     }
     @BeforeTest
     public LandingPage launchApplication() throws IOException {
    	 driver=intializeDriver();
    	 landingPage = new LandingPage(driver);
 		 landingPage.goTo();
 		 return landingPage;
     }
     @AfterTest
     public void tearDown() {
    	 driver.close();
     }
}
