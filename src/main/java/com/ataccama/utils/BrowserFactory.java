package com.ataccama.utils;


import groovy.util.logging.Log;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.ataccama.test.BaseTest;

public class BrowserFactory {
    
	protected static Logger log=Logger.getLogger(BaseTest.class);

     public static WebDriver InitBrowser(String browserName)
     {
    	 WebDriver driver=null;
    	 String userDirectory=System.getProperty("user.dir");
         try
         {
             switch (browserName)
             {

                 case "Firefox":
                    
                    	 String geckopath=userDirectory+"\\Gecko\\";
                         FirefoxOptions Firefoxoptions = new FirefoxOptions();
                         Firefoxoptions.addArguments("--start-maximized");
                         System.setProperty("webdriver.gecko.driver", geckopath+"geckodriver.exe");
                         driver = new FirefoxDriver(Firefoxoptions);
                     break;

                 case "IE":
                        driver = new InternetExplorerDriver();
                     
                     break;

                 case "Chrome":
                         
                    	 String chromePath=userDirectory+"\\Chrome\\";
                         ChromeOptions options = new ChromeOptions();
                         options.addArguments("--start-maximized");
                         System.setProperty("webdriver.chrome.driver", chromePath+"chromedriver.exe");
                         driver = new ChromeDriver(options);
                     
                     break;
                     
             }
             
             return driver;
         }
         catch(Exception e)
         {
        	 log.info("Driver Setup exception: " +e.getMessage());
        	 return null;
         }
         
     }

     
}
