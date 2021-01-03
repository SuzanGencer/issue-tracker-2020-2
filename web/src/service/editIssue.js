import axios from 'axios'

export const editIssue = async (id, title, description, labels) => {
  const url = 'http://localhost:8080/virtserver.swaggerhub.com/Kodstar/Issue_Tracker/1.0.0/issue/' + id

  let issue = {
    title: title,
    description: description,
    labels: []
  }

  labels.forEach(element => {
    issue.labels.push({ labelName: element.labelName })
  })

  const response = await axios
    .put(url, issue)
    .catch(error => console.log(error))

  window.location.reload()
  return response
}
