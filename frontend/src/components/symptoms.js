import React, { Component } from 'react';
import './symptoms.css';

const Description = (props) => {
    let diseases = props.diseaseDescription;
    diseases = diseases.map((x) => x.split(":"));
    console.log(diseases);
    return (
        <div> 
         {diseases.map((d,i) => 
              <div className="dbox" key={i}>
                  <h2>{d[0]}</h2>
                  <p>{d[1]}</p>
              </div>
         )}
        </div>
    );
}

const Symp = (props) => {
    const sympClassName = (s) => {
        if (props.selectedSymptoms.indexOf(s) >= 0){
            return 'selected symptomsdiv';
        } else {
            return 'symptomsdiv'
        }
    }
    const arrayOfSymptoms = [ "abdominal_cramps",
                                "abdominal_pain",
                                "acute_respiratory",
                                "anemia",
                                "anorexia",
                                "arthralgia",
                                "biliary_colic",
                                "bloating",
                                "blood_coughing",
                                "chills",
                                "chronic_suppurative_infection",
                                "closed_sores",
                                "conjuntivitis",
                                "coryza",
                                "cough",
                                "diarrhea",
                                "difficultly_swallowing",
                                "distress_syndrome",
                                "dry_mucous_membranes",
                                "elavated_heart_rate",
                                "epigastric_pain",
                                "facial_palsy",
                                "fatigue",
                                "flatulence",
                                "high_temperature",
                                "intense_itching",
                                "jaundice",
                                "kidney_failure",
                                "loss_of_appetite",
                                "loss_of_skin_tugor",
                                "maculopapular",
                                "malaise",
                                "marked_eosinophillia",
                                "meningitis",
                                "meningoencephalitis",
                                "mental_confusion",
                                "mild_conjunctivitis",
                                "myalgia",
                                "nausea",
                                "night_sweat",
                                "nonproductive_cough",
                                "open_sores",
                                "pain",
                                "papular_rash",
                                "papulovesicular_erythematous_rash",
                                "paresthesia",
                                "parotid_glands_swelling",
                                "chest_pain",
                                "pneumonia",
                                "pharynx_membrane",
                                "progressive_encephalitis",
                                "pruritus",
                                "larynx_hoarness",
                                "radiculopathy",
                                "rash",
                                "red_rash",
                                "runny_nose",
                                "seizures",
                                "sepsis",
                                "small_pox",
                                "sore_throat",
                                "stiff_neck",
                                "thirst",
                                "urticaria",
                                "vomit",
                                "weakness",
                                "weight_loss",
                                "blood_stinged_nasal_discharge",
                                "skin_membrane",
                                "trachea_membrane",
                                "pseudo_membrane",
                                "larynx_membrane",
                                "tonsil_membrane",
                                "nose_membrane",
                                "abdominal_discomfort",
                                "headache",
                                "sweat",
                                "colored_stool",
                                "muscle_pain",
                                "shortness_of_breath",
                            ];
    
    return (
        <div className="gridcontainer">
                {
                    arrayOfSymptoms.map((s, i) => 
                    <div key={i} className={sympClassName(s)} onClick={() => {
                                            props.selectSymptom(s);
                                            props.addToSymptoms(s);
                                           }}>{s}</div>
                )}
        </div>
    );
}


class Symptoms extends Component {
  constructor() {
    super();
    this.state = {
      height: 170,
      weight: 70,
      bodytemperature: 37.5, 
      bpsystolic: 70,
      bpdiastolic: 110,
      bloodsugar: 102.2,
      symptoms: [],
      diseaseDescription: [],
      selectedSymptoms: []
    };

    this.handleInputChange = this.handleInputChange.bind(this);
    this.submitInfor = this.submitInfor.bind(this);
  }

  componentDidMount () {
    this.setState((state) => {
      return {
        height: 170,
        weight: 70,
        bodytemperature: 37.5, 
        bpsystolic: 70,
        bpdiastolic: 110,
        bloodsugar: 102.2,
        symptoms: [],
        diseaseDescription: [],
        selectedSymptoms: []
      };
    });
  }

  addToSymptoms (clickedSymptom) {
    let newSymptom = clickedSymptom;
    let removedIndex = this.state.symptoms.indexOf(newSymptom);
    let tempList = this.state.symptoms;
    
    if (removedIndex > -1) {
      tempList.splice(removedIndex,1);
    } else {
      tempList = tempList.concat(newSymptom);
    }
    this.setState((state) => {
      return {symptoms: tempList};
    });
  }

  handleInputChange(event) {
    const target = event.target;
    const value = target.type === 'checkbox' ? target.checked : target.value;
    const name = target.name;
    this.setState({
      [name]: value
    });
  }


