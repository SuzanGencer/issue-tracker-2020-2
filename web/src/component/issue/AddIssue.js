import React from 'react'
import { createIssue } from '../../service/createIssue'
import './add-issue.scss'
import {
  Button,
  Modal,
  Container,
  Form,
  InputGroup,
  FormControl,
  Badge
} from 'react-bootstrap'

export default class AddIssue extends React.Component {
  constructor (props) {
    super(props)
    this.state = {
      title: '',
      description: '',
      labels: [],
      lgShow: false
    }

    this.setLgShow = this.setLgShow.bind(this)
  }

  handleFormValues = event => {
    event.preventDefault()
    const title = event.target.title.value
    const description = event.target.description.value
    const labels = this.state.labels
    this.addIssue(title, description, labels)
    this.setLgShow(false)
    this.flushState()
  }

  handleLabel = event => {
    this.setState({ label: event.target.value })
  }

  addLabelToLabels = () => {
    this.setState({
      labels: [...this.state.labels, '#' + this.state.label.toLowerCase()],
      label: ''
    })
  }
  addIssue = (title, description, labels) => {
    createIssue(title, description, labels)
  }

  setLgShow (lgShow) {
    this.setState({
      lgShow: lgShow
    })
  }
  removeLabel = labelName => {
    const oldLabels = this.state.labels
    let index = oldLabels.indexOf(labelName)
    oldLabels.splice(index, 1)
    this.setState({ labels: oldLabels })
  }

  flushState = () => {
    this.setState({
      title: '',
      description: '',
      labels: []
    })
  }

  render () {
    return (
      <Container>
        <Button
          variant=''
          className='add-issue-btn'
          onClick={() => {
            this.setLgShow(true)
          }}
        >
          Add A New Issue
        </Button>
        <Modal
          size='lg'
          show={this.state.lgShow}
          onHide={() => {
            this.setLgShow(false)
          }}
          aria-labelledby='example-modal-sizes-title-lg'
        >
          <Modal.Header closeButton>
            <Modal.Title id='example-modal-sizes-title-lg'>
              Add A New Issue
            </Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <Form onSubmit={this.handleFormValues}>
              <Form.Group controlId='newIssue'>
                <Form.Label>Title : </Form.Label>
                <Form.Control
                  type='text'
                  name='title'
                  placeholder="Ex. 'Issue tracker 1' "
                ></Form.Control>
                <Form.Label className='description-label'>
                  Description :{' '}
                </Form.Label>
                <Form.Control
                  className='description-text-area'
                  as='textarea'
                  name='description'
                  rows={3}
                  placeholder="Ex. 'Issue tracker 1 Description' "
                ></Form.Control>
                <Form.Label className='add-label'>Labels : </Form.Label>
                <div className='added-labels-container'>
                  {this.state.labels.map((label, i) => (
                    <Badge
                      key={i++}
                      className='added-labels'
                      variant='primary'
                      onClick={() => this.removeLabel(label)}
                    >
                      {label}
                    </Badge>
                  ))}{' '}
                </div>
                <span
                  style={{
                    visibility:
                      this.state.labels.length > 0 ? 'visible' : 'hidden'
                  }}
                  className='remove-message'
                >
                  (on click to remove)
                </span>
                <InputGroup className='mb-3'>
                  <InputGroup.Prepend>
                    <InputGroup.Text>#</InputGroup.Text>
                  </InputGroup.Prepend>
                  <FormControl
                    aria-label='label'
                    type='text'
                    name='label'
                    placeholder='Bug, Front-End, ... '
                    onChange={this.handleLabel}
                    value={this.state.label}
                  />
                  <InputGroup.Append>
                    <Button
                      id='add-label-btn'
                      className='add-label-btn'
                      variant='success'
                      type='button'
                      onClick={this.addLabelToLabels}
                    >
                      Add
                    </Button>
                  </InputGroup.Append>
                </InputGroup>
                <Container className='create-btn-group'>
                  <Button
                    id='create-btn'
                    className='create-btn'
                    variant='primary'
                    type='submit'
                    block
                  >
                    Create
                  </Button>
                  <Button
                    id='cancel-btn'
                    className='cancel-btn'
                    variant='danger'
                    type='cancel'
                    block
                    onClick={() => {
                      this.setLgShow(false)
                      this.flushState()
                    }}
                  >
                    Cancel
                  </Button>
                </Container>
              </Form.Group>
            </Form>
          </Modal.Body>
        </Modal>
      </Container>
    )
  }
}
