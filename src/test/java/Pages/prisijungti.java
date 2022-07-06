package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import Tests.TestParameters;

public class prisijungti extends AbstractPage {
	
	//http://localhost:8080/prisijungti
	//http://localhost:8080/prisijungti?error
	//http://localhost:8080/prisijungti?logout
	
	//lokatoriai
	
	public By inputUserName = By.xpath("//*[@name='username']");
	public By inputPassword = By.xpath("//*[@name='password']");
	
	public By buttonSubmit = By.xpath("//*[@type='submit']");
	
	public By textError = By.xpath("//*/span[2]");
	public By textSekmingaiAtsijungete = By.xpath("//*/span[1]");

	public By linkRegistruoti = By.xpath("//*/a");
	
	String prisijungimo_adresas = "http://localhost:8080/prisijungti";
	
	String prisijungimo_klaida = "Įvestas prisijungimo vardas ir/ arba slaptažodis yra neteisingi";
	String atsijungimo_pranesimas = "Sėkmingai atsijungėte";
	
	//2.konstruktorius
	public prisijungti(WebDriver driver){
		this.driver = driver;

		}


	public void atidarytiLanga() { //užkrauname prisijungimo puslapį

		try{driver.navigate().to(prisijungimo_adresas); 
		//report išvedimas į failą
		TestParameters.resultsToFile(TestParameters.report_i_faila,TestParameters.format,TestParameters.TimeStamp(),TestParameters.getCurrentMethodName(),"OK","vartotojas yra [Prisijungimo] lange ["+prisijungimo_adresas+"]");
		}catch(Exception e) {
		TestParameters.resultsToFile(TestParameters.report_i_faila,TestParameters.format,TestParameters.TimeStamp(),TestParameters.getCurrentMethodName(),"FALSE","vartotojui nepavyko atidaryti [Prisijungimo] lango!");
		TestParameters.testoRezultatas = new String[]{"FALSE","Scenarijaus pabaiga: TESTAS praėjo su klaidomis!!!",};
		}
		
		}	
	
	
	public void uzpildytiForma(String... args) { //Aktorius užpildo formos duomenis: Prisijungimo vardas, slaptažodis

		try {	
		driver.findElement(inputUserName).sendKeys(args[0]); //Prisijungimo vardas
		driver.findElement(inputPassword).sendKeys(args[1]); //slaptažodis

		System.out.println("Aktorius suveda reikšmes: "+"|"+args[0]+"|"+args[1]+"|"); //žinutė į konsolę

		//report į failą
		TestParameters.resultsToFile(TestParameters.report_i_faila,"%-19s %-40s %-6s %s %s \n%126s %s ", //teksto eilutės formatas
		TestParameters.TimeStamp(),TestParameters.getCurrentMethodName(),"OK","Aktorius užpildo prisijungimo formos duomenis: Prisijungimo vardas",
		"["+args[0]+"]","slaptažodis","["+args[1]+"]");


		}catch(Exception e) {
			TestParameters.resultsToFile(TestParameters.report_i_faila,TestParameters.format,TestParameters.TimeStamp(),TestParameters.getCurrentMethodName(),"FALSE","Aktoriui nepavyksta užpildyti formos duomenų!");
			TestParameters.testoRezultatas = new String[]{"FALSE","Scenarijaus pabaiga: TESTAS praėjo su klaidomis!!!",};
			}
		}

	public void paspausti_Prisijungti() { //Aktorius paspaudžia mygtuką [PRISIJUNGTI].

		try {TestParameters.wait.until(ExpectedConditions.visibilityOfElementLocated(buttonSubmit)); //laukiame, kol mygtukas bus rodomas	
			driver.findElement(buttonSubmit).click(); //paspaudžiamas mygtukas PRISIJUNGTI
			System.out.println("Aktorius paspaudžia mygtuką 'PRISIJUNGTI'"); //žinutė į konsolę
			TestParameters.resultsToFile(TestParameters.report_i_faila,TestParameters.format,TestParameters.TimeStamp(),TestParameters.getCurrentMethodName(),"OK","Aktorius paspaudžia mygtuką [PRISIJUNGTI].");
		}catch(Exception e) {
			TestParameters.resultsToFile(TestParameters.report_i_faila,TestParameters.format,TestParameters.TimeStamp(),TestParameters.getCurrentMethodName(),"FALSE","Aktoriui nepavyko paspausti mygtuko [PRISIJUNGTI] !");
			TestParameters.testoRezultatas = new String[]{"FALSE","Scenarijaus pabaiga: TESTAS praėjo su klaidomis!!!",};
			}
	}
	
