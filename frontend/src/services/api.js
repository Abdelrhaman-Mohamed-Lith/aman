// src/api/api.js
import axios from 'axios'

// ✅ Create Axios instance
const api = axios.create({
  baseURL: 'http://localhost:8080', // Your Spring Boot backend base URL
  headers: {
    'Content-Type': 'application/json',
  },
})

// ✅ Interceptors (optional but recommended)
api.interceptors.response.use(
  (response) => response,
  (error) => {
    console.error('API Error:', error.response || error.message)
    return Promise.reject(error)
  }
)

// ✅ Entity-specific API helpers
export default {
  // ---- ATTACHMENTS ----
  getAttachments() {
    return api.get('/attachments')
  },
  getAttachment(id) {
    return api.get(`/attachments/${id}`)
  },
  createAttachment(data) {
    return api.post('/attachments', data)
  },
  updateAttachment(id, data) {
    return api.put(`/attachments/${id}`, data)
  },
  deleteAttachment(id) {
    return api.delete(`/attachments/${id}`)
  },

  // ---- MEMBERS ----
  getMembers() {
    return api.get('/members')
  },
  getMember(id) {
    return api.get(`/members/${id}`)
  },
  createMember(data) {
    return api.post('/members', data)
  },
  updateMember(id, data) {
    return api.put(`/members/${id}`, data)
  },
  deleteMember(id) {
    return api.delete(`/members/${id}`)
  },

  // ---- ORDERS ----
  getOrders() {
    return api.get('/orders')
  },
  getOrder(id) {
    return api.get(`/orders/${id}`)
  },
  createOrder(data) {
    return api.post('/orders', data)
  },
  updateOrder(id, data) {
    return api.put(`/orders/${id}`, data)
  },
  deleteOrder(id) {
    return api.delete(`/orders/${id}`)
  },

  // ---- SHEETS ----
  getSheets() {
    return api.get('/sheets')
  },
  getSheet(id) {
    return api.get(`/sheets/${id}`)
  },
  createSheet(data) {
    return api.post('/sheets', data)
  },
  updateSheet(id, data) {
    return api.put(`/sheets/${id}`, data)
  },
  deleteSheet(id) {
    return api.delete(`/sheets/${id}`)
  },
}
