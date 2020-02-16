package com.qa.corejava;

import java.util.HashMap;
import java.util.Map;

class Employee{
	
	
	String s;
	public Employee(String i) {
		this.s=i;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((s == null) ? 0 : s.hashCode());
		System.out.println(result);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (s == null) {
			if (other.s != null)
				return false;
		} else if (!s.equals(other.s))
			return false;
		return true;
	}
	
	
	

}

public class Demo{
	
	
	public static void main(String[] args) {
		Employee emp=new Employee("One");
		Employee emp1=new Employee("Two");
		
		Map<Employee, String> map=new HashMap();
 		map.put(emp, "Two");
		map.put(emp1,"One");
		System.out.println(map);
	}
}