  selectSymptom(clickedSymptom) {
        if (this.state.selectedSymptoms.indexOf(clickedSymptom) >= 0) {
            let i = this.state.selectedSymptoms.indexOf(clickedSymptom);
            let tempList = this.state.selectedSymptoms;
            tempList.splice(i,1);
            this.setState(prevState => ({
                selectedSymptoms: tempList
            }))
            return;
        }
        this.setState(prevState => ({
            selectedSymptoms: prevState.selectedSymptoms.concat(clickedSymptom),
        })); 
  };

  submitInfor(event) {
    event.preventDefault();
    let json_body = 
    {
      "health_data": {
          "body_temperature": parseFloat(this.state.bodytemperature),
          "height": parseFloat(this.state.height),
          "systolic_blood_pressure": parseFloat(this.state.bpsystolic),
          "diastolic_blood_pressure": parseFloat(this.state.bpdiastolic),
          "blood_sugar_level": parseFloat(this.state.bloodsugar),
          "weight": parseFloat(this.state.weight),
      },
      "symptoms" : {}
    };
    let symptomsList = this.state.symptoms;
    symptomsList.forEach((s) => {
      json_body.symptoms[s] = true;
    });

    fetch('/diagnosis?pid=patient_1&phId=physician_1', {
      method: 'POST',
      headers: {
        'Accept': 'application/json, text/plain, */*',
        'Content-Type':'application/json',
      },
      body: JSON.stringify(json_body)
    }).then((res) => res.json())
      .then((data) => {
      
        let disease = String(data.result).substring(1,data.result.length - 1);
        if (disease.indexOf(',') > -1){
            disease = disease.split(',');
        };
        
        for (let i = 0; i < disease.length; i++){
            if (disease[i][0] === ' '){
                disease[i] = disease[i].substring(1, disease[i].length);
            }
        }
        if (typeof disease === 'object') {
            this.setState(prevState => ({diseaseDescription: []}));
           
            disease.forEach((d) => {
            fetch('/sd?sId='+d)
                .then((res) => res.text())
                .then((newData) => {
                    this.setState(prevState => ({diseaseDescription: prevState.diseaseDescription.concat(newData)}))
          });
        }); 
        } else if (typeof disease === 'string'){
            this.setState(prevState => ({diseaseDescription: []}));
            fetch('/sd?sId='+disease)
                .then((res) => res.text())
                .then((newData) => {
                    this.setState(prevState => ({diseaseDescription: prevState.diseaseDescription.concat(newData)}))
            })
                
        } else {
            return;
        }
        
        
      });
  }

  render() {
    return (
      <div id="main">
          <h2>Patient</h2>
          <div id="healthInfor">
          <table>
            <thead>
            </thead>
            <tbody>
              <tr>
                <td>Height</td>
                <td><input type="text" placeholder="height" 
                      value={this.state.height} 
                      name="height" 
                      onChange={this.handleInputChange}/></td>
              </tr>
              <tr>
                <td>Weight</td>
                <td><input type="text" placeholder="weight" 
                      value={this.state.weight} 
                      name="weight" 
                      onChange={this.handleInputChange}/></td>
              </tr>
              <tr>
                <td>Body Temperature</td>
                <td><input type="range" min="35" max="41" step="0.1" id="myRange" 
                      value={this.state.bodytemperature}
                      name="bodytemperature" 
                      onChange={this.handleInputChange}/><span>{this.state.bodytemperature}</span></td>
              </tr>
              <tr>
                <td>Blood Pressure</td>
                <td> <input type="text" placeholder="systolic" 
                      value={this.state.bpsystolic} 
                      name="bpsystolic" 
                      onChange={this.handleInputChange}/> 
                <input type="text" placeholder="diastolic" 
                      value={this.state.bpdiastolic}
                      name="bpdiastolic" 
                      onChange={this.handleInputChange}/></td>
              </tr>
              <tr>
                <td>Blood Sugar Level</td> 
                <td> <input type="text" placeholder="blood sugar level" 
                      value={this.state.bloodsugar}
                      name="bloodsugar" 
                      onChange={this.handleInputChange}/></td>
              </tr>
            </tbody>
          </table>
        </div>
        <Symp addToSymptoms={this.addToSymptoms.bind(this)} selectedSymptoms={this.state.selectedSymptoms} selectSymptom={this.selectSymptom.bind(this)} />
        
        <button type="submit" onClick={this.submitInfor}>submit</button>
        <Description diseaseDescription = {this.state.diseaseDescription}/>
      </div>
    );
  }
}

export default Symptoms;
