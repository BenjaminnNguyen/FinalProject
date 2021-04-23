package converter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class writer {
	public String beforeMethod(String before)
     {
         String sample = "\t @BeforeMethod \n" +
             "\t public void beforeMethod() { \n \t\t" +
             before + ";" + "\n\t } \n";
         return sample;
     }
	 public String afterMethod(String after)
     {
         String sample = "\t @AfterMethod \n" +
             "\t public void afterMethod() { \n \t\t" +
             after + ";" + "\n\t } \n";
         return sample;
     }
	 public String test(String nameTC, String test )
     {
         String sample = "\t @Test \n" +
             "\t public void "+nameTC+"() { \n \t\t" +
             test + ";" + "\n\t } \n";
         return sample;
     }
	 public String importHeader()
     {
         String sLine = "package excute;\n"
         		+ "import common.CommonBase;\n" +
                 "import org.openqa.selenium.By;\n" +
                 "import org.openqa.selenium.WebElement;\n" +
                 "import org.testng.annotations.*;\n" +
                 " \n";
         return sLine;
     }
	 public String className(String name, String body)
     {
         String sLine = "\npublic final class "+name+" extends CommonBase { \n"+body+"\n}";
         return sLine;
     }
	 public void writing() {
         String sLine = importHeader() + className("Test",beforeMethod("init") + test("tc1", "Test") + afterMethod("quitDriver(driver)"));
	        try {
	            //Whatever the file path is.
	            File statText = new File("F:\\Test\\PROJECT\\src\\test\\java\\excute\\Test.java");
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
