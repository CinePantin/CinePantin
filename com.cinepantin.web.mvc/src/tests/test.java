package tests;

import com.opensymphony.xwork2.ActionSupport;

public class test 
			extends ActionSupport
{
	private static final long serialVersionUID = -3427816852561078662L;


	public String model;
	
	public String test1() {
		this.model = "test1 model string initialized";
		return SUCCESS;
	}
	
}
