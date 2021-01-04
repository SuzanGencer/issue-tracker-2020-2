import React, { useEffect, useState } from 'react'
import { Container, Form, Navbar, Nav, Dropdown, Badge } from 'react-bootstrap'
import Issue from '../../model/issue/Issue'
import { getLabels } from '../../service/getLabels'

import './scss/issues.scss'

export default function Issues () {
  // var _ = require('lodash')
  let [labels, setItem] = useState([])
  let [filteredLabels, setFilteredLabels] = useState([])
  let [filterOfLabel, setLabelFilter] = useState('')
  let [filterIssue, setIssueFilter] = useState('')

  useEffect(() => {
    getLabels().then(labels => {
      setItem(labels.data)
      setFilteredLabels(labels.data)
    })
  }, [])

  useEffect(() => {
    let updatedLabels = []
    labels.forEach(element =>
      element.labelName.search(filterOfLabel) !== -1
        ? updatedLabels.push(element)
        : null
    )
    setFilteredLabels([...updatedLabels])
  }, [filterOfLabel])

  const handleFilterIssue = e => {
    setIssueFilter(e.target.value)
  }

  return (
    <Container className='issue-container'>
      <Navbar collapseOnSelect expand='lg' bg='dark' variant='dark'>
        <Form>
          <Form.Check></Form.Check>
          <div className='search-container'>
            <Form.Control
              className='search'
              placeholder='(default) title: description:'
              onChange={handleFilterIssue.bind(this)}
              value={filterIssue}
            ></Form.Control>
          </div>
        </Form>
        <Navbar.Toggle aria-controls='responsive-navbar-nav' />
        <Navbar.Collapse id='responsive-navbar-nav'>
          <Nav className='mr-auto'></Nav>
          <Nav>
            <Dropdown>
              <Dropdown.Toggle id='dropdown-basic' variant='outline-info'>
                Label
              </Dropdown.Toggle>
              <Dropdown.Menu className='dropdown-container'>
                <Form>
                  <Form.Control
                    type='text'
                    placeholder='type something..'
                    value={filterOfLabel}
                    onChange={e => setLabelFilter(e.target.value)}
                  ></Form.Control>
                </Form>
                <Dropdown.Item
                  onClick={() => {
                    setLabelFilter('unlabeled')
                  }}
                >
                  unlabeled
                </Dropdown.Item>
                {filteredLabels.map(label => {
                  return (
                    <Dropdown.Item
                      href=''
                      key={label.id}
                      onClick={() => {
                        setLabelFilter(label.labelName)
                      }}
                    >
                      <Badge style={{ backgroundColor: label.labelColor }}>
                        {label.labelName[0].toUpperCase()}
                      </Badge>
                      {label.labelName}
                    </Dropdown.Item>
                  )
                })}
              </Dropdown.Menu>
            </Dropdown>
          </Nav>
        </Navbar.Collapse>
      </Navbar>
      <Issue issueFilter={filterIssue}/>
    </Container>
  )
}
