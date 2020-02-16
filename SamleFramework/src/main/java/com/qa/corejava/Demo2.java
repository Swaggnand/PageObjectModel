package com.qa.corejava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Demo2 {

	
public static boolean solution(long[] numbers, long target) {
        
        for (int i=0;i<numbers.length;i++){
            for(int j=i+1;j<numbers.length;j++){
                if((numbers[i]+numbers[j])==target){
                    return true;
                }
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
    
        System.out.println(solution(new long[]{7,2,2,2},7));
        
	}

//	private void permute(String str, int l, int r) {
//		if (l == r)
//			System.out.println(str);
//		else {
//			for (int i = l; i <= r; i++) {
//				str = swap(str, l, i);
//				permute(str, l + 1, r);
//				str = swap(str, l, i);
//			}
//		}
//	}
//
//	public String swap(String a, int i, int j) 
//		    { 
//		        char temp; 
//		        char[] charArray = a.toCharArray(); 
//		        temp = charArray[i] ; 
//		        charArray[i] = charArray[j]; 
//		        charArray[j] = temp; 
//		        return String.valueOf(charArray); 
//		    }
//	

//		int num=2110;
//		boolean flag = false;
//		
//		for(int i=2;i<20/2;i++) {
//			if(num%i==0) {
//				flag=true;
//				break;
//			}
//		}
//		
//		if(flag) {
//			System.out.println("Given number is not prime");
//		}
//		else {
//			System.out.println("Given number is prime");
//		}

//		int a[]= {0,6,1,2,2,3,4,5,5,6};
//		int temp;
//		for(int i=0;i<a.length;i++) {
//			for(int j=i+1;j<a.length;j++) {
//				if(a[i]<a[j]) {
//				temp=a[j];	
//				a[j]=a[i];
//				a[i]=temp;
//				}
//			}
//		}
//		System.out.println(Arrays.toString(a));
//		int max=a[0];
//		System.out.println(max);
//		for(int i=1;i<a.length;i++) {
//			if(a[i]<max) {
//				max=a[i];
//				break;
//			}
//		}
//		System.out.println(max);

//		Arrays.sort(a);
//		int max=a[a.length-1];
//		System.out.println(max);
//		for(int i=a.length-2;i>=0;i--) {
//			if(max>a[i]) {max=a[i];break;}
//		}
//		System.out.println(max);
}
