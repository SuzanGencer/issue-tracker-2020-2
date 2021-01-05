import React from 'react'
import { Nav, Navbar } from 'react-bootstrap'

export function Navigation () {
  return (
    <Navbar expand='lg' variant='dart' bg='dark'>
      <Navbar.Brand variant='light' href='#'>
        Issue Tracker
      </Navbar.Brand>
      <Nav className='mr-auto'>
        <Nav.Link href='home'>Home</Nav.Link>
        <Nav.Link href='issues'>Issues</Nav.Link>
      </Nav>
    </Navbar>
  )
}
