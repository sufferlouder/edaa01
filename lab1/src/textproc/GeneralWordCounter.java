package textproc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class GeneralWordCounter implements TextProcessor {
	
	Map<String,Integer> m = new TreeMap<String,Integer>(); //HashMap
	Set<String> set = new HashSet<String>();
	
	public GeneralWordCounter(Set<String> set) {
		this.set = set;
		
	}
	@Override
	public void process(String w) {
		if(set.contains(w)) { 				//Om w är ett undantagsord
			
		}
		else { 								//Om w inte är ett undantagsord
			if(m.containsKey(w)) {			//Om nyckeln w finns i hashmap
				m.replace(w, m.get(w) + 1);	//inkrementera värdet hos nyckeln w
			}
			else {							//Om nyckeln w inte finns
				m.put(w, 1);				//Lägger till nyckeln w och ger den värdet 1
			}
		}
	}

	@Override
	public void report() {
		Set<Map.Entry<String, Integer>> wordSet = m.entrySet();
		List<Map.Entry<String,Integer>> wordList = new ArrayList<>(wordSet);
		wordList.sort(new WordCountComparator());
		for(int i = 0; i<100/*wordList.size()*/; i++) {
			System.out.println(i+": " +wordList.get(i));
		}
	}
	public Set<Map.Entry<String, Integer>> getWords(){
		return m.entrySet();
	}

}
