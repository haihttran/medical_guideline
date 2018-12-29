package Models;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import org.bson.types.ObjectId;

import xyz.morphia.annotations.*;
//import xyz.morphia.annotations.Entity;
//import xyz.morphia.annotations.Field;
//import xyz.morphia.annotations.Id;
//import xyz.morphia.annotations.Index;
//import xyz.morphia.annotations.Indexes;

@Entity("medical_record")
@Indexes(
    @Index(value = "pId", fields = @Field("pId"))
)
public class MedicalRecord {
	
//	private String id; //Medical Record ID
	@Id
	private ObjectId _id;
	private String pId; //Patient ID
	private String phId; //Physician ID
	public String getPhId() {
		return phId;
	}

	public void setPhId(String phId) {
		this.phId = phId;
	}
	
	private Date date; //Created Date
	private String health_data;
	
	public String getHealth_data() {
		return health_data;
	}

	public void setHealth_data(String health_data) {
		this.health_data = health_data;
	}

	private String symptoms;
	private String result; //Result
	
//	public String getId() {
//		return id;
//	}

	public String getSymptoms() {
		return symptoms;
	}

	public String getpId() {
		return pId;
	}

	public Date getDate() {
		return date;
	}
	
	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

	public MedicalRecord(String id, String pId, String phId, Date date) {
//		this.id = id;
		this.pId = pId;
		this.phId = phId;
		this.date = date;
	}
	
	public MedicalRecord() {
		
	}
	
	public void setResult(HashMap<String, Double> h, HashMap<String, Boolean> s) {
		try {
		this.result = Result.getDiagnosis(h, s);
		} catch (IOException e) {}
	}
	
	public String getResult() {
		return result;
	}
}


