import axios from 'axios'

const api = axios.create({ baseURL: '/api/v1' })

export function analyzeEvent(eventJson) {
  return api.post('/analysis', { event: JSON.parse(eventJson) })
}
