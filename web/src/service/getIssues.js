import axios from 'axios'

export const getIssues = async () => {
  const url = 'http://localhost:8080/issues'

  const response = await axios.get(url)
  
  return response
}