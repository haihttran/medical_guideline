import React, { Component } from 'react';
import './recordhistory.css';
const Record = (props) => {
    let renderData = props.recordData;
    return (
    <div>
        {
            renderData.map((r, i) => 
                <div className="box" key={i} onClick={() => {}}>
                    <h2>{r['date']} | {r['pId']} | {r['phId']}</h2>
                    <p> </p>
                    <table id="recordsHistoryStyle">
                        <thead>
                        </thead>
                        <tbody>
                            <tr>
                                <td>{r.health_data[0][0]}</td>
                                <td>{r.health_data[0][1]}</td>
                            </tr>
                            <tr>
                                <td>{r.health_data[1][0]}</td>
                                <td>{r.health_data[1][1]}</td>
                            </tr>
                            <tr>
                                <td>{r.health_data[2][0]}</td>
                                <td>{r.health_data[2][1]}</td>
                            </tr>
                            <tr>
                                <td>{r.health_data[3][0]}</td>
                                <td>{r.health_data[3][1]}</td>
                            </tr>
                            <tr>
                                <td>{r.health_data[4][0]}</td>
                                <td>{r.health_data[4][1]}</td>
                            </tr>
                            <tr>
                                <td>symptoms</td>
                                <td><ul>
                                        {r.symptoms.map((x,i) => <li key={i}>{x}</li>)}
                                </ul></td>
                            </tr>
                            <tr>
                                <td>results</td>
                                <td><ul>
                                        {r.result.map((x,i) => <li key={i}>{x}</li>)}
                                </ul></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
        )}
    </div>
    );
};


                

class RecordHistory extends Component {
	constructor(){
		super();
		this.state= {
			records: [],
            currentPage: 1,
            todosPerPage: 3
		};
        this.handleClick = this.handleClick.bind(this);

	}
    
    handleClick(event) {
        this.setState({
          currentPage: Number(event.target.id)
        });
      }
    
	componentDidMount() {
		fetch('/medical_records?pid=patient_1')
		.then((res) => res.json())
		.then((newData) => {
            console.log(newData);
            newData.forEach((r) => delete r._id);
            let recs = newData;
            for (let i = 0; i < newData.length; i++) {   
                recs[i].health_data = recs[i].health_data.substring(1,recs[i].health_data.length - 1).split(',');
                recs[i].health_data = recs[i].health_data.filter((x) => x[0] === ' ').map((x) => x.substring(1,x.length));
                recs[i].health_data = recs[i].health_data.map((x) => x.split(' : '));
                recs[i].result = recs[i].result.substring(1, recs[i].result.length - 1).split(',').map((x) => x[0] === ' ' ? x.substring(1,x.length): x);
                recs[i].symptoms = recs[i].symptoms.substring(1, recs[i].symptoms.length - 1).split(',').map((x) => x[0] === ' ' ? x.substring(1,x.length): x);
            }
            this.setState(prevState => ({records: recs.reverse()}))
          });

	}

	render() {
        const { records, currentPage, todosPerPage } = this.state;
        const indexOfLastTodo = currentPage * todosPerPage;
        const indexOfFirstTodo = indexOfLastTodo - todosPerPage;
        const currentTodos = records.slice(indexOfFirstTodo, indexOfLastTodo);
        const pageNumbers = [];
        for (let i = 1; i <= Math.ceil(records.length / todosPerPage); i++) {
          pageNumbers.push(i);
        }
        
        const renderPageNumbers = pageNumbers.map(number => {
          return (
            <li
              key={number}
              id={number}
              onClick={this.handleClick}
            >
              {number}
            </li>
          );
        });

        {if (records.length > 0){
             return(
			     <div>
                    <Record recordData={records.slice(indexOfFirstTodo,indexOfLastTodo )}/>
                    <ul id="page-numbers">
                    {renderPageNumbers}
                    </ul>
			     </div>
		      );
        }
            return <div>Loading...</div>   
        }
		
	}
}

export default RecordHistory;
