package Models;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import LU.Rules_Engine;

public class Result {
	public static String getDiagnosis(HashMap<String, Double> h, HashMap<String, Boolean> s) throws IOException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    // IMPORTANT: Save the old System.out!
	    PrintStream old = System.out;
	    // Tell Java to use your special stream
	    System.setOut(ps);
	    
	    Rules_Engine r = new Rules_Engine();
		r.activateRulesEngine(h, s);
		
	    List<String> str = findText(baos.toString());	    
	    System.out.flush();
	    System.setOut(old);
		
		StringBuilder output = new StringBuilder();
		output.append('[');
		ListIterator<String> itr = str.listIterator();
		while(itr.hasNext()) {
			output.append(itr.next().toString());
			if(itr.hasNext())
				output.append(", ");
		}
		//System.out.println(output);
		output.append(']');
		return output.toString();
	}
		
	private static List<String> findText(String txt) {
		
		List<String> result = new ArrayList<String>();
		Pattern pattern = Pattern.compile("(\\<\\<)(.*?)(\\>\\>)");
        Matcher matcher = pattern.matcher(txt);
        while(matcher.find())
        {
        	result.add(matcher.group(2));
        }
        return result;
	}
}
