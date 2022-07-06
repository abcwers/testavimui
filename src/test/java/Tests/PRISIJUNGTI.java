package Tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import Pages.prisijungti;
import Pages.skaiciuotuvas;

public class PRISIJUNGTI extends TestParameters{
	
	@Test
	public static void PRISIJUNGTI_PS1() { //PRISIJUNGTI_PS1 Esamo vartotojo prisijungimas (pozityvus scenarijus + galimų klaidų tikrinimas)
		
		prisijungti.atidarytiLanga();
		prisijungti.uzpildytiForma(duomenys2[1][0],duomenys2[1][1]);
		prisijungti.paspausti_Prisijungti();
		prisijungti.arRodoma_klaida("Įvestas prisijungimo vardas ir/ arba slaptažodis yra neteisingi"); // alternatyvaus scenarijaus patikrinimas
		skaiciuotuvas.arPrisijungta(duomenys2[1][0]); // tikrinama ar prisijungta  su konkrečiu vartotoju
		resultsToFile(report_i_faila,format,TimeStamp(),getCurrentMethodName(),testoRezultatas[0],testoRezultatas[1]);//3. Scenarijaus pabaiga.
			
		
	}
	@Test
	public static void PRISIJUNGTI_AS1() { //PRISIJUNGTI_AS1 Aktorius atšaukia prisijungimą prie paskyros
		
		prisijungti.atidarytiLanga();
		prisijungti.uzpildytiForma(duomenys2[2][0],duomenys2[2][1]);
		prisijungti.paspausti_Registruotis(); // aktorius paspaudžia nuorodą į registraciją
		skaiciuotuvas.arPrisijungta(); // tikrinama ar prisijungta
		
		resultsToFile(report_i_faila,format,TimeStamp(),getCurrentMethodName(),testoRezultatas[0],testoRezultatas[1]);//3. Scenarijaus pabaiga.
			
		
	}
	
	@Test
	public static void PRISIJUNGTI_AS2() { //PRISIJUNGTI_AS2 Aktorius neįveda privalomų laukų + galimas pozityvus testas
		
		prisijungti.atidarytiLanga();
		prisijungti.uzpildytiForma(duomenys2[3][0],duomenys2[3][1]);
		prisijungti.paspausti_Prisijungti();
		prisijungti.arRodoma_klaida("Įvestas prisijungimo vardas ir/ arba slaptažodis yra neteisingi"); // alternatyvaus scenarijaus patikrinimas
		skaiciuotuvas.arPrisijungta(); // tikrinama ar prisijungta
		resultsToFile(report_i_faila,format,TimeStamp(),getCurrentMethodName(),testoRezultatas[0],testoRezultatas[1]);//3. Scenarijaus pabaiga.
			
		
	}
	
	@Test
	public static void PRISIJUNGTI_AS3() { //PRISIJUNGTI_AS3 Aktorius įveda neteisingus prisijungimo prie paskyros duomenis + galimas pozityvus testas
		
		prisijungti.atidarytiLanga();
		prisijungti.uzpildytiForma(duomenys2[4][0],duomenys2[4][1]);
		prisijungti.paspausti_Prisijungti();
		prisijungti.arRodoma_klaida("Įvestas prisijungimo vardas ir/ arba slaptažodis yra neteisingi"); // alternatyvaus scenarijaus patikrinimas
		skaiciuotuvas.arPrisijungta(); // tikrinama ar prisijungta
		resultsToFile(report_i_faila,format,TimeStamp(),getCurrentMethodName(),testoRezultatas[0],testoRezultatas[1]);//3. Scenarijaus pabaiga.
			
		
	}
	
	
	@BeforeMethod
	public static void isankstines_salygos() { 	
		
	//------- kintamųjų nustatymas  -- prisijungti -- skaiciuotuvas	
		prisijungti = new prisijungti(driver); //prisijungti kintamojo reikšmės nustatymas
		skaiciuotuvas = new skaiciuotuvas(driver); //skaiciuotuvas kintamojo reikšmės nustatymas

	}

}
