import axios from 'axios'

export async function getIssue (id) {
  const url = 'http://localhost:8080/virtserver.swaggerhub.com/Kodstar/Issue_Tracker/1.0.0/issue/' + id

  const response = await axios.get(url)
  return response
}
