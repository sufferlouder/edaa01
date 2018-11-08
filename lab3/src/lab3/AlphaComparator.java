package lab3;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

public class AlphaComparator implements Comparator<Map.Entry<String, Integer>> {

	@Override
	public int compare(Entry<String, Integer> arg0, Entry<String, Integer> arg1) {
		// TODO Auto-generated method stub
		return arg0.getKey().compareTo(arg1.getKey());
	}

}
