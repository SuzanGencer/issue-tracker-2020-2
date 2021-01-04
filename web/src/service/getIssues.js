import axios from 'axios'

export const getIssues = async () => {
  const url = 'http://localhost:8080/virtserver.swaggerhub.com/Kodstar/Issue_Tracker/1.0.0/issues'

  const response = await axios.get(url)
  
  return response
}