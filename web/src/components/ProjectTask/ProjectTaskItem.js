import React, { Component } from 'react'



class ProjectTaskItem extends Component {

  constructor(){
    super()
    this.deleteIssue = this.deleteIssue.bind(this);
  }

  deleteIssue(){
    alert("It is not implemented yet. Come back soon :)")
  }

 render() {
  const {issueId,title,description} = this.props;
  return(
   <div>
       <div className="card mb-1 bg-light">
                <div className="card-header text-primary">
                  ID: {issueId}
                </div>
                <div className="card-body bg-light">
                  <h5 className="card-title">{title}</h5>
                  <p className="card-text text-truncate ">{description}</p>
                  <a href="" className="btn btn-primary">
                    View / Update
                  </a>

                  <button className="btn btn-danger ml-4" onClick={this.deleteIssue}>Delete</button>
                </div>
              </div>
   </div>
    )
   }
 }



export default ProjectTaskItem