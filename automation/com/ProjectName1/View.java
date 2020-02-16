package com.ProjectName1;

import com.generic.Pojo;

public class View {
	
	private Pojo objPojo;
	private Page objPage;
	
	public View(Pojo pojo){
		this.objPojo=pojo;
		objPage = new Page(objPojo);
	}
	
	
	public void displayMessage() {
		System.out.println("Testinggg");
		objPojo.getObjUtilities().dpString("Name");
		System.out.println(objPojo.getObjUtilities().dpString("Name"));
	}

}
