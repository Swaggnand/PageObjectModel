package prac;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class hashMap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		HashMap<Integer,String> hm = new HashMap<Integer,String>();
		hm.put(0, "Swanand");
		hm.put(1, "Mau");
		Set sn = hm.entrySet();
		Iterator it = sn.iterator();
		while(it.hasNext()) {
			
			Map.Entry mp = (Map.Entry)it.next();
			System.out.println(mp.getKey());
			System.out.println(mp.getValue());
			if(mp.getValue().equals("Swanand")) {
				System.out.println("Found");
			}
		}

	}

}
