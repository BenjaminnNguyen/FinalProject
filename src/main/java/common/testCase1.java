package common;

public class testCase1 {
	public String name;
	public String runMode;
	public testCase1(String name, String runMode){
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
