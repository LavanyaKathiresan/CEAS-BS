import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class CustomerCreateMain {
	
	public void readfile() throws IOException
	{
		File fpath=new File("D:\\Eclipse\\Lib\\SampleReadFile.xlsx");
		FileInputStream fin=new FileInputStream(fpath);  
		XSSFWorkbook wb=new XSSFWorkbook(fin);
		XSSFSheet sh1= wb.getSheetAt(0);
		String val=sh1.getRow(0).getCell(0).getStringCellValue();
		System.out.println(val);
		
	}

	public static void main(String[] args) throws IOException {
		
		CustomerCreateMain cc=new CustomerCreateMain();
		cc.readfile();
		
	}

}
