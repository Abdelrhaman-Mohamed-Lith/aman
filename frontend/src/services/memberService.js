import api from './api'


export default {
create(payload) { return api.post('/members', payload).then(r => r.data) },
getAll() { return api.get('/members').then(r => r.data) },
getById(id) { return api.get(`/members/${id}`).then(r => r.data) },
update(id, payload) { return api.put(`/members/${id}`, payload).then(r => r.data) },
delete(id) { return api.delete(`/members/${id}`) }
}