	public void paspausti_Registruotis() { //Aktorius paspaudžia mygtuką [linkRegistruoti].

		try {TestParameters.wait.until(ExpectedConditions.visibilityOfElementLocated(linkRegistruoti)); //laukiame, kol nuoroda bus rodomas	
			driver.findElement(linkRegistruoti).click(); //paspaudžiamas nuorodą [Sukurti naują paskyrą]
			System.out.println("Aktorius paspaudžia nuorodą 'Sukurti naują paskyrą'"); //žinutė į konsolę
			TestParameters.resultsToFile(TestParameters.report_i_faila,TestParameters.format,TestParameters.TimeStamp(),TestParameters.getCurrentMethodName(),"OK","Aktorius paspaudžia nuorodą [Sukurti naują paskyrą].");
		}catch(Exception e) {
			TestParameters.resultsToFile(TestParameters.report_i_faila,TestParameters.format,TestParameters.TimeStamp(),TestParameters.getCurrentMethodName(),"FALSE","Aktoriui nepavyko paspausti nuorodos [Sukurti naują paskyrą] !");
			TestParameters.testoRezultatas = new String[]{"FALSE","Scenarijaus pabaiga: TESTAS praėjo su klaidomis!!!",};
			}
	}
	
	public void arRodoma_klaida(String text) { //tikrinam ar rodoma klaidos pavadinimas sutampa
		
		try{TestParameters.wait.until(ExpectedConditions.urlContains("?error")); //tikrinam url adresą
		TestParameters.wait.until(ExpectedConditions.visibilityOfElementLocated(textError)); 
		Assert.assertEquals(driver.findElement(textError).getText(),text);// Patikrinam ar tai numatyta klaida: "Įvestas prisijungimo vardas ir/ arba slaptažodis yra neteisingi"
		System.out.println("Rodoma klaida '"+text+ "' Prisijungimo lange: OK");
		TestParameters.resultsToFile(TestParameters.report_i_faila,TestParameters.format,TestParameters.TimeStamp(),TestParameters.getCurrentMethodName(),"OK","vartotojas mato klaidą '"+text+ "'");
		}catch(Error e) {System.out.println("Rodomos klaidos tekstas nesutampa: '"+driver.findElement(textError).getText()+"' vs "+text);
		TestParameters.resultsToFile(TestParameters.report_i_faila,TestParameters.format,TestParameters.TimeStamp(),TestParameters.getCurrentMethodName(),"FALSE","rodomos klaidos tekstas nesutampa: "+driver.findElement(textError).getText()+" vs "+text);
		TestParameters.testoRezultatas = new String[]{"FALSE","Scenarijaus pabaiga: TESTAS praėjo su klaidomis!!!",};
		}catch(Exception e) {/*System.out.println("pranešimo nėra:"); */}

	}
	
	public void sekmingai_atsijunga(String text) { //tikrinam ar rodoma klaidos pavadinimas sutampa
		
		try{TestParameters.wait.until(ExpectedConditions.urlContains("?logout")); //tikrinam url adresą
		TestParameters.wait.until(ExpectedConditions.visibilityOfElementLocated(textSekmingaiAtsijungete)); 
		Assert.assertEquals(driver.findElement(textSekmingaiAtsijungete).getText(),text);// Patikrinam ar tai numatyta klaida: "Įvestas prisijungimo vardas ir/ arba slaptažodis yra neteisingi"
		System.out.println("Rodomas atsijungimo pranešimas '"+text+ "' Prisijungimo lange: OK");
		TestParameters.resultsToFile(TestParameters.report_i_faila,TestParameters.format,TestParameters.TimeStamp(),TestParameters.getCurrentMethodName(),"OK","vartotojas mato atsijungimo pranešimą '"+text+ "'");
		}catch(Error e) {System.out.println("Rodomas atsijungimo tekstas nesutampa: '"+driver.findElement(textSekmingaiAtsijungete).getText()+"' vs "+text);
		TestParameters.resultsToFile(TestParameters.report_i_faila,TestParameters.format,TestParameters.TimeStamp(),TestParameters.getCurrentMethodName(),"FALSE","rodomas atsijungimo tekstas nesutampa: "+driver.findElement(textSekmingaiAtsijungete).getText()+" vs "+text);
		TestParameters.testoRezultatas = new String[]{"FALSE","Scenarijaus pabaiga: TESTAS praėjo su klaidomis!!!",};
		}catch(Exception e) {/*System.out.println("pranešimo nėra:"); */}

	}
}
