import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import org.jeasy.rules.api.RulesEngine;

import LU.Rules_Engine;

public class TestRulesEngine {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		HashMap<String, Boolean> s = new HashMap<String, Boolean>();
		s.put("diarrhea", true);
    	s.put("high_temperature", true);
    	s.put("nausea", true);
    	s.put("vomitting", true);
    	s.put("elavated_heart_rate", true);
    	s.put("loss_of_skin_tugor", true);
    	s.put("dry_mucous_membranes", true);
    	s.put("hypotension", true);
    	s.put("thirst", true);
    	s.put("abdominal_cramps", true);
    	s.put("bloating", true);
    	s.put("flatulence", true);
    	s.put("fatigue", true);
    	s.put("anorexia", true);
    	s.put("nausea", true);
    	
    	HashMap<String, Double> h = new HashMap<String, Double>();
		h.put("body_temperature", 38.1);
		h.put("height", 174.2);
		h.put("systolic_blood_pressure", 125.2);
		h.put("diastolic", 81.2);
		h.put("blood_sugar_level", 102.0);
    	
    	ByteArrayOutputStream consoleStorage = new ByteArrayOutputStream();
    	PrintStream stdout = System.out;
    	PrintStream newConsole = new PrintStream(consoleStorage);
    	System.setOut(newConsole);	    	
    	Rules_Engine r = new Rules_Engine();
		r.activateRulesEngine(h, s);
		String str = consoleStorage.toString();
		System.out.flush();		
		System.setOut(stdout);
		System.out.println(str);	
	}	
}
