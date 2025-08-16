package data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDriven {
	

	//Identify TestCase column by scanning the entire excel sheet
	//once column is identified then scan the entire column to identify purchase tetcase row
	//after that we can get the data from the row and column and feed it to the test case
	
	
	public ArrayList<String> getdata(String Testcase, String SheetName) throws IOException 
	{
		//First fetch the file from the location
		
				FileInputStream fis = 
						new FileInputStream("C:\\Users\\HP\\eclipse-workspace\\APIFramework-Excel_DataDriven\\src\\test\\java\\data\\Data.xlsx");
				ArrayList <String> a = new ArrayList <String>();
				
				//Then create a workbook instance that refers to .xlsx file
				
				XSSFWorkbook workbook = new XSSFWorkbook(fis);
				
				//Now we need to get the number of sheets in the workbook
				int sheets = workbook.getNumberOfSheets();
				
				//Now we need to loop through the sheets and find the sheet with name "testdata"
				for(int i=0; i<sheets; i++) 
				{
					//We can get the name of the sheet using getSheetName method
					if(workbook.getSheetName(i).equalsIgnoreCase(SheetName)) 
					{
						
						//If the sheet name is "testdata", we can get the sheet using getSheetAt method
						XSSFSheet sheet = workbook.getSheetAt(i);
						
						//Now we can iterate through the rows of the sheet
						Iterator<Row> rows = sheet.iterator();
						
						//We can get the first row of the sheet using next method
						Row firstRow = rows.next();
						
						//Now we can iterate through the cells of the first row
						Iterator<Cell> cells = firstRow.cellIterator();
						int k=0;
						int coloumn=0; 
						while(cells.hasNext()) 
						{
							Cell cell = cells.next();
							
							//We can get the cell value using getStringCellValue method
							if(cell.getStringCellValue().equalsIgnoreCase("TestcaseName")) 
							{
								coloumn = k;
								
							}
							
							k++;
						}
						
						//System.out.println(coloumn);
						
						//once coloumn is identified, we can iterate through the rows of the sheet
						
						while(rows.hasNext()) 
						{
							
							Row r = rows.next();
							
							if(r.getCell(coloumn).getStringCellValue().equalsIgnoreCase(Testcase)) 
							{
								Iterator <Cell> cv =r.cellIterator();
								while(cv.hasNext()) 
								{
									
									Cell c = cv.next();
									
									if(c.getCellType()==CellType.STRING) {
									
									//System.out.println(cv.next().getStringCellValue());
									a.add(c.getStringCellValue());
									}
									else 
									{
										
										//NumberToTextConverter.toText(c.getNumericCellValue()) ;
										a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
									}
								}
							}
							
						}
					
					
					}
					
				}
				return a;
	}
	
	public static void main(String[] args) throws IOException {
		
		
		
		

	}

}
