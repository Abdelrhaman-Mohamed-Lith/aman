import api from './api'
export default {
create(payload) { return api.post('/sheets', payload).then(r => r.data) },
getAll() { return api.get('/sheets').then(r => r.data) },
getById(id) { return api.get(`/sheets/${id}`).then(r => r.data) },
update(id, payload) { return api.put(`/sheets/${id}`, payload).then(r => r.data) },
delete(id) { return api.delete(`/sheets/${id}`) }
}