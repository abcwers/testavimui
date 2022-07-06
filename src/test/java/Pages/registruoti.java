package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import Tests.TestParameters;

public class registruoti extends AbstractPage {
	
	//http://localhost:8080/registruoti
	
	//lokatoriai
	
	public By inputUserName = By.xpath("//*[@name='username']");
	public By inputPassword = By.xpath("//*[@name='password']");
	public By inputPasswordConfirm = By.xpath("//*[@name='passwordConfirm']");
	
	public By buttonSubmit = By.xpath("//*[@type='submit']");
	
	public By textNameError = By.xpath("//*[@id='username.errors']"); 
	public By textPasswordError = By.xpath("//*[@id='password.errors']"); 
	public By textPasswordConfirmError = By.xpath("//*[@id='passwordConfirm.errors']");
	
	String registracijos_adresas = "http://localhost:8080/registruoti";
	
	//2.konstruktorius
	public registruoti(WebDriver driver){
		this.driver = driver;
		}
	
	public void atidaryti_Langa() { //užkrauname registracijos puslapį

		try{driver.navigate().to(registracijos_adresas); 
		//report išvedimas į failą
		TestParameters.resultsToFile(TestParameters.report_i_faila,TestParameters.format,TestParameters.TimeStamp(),TestParameters.getCurrentMethodName(),"OK","vartotojas yra [Registracijos] lange ["+registracijos_adresas+"]");
		}catch(Exception e) {
		TestParameters.resultsToFile(TestParameters.report_i_faila,TestParameters.format,TestParameters.TimeStamp(),TestParameters.getCurrentMethodName(),"FALSE","vartotojui nepavyko atidaryti [Registracijos] lango!");
		TestParameters.testoRezultatas = new String[]{"FALSE","Scenarijaus pabaiga: TESTAS praėjo su klaidomis!!!",};
		}
		
		}	
	
	public void uzpildyti_forma(String... args) { //Aktorius užpildo formos duomenis: Prisijungimo vardas, Slaptažodis, Slaptažodžio patvirtinimas

		try {	
		driver.findElement(inputUserName).sendKeys(args[0]);
		driver.findElement(inputPassword).sendKeys(args[1]);
		driver.findElement(inputPasswordConfirm).sendKeys(args[2]);

		System.out.println("Aktorius suveda reikšmes: "+"|"+args[0]+"|"+args[1]+"|"+args[2]+"|"); //žinutė į konsolę

		//report į failą
		TestParameters.resultsToFile(TestParameters.report_i_faila,"%-19s %-40s %-6s %s \n%126s %s \n%126s %s \n%126s %s ", //teksto eilutės formatas
		TestParameters.TimeStamp(),TestParameters.getCurrentMethodName(),"OK","Aktorius užpildo registracijos formos duomenis:", "Prisijungimo vardas",
		"["+args[0]+"]","Slaptažodis","["+args[1]+"]","Slaptažodžio patvirtinimas","["+args[2]+"]");


		}catch(Exception e) {
			TestParameters.resultsToFile(TestParameters.report_i_faila,TestParameters.format,TestParameters.TimeStamp(),TestParameters.getCurrentMethodName(),"FALSE","Aktoriui nepavyksta užpildyti formos duomenų!");
			TestParameters.testoRezultatas = new String[]{"FALSE","Scenarijaus pabaiga: TESTAS praėjo su klaidomis!!!",};
			}
		}
	
