import React, { Component } from "react";
import { Link } from "react-router-dom";
import axios from 'axios'

class AddProjectTask extends Component {
  constructor(){
    super()
    this.state={
      title:"",
      description:""
    };
    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
    this.submit = this.submit.bind(this);

    
  }

  
  onChange(props){
    this.setState({[props.target.name]:props.target.value})
  }
  onSubmit(props){
    props.preventDefault()
    const newProjectTask = {
      title:this.state.title,
      description:this.state.description
    }
  }

  

  submit(props){
    props.preventDefault()
    // axios({
    //   method: 'post',
    //   url: 'localhost:8080/virtserver.swaggerhub.com/Kodstar/Issue_Tracker/1.0.0/issue',
    //   data: {
    //     title:this.state.title,
    //     description:this.state.description
    //   }
    // });

    // const newProjectTask = {
    //   title:this.state.title,
    //   description:this.state.description
    // }

    // const xhr = new XMLHttpRequest();
    // const url = 'localhost:8080/virtserver.swaggerhub.com/Kodstar/Issue_Tracker/1.0.0/issues';

    // xhr.open('GET', url);
    // xhr.send();


    // axios({
    //   method: 'post',
    //   headers: { 'Content-Type': 'application/json'},
    //   url: 'localhost:8080/virtserver.swaggerhub.com/Kodstar/Issue_Tracker/1.0.0/issue',
    //   data: newProjectTask,
    // }).then(function (response) {
    //   console.log(response);
    // });

  //   axios.get('http://localhost:8080/virtserver.swaggerhub.com/Kodstar/Issue_Tracker/1.0.0/issues')
  // .then((response) => {
  //   console.log(typeof response)
  //   console.log(Object.entries(response))
  //   for (const obj of Object.entries(response)) {
  //     // console.log(`${obj.title}: ${obj.description}`);
  //     console.log(obj[0])
  //   }
  // })
  // .catch(err => {
  //   if (err.response) {
  //     // client received an error response (5xx, 4xx)
  //     console.log('client received an error response (5xx, 4xx)')
  //   } else if (err.request) {
  //     // client never received a response, or request never left
  //     console.log('client never received a response, or request never left')
  //   } else {
  //     // anything else
  //     console.log("anything else")
  //   }
  // });


  axios.post('http://localhost:8080/virtserver.swaggerhub.com/Kodstar/Issue_Tracker/1.0.0/issue', {
    title:this.state.title,
    description:this.state.description
  })
  .then((response) => {
    console.log(response.data.title);
  }, (error) => {
    console.log(error);
  });

  window.history.back();
  // history.push("/");

  }

  

  render() {
    return (
      <div className="addProjectTask">
        <div className="container">
          <div className="row">
            <div className="col-md-8 m-auto">
              { <Link to="/" className="btn btn-light">
                Back to Board
              </Link> }
              <h4 className="display-4 text-center">
                Add /Update Issue Task
              </h4>
              <form onSubmit={this.onSubmit}>
                <div className="form-group">
                  <input
                    type="text"
                    className="form-control form-control-lg"
                    name="title"
                    value={this.state.title}
                    placeholder="Issue Task summary"
                    onChange={this.onChange}
                    
                  />
                </div>
                <div className="form-group">
                  <textarea
                    className="form-control form-control-lg"
                    placeholder="Acceptance Criteria"
                    name="description"
                    value={this.state.description}
                    onChange={this.onChange}
                  />
                </div>
                <div className="form-group">
                  <select
                    className="form-control form-control-lg"
                    name="status"
                    value={this.state.status}
                    onChange={this.onChange}
                  >
                    <option value="">Select Status</option>
                    <option value="TO_DO">To Do</option>
                    <option value="IN_PROGRESS">In Progress</option>
                    <option value="DONE">Done</option>
                  </select>
                </div>
                <input
                  type="submit"
                  className="btn btn-primary btn-block mt-4"
                  onClick={this.submit}
                />
              </form>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default AddProjectTask;
