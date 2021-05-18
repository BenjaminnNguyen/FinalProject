package converter.Excel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import converter.*;
import model.TestCase;
import model.TestDetail;
import model.TestObject;
import model.TestParam;
import common.*;

public class ReadExcel {

	public String path = "";
	String sheetTestcaseList = "ListTestcase";
	String sheetDetail = "Detail";
	String sheetObject = "Object";
	String sheetParam = "Param";
	ExcelUtils eu = new ExcelUtils(path);
	
	public ReadExcel(String filepath) {
		this.path =filepath;
		eu = new ExcelUtils(filepath);
		
	}

	
	public List<TestCase> readTestCase() throws Exception {
		List<TestCase> lst = new ArrayList();
		try {

			eu.setExcelFile(path, sheetTestcaseList);
			for (int i = 1; i < eu.getRowCount(sheetTestcaseList); i++) {
				if (eu.getCellValue(i, 1).toUpperCase().trim().equals("YES")) {
					lst.add(new TestCase(eu.getCellValue(i, 0), eu.getCellValue(i, 1)));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lst;
	}
	public List<TestObject> readTestObject() throws Exception {
		List<TestObject> lst = new ArrayList();
		try {
			eu.setExcelFile(path, sheetObject);
			for (int i = 1; i < eu.getRowCount(sheetObject); i++) {
				if(eu.getCellValue(i, 0).trim()!=""&&(eu.getCellValue(i, 1).trim()!=""||eu.getCellValue(i, 2).trim()!="")) {
					if (eu.getCellValue(i, 1).trim()!="") {
						lst.add(new TestObject(eu.getCellValue(i, 0), eu.getCellValue(i, 1), "id"));
					}else {
						lst.add(new TestObject(eu.getCellValue(i, 0), eu.getCellValue(i, 2), "xpath"));

					}
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lst;
	}
	public List<TestParam> readTestParam() throws Exception {
		List<TestParam> lst = new ArrayList();
		try {

			eu.setExcelFile(path, sheetParam);
			for (int i = 1; i < eu.getRowCount(sheetParam); i++) {
				if (eu.getCellValue(i, 0).trim()!=""&&eu.getCellValue(i, 1)!="") {
					lst.add(new TestParam(eu.getCellValue(i, 0), eu.getCellValue(i, 1)));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lst;
	}
///
	///
	/// if row(i)!="" -> getIndex ->read from index to nextIndex;
	public List<TestDetail> readDetail() throws Exception {
		List<TestCase> lstTC = new ArrayList();
		List<TestDetail> lst = new ArrayList();
		eu.setExcelFile(path, sheetDetail);
		int flag = 0;
		lstTC = readTestCase();
		int index = 1;
		while (index < eu.getRowCount(sheetDetail)) {
			if (eu.getCellValue(index, 2).trim() != null) {
				if (eu.getCellValue(index, 0).trim() != "") {
					// System.out.println(eu.getCellValue(index, 0));
					flag = 0;
					for (TestCase a : lstTC) {
						if (a.name.equals(eu.getCellValue(index, 0))||eu.getCellValue(index, 0).toUpperCase().equals("INIT")||eu.getCellValue(index, 0).toUpperCase().equals("AFTER")) {
							flag = 1;
							break;
						}
						// flag=0;
					}
					if (flag == 1) {
						lst.add(new TestDetail(eu.getCellValue(index, 0), eu.getCellValue(index, 2),
								eu.getCellValue(index, 3), eu.getCellValue(index, 4), eu.getCellValue(index, 5)));
					}
				} else {
					if (flag == 1) {
						lst.add(new TestDetail(eu.getCellValue(index, 0), eu.getCellValue(index, 2),
								eu.getCellValue(index, 3), eu.getCellValue(index, 4), eu.getCellValue(index, 5)));
					}
				}

			}
			index++;
		}
		return lst;
	}
	public String fillObject(String object) throws Exception {
		List<TestObject> lst = readTestObject();
		String oj="";
		for(TestObject to:lst) {
			if(to.getName()==object) {
				oj=to.getValue();
			}else oj=object;
		}
		return oj;
	}
	public String fillParam(String param) throws Exception {
		List<TestParam> lst = readTestParam();
		String oj="";
		for(TestParam to:lst) {
			if(to.getName()==param) {
				oj=to.getValue();
			}else oj=param;
		}
		return oj;
	}
}
