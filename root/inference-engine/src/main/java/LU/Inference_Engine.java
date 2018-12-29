package LU;

import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.mvel.MVELRuleFactory;

public class Inference_Engine {
    public static void main(String[] args) throws IOException {
        Facts facts = new Facts();        
        ByteArrayOutputStream consoleStorage = new ByteArrayOutputStream();
//        PrintStream newConsole = System.out;
//        System.setOut(new PrintStream(consoleStorage));
//        Reader reader = new FileReader("src/main/resources/weather-rules.yml");
        Reader reader = new FileReader("src/main/resources/diseases.yml");
        Rules weatherRule = MVELRuleFactory.createRulesFrom(reader);
        reader.close();
                
//        for(int t = 10; t <= 40; t++) {
        	//facts.put("diarrhea", true);
        	facts.put("body_temperature", 38.1);
        	facts.put("abdominal_cramps", true);
        	facts.put("abdominal_pain", true);
        	facts.put("acute_respiratory", true);
        	facts.put("anemia", true);
        	facts.put("anorexia", true);
        	facts.put("arthralgia", true);
        	facts.put("biliary_colic", true);
        	facts.put("bloating", true);
        	facts.put("blood_coughing", true);
        	facts.put("chills", true);
        	facts.put("chronic_suppurative_infection", true);
        	facts.put("closed_sores", true);
        	facts.put("conjuntivitis", true);
        	facts.put("coryza", true);
        	facts.put("cough", true);
        	facts.put("diarrhea", true);
        	facts.put("difficultly_swallowing", true);
        	facts.put("distress_syndrome", true);
        	facts.put("dry_mucous_membranes", true);
        	facts.put("elavated_heart_rate", true);
        	facts.put("epigastric_pain", true);
        	facts.put("facial_palsy", true);
        	facts.put("fatigue", true);
        	facts.put("flatulence", true);
        	facts.put("high_temperature", true);
        	facts.put("intense_itching", true);
        	facts.put("jaundice", true);
        	facts.put("kidney_failure", true);
        	facts.put("loss_of_appetite", true);
        	facts.put("loss_of_skin_tugor", true);
        	facts.put("maculopapular", true);
        	facts.put("malaise", true);
        	facts.put("marked_eosinophillia", true);
        	facts.put("meningitis", true);
        	facts.put("meningoencephalitis", true);
        	facts.put("mental_confusion", true);
        	facts.put("mild_conjunctivitis", true);
        	facts.put("myalgia", true);
        	facts.put("nausea", true);
        	facts.put("night_sweat", true);
        	facts.put("nonproductive_cough", true);
        	facts.put("open_sores", true);
        	facts.put("pain", true);
        	facts.put("papular_rash", true);
        	facts.put("papulovesicular_erythematous_rash", true);
        	facts.put("paresthesia", true);
        	facts.put("parotid_glands_swelling", true);
        	facts.put("pleuritic_chest_pain", true);
        	facts.put("pneumonia", true);
        	facts.put("pharynx_membrane", true);
        	facts.put("progressive_encephalitis", true);
        	facts.put("pruritus", true);
        	facts.put("larynx_hoarness", true);
        	facts.put("radiculopathy", true);
        	facts.put("rash", true);
        	facts.put("red_rash", true);
        	facts.put("runny_nose", true);
        	facts.put("seizures", true);
        	facts.put("sepsis", true);
        	facts.put("small_pox", true);
        	facts.put("sore_throat", true);
        	facts.put("stiff_neck", true);
        	facts.put("thirst", true);
        	facts.put("urticaria", true);
        	facts.put("vomit", true);
        	facts.put("weakness", true);
        	facts.put("weight_loss", true);
        	facts.put("blood_stinged_nasal_discharge", true);
        	facts.put("skin_membrane", true);
        	facts.put("trachea_membrane", true);
        	facts.put("pseudo_membrane", true);
        	facts.put("larynx_membrane", true);
        	facts.put("tonsil_membrane", true);
        	facts.put("nose_membrane", true);
        	facts.put("abdominal_discomfort", true);
        	facts.put("headache", true);
        	facts.put("sweat", true);
        	facts.put("colored_stool", true);
        	facts.put("muscle_pain", true);
        	facts.put("shortness_of_breath", true);
        	
//        	System.out.println(rulesEngine.check(weatherRule, facts));
        	RulesEngine rulesEngine = new DefaultRulesEngine();	
        	rulesEngine.fire(weatherRule, facts);
//        }        
    }    
}