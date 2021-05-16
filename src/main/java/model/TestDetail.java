package model;

public class TestDetail
{
    private String testCase;
    private String script;
    private String object;
    private String input;
    private String output;
    
    public TestDetail(String testCase, String script, String object, String input, String output) {
    	this.testCase=testCase;
    	this.script=script;
    	this.object=object;
    	this.input=input;
    	this.output=output;
    	
    }
    
    public String getScript() {
        return this.script;
    }
    
    public TestDetail setScript(final String script) {
        this.script = script;
        return this;
    }
    
    public String getObject() {
        return this.object;
    }
    
    public TestDetail setObject(final String object) {
        this.object = object;
        return this;
    }
    
    public String getInput() {
        return this.input;
    }
    
    public TestDetail setInput(final String input) {
        this.input = input;
        return this;
    }
    
    public String getTestCase() {
        return this.testCase;
    }
    
    public TestDetail setTestCase(final String testCase) {
        this.testCase = testCase;
        return this;
    }
    
    public String getOutput() {
        return this.output;
    }
    
    public TestDetail setOutput(final String output) {
        this.output = output;
        return this;
    }
   
}