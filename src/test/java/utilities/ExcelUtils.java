package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
/*public: This keyword specifies the access level of the variable. public means the variable can be accessed from any other class.

static: The static keyword means that this variable belongs to the class itself, rather than to instances (objects) of the class. This means you can access this variable without creating an instance of the class.

FileInputStream: This is the type of the variable. FileInputStream is a class in the java.io package used for reading byte-oriented data from a file.

fi: This is the name of the variable. It will hold a reference to a FileInputStream object.
*/
	//create all user defined methods to perform different operation
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static XSSFCellStyle style;
	String path;
	
	public ExcelUtils(String path) 
	{
		this.path=path;	
		
	}
	
	
	//count no of rows in excel sheet
	public  int getRowCount(String sheetName) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		int rowcount=sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;
			
	}
	
	public int getCellCount(String sheetName, int rownum) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		int cellcount=row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellcount;
			
	}
	
	public String getCellData(String sheetName, int rownum, int colnum) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);
		
		String data;
		try
		{
//DataFormatter: This class converts the cell's data into a readable string format (handling various types of data, such as dates, numbers, or strings).			
			//data=cell.toString();
			DataFormatter formatter=new DataFormatter();
			data= formatter.formatCellValue(cell);//return the formatted value of a cell as a String regardless 
		}
		catch(Exception e)
		{
			data="";
		}
		workbook.close();
		fi.close();
		return data;
	}
	
	public void  setCellData(String sheetName, int rownum, int colnum, String data) throws IOException
	{
		File xlfile=new File(path);
		if (!xlfile.exists()) {
			workbook = new XSSFWorkbook();
			fo = new FileOutputStream(path);
			workbook.write(fo);
		}
		
		fi=new FileInputStream(xlfile);
		workbook=new XSSFWorkbook(fi);
		
		if (workbook.getSheetIndex(sheetName) == -1) // if sheet is not exist create new sheet
	
			workbook.createSheet(sheetName);
			sheet = workbook.getSheet(sheetName);
	

		
		if (sheet.getRow(rownum) == null)// if row is not present create new row
			 sheet.createRow(rownum);
		    row= sheet.getRow(rownum);
		
		
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(path);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
			
	}
	
	public void fillGreenColor(String sheetName,int rownum, int colnum) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		
		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);
		
		 style= workbook.createCellStyle();
		 
		 style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		 style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		 
		 cell.setCellStyle(style);
		 workbook.write(fo);
		 workbook.close();
		 fi.close();
		 fo.close();
	}	
	
	public void fillRedColor(String sheetName,int rownum, int colnum) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);
		
		 style= workbook.createCellStyle();
		 
		 style.setFillForegroundColor(IndexedColors.RED.getIndex());
		 style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		 
		 cell.setCellStyle(style);
		 workbook.write(fo);
		 workbook.close();
		 fi.close();
		 fo.close();
		 
	}	
}
