import axios from 'axios'
import { getIssues } from './getIssues'

export const getFilteredIssues = async searchFilter => {
  const title_search_url = 'http://localhost:8080/issues/search/title/'
  const description_search_url =
    'http://localhost:8080/issues/search/description/'

  let filterKeyword = searchFilter
    .slice(searchFilter.indexOf(':') + 1)
    .replace(/\s/gi, '')

  let keyword = searchFilter
    .slice(0, searchFilter.indexOf(':'))
    .replace(/\s/gi, '')

  if (searchFilter === '') {
    return await getIssues()
  } else if (searchFilter.indexOf(':') === -1 || keyword === 'title') {
    return await axios.get(title_search_url + filterKeyword)
  } else if (searchFilter.indexOf(':') !== -1 && keyword === 'description') {
    return await axios.get(description_search_url + filterKeyword)
  }
}
