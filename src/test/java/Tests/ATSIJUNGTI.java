package Tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//import Pages.registruoti;
import Pages.skaiciuotuvas;

public class ATSIJUNGTI extends TestParameters{
	
	@Test
	public static void ATSIJUNGTI_PS1() { //ATSIJUNGTI_PS1 Vartotojo atsijungimas
		
		skaiciuotuvas.koks_langas(); //funkcija tikrinanti koks URL atidarytas
		skaiciuotuvas.arPrisijungta();
		skaiciuotuvas.atsijungti(); // vartotojo atsijungimas
		//skaiciuotuvas.arPrisijungta();
		registruoti.BrowserAtgal(); //paspausti naršyklėj mygtuką ATGAL pasitikrinimui ar vartotojas grįžtų į prieš tai buvusį langą, kuriame būtų prisijungęs
		skaiciuotuvas.arPrisijungta();
		
		resultsToFile(report_i_faila,format,TimeStamp(),getCurrentMethodName(),testoRezultatas[0],testoRezultatas[1]);//3. Scenarijaus pabaiga.
			
		
	}
	
	@BeforeMethod
	public void isankstines_salygos() { 	
		
	//------- kintamųjų nustatymas  -- registruoti --	
		registruoti = new Pages.registruoti(driver); //registruoti kintamojo reikšmės nustatymas
		skaiciuotuvas = new skaiciuotuvas(driver); //skaiciuotuvas kintamojo reikšmės nustatymas
		
		//Tests.PRISIJUNGTI.isankstines_salygos();
		//Tests.PRISIJUNGTI.PRISIJUNGTI_PS1();
		
		resultsToFile(report_i_faila,"────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");	
	}

}
