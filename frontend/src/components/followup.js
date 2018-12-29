import React, { Component } from 'react';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend } from 'recharts';

class FollowUp extends Component {
	constructor(){
		super();
		this.state= {
			followup: ''
		};
	}

	componentDidMount() {
		fetch('/followup?pid=patient_1&n=10')
		.then((res) => res.text())
		.then((newData) => {
            console.log(newData);
            this.setState(prevState => ({followup: newData}));
          });
	}

	render() {
        let dataString = this.state.followup;
        dataString = dataString.substring(1, dataString.length-1);
        dataString = dataString.split(';');
        dataString = dataString.map((dp) => (dp.split(' - ')));
        let data = [];
        
        
    
        dataString.forEach((p) => {
            let point = {};
            if (p[0][0] === ' '){
                p[0] = p[0].substring(1, p[0].length);
            }
            if (p.length > 1) {
                let name = p[0].substring(0,6);
                let tempList = p[1].substring(1,p[1].length-1).split(',');
                
                
                for (let i = 0; i < tempList.length; i++){
                    if (tempList[i][0] === ' '){
                        tempList[i] = tempList[i].substring(1, i.length);
                    }
                }
                
                tempList = tempList.map((i) => (i.split(' : ')));
                point['name'] = name;
                tempList.forEach((i) => {
                    if (i[0][0] === ' '){
                        i[0] = i[0].substring(1, p[0].length - 1);
                    }
                    point[i[0]] = parseFloat(i[1]);
                });
                console.log(point);
                data.push(point);
            }
        });
        
        if (data.length > 10) {
            data = data.splice(data.length-10,data.length);
        }
        
		return(
            <div>
            
            <div className="new">
            
            </div>
            
			<div>
                <h2>Patient_1</h2>
                <LineChart width={600} height={300} data={data} margin={{top: 5, right: 30, left: 20, bottom: 5}}>
                <XAxis dataKey="name"/>
                <YAxis type="number" domain={[0,400]}/>
                <CartesianGrid strokeDasharray="3 3"/>
                <Tooltip/>
                <Legend />
                <Line type="monotone" dataKey="blood_sugar_level" stroke="#8884d8" activeDot={{r: 8}}/>
                </LineChart>
            
                <LineChart width={600} height={300} data={data} margin={{top: 5, right: 30, left: 20, bottom: 5}}>
                <XAxis dataKey="name" />
                <YAxis type="number" domain={[35,45]}/>
                <CartesianGrid strokeDasharray="3 3"/>
                <Tooltip/>
                <Legend />
                <Line type="monotone" dataKey="body_temperature" stroke="#82ca9d" />
                </LineChart>
            
                <LineChart width={600} height={300} data={data} margin={{top: 5, right: 30, left: 20, bottom: 5}}>
                <XAxis dataKey="name"/>
                <YAxis type="number" domain={[50,250]}/>
                <CartesianGrid strokeDasharray="3 3"/>
                <Tooltip/>
                <Legend />
                <Line type="monotone" dataKey="diastolic_blood_pressure" stroke="#82caff" />
                <Line type="monotone" dataKey="systolic_blood_pressure" stroke="#82ff9d" />
                </LineChart>
			</div>
            </div>
		);
	}
}

export default FollowUp;
