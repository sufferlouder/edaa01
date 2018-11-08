package textproc;
import java.util.*;
import java.util.Map.Entry;

public class WordCountComparator implements Comparator<Map.Entry<String, Integer>> {
	
	
	@Override
	public int compare(Entry<String, Integer> arg0, Entry<String, Integer> arg1) {
		
		if(arg1.getValue().compareTo(arg0.getValue()) == 0){
			return arg0.getKey().compareTo(arg1.getKey());
		}
		else {
			return arg1.getValue().compareTo(arg0.getValue());
		}
		
	}

}
