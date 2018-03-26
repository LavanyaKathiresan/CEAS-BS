import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CustomerCreation 
{
	WebDriver driver = new InternetExplorerDriver();
	public void login()
	{
				String sampleURL = "http://172.24.2.116:7001/ceas/";
		driver.get(sampleURL); 		 
		driver.findElement(By.name("userName")).sendKeys("futterer");
		driver.findElement(By.name("login")).click();
		WebElement profiletable=driver.findElement(By.id("loginPageSegment"));
		List<WebElement> tablerows=profiletable.findElements(By.tagName("tr"));
		for(int j=0;j < tablerows.size();j++)
		{
			List<WebElement> tabledata=tablerows.get(j).findElements(By.tagName("td"));
			if(tabledata.get(1).getText().equalsIgnoreCase("RMG") && tabledata.get(2).getText().equalsIgnoreCase("Bank and Sovereign")&&tabledata.get(3).getText().equalsIgnoreCase("Bank and Sovereign"))
			{
				tabledata.get(0).click();
				break;
			}
		} 
	    driver.findElement(By.id("btnDiv")).click();
	}
	
	public String swithchwindow()
  		{
		String cussearchresult=driver.getWindowHandle();
		String title1current=driver.getTitle();
		//System.out.println(title1current); 
		Object [] allwindows=driver.getWindowHandles().toArray();	
		int allwindowssize=allwindows.length;
	
		while(allwindowssize>0)
		{
			cussearchresult=driver.getWindowHandle();
			allwindows=driver.getWindowHandles().toArray();
			allwindowssize=allwindows.length;
			
			if(allwindowssize>1){
				break;
			}
		}
		
		//System.out.println("window lenth get==================>>>>>"+allwindowssize);
		if(allwindowssize>1)
		  {
			for(int i=0;i< allwindowssize;i++)
			   {
				String allwindowscurr=allwindows[i].toString();
				if(!allwindowscurr.equalsIgnoreCase(cussearchresult))
		      {
					String beforesw=driver.getTitle();
				//System.out.println(beforesw);
	           driver.switchTo().window(allwindowscurr);
	           String aftersw=driver.getTitle();
				//System.out.println(aftersw);
	        
					   }
		           }
		   }
	return (cussearchresult);
	}
	public void ProfileCreation() throws InterruptedException, IOException
	{
	driver.findElement(By.id("link7")).click();
	String legal_name="CEAS_BS_AUTOTEST16";
    driver.findElement(By.name("searchcriteria")).sendKeys(legal_name);
	driver.findElement(By.className("button-c")).click();
	driver.findElement(By.name("icifSearchButton")).click();
	String cuwin=swithchwindow();
	String url=driver.getCurrentUrl();
	// System.out.println(url);
	 Thread.sleep(1000);
   	 driver.findElement(By.name("btnCreateCustomer")).click();
   	 //reading customer type
   	File fpath=new File("D:\\Eclipse\\Lib\\Custypeselection.xlsx");
	FileInputStream fin=new FileInputStream(fpath);  
	XSSFWorkbook wb=new XSSFWorkbook(fin);
	XSSFSheet sh1= wb.getSheetAt(0);
	String val=sh1.getRow(0).getCell(0).getStringCellValue();
   	 if(val.equalsIgnoreCase("Non-personal"))
   			 {
   	 driver.findElement(By.id("btnDefaultVal")).click();
	 driver.findElement(By.name("legalName")).sendKeys("legal_name12");
	 driver.findElement(By.name("legalEntityIdentifier")).sendKeys("legal_name3");
	 driver.findElement(By.name("btnCreateCustomer")).click();
	 driver.switchTo().window(cuwin);
	 driver.findElement(By.name("addCCIFCustomerToCeasButton")).click();
	 Thread.sleep(2000);
	 driver.findElement(By.className("button-c")).click();
	      }
   	 else
   	 {
   		 driver.findElement(By.id("clientTypePers")).click();
   		 driver.findElement(By.name("firstName")).sendKeys("Firstname2");
   		 driver.findElement(By.name("middleName")).sendKeys("middleName2");
   		 driver.findElement(By.name("surname")).sendKeys("surname2");
   	   	 driver.findElement(By.id("btnDefaultVal")).click();
   		 driver.findElement(By.name("btnCreateCustomer")).click();
   		 driver.switchTo().window(cuwin);
   		 driver.findElement(By.name("addCCIFCustomerToCeasButton")).click();
   		 Thread.sleep(2000);
   		 driver.findElement(By.className("button-c")).click();
   		    	 }
	}
     public void bankcustype() throws InterruptedException
     {
    String cuwin1=swithchwindow();
    // CustomerTypeSelection();
     Thread.sleep(1000);
     driver.findElement(By.cssSelector("input[value=BNK]")).click();
     driver.findElement(By.name("Ok")).click();
	 driver.switchTo().window(cuwin1);
     //swithchwindow();
	 String tit=driver.getCurrentUrl();
	// System.out.println("window switched " +tit);
	 Select dropdown=new Select(driver.findElement(By.name("regulatoryClass"))); 
	 dropdown.selectByIndex(3);
	 driver.findElement(By.name("branchOfAccount")).sendKeys("00000");
     Thread.sleep(2000);
     driver.findElement(By.id("weight[1]")).sendKeys("100");
	 //driver.findElement(By.name("weight")).sendKeys("100");
	 driver.findElement(By.name("Save")).click();;
	 driver.findElement(By.linkText("Status")).click();
	 String ststusurl=driver.getCurrentUrl();
	 System.out.println(ststusurl);
	 driver.findElement(By.name("isCore")).click();
	 driver.findElement(By.name("Save")).click();
	 driver.findElement(By.linkText("Business")).click();
	 Select cop=new Select(driver.findElement(By.name("country")));
	 cop.selectByIndex(5);
	 Thread.sleep(2000);
	 Select coc=new Select(driver.findElement(By.name("countryOfRisk")));
	 coc.selectByIndex(5);
	 driver.findElement(By.cssSelector("input[name=economicOwnership][value=PUB]")).click();
	 Thread.sleep(3000);
     driver.findElement(By.cssSelector("input[name=votingControlOwnership][value=PUB]")).click();
	// driver.findElement(By.cssSelector("input[name=votingControlOwnership][value=PUB")).click();
	 Thread.sleep(3000);
	 driver.findElement(By.cssSelector("input[name=issuerPublicTradingDebt][value=1]")).click();
	 Thread.sleep(3000);
	 driver.findElement(By.name("Save")).click();  	
	 //String url=driver.getCurrentUrl();
	// System.out.println(url);
	}
     public void sovcustype() throws InterruptedException
     {
    	 String cuwin1=swithchwindow();
    	    // CustomerTypeSelection();
    	  Thread.sleep(1000);
    	  driver.findElement(By.cssSelector("input[value=SOV]")).click();
    	  driver.findElement(By.name("Ok")).click();
    		 driver.switchTo().window(cuwin1);
    	     //swithchwindow();
    		 String tit=driver.getCurrentUrl();
    		// System.out.println("window switched " +tit);
    		 Select dropdown=new Select(driver.findElement(By.name("regulatoryClass"))); 
    		 dropdown.selectByIndex(3);
    		 driver.findElement(By.name("branchOfAccount")).sendKeys("00000");
    	     Thread.sleep(2000);
    	     driver.findElement(By.id("weight[1]")).sendKeys("100");
    		 //driver.findElement(By.name("weight")).sendKeys("100");
    		 driver.findElement(By.name("Save")).click();;
    		 driver.findElement(By.linkText("Status")).click();
    		 String ststusurl=driver.getCurrentUrl();
    		 System.out.println(ststusurl);
    		 driver.findElement(By.name("isCore")).click();
    		 driver.findElement(By.name("Save")).click();
    		 driver.findElement(By.linkText("Business")).click();
    		 Select cop=new Select(driver.findElement(By.name("country")));
    		 cop.selectByIndex(5);
    		 Thread.sleep(2000);
    		 Select coc=new Select(driver.findElement(By.name("countryOfRisk")));
    		 coc.selectByIndex(5);
    		 driver.findElement(By.cssSelector("input[name=economicOwnership][value=PUB]")).click();
    		 Thread.sleep(3000);
    	     driver.findElement(By.cssSelector("input[name=votingControlOwnership][value=PUB]")).click();
    		// driver.findElement(By.cssSelector("input[name=votingControlOwnership][value=PUB")).click();
    		 Thread.sleep(3000);
    		 driver.findElement(By.cssSelector("input[name=issuerPublicTradingDebt][value=1]")).click();
    		 Thread.sleep(3000);
    		 driver.findElement(By.name("Save")).click();  	
    		 //String url=driver.getCurrentUrl();
    		// System.out.println(url);
    		}
     
     public void CorpCusType() throws InterruptedException
     {
    	 String cuwin1=swithchwindow();
 	    // CustomerTypeSelection();
 	  Thread.sleep(1000);
 	  driver.findElement(By.cssSelector("input[value=ORG]")).click();
 	  driver.findElement(By.name("Ok")).click();
 		 driver.switchTo().window(cuwin1);
 	     //swithchwindow();
 		 String tit=driver.getCurrentUrl();
 		// System.out.println("window switched " +tit);
 		 Select dropdown=new Select(driver.findElement(By.name("regulatoryClass"))); 
 		 dropdown.selectByIndex(3);
 		 driver.findElement(By.name("branchOfAccount")).sendKeys("00000");
 	     Thread.sleep(2000);
 	     driver.findElement(By.id("weight[1]")).sendKeys("100");
 		 //driver.findElement(By.name("weight")).sendKeys("100");
 		 driver.findElement(By.name("Save")).click();;
 		 driver.findElement(By.linkText("Status")).click();
 		 String ststusurl=driver.getCurrentUrl();
 		 System.out.println(ststusurl);
 		 driver.findElement(By.name("isCore")).click();
 		 driver.findElement(By.name("Save")).click();
 		 driver.findElement(By.linkText("Business")).click();
 		 Select cop=new Select(driver.findElement(By.name("country")));
 		 cop.selectByIndex(5);
 		 Thread.sleep(2000);
 		 Select coc=new Select(driver.findElement(By.name("countryOfRisk")));
 		 coc.selectByIndex(5);
 		 driver.findElement(By.cssSelector("input[name=economicOwnership][value=PUB]")).click();
 		 Thread.sleep(3000);
 	     driver.findElement(By.cssSelector("input[name=votingControlOwnership][value=PUB]")).click();
 		// driver.findElement(By.cssSelector("input[name=votingControlOwnership][value=PUB")).click();
 		 Thread.sleep(3000);
 		 driver.findElement(By.cssSelector("input[name=issuerPublicTradingDebt][value=1]")).click();
 		 Thread.sleep(3000);
 		 driver.findElement(By.name("Save")).click();  	
 		 //String url=driver.getCurrentUrl();
 		// System.out.println(url);
 		  		  		}
     public void IndivdualCusType() throws InterruptedException
     {
    	 //String cuwin1=swithchwindow();
  	    // CustomerTypeSelection();
  	 // Thread.sleep(1000);
  	  Select cor=new Select(driver.findElement(By.name("countryOfRisk")));
  	  cor.selectByIndex(6);
  	  driver.findElement(By.name("branchOfAccount")).sendKeys("00000");
  	  driver.findElement(By.name("Add")).click();
  	 String cuwin2=swithchwindow();
  	 System.out.println("window switched");
  	 Select jur=new Select(driver.findElement(By.name("jurisdiction")));
     jur.selectByIndex(2);
     driver.switchTo().window(cuwin2);
  	 driver.findElement(By.name("Save")).click();  	
  	 driver.findElement(By.linkText("Status")).click();
  	 driver.findElement(By.name("isCore")).click();
  	 driver.findElement(By.name("Save")).click();
  	   	      
     }
          public void PseCusType() throws InterruptedException
     {
    	 String cuwin1=swithchwindow();
  	    // CustomerTypeSelection();
  	  Thread.sleep(1000);
  	  driver.findElement(By.cssSelector("input[value=GOV]")).click();
  	  driver.findElement(By.name("Ok")).click();
  		 driver.switchTo().window(cuwin1);
  	     //swithchwindow();
  		 String tit=driver.getCurrentUrl();
  		// System.out.println("window switched " +tit);
  		 Select dropdown=new Select(driver.findElement(By.name("regulatoryClass"))); 
  		 dropdown.selectByIndex(3);
  		 driver.findElement(By.name("branchOfAccount")).sendKeys("00000");
  	     Thread.sleep(2000);
  	     driver.findElement(By.id("weight[1]")).sendKeys("100");
  		 //driver.findElement(By.name("weight")).sendKeys("100");
  		 driver.findElement(By.name("Save")).click();;
  		 driver.findElement(By.linkText("Status")).click();
  		 String ststusurl=driver.getCurrentUrl();
  		 System.out.println(ststusurl);
  		 driver.findElement(By.name("isCore")).click();
  		 driver.findElement(By.name("Save")).click();
  		 driver.findElement(By.linkText("Business")).click();
  		// Thread.sleep(3000);
  		 Select GPtype=new Select(driver.findElement(By.name("publicSectorEntityType")));
  		 GPtype.selectByIndex(1);
  	     Thread.sleep(3000);
  		 Select fedjuris=new Select(driver.findElement(By.name("fdrlJurisdiction")));
  		 fedjuris.selectByIndex(1);
  		 Select Government_Type=new Select(driver.findElement(By.name("legalStatus")));
  		 Government_Type.selectByIndex(1);
  		 driver.findElement(By.cssSelector("input[name=economicOwnership][value=PUB]")).click();
 		 Thread.sleep(3000);
 	     driver.findElement(By.cssSelector("input[name=votingControlOwnership][value=PUB]")).click();
 		// driver.findElement(By.cssSelector("input[name=votingControlOwnership][value=PUB")).click();
 		 Thread.sleep(2000);
 		 driver.findElement(By.cssSelector("input[name=issuerPublicTradingDebt][value=1]")).click();
 		 Thread.sleep(2000);
  		/* driver.findElement(By.cssSelector("input[name=economicOwnership][value=PUB]")).click();
  		 Thread.sleep(3000);
  		 driver.findElement(By.cssSelector("input[name=votingControlOwnership][value=PUB")).click();
  		 driver.findElement(By.cssSelector("input[name=issuerPublicTradingDebt][value=1]")).click();
  		 Thread.sleep(2000);*/
  		 Select Fiscal_year_end=new Select(driver.findElement(By.name("fiscalYear")));
  		Fiscal_year_end.selectByIndex(1);
  		 Thread.sleep(1000);
  		 driver.findElement(By.name("Save")).click();
       }
    	   	  
    public static void main(String[] args) throws InterruptedException, IOException
    {
    System.setProperty("webdriver.ie.driver", "D:\\Eclipse\\Driver for Browser\\IEDriverServer.exe");
	CustomerCreation ccobj=new CustomerCreation();
	// ccobj.bankcustype();
   // ccobj.sovcustype();
   // ccobj.CorpCusType();
   //ccobj.IndivdualCusType();
   //ccobj.PseCusType();
    File path=new File("D:\\Eclipse\\Lib\\SampleReadFile.xlsx");
	FileInputStream fin=new FileInputStream(path); 
	XSSFWorkbook wb1=new XSSFWorkbook(fin);
	XSSFSheet custtype=wb1.getSheet("custtype");
	XSSFRow row=custtype.getRow(0);
	int col_numj=-1;
	//System.out.println("loop started");
    for(int i=0; i<row.getLastCellNum(); i++)
	{
		//System.out.println("inside of for started");
		String forprint=row.getCell(i).getStringCellValue();
		//System.out.println(forprint);
	
		  //if(row.getCell(i).getStringCellValue().trim().equals("Status"))
     if(row.getCell(i).getStringCellValue().trim().equalsIgnoreCase("Status"))
		   {
				System.out.println("inside of if started");
				for(int j=1;j<6;j++)
				{
				
				XSSFRow row1=custtype.getRow(j);
 				//System.out.println("row increased");
			   if(row1.getCell(i).getStringCellValue().equalsIgnoreCase("Y"))
		      {
			   String customertype=row1.getCell(i-1).getStringCellValue();
			      switch(customertype)
			         {
			  case "ORG":
				ccobj.login();
				ccobj.ProfileCreation();
			   	ccobj.CorpCusType();
				break;
				  
			  case "PSE":
				 ccobj.login();
				 ccobj.ProfileCreation();
				 ccobj.PseCusType();
				 break;
			  case "BNK":
				 ccobj.login();
				 ccobj.ProfileCreation();
				 ccobj.bankcustype();
				  break;
			  case "SOV":
				 ccobj.login();
				 ccobj.ProfileCreation();
				 ccobj.sovcustype();
				  break;
			  case "IND":
				 ccobj.login();
				 ccobj.ProfileCreation();
				 ccobj.IndivdualCusType();
				  break;
			        }
				  
		      }
			   }
		
		   }
      	}
			}
    
	}

