package Tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.skaiciuotuvas;
import Pages.registruoti;

public class REGISTRACIJA extends TestParameters{

	@Test
	public static void REGISTRACIJA_PS1() { //REGISTRACIJA_PS1 Naujo vartotojo registracija (pozityvus scenarijus + )
		
		prisijungti.atidarytiLanga(); //aktorius yra prisijungimo lange
		prisijungti.paspausti_Registruotis(); // aktorius paspaudžia nuorodą į registraciją
		registruoti.uzpildyti_forma(duomenys2[5][0],duomenys2[5][1],duomenys2[5][2]);
		registruoti.paspausti_Sukurti();

		registruoti.klaida_Username(); //[Prisijungimo vardas] rodomų klaidų tikrinimas
		registruoti.klaida_Password(); //[Slaptažodis] rodomų klaidų tikrinimas
		registruoti.klaida_PasswordConfirm(); //[Slaptažodžio patvirtinimas] rodomų klaidų tikrinimas
		
		skaiciuotuvas.arPrisijungta(duomenys2[5][0]); // patikrinam ar prisijungta
		resultsToFile(report_i_faila,format,TimeStamp(),getCurrentMethodName(),testoRezultatas[0],testoRezultatas[1]);//3. Scenarijaus pabaiga.
			
		
	}
	
	@Test
	public static void REGISTRACIJA_AS1() { //REGISTRACIJA_AS1 Aktorius atšaukia naujo vartotojo registraciją
		
		prisijungti.atidarytiLanga(); //aktorius yra prisijungimo lange
		prisijungti.paspausti_Registruotis(); // aktorius paspaudžia nuorodą į registraciją
		registruoti.uzpildyti_forma(duomenys2[6][0],duomenys2[6][1],duomenys2[6][2]);
		registruoti.BrowserAtgal(); //paspausti naršyklėj mygtuką ATGAL
		
		skaiciuotuvas.arPrisijungta(duomenys2[6][0]); // patikrinam ar prisijungta
		resultsToFile(report_i_faila,format,TimeStamp(),getCurrentMethodName(),testoRezultatas[0],testoRezultatas[1]);//3. Scenarijaus pabaiga.
			
		
	}
	
	@Test
	public static void REGISTRACIJA_AS2() { //REGISTRACIJA_AS2 Aktorius neįveda privalomų laukų 
		
		prisijungti.atidarytiLanga(); //aktorius yra prisijungimo lange
		prisijungti.paspausti_Registruotis(); // aktorius paspaudžia nuorodą į registraciją
		registruoti.uzpildyti_forma(duomenys2[7][0],duomenys2[7][1],duomenys2[7][2]);
		registruoti.paspausti_Sukurti();
		registruoti.klaida_Username(); //[Prisijungimo vardas] rodomų klaidų tikrinimas
		registruoti.klaida_Password(); //[Slaptažodis] rodomų klaidų tikrinimas
		registruoti.klaida_PasswordConfirm(); //[Slaptažodžio patvirtinimas] rodomų klaidų tikrinimas
		
		skaiciuotuvas.arPrisijungta(duomenys2[7][0]); // patikrinam ar prisijungta
		resultsToFile(report_i_faila,format,TimeStamp(),getCurrentMethodName(),testoRezultatas[0],testoRezultatas[1]);//3. Scenarijaus pabaiga.
			
		
	}
	
	@Test
	public static void REGISTRACIJA_AS3() { //REGISTRACIJA_AS3 Aktorius įveda registracijos duomenis neatitinkančius reikalavimų
		
		prisijungti.atidarytiLanga(); //aktorius yra prisijungimo lange
		prisijungti.paspausti_Registruotis(); // aktorius paspaudžia nuorodą į registraciją
		registruoti.uzpildyti_forma(duomenys2[8][0],duomenys2[8][1],duomenys2[8][2]);
		registruoti.paspausti_Sukurti();
		registruoti.klaida_Username(); //[Prisijungimo vardas] rodomų klaidų tikrinimas
		registruoti.klaida_Password(); //[Slaptažodis] rodomų klaidų tikrinimas
		registruoti.klaida_PasswordConfirm(); //[Slaptažodžio patvirtinimas] rodomų klaidų tikrinimas
		
		skaiciuotuvas.arPrisijungta(duomenys2[8][0]); // patikrinam ar prisijungta
		resultsToFile(report_i_faila,format,TimeStamp(),getCurrentMethodName(),testoRezultatas[0],testoRezultatas[1]);//3. Scenarijaus pabaiga.
			
		
	}
	
	@Test
	public static void REGISTRACIJA_AS4() { //REGISTRACIJA_AS4 Aktorius įveda egzistuojančios paskyros duomenis
		
		prisijungti.atidarytiLanga(); //aktorius yra prisijungimo lange
		prisijungti.paspausti_Registruotis(); // aktorius paspaudžia nuorodą į registraciją
		registruoti.uzpildyti_forma(duomenys2[9][0],duomenys2[9][1],duomenys2[9][2]);
		registruoti.paspausti_Sukurti();
		registruoti.klaida_Username(); //[Prisijungimo vardas] rodomų klaidų tikrinimas
		registruoti.klaida_Password(); //[Slaptažodis] rodomų klaidų tikrinimas
		registruoti.klaida_PasswordConfirm(); //[Slaptažodžio patvirtinimas] rodomų klaidų tikrinimas
		
		skaiciuotuvas.arPrisijungta(duomenys2[9][0]); // patikrinam ar prisijungta
		resultsToFile(report_i_faila,format,TimeStamp(),getCurrentMethodName(),testoRezultatas[0],testoRezultatas[1]);//3. Scenarijaus pabaiga.
			
		
	}
	
	@BeforeMethod
	public void isankstines_salygos() { 	
		
	//------- kintamųjų nustatymas  -- registruoti --	
		registruoti = new registruoti(driver); //registruoti kintamojo reikšmės nustatymas
		skaiciuotuvas = new skaiciuotuvas(driver); //skaiciuotuvas kintamojo reikšmės nustatymas
		prisijungti = new Pages.prisijungti(driver); //prisijungti kintamojo reikšmės nustatymas
		

	}
	
	
}
