import api from './api'
export default {
create(payload) { return api.post('/regions', payload).then(r => r.data) },
getAll() { return api.get('/regions').then(r => r.data) },
getById(id) { return api.get(`/regions/${id}`).then(r => r.data) },
update(id, payload) { return api.put(`/regions/${id}`, payload).then(r => r.data) },
delete(id) { return api.delete(`/regions/${id}`) }
}