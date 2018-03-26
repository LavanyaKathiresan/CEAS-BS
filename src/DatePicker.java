import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class DatePicker {


	public void pickDate(String date)
	{
		System.setProperty("webdriver.chrome.driver", "D:\\Eclipse\\Driver for Browser\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("http://seleniumpractise.blogspot.in/2016/08/how-to-handle-calendar-in-selenium.html");
		driver.manage().window().maximize();
		String array[]=date.split("/");
		String day=array[0];
		String month=array[1];
		String year=array[2];
		driver.findElement(By.id("datepicker")).click();;
		String matchMonth = driver.findElement(By.className("ui-datepicker-month")).getText();
		String matchYear = driver.findElement(By.className("ui-datepicker-year")).getText();
		WebElement nextButton = driver.findElement(By.xpath("//*[@class='ui-icon ui-icon-circle-triangle-e']"));
		while( !matchYear.equalsIgnoreCase(year))
		{ 	int i=0;
		while(i<10)
		{
			try {
				nextButton.click();
				System.out.println("Next button clicked");
				break;
			} catch (StaleElementReferenceException e) {
				i++;	
				System.out.println(i);}
		}
		matchMonth = driver.findElement(By.className("ui-datepicker-month")).getText();
		matchYear = driver.findElement(By.className("ui-datepicker-year")).getText();
		}
		System.out.println("matchMonth"+ matchMonth);
		System.out.println("matchYear"+ matchYear);


		List<WebElement> listDay = driver.findElements(By.xpath("//*[@id='ui-datepicker-div']/table//td"));
		for(WebElement i :listDay )
		{
			String each = i.getText();
			System.out.println(each);
			if(each.equalsIgnoreCase(day))
				i.click();
			System.out.println("Clicked");
		}

	}

	public static void main(String[] args) {
		DatePicker datepic=new DatePicker();
		datepic.pickDate("2/February/2020");

	}

}
