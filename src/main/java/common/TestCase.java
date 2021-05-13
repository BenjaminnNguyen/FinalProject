package common;

public class TestCase {
	public String name;
	public String runMode;
	public TestCase(String name, String runMode){
		this.name= name;
		this.runMode=runMode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRunMode() {
		return runMode;
	}
	public void setRunMode(String runMode) {
		this.runMode = runMode;
	}
	
}
