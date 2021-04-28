package converter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//import config.Constants;


public class ExcelUtils {
	private static XSSFWorkbook workBook;
	private static XSSFSheet workSheet;
	private static XSSFCell cell;
//	private static XSSFRow row;
	
	private String filepath;
	
	public ExcelUtils(String path) {
		filepath=path;
	}
	
//1. WORKING WITH EXCEL FILE
	public static  void setExcelFile(String path, String SheetName) throws Exception{
		File file=new File(path);
		if(file.canRead()) {
		FileInputStream ExcelFile=new FileInputStream(file);
		workBook =new XSSFWorkbook(ExcelFile);
		workSheet=workBook.getSheet(SheetName);
		ExcelFile.close();
		}
	}
	
	public static XSSFSheet getSheet(String SheetName) {
		XSSFSheet sheet=workBook.getSheet(SheetName);
		if(!isSheet(SheetName)) {
			sheet=workBook.createSheet(SheetName);
		}
		return sheet;
	}
	
//	public  void open() throws IOException{
//		File file=new File(filepath);
//		if(file.canRead()) {
//			FileInputStream streamIn=new FileInputStream(file);
//			workBook=new XSSFWorkbook(streamIn);
//			streamIn.close();
//		}
//	}
	
	public void save()throws IOException{
		FileOutputStream streamOut=new FileOutputStream(filepath);
		workBook.write(streamOut);
		streamOut.flush();
		streamOut.close();
	}
	
	public static void saveAs(String path)throws IOException{
		FileOutputStream streamOut=new FileOutputStream(path);
		workBook.write(streamOut);
		streamOut.flush();
		streamOut.close();
	}
	
	public static boolean isSheet(String SheetName) {
		return workBook.getSheetIndex(SheetName)>=0;
	}
	
	public static void addSheet(String SheetName) {
		if(!isSheet(SheetName)) {
			workBook.createSheet(SheetName);
		}
	}
	
	public static void removeSheet(int SheetIndex) {
		workBook.removeSheetAt(SheetIndex);
	}
	
	public static void removeSheet(String SheetName) {
		int index=workBook.getSheetIndex(SheetName);
		removeSheet(index);
	}

//2. WORKING WITH COLUMN IN EXCEL
	public static void addColumn(String SheetName,String ColName) {
		XSSFSheet sheet=getSheet(SheetName);
		addColumn(sheet, ColName);
	}
	
