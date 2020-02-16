package generic.automation;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class DataPool3 {
	private FileInputStream excelFile;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private Row headerRow;
	private Row testDataRow;
	private XSSFCell cell;

	@Test
	 public Hashtable<Object, Object> loadTestData() {
		 Hashtable<String, String> objDataProvider=new Hashtable<String, String>();
		 objDataProvider.put("A", "Ja");
		 objDataProvider.put("B", "Bedge");

		 Hashtable<Object, Object> objTCIDMapping=new Hashtable<Object, Object>();
		 objTCIDMapping.put("TC001", objDataProvider);
		 return objTCIDMapping;
		 
	 }
	
	
}