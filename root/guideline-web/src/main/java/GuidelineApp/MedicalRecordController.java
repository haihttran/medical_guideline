package GuidelineApp;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
//import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import Models.MedicalRecord;
import Utils.MongoDBConnection;
import Utils.Utilities;
import Utils.MongoDBUtils;
import xyz.morphia.Datastore;
/*
 * The RESTFUL Http request mapping function
 * Receive Http requests and send Http responses
 */
import xyz.morphia.Morphia;
import xyz.morphia.query.Query;
@RestController
public class MedicalRecordController {
	//Create new medical record
		
    @RequestMapping(value="/diagnosis",
    		method=RequestMethod.POST,
    		consumes="application/json",
    		produces="application/json")
    
    //Get a list of relevant sicknesses based on input symptoms
    @ResponseBody
    public MedicalRecord diagnosis(
    		@RequestParam("pid") String pid,
    		@RequestParam("phId") String phid,
    		@RequestBody String txt) throws IOException {    	
    	JSONObject object = new JSONObject(txt);
    	HashMap<String, Boolean> s = new HashMap<String, Boolean>();
    	HashMap<String, Double> h = new HashMap<String, Double>();
    	s = Utilities.parseSymptoms(object);
    	h = Utilities.parseHealthData(object);
    	String id = String.valueOf(System.currentTimeMillis());
    	Date d = new Date();
    	MedicalRecord r = new MedicalRecord(id, pid, phid, d);    	
    	r.setResult(h, s);
    	r.setHealth_data(Utilities.createHealthDataList(h));
    	r.setSymptoms(Utilities.createSymptomList(s));
    	Datastore ds = MongoDBUtils.storeConstruction();
    	ds.save(r);
		return r;		
    }
    
    //Get all historical medical records of a patient
    @RequestMapping(value="/medical_records",
    		method=RequestMethod.GET)
    @ResponseBody
    public ArrayList<MedicalRecord> getMedicalRecord(
    	@RequestParam("pid") String pid) throws IOException {    	    	
    	System.out.println(MongoDBUtils.getListOfMedicalRecord(pid));
		return MongoDBUtils.getListOfMedicalRecord(pid);		
    }
    
    //Followup patient's health data measurements
    @RequestMapping(value="/followup",
    		method=RequestMethod.GET)    
    public String monitor_blood_pressure(
    	@RequestParam("pid") String pid,
    	@RequestParam(value = "n", defaultValue = "10") int n) throws IOException, ParseException {    	
 	    	
    	StringBuilder blood_sugar = new StringBuilder();
    	blood_sugar.append("{");    	
    	String sep = "";    	    	
    	for(MedicalRecord temp : MongoDBUtils.getListOfMedicalRecord(pid)) {    		    		
    		blood_sugar.append(sep);
			blood_sugar.append(temp.getDate() + " - " 
							+ temp.getHealth_data());			
    		sep = "; ";    		
    	}
    	blood_sugar.append("}");
    	return blood_sugar.toString();
    }
   
    //Return description of a sickness
    @RequestMapping(value="/sd",
    		method=RequestMethod.GET)
    @ResponseBody
    public String getSicknessDescription(
    	@RequestParam("sId") String sicknessId) throws IOException {    	
    	
		return MongoDBUtils.getSicknessDescription(sicknessId);
    }
}
