import React, { Component } from "react";
import { Link } from "react-router-dom";
import ProjectTaskItem from "./ProjectTask/ProjectTaskItem";
import axios from 'axios'
class ProjectBoard extends Component {

  constructor(props) {
    super(props);
    this.state = {
      arr: []
    }
    this.handleLoad = this.handleLoad.bind(this);
 }

 componentDidMount() {
    window.addEventListener('load', this.handleLoad);
    
 }

//  componentWillUnmount() { 
//    window.removeEventListener('load', this.handleLoad)  
//  }

 handleLoad() {
  axios.get('http://localhost:8080/virtserver.swaggerhub.com/Kodstar/Issue_Tracker/1.0.0/issues')
  .then((response) => {
    this.setState({
      arr: [ ...this.state.arr, ...response.data ]
    })
   
    // console.log(Object.entries(this.state.returnObj).forEach((obj) => console.log(obj[1].title)))
    // console.log(this.state.returnObj)
  }, (error) => {
    console.log(error); 
  });
 }
 
  render() {

    return (

      
      
      <div className="container">
       { 
         <Link to="/addProjectTask" className="btn btn-primary mb-3">
          <h8 className="fas fa-plus-circle"> Create Issue</h8>
        </Link> 
      }
        <br />
        <hr />
        <div className="container">
          <div className="row">
            <div className="col-md-4">
              <div className="card text-center mb-2">
                <div className="card-header bg-secondary text-white">
                  <h3>To Do</h3>
                </div>
               
              </div>
              {
                this.state.arr.map(item => 
                  <ProjectTaskItem issueId = {item.id} title={item.title} description={item.description}/>
                )
              }
            </div>
            <div className="col-md-4">
              <div className="card text-center mb-2">
                <div className="card-header bg-primary text-white">
                  <h3>In Progress</h3>
                </div>
              </div>
              {
               
              }
              <ProjectTaskItem/>
            </div>
            <div className="col-md-4">
              <div className="card text-center mb-2">
                <div className="card-header bg-success text-white">
                  <h3>Done</h3>
                </div>
              </div>
              {
                
              }
              <ProjectTaskItem/>
            </div>
          </div>
        </div>
        {
          
        }{" "}
      </div>
    );
  }
}

export default ProjectBoard;