	public static void addColumn(XSSFSheet sheet, String ColName) {
		XSSFCellStyle style=workBook.createCellStyle();
		style.setFillForegroundColor(HSSFColorPredefined.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		XSSFRow row=sheet.getRow(0);
		if(row==null)
			row=sheet.createRow(0);
		
		XSSFCell cell;
		if(row.getLastCellNum()==-1) 
			cell=row.createCell(0);
		else
			cell=row.createCell(row.getLastCellNum());
		cell.setCellValue(ColName);
		//cell.setCellStyle(style);
	}
	
	public static void removeColumn(String SheetName, int colNum) {
		XSSFSheet sheet=getSheet(SheetName);

		int rowCount=sheet.getLastRowNum()+1; 
		 
		XSSFRow row;
		for(int i=0;i<rowCount;i++) {
			row=sheet.getRow(i);
			if(row!=null) {
				XSSFCell cell =row.getCell(colNum);
				if(cell!=null) {
					row.removeCell(cell);
				}
			}
		}
	}
	
	public static int convertColNameToColNum(XSSFSheet sheet, String colName) {
		XSSFRow row=sheet.getRow(0);
		int cellRowNumber=row.getLastCellNum();
		int colNum=-1;
		
		for(int i=0;i<cellRowNumber;i++) {
			if(row.getCell(i).getStringCellValue().trim().equals(colName)) {
				colNum=i;
			}
		}
		return colNum;
	}
	
//3. WORKING WITH ROW IN EXCEL
	public static XSSFRow getRow(XSSFSheet sheet, int rowIndex) {
		XSSFRow row=sheet.getRow(rowIndex);
		if(row==null) {
			row=sheet.createRow(rowIndex);
		}
		return row;
	}
	
	// This method is to get the row count used of the excel sheet
	public static int getRowCount(String SheetName) {
		workSheet = workBook.getSheet(SheetName);
		int number = workSheet.getLastRowNum() + 1;
		return number;
	}

	// This method is to get the Row number of the test case
	// This methods takes three arguments(Test Case name , Column Number & Sheet
	// name)
	public static int getRowContains(String SheetName,String sTestCaseName, int colNum ) throws Exception {
		int i;
		workSheet = workBook.getSheet(SheetName);	
		int rowCount = ExcelUtils.getRowCount(SheetName);
		for (i = 0; i < rowCount; i++) {
			if (ExcelUtils.getCellValue(SheetName, i, colNum).equalsIgnoreCase(sTestCaseName)) {
				break;
			}
		}
		return i;
	}

	
	public static String getCellValue(int RowNum, int ColNum) throws Exception {
		try {
			cell = workSheet.getRow(RowNum).getCell(ColNum);
			String cellValue = cell.getStringCellValue();
			return cellValue;
		} catch (Exception e) {
			return "";
		}
	}
	
	public static String getCellValue(String sheetName, int rowIndex, int colIndex) {
		XSSFSheet sheet = getSheet(sheetName);
		XSSFRow row=getRow(sheet, rowIndex);
		XSSFCell cell=getCell(row, colIndex);
		return cell.getStringCellValue();
	} 
	
	

	public static String getCellValue(XSSFCell cell) {
		FormulaEvaluator evaluator = workBook.getCreationHelper().createFormulaEvaluator();
		DataFormatter df = new DataFormatter();
		
		if(cell!=null) {
			switch(evaluator.evaluateInCell(cell).getCellType()) {
			
			case STRING:
				return cell.getStringCellValue();
				
			case NUMERIC:
//				return String.valueOf(cell.getNumericCellValue());
				return df.formatCellValue(cell);
			
			case BOOLEAN:
				return String.valueOf(cell.getBooleanCellValue());
			
			case BLANK:
				return "";
			
			case ERROR:
				return cell.getErrorCellString();
			
			case FORMULA:
				return cell.getCellFormula();
			default:
				break;
			}
		}
		return null;
	}
	
	public static XSSFCell getCell(XSSFRow row, int colIndex) {
		XSSFCell cell=row.getCell(colIndex-1);
		if(cell==null) {
			cell=row.createCell(colIndex-1);
		}
		return cell;
	}
	
	public static void setCell(XSSFSheet sheet, int rowIndex, int colIndex, String value) {
		XSSFRow row=getRow(sheet, rowIndex);
		XSSFCell cell=getCell(row,colIndex);
		cell.setCellValue(value);
	} 
	public static void setCell(String sheetName,int rowIndex,int colIndex, String value) {
		XSSFSheet sheet=getSheet(sheetName);
		setCell(sheet, rowIndex, colIndex, value);
	}
	
	public static void setCell(String sheetName,String colName,int rowIndex, String value) {
		XSSFSheet sheet=getSheet(sheetName);
		int colIndex=convertColNameToColNum(sheet,colName);
		setCell(sheet, rowIndex, colIndex, value);
	}
	
//	public double getCellNumber(XSSFSheet sheet, int rowIndex, int colIndex) {
//		XSSFRow row=getRow(sheet, rowIndex);
//		XSSFCell cell=getCell(row, colIndex);
//		
//		FormulaEvaluator evaluator=_workBook.getCreationHelper().createFormulaEvaluator();
//		
//		if(cell!=null) {
//			switch(evaluator.evaluateInCell(cell).getCellType()) {
//			case Cell.CELL_TYPE_STRING:
//			}
//		}
//		return cell.getNumericCellValue();
//	}
	
	public static List<List<XSSFCell>> createList(String sheetName) {
        // Create an ArrayList to store the data read from excel sheet.
        List<List<XSSFCell>> myList = new ArrayList<List<XSSFCell>>();
        
        // When we have a sheet object in hand we can iterator on
        // each sheet's rows and on each row's cells. We store the
        // data read on an ArrayList so that we can printed the
        // content of the excel to the console.
        try {
        	XSSFSheet sheet = getSheet(sheetName);
        	Iterator<Row> rows = sheet.rowIterator();
        	while (rows.hasNext()) {
        		XSSFRow row = (XSSFRow) rows.next();
        		Iterator<Cell> cells = row.cellIterator();

        		List<XSSFCell> data = new ArrayList<XSSFCell>();
        		while (cells.hasNext()) {
        			XSSFCell cell = (XSSFCell) cells.next();
        			data.add(cell);
        		}
        		myList.add(data);
        	}
        } catch (Exception e) {
        	e.getMessage();
        }
        return myList;
	}
	
//	public static void setCellData(String Result, int RowNum, int ColNum, String SheetName) throws Exception {
//		try {
//			workSheet = workBook.getSheet(SheetName);
//			row = workSheet.getRow(RowNum);
//			cell = row.getCell(ColNum);
//			if (cell == null) {
//				cell = row.createCell(ColNum);
//				cell.setCellValue(Result);
//			} else {
//				cell.setCellValue(Result);
//			}
//			// Constant variables Test Data path and Test Data file name
//			FileOutputStream fileOut = new FileOutputStream(Constants.Path_TestData);
//			workBook.write(fileOut);
//			// fileOut.flush();
//			fileOut.close();
//			workBook = new XSSFWorkbook(new FileInputStream(Constants.Path_TestData));
//		} catch (Exception e) {
//			
//			//ExecuteTestScript_BMI.bResult = false;
//		}
//	} 
//	

}

