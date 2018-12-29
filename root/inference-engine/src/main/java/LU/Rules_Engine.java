package LU;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.mvel.MVELRuleFactory;

public class Rules_Engine {
	
	private static final String PREFIX = "rules";
    private static final String SUFFIX = ".yml";
	
	public void activateRulesEngine(HashMap<String, Double> h, HashMap<String, Boolean> s) throws IOException {		
		RulesEngine rulesEngine = new DefaultRulesEngine();	        
		
		rulesEngine.fire(createRules(getResourcesFile()),createFacts(h, s)); 
	}
	
	public Facts createFacts(HashMap<String, Double> h, HashMap<String, Boolean> s) {		
		Facts facts = new Facts();
		for(Map.Entry<String, Double> entry : h.entrySet()) {
		    String fact = entry.getKey();
		    Double value = entry.getValue();
		    facts.put(fact, value);
		}		
		for(Map.Entry<String, Boolean> entry : s.entrySet()) {
		    String fact = entry.getKey();
		    Boolean value = entry.getValue();
		    facts.put(fact, value);
		}
		return facts;
	}
	
	public Rules createRules(String fileName) throws IOException {		
	      Reader reader = new FileReader(fileName);
	      Rules rules = MVELRuleFactory.createRulesFrom(reader);
	      reader.close();
	      return rules;	      
	}
	
	public Rules createRules(File file) throws IOException {		
	      Reader reader = new FileReader(file);
	      Rules rules = MVELRuleFactory.createRulesFrom(reader);
	      reader.close();
	      return rules;	      
	}
	
	private File getResourcesFile() throws IOException {
		InputStream in = getClass().getResourceAsStream("/diseases.yml"); 
		return stream2file(in);
	}
	
	private File stream2file(InputStream in) throws IOException {
        final File tempFile = File.createTempFile(PREFIX, SUFFIX);
        tempFile.deleteOnExit();
        FileOutputStream out = new FileOutputStream(tempFile);
        IOUtils.copy(in, out);        
        return tempFile;
    }
}
