import React from 'react'
import { Navbar} from 'react-bootstrap'

export class Nav extends React.Component {
  render () {
    return (
      <Navbar expand='lg' variant='dart' bg='dark'>
        <Navbar.Brand variant="light" href='#'>Issue Tracker</Navbar.Brand>
      </Navbar>
    )
  }
}
