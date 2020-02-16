package com.ProjectName1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


/**
 * Unit test for simple App.
 */

// Factorial number and Fibonacci Series
public class AppTest2 {

	public static void main(String args[]) {

		
		test3();
//		lenghtWithoutLenght();
		
		
//		
//		int n1=0,n2=1;
//	    System.out.print(n1+","+n2+",");
//	    for(int i=0;i<20;i++) {
//	    	
//	    	int n3=n1+n2;
//	    	n1=n2;
//	    	n2=n3;
//	    	System.out.print(n3+",");
//	    }
//	    System.out.println("");
//	    factorial(6);
	}
	
	public static void factorial(int num) {
		int fact=1;
		for(int i=0;i<num;i++) {
			 fact=fact*(num-i);
		}
		System.out.println("The factorial of number is=>"+fact);
	} 
	
	
	public void revereseNumber(int num) {
		int revnum=0;
		while(num!=0) {
		int rem=num%10;
		revnum=revnum*10+rem;
			num=num/10;
		}
		
		System.out.println("The reverese number is=>"+revnum);
		
	}
	
	int a[] = {1,4,2,4};
	
	public void test1() {
		Collections.reverseOrder();
	}
	
	
	
//		   public static void lenghtWithoutLenght() {
		      String str = "sampleString";
//		      int i = 0;
//		      for(char c: str.toCharArray()) {
//		         i++;
//		      }
//		      System.out.println("Length of the given string ::"+i);
//		      
//		      int cnt=0;
//		      for(int k=0;;k++) {
//		    	  System.out.println("within for");
//		      try {
//		    	  System.out.println("within try");
//		    	  while(true) {
//		    		 System.out.println("within while");
//					 str.charAt(k) ;
//					 System.out.println(str.charAt(k));
//					 cnt++;
//					 k++;
//		    	  }
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				System.out.println("The count is==>"+cnt);
//				e.printStackTrace();
//				return;
//			}
//		      }
//		   }
	
		      
		      public static void test3() {
		    	  String a="abcd123";
		    	  String b=a.replaceAll("[0-9]", "");
		    	  System.out.println("B is="+b);
		    	  System.out.println("A is="+a);
		      }
 }  
