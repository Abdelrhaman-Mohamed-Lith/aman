import api from './api'
export default {
create(payload) { return api.post('/attachments', payload).then(r => r.data) },
getAll() { return api.get('/attachments').then(r => r.data) },
getById(id) { return api.get(`/attachments/${id}`).then(r => r.data) },
update(id, payload) { return api.put(`/attachments/${id}`, payload).then(r => r.data) },
delete(id) { return api.delete(`/attachments/${id}`) }
}