import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class CusTypePrint {
	
	public void printfile() throws IOException
	{
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
		  if(row.getCell(i).getStringCellValue().trim().equalsIgnoreCase("Status"))
			      {
					//System.out.println("inside of if started");
					  for(int j=1;j<5;j++)
					  {
						  XSSFRow row1=custtype.getRow(j);
					 //  System.out.println("row increased");
				   			   
			       if(row1.getCell(i).getStringCellValue().equalsIgnoreCase("Y"))
			      {
				 // System.out.println("customertype");
				  String customertype=row1.getCell(i-1).getStringCellValue();
				  System.out.println(customertype);
				     }
			       
					  }
			    }
	      	}
	}
	      

	public static void main(String[] args) throws IOException
	{
		CusTypePrint ob=new CusTypePrint();
		ob.printfile();
	}

}
