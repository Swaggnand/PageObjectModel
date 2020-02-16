package com.qa.corejava;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class DuplicateNumbers {

	public static void main(String[] args) {

		int a[] = {2, 3, 4, 9, 8, 7, 2, 3};

/*		for (int i = 0; i < a.length; i++) {

			for (int j = 1; j < a.length; j++) {
				if (a[i] == a[j] && i!=j) {

					System.out.println("Duplicate Numbers are : " + a[j]);
				}

			}

		}*/
		
		
/*		HashSet<Integer> hs = new HashSet<Integer>();
		
		for (int i=0; i<a.length; i++) {
			
			if(hs.add(a[i]) == false) {
				
				System.out.println("Duplicate Numbers are : " + a[i]);
			}
		}*/
		
		
		HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
		
		for (int i=0; i< a.length; i++) {
			
			if(hm.containsKey(a[i])) {
				hm.put(a[i], hm.get(a[i])+ 1);
			}
			else {
				
				hm.put(a[i], 1);
			}
		}
		
		Set<Entry<Integer,Integer>> entry = hm.entrySet();
		
		for (Map.Entry<Integer,Integer> m : entry) {		
			if(m.getValue() > 1) {
				
				System.out.println("Duplicate Numbers is " + m.getKey());
			}
		}
		
	
		
		
		

	}

}
