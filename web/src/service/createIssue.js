import axios from 'axios'

export const createIssue = async (title, description, labels) => {
  const url = 'http://localhost:8080/virtserver.swaggerhub.com/Kodstar/Issue_Tracker/1.0.0/issue'

  let issue = {
    title: title,
    description: description,
    labels: []
  }

  labels.forEach(element => {
    issue.labels.push({ labelName: element.substring(1) })
  })

  const response = await axios.post(url, issue, {
    headers: { 'content-type': 'application/json' }
  })
  window.location.reload()
  return response
}
