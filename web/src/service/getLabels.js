import axios from 'axios'


export const getLabels = async () => {
  const url = 'http://localhost:8080/issues/labels'

  let response = await axios.get(url)

  return response
}
