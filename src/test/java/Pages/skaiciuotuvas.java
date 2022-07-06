package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.Objects;
import java.util.regex.*;

import Tests.TestParameters;

public class skaiciuotuvas extends AbstractPage {
	
	//http://localhost:8080/
	//http://localhost:8080/skaiciuotuvas
	
	//lokatoriai
	
	public By inputPirmas_Skaicius = By.xpath("//*[@id='sk1']");
	public By inputAntras_Skaicius = By.xpath("//*[@id='sk2']");
	public By selectOperacijos_Zenklas = By.xpath("//*[@name='zenklas']");
	public By optionOperacijos_Zenklas_Sudeti = By.xpath("//*[@value='+']");
	public By optionOperacijos_Zenklas_Atimti = By.xpath("//*[@value='-']");
	public By optionOperacijos_Zenklas_Dauginti = By.xpath("//*[@value='*']");
	public By optionOperacijos_Zenklas_Dalinti = By.xpath("//*[@value='/']");
	
	public By buttonSubmit = By.xpath("//*[@type='submit']");
	
	public By linkLogout = By.xpath("//a[contains(text(), 'Logout')]");
	public By linkSkaiciuotuvas = By.xpath("//a[contains(text(), 'Skaičiuotuvas')]");
	public By linkAtliktos_Operacijos = By.xpath("//a[contains(text(), 'Atliktos operacijos')]");
	
// //*/td[1][normalize-space(text())='5']/following-sibling::td[normalize-space(text())='*']/following-sibling::td[normalize-space(text())='4']/following-sibling::td[normalize-space(text())='20']/following-sibling::td/a[normalize-space(text())='Keisti']
// //*/td[1][normalize-space(text())='5']/following-sibling::td[normalize-space(text())='*']/following-sibling::td[normalize-space(text())='4']/following-sibling::td[normalize-space(text())='20']/following-sibling::td/a[normalize-space(text())='Rodyti']
// //*/td[1][normalize-space(text())='5']/following-sibling::td[normalize-space(text())='*']/following-sibling::td[normalize-space(text())='4']/following-sibling::td[normalize-space(text())='20']/following-sibling::td/a[normalize-space(text())='Trinti']	
	
	//2.konstruktorius
	public skaiciuotuvas(WebDriver driver){
		this.driver = driver;
		}
	
	public void arPrisijungta(String text) { //tikrinam vartotojo vardą
		
		try{TestParameters.wait.until(ExpectedConditions.urlContains("http://localhost:8080/")); //tikrinam url adresą
			TestParameters.wait.until(ExpectedConditions.visibilityOfElementLocated(linkLogout)); 
		
		Assert.assertEquals(driver.findElement(linkLogout).getText().replaceAll("Logout, ",""),text);// Patikrinam ar tai numatyta klaida: "Įvestas prisijungimo vardas ir/ arba slaptažodis yra neteisingi"
		System.out.println("Prisijungta su vartotoju '"+text+ "': OK");
		TestParameters.resultsToFile(TestParameters.report_i_faila,TestParameters.format,TestParameters.TimeStamp(),TestParameters.getCurrentMethodName(),"OK","vartotojas prisijungęs su vartotoju '"+text+ "'");
		}catch(Error e) {System.out.println("Vartotojai nesutampa: '"+driver.findElement(linkLogout).getText().replaceAll("Logout, ","")+"' vs "+text);
		TestParameters.resultsToFile(TestParameters.report_i_faila,TestParameters.format,TestParameters.TimeStamp(),TestParameters.getCurrentMethodName(),"FALSE","Vartotojai nesutampa: "+driver.findElement(linkLogout).getText().replaceAll("Logout, ","")+" vs "+text);
		TestParameters.testoRezultatas = new String[]{"FALSE","Scenarijaus pabaiga: TESTAS praėjo su klaidomis!!!",};
		}catch(Exception e) {/*System.out.println("pranešimo nėra:"); */}

	}
	
	public void arPrisijungta() { //tikrinam vartotojo vardą
		
		String URL = driver.getCurrentUrl(); // Esamas URL adresas
		String zinute = "Elementas [Logout] nerastas, aktorius yra lange ["+URL+"]";

		
		
		try{
			// tikrinama ar egzistuoja elementas [Logout]
			Boolean exist = driver.findElements(linkLogout).size() == 0;
			if(!exist)    {
				String text = driver.findElement(linkLogout).getText();	
				zinute = "Elementas ["+text+"] yra matomas, aktorius yra lange ["+URL+"]";
				System.out.println(zinute);
				}else {
				System.out.println(zinute);}

		TestParameters.resultsToFile(TestParameters.report_i_faila,TestParameters.format,TestParameters.TimeStamp(),TestParameters.getCurrentMethodName(),"OK",zinute);
		}catch(Error e) {System.out.println(zinute);
		TestParameters.resultsToFile(TestParameters.report_i_faila,TestParameters.format,TestParameters.TimeStamp(),TestParameters.getCurrentMethodName(),"FALSE",zinute);
		TestParameters.testoRezultatas = new String[]{"FALSE","Scenarijaus pabaiga: TESTAS praėjo su klaidomis!!!",};
		}catch(Exception e) {/*System.out.println("pranešimo nėra:"); */}

	}
	
