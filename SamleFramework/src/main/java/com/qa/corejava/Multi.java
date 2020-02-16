package com.qa.corejava;

import org.apache.poi.ss.formula.functions.T;

class Multi extends Thread{  
public void run(){  
System.out.println("thread is running...");  
}  
public static void main(String args[]){  
Multi t1=new Multi();  
t1.run();
t1.start();  
Object obj;

 }  
}  