package com.generic;

public class UnitTest {
	
	static int i=1;
	
	public UnitTest() {
		
		i++;
		System.out.println(i);		
	}
	
	
	public static void main(String[] args) {
		
		UnitTest m = new UnitTest();
		UnitTest m1 = new UnitTest();
		UnitTest m2 = new UnitTest();
		
	}

}
