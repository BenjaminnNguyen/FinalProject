package converter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import model.TestDetail;
import model.TestObject;
import model.TestParam;

public class writer extends readExcel {
	public writer(String filepath) {
		super(filepath);
		// TODO Auto-generated constructor stub
	}

	// readExcel re = new readExcel();

	public String TestCase() throws Exception {
		String name = "";
		String body = "";
		String sample = "";

		List<TestDetail> lst = new ArrayList();
		lst = readDetail();
		List<TestParam> lstPr = new ArrayList();
		lstPr = readTestParam();
		List<TestObject> lstOj = new ArrayList();
		lstOj = readTestObject();
		// body=convert(lst, lstOj, lstPr, "acb");
		for (TestDetail a : lst) {
			if (a.getTestCase() != "" && a.getTestCase() != null) {
				name = a.getTestCase();
				if (name.toUpperCase().equals("INIT")) {
					sample = sample + "\t@BeforeMethod \n" + "\tpublic void beforeMethod() { ";
				}
				if (a.getTestCase().toUpperCase().equals("AFTER")) {
					sample = sample + "}" + "\n\t@AfterMethod" + "\n\tpublic void afterMethod() {";
				}
				if (!a.getTestCase().toUpperCase().equals("INIT") && !a.getTestCase().toUpperCase().equals("AFTER")) {
					sample = sample + "}" + "\n\t@Test" + "\n\tpublic void " + name + "() {";
				}
			}
			if (a.getScript().trim() != "" && a.getScript().trim() != null) {
				if (a.getOutput().trim() != null && a.getOutput().trim() != "" && !a.getOutput().trim().equals("")) {
					sample = sample + "\n\t\t" + "String " + a.getOutput() + "=" + a.getScript() + "(";
					if (a.getObject().trim() != "" && a.getObject().trim() != null && a.getInput().trim() != "") {
						sample = sample + getObject(lstOj, a.getObject()) + "," + getInput(lstPr, a.getInput()) + ");";
					}
					if (a.getObject().trim() != "" && a.getObject().trim() != null
							&& (a.getInput().trim() == "" || a.getInput().trim() == null)) {
						sample = sample + getObject(lstOj, a.getObject()) + ");";
					}
					if (a.getObject().trim().equals("") && a.getInput().trim().equals("")) {
						sample = sample + ");";
					}
				} else {
					sample = sample + "\n\t\t" + a.getScript() + "(";
					if (a.getObject() != "" && a.getObject() != null) {
						if (a.getInput() != "" && a.getInput() != null) {
							sample = sample + getObject(lstOj, a.getObject()) + "," + getInput(lstPr, a.getInput())
									+ ");";

						} else {
							sample = sample + getObject(lstOj, a.getObject()) + ");";
						}
					} else if (a.getInput() != "" && a.getInput() != null) {
						sample = sample + getInput(lstPr, a.getInput()) + ");";
					}
					if ((a.getObject().trim() == "" || a.getObject().trim() == null)
							&& (a.getInput().trim() == "" || a.getInput().trim() == null)) {
						sample = sample + ");";
					}
				}
			}

		}

		return sample = sample + "\n}";
	}

	public String importHeader() {
		String sLine = "package excute;\n" + "import common.CommonBase;\n" + "import org.openqa.selenium.By;\n"
				+ "import org.openqa.selenium.WebElement;\n" + "import org.testng.annotations.*;\n" + " \n";
		return sLine;
	}

	public String className(String name, String body) {
		String sLine = "\npublic final class " + name + " extends CommonBase { \n" + body + "\n}";
		return sLine;

	}

	public String getObject(List<TestObject> lst, String object) throws Exception {
		List<TestObject> lstOj = new ArrayList();
		lstOj = readTestObject();
		for (TestObject obj : lstOj) {
			if (obj.getName().equals(object)) {
				if (obj.getType().equals("id")) {
					return "By.id(\"" + obj.getValue() + "\")";
				} else {
					return "By.xpath(\"" + obj.getValue() + "\")";

				}
			}
		}

		return "";
	}

	public String getInput(List<TestParam> lst, String input) throws Exception {
		List<TestParam> lstPr = new ArrayList();
		lstPr = readTestParam();
		String[] inputs = input.split(";");
		String output = "";
		for (String i : inputs) {
			if (i != inputs[inputs.length - 1]) {
				if (i.contains("$")) {
					for (TestParam pr : lstPr) {
						if (pr.getName().equals(i.replace("$", ""))) {
							output = output + "\"" + pr.getValue() + "\",";
						}
					}
				} else {
					output = output + i + ",";
				}
			} else {
				if (i.contains("$")) {
					for (TestParam pr : lstPr) {
						if (pr.getName().equals(i.replace("$", ""))) {
							output = output + "\"" + pr.getValue() + "\"";
						}
					}
				} else {
					output = output + i;
				}
			}

		}
		// output= output.substring(0, output.length()-1);
		return output;
	}

	public void writing(String name) throws Exception {
		String sLine = importHeader() + className(name, TestCase());
		try {
			// Whatever the file path is.
			File statText = new File(System.getProperty("user.dir") + "/src/test/java/excute/" + name + ".java");
			FileOutputStream is = new FileOutputStream(statText);
			OutputStreamWriter osw = new OutputStreamWriter(is);
			Writer w = new BufferedWriter(osw);
			w.write(sLine);
			w.close();
		} catch (IOException e) {
			e.getMessage();
		}
	}
}
