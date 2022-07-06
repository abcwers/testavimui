package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AbstractPage {

	protected static WebDriver driver;
	public void AbstractPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver,this);
		}
}