	public void paspausti_Sukurti() { //Aktorius paspaudžia mygtuką [SUKURTI].

		try {TestParameters.wait.until(ExpectedConditions.visibilityOfElementLocated(buttonSubmit)); //laukiame, kol mygtukas bus rodomas	
	driver.findElement(buttonSubmit).click(); //paspaudžiamas mygtukas SUKURTI
	System.out.println("Aktorius paspaudžia mygtuką 'SUKURTI'"); //žinutė į konsolę
	TestParameters.resultsToFile(TestParameters.report_i_faila,TestParameters.format,TestParameters.TimeStamp(),TestParameters.getCurrentMethodName(),"OK","Aktorius paspaudžia mygtuką [SUKURTI].");
		}catch(Exception e) {
			TestParameters.resultsToFile(TestParameters.report_i_faila,TestParameters.format,TestParameters.TimeStamp(),TestParameters.getCurrentMethodName(),"FALSE","Aktoriui nepavyko paspausti mygtuko [SUKURTI] !");
			TestParameters.testoRezultatas = new String[]{"FALSE","Scenarijaus pabaiga: TESTAS praėjo su klaidomis!!!",};
			}
	}
	
	public void BrowserAtgal() { //paspaudžiame ATGAL mygtuką

		try{driver.navigate().back(); //paspausti naršyklėj mygtuką ATGAL
		String URL = driver.getCurrentUrl();
		System.out.println("vartotojas paspaudžia Naršyklės mygtuką [ATGAL] ir yra lange ["+URL+"]"); //žinutė į konsolę
		//report išvedimas į failą
		TestParameters.resultsToFile(TestParameters.report_i_faila,TestParameters.format,TestParameters.TimeStamp(),TestParameters.getCurrentMethodName(),"OK","vartotojas paspaudžia Naršyklės mygtuką [ATGAL] ir yra lange ["+URL+"]");
		}catch(Exception e) {
		TestParameters.resultsToFile(TestParameters.report_i_faila,TestParameters.format,TestParameters.TimeStamp(),TestParameters.getCurrentMethodName(),"FALSE","vartotojui nepavyko paspausti naršyklės mygtuko [ATGAL]!");
		TestParameters.testoRezultatas = new String[]{"FALSE","Scenarijaus pabaiga: TESTAS praėjo su klaidomis!!!",};
		}
		
		}
	
	public void klaida_Username() { //tikrinam ar rodoma klaida po [Prisijungimo vardas] laukeliu

		String zinute = "Aktorius nemato [Prisijungimo vardas] klaidos";
		
		// galimos [Prisijungimo vardas] klaidos
		String tekstas1 = "Šį laukelį būtina užpildyti<br>Privaloma įvesti nuo 3 iki 32 simbolių";
		String tekstas2 = "Privaloma įvesti nuo 3 iki 32 simbolių";
		String tekstas3 = "Šį laukelį būtina užpildyti";
		String tekstas4 = "Toks vartotojo vardas jau egzistuoja";
		
		try {
		
		Boolean exist = driver.findElements(textNameError).size() == 0; //tikriname ar klaidos laukelis egzistuoja
		if(!exist)    {
			String klaida = driver.findElement(textNameError).getAttribute("innerHTML").toString();	// [Prisijungimo vardas] klaidos laukelio reikšmės gavimas
			zinute = "Aktorius mato [Prisijungimo vardas] klaidą '"+klaida+"'";
			System.out.println(zinute); //žinutė į konsolę
			
			boolean isEqual = klaida.equals(tekstas1) || klaida.equals(tekstas2)|| klaida.equals(tekstas3)|| klaida.equals(tekstas4);
			Assert.assertTrue(isEqual, "The text " + klaida + " is not " + tekstas1 + " or " + tekstas2+ " or " + tekstas3+ " or " + tekstas4); //klaidos teksto atitikimo tikrinimas
			
			}else {
			System.out.println(zinute);}
		

	TestParameters.resultsToFile(TestParameters.report_i_faila,TestParameters.format,TestParameters.TimeStamp(),TestParameters.getCurrentMethodName(),"OK",zinute);
		}catch(Exception e) {
			TestParameters.resultsToFile(TestParameters.report_i_faila,TestParameters.format,TestParameters.TimeStamp(),TestParameters.getCurrentMethodName(),"FALSE","kažkas nepavyko - [Username] klaida !" +e);
			TestParameters.testoRezultatas = new String[]{"FALSE","Scenarijaus pabaiga: TESTAS praėjo su klaidomis!!!",};
			}
	}
	
