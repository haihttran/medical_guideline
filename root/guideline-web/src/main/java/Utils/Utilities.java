package Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

public class Utilities {
	
	public static final String PREFIX = "rules";
    public static final String SUFFIX = ".yml";
	
	public static HashMap<String,Boolean> parseSymptoms(JSONObject o){
		HashMap<String,Boolean> m = new HashMap<String, Boolean>();
        JSONObject symptoms = o.getJSONObject("symptoms");        
        for (String key: symptoms.keySet()){
            m.put(key.toString(), symptoms.getBoolean(key));
        }
        return m;
	}
	
	public static HashMap<String, Double> parseHealthData(JSONObject o){
		HashMap<String,Double> m = new HashMap<String, Double>();
        JSONObject symptoms = o.getJSONObject("health_data");        
        for (String key: symptoms.keySet()){
            m.put(key.toString(), symptoms.getDouble(key));
        }
        return m;
	}
	
	public static String createHealthDataList(HashMap<String, Double> m) {
		StringBuilder health_data = new StringBuilder();
    	health_data.append("[");
    	Iterator<?> it1 = m.entrySet().iterator();
    	String sep = "";
    	while(it1.hasNext()) {    		 		
    		Map.Entry pair = (Map.Entry) it1.next();
    		health_data.append(sep);
			health_data.append(pair.getKey() + " : " + pair.getValue());
    		sep = ", ";
    	}
    	health_data.append("]");
    	return health_data.toString();
	}
	
	public static String createSymptomList(HashMap<String, Boolean> m) {
		StringBuilder symptoms = new StringBuilder();
    	symptoms.append("[");
    	Iterator<?> it2 = m.entrySet().iterator();
    	String sep2 = "";
    	while(it2.hasNext()) {    		 		
    		Map.Entry pair = (Map.Entry) it2.next();
    		if(Boolean.valueOf(pair.getValue().toString()) == true) {
    			symptoms.append(sep2);
    			symptoms.append(pair.getKey());
    			sep2 = ", ";
    		}    		
    	}
    	symptoms.append("]");
    	return symptoms.toString();
	}
	
	public static File stream2file (InputStream in) throws IOException {
        final File tempFile = File.createTempFile(PREFIX, SUFFIX);
        tempFile.deleteOnExit();
        FileOutputStream out = new FileOutputStream(tempFile);
        IOUtils.copy(in, out);        
        return tempFile;
    }
}
