package GridTest.GridTest;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GridTestCls {

	public static void main(String[] args) throws MalformedURLException {
		
		
		// TODO Auto-generated method stub

		//Desired Capabilities tanımlama
		//1.browser sectik
		DesiredCapabilities cap =new DesiredCapabilities();
		cap.setBrowserName("chrome");
		
		cap.setPlatform(Platform.WINDOWS);//benim platform bu hub serverdeki
		
		//chromeoption
		ChromeOptions chromeOption=new ChromeOptions();
		chromeOption.merge(cap);
		
		//3. ub adresini ver
		String hubUrl="http://192.168.0.17:4443/wd/hub";
		
		
		 WebDriver driver= new RemoteWebDriver(new URL(hubUrl),chromeOption);
		//WebDriver is an interface.It has lots of child class such ChromeDriver,RemoteWebDriver
		
		
		driver.get("https://www.google.com");
		System.out.println(driver.getTitle());
		driver.quit();
		
		//ya url silinmisse ya yanlıssa diye throws exception yaptık
		
		
		} 
		


	//endregion
		
	

}
