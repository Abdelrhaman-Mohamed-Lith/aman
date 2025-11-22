import api from './api'
export default {
create(payload) { return api.post('/orders', payload).then(r => r.data) },
getAll() { return api.get('/orders').then(r => r.data) },
getById(id) { return api.get(`/orders/${id}`).then(r => r.data) },
update(id, payload) { return api.put(`/orders/${id}`, payload).then(r => r.data) },
delete(id) { return api.delete(`/orders/${id}`) }
}