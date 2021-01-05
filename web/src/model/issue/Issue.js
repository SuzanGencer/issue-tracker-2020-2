import React, { useState, useEffect } from 'react'
import { Badge, Form, Table } from 'react-bootstrap'
import { getIssues } from '../../service/getIssues'
import { getFilteredIssues } from '../../service/getFilteredIssues'
import { getSortedIssues } from '../../service/getSortedIssues'
import './scss/issue.scss'

export default function Issue (props) {
  let [issues, setIssue] = useState([])
  let [checkedIssues, setCheck] = useState([])

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

  useEffect(() => {
    let checked = []
    issues.map(issue =>
      checked.push({ id: issue.id, status: props.checkStatus })
    )
    setCheck(checked)
  }, [props.checkStatus])

  useEffect(() => {
    getSortedIssues(props.sortParams).then(issue => {
      setIssue(issue.data)
    })
  }, [props.sortParams])

  const getStatus = issue => {
    let s = false
    checkedIssues.map(i => {
      if (i.id === issue.id) {
        s = i.status
      }
      return s
    })
    return s
  }
  const handleChanges = (e, id) => {
    let newChecked = []

    checkedIssues.map(i => {
      if (i.id === id) {
        newChecked.push({ id: id, status: e.target.checked })
      } else {
        newChecked.push(i)
      }
      return newChecked
    })
    setCheck(newChecked)
  }

  const dateOptions = {
    day: '2-digit',
    year: '2-digit',
    month: 'short'
  }
  const timeOptions = {
    hour: '2-digit',
    minute: '2-digit'
  }

  return (
    <Table striped bordered hover variant='dark'>
      <tbody className='container'>
        {issues.map(issue => {
          return (
            <tr className='row' key={issue.id}>
              <td className='col-12'>
                <div className='check-issue-container col-1'>
                  <Form className='check-issue'>
                    <Form.Check
                      checked={getStatus(issue)}
                      onChange={e => {
                        handleChanges(e, issue.id)
                      }}
                    ></Form.Check>
                  </Form>
                </div>
                <div className='issue-body col-6'>
                  <h4>{issue.title}</h4>
                  <p className='description-text'>{issue.description}</p>
                </div>
                <div className='offset-1 col-2 times'>
                  <div className='date-container'>
                    created:{' '}
                    <p className='date'>
                      {new Date(issue.createTime).toLocaleDateString(
                        'en-US',
                        dateOptions
                      )}
                    </p>
                    <p className='hour'>
                      {new Date(issue.createTime).toLocaleTimeString(
                        [],
                        timeOptions
                      )}
                    </p>
                  </div>
                  <div className='date-container'>
                    updated:{' '}
                    <p className='date'>
                      {new Date(issue.updateTime).toLocaleDateString(
                        'en-US',
                        dateOptions
                      )}
                    </p>
                    <p className='hour'>
                      {new Date(issue.updateTime).toLocaleTimeString(
                        [],
                        timeOptions
                      )}
                    </p>
                  </div>
                </div>
                <div className='labels-container col-2'>
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
