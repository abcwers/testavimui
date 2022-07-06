package Tests;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;



//import Pages.prisijungti; //bandymas įsidėti kintamuosius pasiekiamus kituose failuose


public class TestParameters {
	
	
	protected static WebDriver driver;
	
	public static Alert alert;
	public static FluentWait wait; //wait kintamasis
	
	static Pages.prisijungti prisijungti;
	static Pages.registruoti registruoti;
	static Pages.skaiciuotuvas skaiciuotuvas;
	
	public static String report_i_faila ;//= "src/test/resources/*_Rezultatai.txt";
	public static String skaityti_isTxtFailo ;//= "src/test/resources/duomenys*Test.txt";
	public static String format = "%-19s %-40s %-6s %-70s"; //teksto failo formatavimas
//	public static String[] duomenys;
	public static Dictionary<Integer, String[]> duomenys = new Hashtable<Integer, String[]>(); //bandymas įrašyti duomenis į kintamąjį
	public static Dictionary<String, String[]> reiksmes = new Hashtable<String, String[]>();//kitas bandymas įrašyti duomenis į kintamąjį (kitas kintamasis)
	
	public static String duomenys2[][] ;// duomenų įrašymui į array kintamjį
	public static String[] testoRezultatas;// = new String[]{"OK","Scenarijaus pabaiga: TESTAS praėjo be klaidų",};
	
	@BeforeTest
	public static void SetUp() {
//		FirefoxOptions options = new FirefoxOptions();
//		options.setHeadless(true);
//		driver = new FirefoxDriver(options);
		System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
		driver = new ChromeDriver();
		
		wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(5)) // wait kinamajam uždėta reikšmė bendras laukimo laikas 5 sekundės, 
		.pollingEvery(Duration.ofMillis(250)) // bandymai kas 250ms 	
        .ignoring(NoSuchElementException.class); 	
		
