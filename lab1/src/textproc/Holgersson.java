package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		
		long t0 = System.nanoTime();
		List<TextProcessor> tpList = new ArrayList<TextProcessor>();
		
		TextProcessor p = new SingleWordCounter("nils");
		TextProcessor p1 = new SingleWordCounter("norge");
		tpList.add(p);
		tpList.add(p1);
		
		TextProcessor r = new MultiWordCounter(REGIONS);
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		Scanner scan = new Scanner(new File("undantagsord.txt"));
		Set<String> stopwords = new HashSet<String>();
		
		while(scan.hasNext()) {
			String word = scan.next().toLowerCase();
			stopwords.add(word);
		}
		
		scan.close();
		TextProcessor p2 = new GeneralWordCounter(stopwords);
		tpList.add(p2);
		
		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			tpList.get(0).process(word);
			tpList.get(1).process(word);
			
			tpList.get(2).process(word);
			
			r.process(word);
			//p.process(word);
		}

		s.close();
		tpList.get(0).report();
		tpList.get(1).report();
		r.report();
		tpList.get(2).report();
		//p.report();
		long t1 = System.nanoTime();
		System.out.println("tid: " + (t1-t0)/1000000.0 + " ms");
	}
}