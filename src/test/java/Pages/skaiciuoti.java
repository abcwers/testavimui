package Pages;

import org.openqa.selenium.By;

public class skaiciuoti extends AbstractPage {
	
	//http://localhost:8080/skaiciuoti
	
	//lokatoriai
	
	public By textResult = By.xpath("//*/h4"); 
	
	public By linkLogout = By.xpath("//a[contains(text(), 'Logout')]");
	public By linkSkaiciuotuvas = By.xpath("//a[contains(text(), 'Skaičiuotuvas')]");
	public By linkAtliktos_Operacijos = By.xpath("//a[contains(text(), 'Atliktos operacijos')]");
	
}
