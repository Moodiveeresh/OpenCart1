package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders 
{

	//Data provider1 for login test
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException //method to get data from excel sheet
	{
	//	String path = System.getProperty("user.dir") + "/src/test/java/testData/OpenCart_LoginData.xlsx";
		String path=".\\testData\\OpenCart_LoginData.xlsx";//path of excel sheet
		
	    ExcelUtils excel = new ExcelUtils(path);//create object of excel utils
	    
		int totalrow = excel.getRowCount("Sheet1");//get total row count
		int totalcols = excel.getCellCount("Sheet1", 1);//get total column count

		
		String[][] logindata = new String[totalrow][totalcols];//create 2D array to store data from excel sheet
		
		for (int i = 1; i <= totalrow; i++)//loop to get data from excel sheet 
		{
			for (int j = 0; j < totalcols; j++)//loop to get data from excel sheet 
			{
				logindata[i - 1][j] = excel.getCellData("Sheet1", i, j);// store data in 2D array
			}
		}
		
		return logindata;//return 2D array
	}
	
	
	//Data provider2 
	
	//Data provider3
}
