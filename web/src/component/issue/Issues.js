import React from 'react'
import { Container, Row } from 'react-bootstrap'
import Issue from '../../model/issue/Issue'
import { getIssue } from '../../service/getIssue'
import { getIssues } from '../../service/getIssues'
import AddIssue from './AddIssue'
import './issues.scss'

export default class Issues extends React.Component {
  constructor (props) {
    super(props)
    this.state = {
      arr: [],
      selectedIssue: null,
      selectedIssueId: 0
    }
    this.componentDidMount = this.componentDidMount.bind(this)
    this.componentDidUpdate = this.componentDidUpdate.bind(this)
  }

  componentDidMount = async props => {
    const response = await getIssues().then(res => res.data)
    this.setState({
      arr: response
    })
  }

  arrayEquals = (a, b) => {
    return (
      Array.isArray(a) &&
      Array.isArray(b) &&
      a.length === b.length &&
      a.every((val, index) => val.id === b[index].id)
    )
  }

  componentDidUpdate = async props => {
    const response = await getIssues().then(res => res.data)

    if (!this.arrayEquals(response, this.state.arr)) {
      this.setState({
        arr: response
      })
    }
  }

  getIssues = () => {
    return Array.from(this.state.arr)
  }

  showDetailedIssue = async id => {
    let issue = await getIssue(id).then(res => res.data)
    this.setState({ selectedIssueId: id, selectedIssue: issue })
  }

  render () {
    return (
      <Container>
        <Row>
          <AddIssue />
          <div className='issue-cards col-6'>
            {this.getIssues().map(issue => (
              <Issue
                selectedIssue={this.showDetailedIssue}
                key={issue.id}
                id={issue.id}
                title={issue.title}
                description={issue.description}
                labels={issue.labels}
              />
            ))}
          </div>
          <div className='issue-detail col-6'>
            {this.state.selectedIssueId !== 0 && (
              <Issue
                key={this.state.selectedIssue.id}
                id={this.state.selectedIssue.id}
                title={this.state.selectedIssue.title}
                description={this.state.selectedIssue.description}
                labels={this.state.selectedIssue.labels}
              />
            )}
          </div>
        </Row>
      </Container>
    )
  }
}