	//	duomenu_tipas(); //kur saugome, kokie duomenys
	}			

	
	@BeforeMethod
	public void kintamieji() { 	

		//------- kintamųjų nustatymas --- testoRezultatas --	

		testoRezultatas = new String[]{"OK","Scenarijaus pabaiga: TESTAS praėjo be klaidų",};

	//	testoRezultatas = new String[]{"FALSE","Scenarijaus pabaiga: TESTAS praėjo su klaidomis!!!",};
	}
	
	@AfterMethod
	public void ReportLinija_PoMetodo() { 	
	resultsToFile(report_i_faila,"════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
//	resultsToFile(report_i_faila,"────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");	
	}
	
	@AfterTest
	public void TearDown() throws IOException {
	driver.manage().deleteAllCookies();
	//driver.close();
	
//	resultsToFile(report_i_faila,"════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
	}
	
	public static List<String> getTestDataList(String filePath) throws IOException {
		List<String> records = new ArrayList<String>(); 
		String record;
		
		FileReader FR = new FileReader(filePath);
		  BufferedReader BR = new BufferedReader(FR);
		  
		  //Loop to read all lines one by one from file and print It.
		  while((record = BR.readLine())!= null){
		   records.add(record);
			//  System.out.println(record);
		  }
		  BR.close();
		  return records;
	}
	
/*	public void scenarijausPabaiga() { 	//ne visai tinka, dėl to, kad getCurrentMethodName rodo į šią funkciją, bet ne tą iš kurios iškvietė.
 // į report failą išvedame viso testo galutinį vertinimą. Ar TESTAS praėjo su klaidomis ar ne.
		resultsToFile(report_i_faila,format,TimeStamp(),getCurrentMethodName(),testoRezultatas[0],testoRezultatas[1]);
	} */
	
	
	public static void takeSnapShot(WebDriver webdriver, String name) {

        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot =((TakesScreenshot)webdriver);
        //Call getScreenshotAs method to create image file
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        //Move image file to new destination
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
      	String filePath = "src/test/resources/screenshots/"+timeStamp+name+".png";
        File DestFile=new File(filePath);
       //Copy file at destination
        try {
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	public static void resultsToFile(String fileName,String format,Object... text) {
		
		OutputStreamWriter fw = null;
		try {
			fw = new OutputStreamWriter(new FileOutputStream(fileName,true), StandardCharsets.UTF_8);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//open file to write info
		BufferedWriter bw = new BufferedWriter(fw);//teksto rasymui
		PrintWriter pw = new PrintWriter(bw);//
		pw.println(); //nauja eilutė	
		pw.format(format,text); //formatavimas,text,text...
		pw.close();//uzdarome irasinejimo faila.
	
	}
	
	
	public static String getCurrentMethodName() { //gauti klasės+metodo vardą
        return Thread.currentThread().getStackTrace()[2].getClassName() + "." + Thread.currentThread().getStackTrace()[2].getMethodName();
    }
	
	public static String TimeStamp() { //gauti klasės+metodo vardą
        return new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(Calendar.getInstance().getTime());
    }

	@BeforeTest
	@Parameters({"regresTest",})
	public static void duomenu_tipas( @Optional("false") boolean regresTest) throws IOException { //Nustatyti kintamuosius

		if(regresTest == true) {
		
		System.out.println("REGRESS TEST");
		report_i_faila = "src/test/resources/RegressTest_Rezultatai.txt";
		skaityti_isTxtFailo = "src/test/resources/duomenysRegressTest.txt";
		resultsToFile(report_i_faila,"════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
		resultsToFile(report_i_faila,"%80s","REGRESS TEST");
		}else{
		System.out.println("SMOKE TEST");
		report_i_faila = "src/test/resources/SmokeTest_Rezultatai.txt";
		skaityti_isTxtFailo = "src/test/resources/duomenysSmokeTest.txt";
		resultsToFile(report_i_faila,"════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
		resultsToFile(report_i_faila,"%80s","SMOKE TEST");
		}
		resultsToFile(report_i_faila,"════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
		resultsToFile(report_i_faila,"%-19s %-39s %-7s %-70s","[    TimeStamp    ]","[     funkcijos pavadinimas       ]","[pass]","[                                       komentarai                                     ]");	
		resultsToFile(report_i_faila,"════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");	
		//[testo pavadinimas] [timestamp] [o čia nežinau kas turėtų būti, galbūt žingsniai, o gal dar kas nors...][pass/false]
		duomenys(); //bandymas įrašyti failo duomenis į kintamąjį
    }
	
	
	
	public static void duomenys() throws IOException  { //Nuskaityti duomenis iš failo ir įrašyti array kintamąjį
		
		List<String> tekstas_isFailo = getTestDataList(skaityti_isTxtFailo);//skaitom visas eilutes
		  int a = tekstas_isFailo.size();//apskaiciuojam eiluciu skaiciu txt faile

		 
          TestParameters.duomenys2=new String[a][];
		  
		  for(int i = 1; i < a; i++){//ciklas. Testavimui eilute po eilutes , pirmąją eilutę praleidžiam(nes ten pasirašėm duomenų anotaciją)

			  duomenys.put(i,tekstas_isFailo.get(i).split("\\|",-1)); //variantas įrašant duomenis į raktažodį (skaičius nuo 1)
			  reiksmes.put(tekstas_isFailo.get(i).split("\\|",-1)[0],tekstas_isFailo.get(i).replaceAll("\\|$","").split("\\|",-1)); //variantas įrašant duomenis į raktažodį (kur raktažodis būtų pirmasis eilutės žodis)
			  duomenys2[i]=tekstas_isFailo.get(i).split("\\|",-1); // įrašomi duomenys į array kintamąjį
			  
			  
		  }
		  String usernameUUID = UUID.randomUUID().toString().replaceAll("-","") ;
		  
		// username|password|password confirm|skaicius1|skaicius2|veiksmo ženklas| -- duomenų struktūra
		  duomenys.put(3,new String[]{usernameUUID,"slaptazodis","slaptazodis","101","50","-"}); //irašoma į failo duomenų kinatamąjį sugeneruotas unikalus Username ID
		  
		  TestParameters.duomenys2[5]= new String[]{usernameUUID,"slaptazodis","slaptazodis","101","50","-"};//perašoma array eilutė su unikaliu Username ID
		  

          
		 // System.out.println(duomenys2[3][0]); //bandymas išsitraukti konkrečią reikšmę iš masyvo kintamojo
		  
			//  System.out.println(duomenys.get(3)[1]); //bandymas išsitraukti reikšmes
			//	  System.out.println(reiksmes.get("username")[1]); //bandymas išsitraukti reikšmes
          
      }
		 
		
		  
	

	
    }
	
	

	
//}