	public void klaida_Password() { //tikrinam ar rodoma klaida po [slaptažodis] laukeliu

		String zinute = "Aktorius nemato [slaptažodis] klaidos";
		
		// galimos [slaptažodis] klaidos
		String tekstas1 = "Šį laukelį būtina užpildyti<br>Privaloma įvesti bent 3 simbolius";
		String tekstas2 = "Privaloma įvesti bent 3 simbolius";
		String tekstas3 = "Šį laukelį būtina užpildyti";
		
		try {
			
			Boolean exist = driver.findElements(textPasswordError).size() == 0; //tikriname ar klaidos laukelis egzistuoja
			if(!exist)    {
				String klaida = driver.findElement(textPasswordError).getAttribute("innerHTML").toString();	// [slaptažodis] klaidos laukelio reikšmės gavimas
				zinute = "Aktorius mato [slaptažodis] klaidą '"+klaida+"'";
				System.out.println(zinute); //žinutė į konsolę
				
				boolean isEqual = klaida.equals(tekstas1) || klaida.equals(tekstas2)|| klaida.equals(tekstas3);
				Assert.assertTrue(isEqual, "The text " + klaida + " is not " + tekstas1 + " or " + tekstas2+ " or " + tekstas3); //klaidos teksto atitikimo tikrinimas
				
				}else {
				System.out.println(zinute);}
			
			
	TestParameters.resultsToFile(TestParameters.report_i_faila,TestParameters.format,TestParameters.TimeStamp(),TestParameters.getCurrentMethodName(),"OK",zinute);
		}catch(Exception e) {
			TestParameters.resultsToFile(TestParameters.report_i_faila,TestParameters.format,TestParameters.TimeStamp(),TestParameters.getCurrentMethodName(),"FALSE","kažkas nepavyko - [slaptažodis] klaida !" +e);
			TestParameters.testoRezultatas = new String[]{"FALSE","Scenarijaus pabaiga: TESTAS praėjo su klaidomis!!!",};
			}
	}
	
	public void klaida_PasswordConfirm() { //tikrinam ar rodoma klaida po [Slaptažodžio patvirtinimas] laukeliu

		String zinute = "Aktorius nemato [Slaptažodžio patvirtinimas] klaidos";
		
		// galimos [Slaptažodžio patvirtinimas] klaidos
		String tekstas1 = "Įvesti slaptažodžiai nesutampa";
		
		try {
			
			Boolean exist = driver.findElements(textPasswordConfirmError).size() == 0; //tikriname ar klaidos laukelis egzistuoja
			if(!exist)    {
				String klaida = driver.findElement(textPasswordConfirmError).getAttribute("innerHTML").toString();	// [Slaptažodžio patvirtinimas] klaidos laukelio reikšmės gavimas
				zinute = "Aktorius mato [Slaptažodžio patvirtinimas] klaidą '"+klaida+"'";
				System.out.println(zinute); //žinutė į konsolę
				
				boolean isEqual = klaida.equals(tekstas1);
				Assert.assertTrue(isEqual, "The text " + klaida + " is not " + tekstas1 ); //klaidos teksto atitikimo tikrinimas
				
				}else {
				System.out.println(zinute);}
			

	TestParameters.resultsToFile(TestParameters.report_i_faila,TestParameters.format,TestParameters.TimeStamp(),TestParameters.getCurrentMethodName(),"OK",zinute);
		}catch(Exception e) {
			TestParameters.resultsToFile(TestParameters.report_i_faila,TestParameters.format,TestParameters.TimeStamp(),TestParameters.getCurrentMethodName(),"FALSE","kažkas nepavyko - [Slaptažodžio patvirtinimas] klaida !" +e);
			TestParameters.testoRezultatas = new String[]{"FALSE","Scenarijaus pabaiga: TESTAS praėjo su klaidomis!!!",};
			}
	}
}
