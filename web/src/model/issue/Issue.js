import React from 'react'
import './issue.scss'
import { deleteIssue } from '../../service/deleteIssue'
import { editIssue } from '../../service/editIssue'
import {
  Card,
  Button,
  Form,
  Col,
  Badge,
  Modal,
  InputGroup,
  FormControl,
  Container
} from 'react-bootstrap'

export default class Issue extends React.Component {
  constructor (props) {
    super(props)
    this.state = {
      title: '',
      description: '',
      labels: [...props.labels],
      label: '',
      lgShow: false
    }

    this.setLgShow = this.setLgShow.bind(this)
  }

  handleFormValues = event => {
    event.preventDefault()
    const title = event.target.title.value
    const description = event.target.description.value
    const labels = this.state.labels
    this.editIssue(this.props.id, title, description, labels)
    this.setLgShow(false)
    this.flushState()
  }

  handleLabel = event => {
    this.setState({ label: event.target.value })
  }

  addLabelToLabels = () => {
    this.setState({
      labels: [
        ...this.state.labels,
        { labelName: this.state.label.toLowerCase() }
      ],
      label: ''
    })
    console.log(this.state.labels)
  }
  editIssue = async (id, title, description, labels) => {
    await editIssue(id, title, description, labels)
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

  setLgShow (lgShow) {
    this.setState({
      lgShow: lgShow
    })
  }
  render () {
    const { id, title, description, labels, selectedIssue } = this.props

    return (
      <Col sm={{ span: 12 }}>
        <Card style={{ width: '30rem' }} className='card'>
          <div
            className='click-mask'
            onClick={() => {
              selectedIssue(id)
            }}
          ></div>
          <Card.Body>
            <div className='edit-container'>
              <Button
                type='button'
                size='sm'
                onClick={() => {
                  this.setLgShow(true)
                }}
              >
                <i className='edit-icon'> </i>{' '}
              </Button>{' '}
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
                    Edit Issues
                  </Modal.Title>
                </Modal.Header>
                <Modal.Body>
                  <Form onSubmit={this.handleFormValues}>
                    <Form.Group controlId='newIssue'>
                      <Form.Label>Title : </Form.Label>
                      <Form.Control
                        type='text'
                        name='title'
                        defaultValue={title}
                      ></Form.Control>
                      <Form.Label className='description-label'>
                        Description :{' '}
                      </Form.Label>
                      <Form.Control
                        className='description-text-area'
                        as='textarea'
                        name='description'
                        rows={3}
                        defaultValue={description}
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
                            #{label.labelName}
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
                          Save
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
              <Button variant='danger' type='button' size='sm'>
                <i
                  className='delete-icon'
                  onClick={() => {
                    deleteIssue(id)
                  }}
                >
                  {' '}
                </i>{' '}
              </Button>{' '}
              <Form.Check type='checkbox' id='btncheck1' autoComplete='off' />
            </div>{' '}
            <Card.Title> {title} </Card.Title>{' '}
            <Card.Text className='description-text'> {description} </Card.Text>
            {labels.map(item => (
              <Badge className='label' key={item.id} variant='primary'>
                #{item.labelName}
              </Badge>
            ))}
          </Card.Body>{' '}
        </Card>
      </Col>
    )
  }
}
