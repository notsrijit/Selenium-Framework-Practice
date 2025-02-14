package resources;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
public String[][] excelRead() throws IOException {
		
		FileInputStream fis = new FileInputStream("D:\\CodeStorage\\eclipse-workspace-practice\\Framework_Practice\\FrameworkSheet.xlsx");
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Data");
		int rowCount = sheet.getPhysicalNumberOfRows();
		XSSFRow row = sheet.getRow(0);
		int cellCount = row.getPhysicalNumberOfCells();
		
		//Creating 2D String array with size of table (Without headers and Sl.no)
		String[][] data = new String[rowCount-1][cellCount-1];
		
		//Putting table values into 2D String array
		String value=null;
		XSSFCell cell;
		for(int i=1; i<rowCount; i++) {
			for(int j=1; j<cellCount; j++) {
				cell = sheet.getRow(i).getCell(j);
				
				//Checking if cell content is numeric or String and type casting as required
				if(cell.getCellType() == CellType.STRING) {
					value = cell.getStringCellValue();
				}
				else if(cell.getCellType() == CellType.NUMERIC) {
					//Converting numeric cell value to text
					value = NumberToTextConverter.toText(cell.getNumericCellValue());
				}
				
				//Inserting found value into 2d String array
				data[i-1][j-1] = value;
			}
		}
		
		return data;
		
	}

}
