package core;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class UserAgent {

    public static void main(String[] args) {

	String url = "http://alex.academy/ua";
	String[] userAgents = {
		"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36",
		"Mozilla/5.0 (Macintosh; Intel Mac OS X 10.13; rv:56.0) Gecko/20100101 Firefox/56.0",
		"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Safari/604.1.38",
		"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.79 Safari/537.36 Edge/15.14393",
		"Mozilla/5.0 (iPhone; CPU OS 11_0 like Mac OS X) AppleWebKit/602.2.14 (KHTML, like Gecko) Version/11.0 Mobile/14B100 Safari/604.1",
		"Mozilla/5.0 (Linux; U; Android 4.0.4; en-gb; GT-I9300 Build/IMM76D) AppleWebKit/534.30 (KHTML, like Gecko) Chrome/61.0.2840.87 Mobile Safari/537.36" };

	for (String i : userAgents) {
	    Logger.getLogger("").setLevel(Level.OFF);
	    WebDriver driver = new HtmlUnitDriver();
	    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	    ((HtmlUnitDriver) driver).setJavascriptEnabled(true);
	    ((HtmlUnitDriver) driver).getBrowserVersion().setUserAgent(i);
	    driver.get(url);
	    String user_agent_string = driver.findElement(By.id("id_ua")).getText();
	    System.out.println("User Agent: \t" + user_agent_string);

	    String regex = "^"
		    +"(?:Mozilla\\/5.0)(?:\\s\\()((?:Macintosh\\;\\sIntel\\s)|(?:Windows\\s)|(?:iPhone\\;\\sCPU\\s)|(?:Linux\\;\\sU\\;\\s)?)"
		    + "((?:Mac\\sOS.+?)?(?:NT\\s.+?)?(?:OS\\s.+?)?(?:Android\\s.+?)?)?(?:\\)\\s)"
		    + "(?:.*\\s((?:Safari\\/537.+?)|(?:Firefox\\/56.+?)|(?:Safari\\/604.+?)|(?:Edge\\/15.+?)|(?:Mobile Safari\\/537.+?)?))"
		    + "$";
		    
		    
//		    "^" + "(?:Mozilla\\/5.0\\s)" // group N/A
//		    + "((?:\\(Macintosh.+?\\)\\s)|(?:\\(Windows NT.+?\\)\\s)|(?:\\(iPhone.+?\\)\\s)|(?:\\(.*Android.+?\\)\\s))" // OS
		    // +

		    // OS
		    // version
//		    + "((?:.*Firefox/.+?)|(?:.*Chrome/.+?)|(?:.*Safari/.+?))" // Browser
//"^"
//		    + "(?:Mozilla\\/5.0)"
//+"(?:\\s\\()((?:Macintosh\\;\\sIntel\\s)?(?:Windows\\s)?(?:iPhone\\;\\sCPU\\s)?(?:Linux\\;\\sU\\;\\s)?)(?:.*.+?)?"
////		     
//		    // // Browser
//		    // version
//		    + "$";

	    Pattern p = Pattern.compile(regex);
	    Matcher m = p.matcher(user_agent_string);
	    m.find();
//	    System.out.println(m);
	    // System.out.println(m.group(0));
	    String os = m.group(1);
	     String osVer = m.group(2);
	    String browser = m.group(3);
	    // String browserVer = m.group(3);
//	    m.reset();
	    System.out.println("OS: \t\t" + os);
	     System.out.println("OS Version: \t" + osVer);
	    
	    System.out.println("Browser: \t" + browser.replaceAll("(\\/.*.+?)", ""));
	     System.out.println("Browser Ver: \t" + browser);
	    System.out.println("Next UA------------------------------------------+");
	    driver.quit();
	}

	System.out.println("End of job-------------------------------------------+");
    }
}
