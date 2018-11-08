package textproc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

public class MultiWordCounter implements TextProcessor {
	
	Map<String,Integer> m = new TreeMap<String,Integer>(); //HashMap
	
	public MultiWordCounter(String[] key) {
		
		Vector<String> v = new Vector<String>(Arrays.asList(key));
		for(int i = 0; i < v.size(); i++) {
			m.put(v.get(i), 0);		//initierar genom att ge varje nyckel värdet 0
		}
	}
	@Override
	public void process(String w) {
		// TODO Auto-generated method stub
		for(String key : m.keySet()) {
			if(w.equals(key)) {
				m.replace(key, m.get(key) + 1);
			}
		}
	}

	@Override
	public void report() {
		// TODO Auto-generated method stub
		for(String key : m.keySet()) {
			System.out.println(key + " :" + m.get(key));
		}

	}

}
