import axios from 'axios'

export const getLabels = async () => {
  const url = 'http://localhost:8080/issues/labels'

  return await axios.get(url)
}