	public void atsijungti() { //atsijungiam nuo sistemos
		//kintamieji
		String username = null;
		String URL = null;
		try{
			TestParameters.wait.until(ExpectedConditions.visibilityOfElementLocated(linkLogout)); // ieškome [Logout] nuorodos
		
			username = driver.findElement(linkLogout).getText().replaceAll("Logout, ","");// Iš [Logout] nuorodos ištraukiam vartotojo vardą
		
			driver.findElement(linkLogout).click(); //paspaudžiamas mygtukas [Logout]
		
			TestParameters.wait.until(ExpectedConditions.urlContains("?logout")); //tikrinam url adresą
		
			URL = driver.getCurrentUrl(); // Esamas URL adresas
		
		System.out.println("Aktorius suranda nuorodą [Logout, " + username + "] ir paspaudžia: OK");
		
		System.out.println("Aktorius yra prisijungimo lange ["+URL+"]: OK");
		
		TestParameters.resultsToFile(TestParameters.report_i_faila,TestParameters.format,TestParameters.TimeStamp(),TestParameters.getCurrentMethodName(),"OK","vartotojas '"+username+ "' atsijungė ir yra lange ["+URL+"]");
		}catch(Error e) {System.out.println("Vartotojui '"+username+ "' nepavyko atsijungti");
		TestParameters.resultsToFile(TestParameters.report_i_faila,TestParameters.format,TestParameters.TimeStamp(),TestParameters.getCurrentMethodName(),"FALSE","Vartotojui '"+username+ "' nepavyko atsijungti");
		TestParameters.testoRezultatas = new String[]{"FALSE","Scenarijaus pabaiga: TESTAS praėjo su klaidomis!!!",};
		}catch(Exception e) {/*System.out.println("pranešimo nėra:"); */}

	}
	
	public void koks_langas() { //atsijungiam nuo sistemos
		//kintamieji
		String URL = driver.getCurrentUrl().toString(); // Esamas URL adresas
		String zinute = "klaida: rodomas nežinomas langas ["+URL+"]";
		
		try{//Thread.sleep(1000);
			
		URL = driver.getCurrentUrl();
		
			   if (URL.contains("/prisijungti")) {

				   zinute = "Aktorius yra [Prisijungimo] lange ["+URL+"]";
				   	   			        
			   } else if (URL.contains("/prisijungti?logout")) {

				   zinute = "Aktorius yra [Prisijungimo] lange [atsijungus] ["+URL+"]";
			     
			   
			   } else if (URL.contains("/prisijungti?error")) {
			       
				   zinute = "Aktorius yra [Prisijungimo] lange [su įvedimo klaidomis] ["+URL+"]";
				   
			   } else if (Objects.equals(URL, "http://localhost:8080/")) { //https://stackoverflow.com/questions/513832/how-do-i-compare-strings-in-java
				   
			   zinute = "Aktorius yra [SKAIČIUOTUVAS] lange ["+URL+"]";

			   } else if (URL.contains("/registruoti")) {	
				   
				   zinute = "Aktorius yra [REGISTRACIJOS] lange ["+URL+"]";
			            	
			   } else if (URL.contains("/skaiciuotuvas")) {	
				   
				   zinute = "Aktorius yra [SKAIČIUOTUVAS] lange ["+URL+"]";
			    	
			   } else if (URL.contains("/skaiciai")) {	    

				   zinute = "Aktorius yra [DUOMENŲ] sarašo lange ["+URL+"]";
			    	
			   } else if (URL.contains("/skaiciuoti")) {	    
		
				   zinute = "Aktorius yra [įvedimo Rezultato] lange ["+URL+"]"; 
			    	
			   } else if (URL.contains("/atnaujinti?")) {
				   
				   zinute = "Aktorius yra [operacijos redagavimo] lange ["+URL+"]";
				       
			   } else if (URL.contains("/rodyti?")) { 
				   
				   zinute = "Aktorius yra [operacijos rodymo] lange ["+URL+"]";
				   
			   } else if (URL.contains("/trinti?")) {  
			        
				   zinute = "Aktorius yra [operacijos trynimo] lange ["+URL+"]";
			    
			   } else {  

			        //nežinomam langui užfiksuoti
				   zinute = "klaida: rodomas nežinomas langas ["+URL+"]";
			    }
		
		
		System.out.println(zinute);
		
		TestParameters.resultsToFile(TestParameters.report_i_faila,TestParameters.format,TestParameters.TimeStamp(),TestParameters.getCurrentMethodName(),"OK",zinute);
		}catch(Error e) {System.out.println("Klaida - nepavyksta nustatyti kuriame lange esame!"+e);
		TestParameters.resultsToFile(TestParameters.report_i_faila,TestParameters.format,TestParameters.TimeStamp(),TestParameters.getCurrentMethodName(),"FALSE",zinute);
		TestParameters.testoRezultatas = new String[]{"FALSE","Scenarijaus pabaiga: TESTAS praėjo su klaidomis!!!",};
		}catch(Exception e) {/*System.out.println("pranešimo nėra:"); */}

	}
}
