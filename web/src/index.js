import React, { Suspense, lazy } from 'react'
import ReactDOM from 'react-dom'
import './index.scss'
import reportWebVitals from './reportWebVitals'
import 'bootstrap/dist/css/bootstrap.min.css'
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'

const Home = lazy(() => import('./App'))
const AddIssue = lazy(() => import('./component/issue/AddIssue'))

ReactDOM.render(
  <React.StrictMode>
    <Router>
      <Suspense fallback={<div> Loading... </div>}>
        {' '}
        <Switch>
          <Route exact path='/' component={Home} />{' '}
          <Route path='/create' component={AddIssue} />{' '}
        </Switch>{' '}
      </Suspense>{' '}
    </Router>{' '}
  </React.StrictMode>,
  document.getElementById('root')
)

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals()
