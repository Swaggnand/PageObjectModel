package prac;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class hashset {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		HashSet<String> hs = new HashSet<String>();
		hs.add("Hello");
		hs.add("Bye");
		hs.add("Swanand");
		boolean flag = hs.remove("Swanand");
		System.out.println("HashSet is ----"+ hs);
		Iterator<String> it =hs.iterator();
		while(it.hasNext() && flag == true) {
			
			System.out.println(it.next());
		}
		
		System.out.println("==========================================");
		ArrayList<String> al = new ArrayList<String>();
		al.add("Apple");
		al.add(1, "Banana");
		System.out.println("ArrayList is ----" + al);
	}

}
