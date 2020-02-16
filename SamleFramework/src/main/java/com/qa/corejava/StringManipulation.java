package com.qa.corejava;

public class StringManipulation {

	
	public static void main(String[] args) {
		
		String s = "Manipulation";
		
		String [] str = s.split("");
		
		for (int i=0; i < str.length; i++) {
			
			for(int j=i+1; j<str.length; j++) {
				
				if(str[i].equals(str[j]) && i!=j) {
					
					System.out.println("Duplicate Characters in String are :" + str[j]);
				}
				
			}
			
		}
		
		
	}
}
