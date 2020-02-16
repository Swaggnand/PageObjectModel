package com.test;

import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.page.PracticePage;

import generic.BaseTest;
import generic.Pojo;

public class PracticeTest extends BaseTest{
	
	private Pojo objPojo;
	private PracticePage objPracticePage;
	private Properties objConfig;
	
	public PracticeTest(Pojo objPojo) {
		this.objPojo = objPojo;
		objPracticePage = new PracticePage(objPojo);
	}
	
	@BeforeClass
	public void initializeWebEnviornment1() {
		this.initializeWebEnvironment();
	}
	
	@AfterClass
	public void teardownEnviornment() {
		this.teardownenviornment();
		objPracticePage = null;
	}
	
	
	@Test
	public void RMID_001() {
		System.out.println("test");
		objPracticePage.setPracticePageCountries();
	}
	
}
	

			
	
	

