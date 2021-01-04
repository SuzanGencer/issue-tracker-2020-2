import axios from 'axios'

export const deleteIssue = async issueId => {
  const url = 'http://localhost:8080/virtserver.swaggerhub.com/Kodstar/Issue_Tracker/1.0.0/issue/' + issueId

  const response = await axios.delete(url)

  window.location.reload()
  return response
}
