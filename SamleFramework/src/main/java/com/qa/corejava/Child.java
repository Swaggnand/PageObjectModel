package com.qa.corejava;

public class Child extends Parent{
	
	
	int k = 10;
	
	public void getRunRate() {
		
		System.out.println("Child Run Rate");
		
	}

	public static void main(String[] args) {
		
		Parent p = new Child();
		System.out.println(p.k);
		p.getRunRate();
		
	}

}
