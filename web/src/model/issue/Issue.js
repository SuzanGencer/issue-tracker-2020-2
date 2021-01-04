import React, { useState, useEffect } from 'react'
import { Badge, Form, Table } from 'react-bootstrap'
import { getIssues } from '../../service/getIssues'
import { getFilteredIssues } from '../../service/getFilteredIssues'

import '../scss/issue.scss'

export default function Issue (props) {
  let [issues, setIssue] = useState([])

  useEffect(() => {
    getIssues().then(issue => {
      setIssue(issue.data)
    })
  }, [])

  useEffect(() => {
    getFilteredIssues(props.issueFilter).then(issue => {
      setIssue(issue.data)
    })
  }, [props.issueFilter])

  return (
    <Table striped bordered hover variant='dark'>
      <tbody className='container'>
        {issues.map(issue => {
          return (
            <tr className='row' key={issue.id}>
              <td className='col-12'>
                <div className='check-issue-container col-1'>
                  <Form className='check-issue'>
                    <Form.Check></Form.Check>
                  </Form>
                </div>
                <div className='issue-body col-6'>
                  <h4>{issue.title}</h4>
                  <p className='description-text'>{issue.description}</p>
                </div>
                <div className='labels-container offset-3 col-2'>
                  <div className='labels'>
                    {issue.labels.map(label => {
                      return (
                        <Badge
                          pill
                          style={{ backgroundColor: label.labelColor }}
                          key={label.id}
                        >
                          #{label.labelName}
                        </Badge>
                      )
                    })}
                  </div>
                </div>
              </td>
            </tr>
          )
        })}
      </tbody>
    </Table>
  )
}
