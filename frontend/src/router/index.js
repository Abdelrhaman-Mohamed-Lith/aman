// src/router/index.js
import { createRouter, createWebHistory } from 'vue-router'

// Import views
import SheetList from '@/views/SheetList.vue'
import SheetForm from '@/views/SheetForm.vue'
import MemberList from '@/views/MemberList.vue'
import MemberForm from '@/views/MemberForm.vue'
import OrderList from '@/views/OrderList.vue'
import OrderForm from '@/views/OrderForm.vue'
import AttachmentList from '@/views/AttachmentList.vue'
import AttachmentForm from '@/views/AttachmentForm.vue'

const routes = [
  {
    path: '/',
    redirect: '/sheets',
  },
  {
    path: '/sheets',
    name: 'SheetList',
    component: SheetList,
  },
  {
    path: '/sheets/new',
    name: 'SheetFormNew',
    component: SheetForm,
  },
  {
    path: '/sheets/:id',
    name: 'SheetFormEdit',
    component: SheetForm,
    props: true,
  },
  {
    path: '/members',
    name: 'MemberList',
    component: MemberList,
  },
  {
    path: '/members/new',
    name: 'MemberFormNew',
    component: MemberForm,
  },
  {
    path: '/members/:id',
    name: 'MemberFormEdit',
    component: MemberForm,
    props: true,
  },
  {
    path: '/orders',
    name: 'OrderList',
    component: OrderList,
  },
  {
    path: '/orders/new',
    name: 'OrderFormNew',
    component: OrderForm,
  },
  {
    path: '/orders/:id',
    name: 'OrderFormEdit',
    component: OrderForm,
    props: true,
  },
  {
    path: '/attachments',
    name: 'AttachmentList',
    component: AttachmentList,
  },
  {
    path: '/attachments/new',
    name: 'AttachmentFormNew',
    component: AttachmentForm,
  },
  {
    path: '/attachments/:id',
    name: 'AttachmentFormEdit',
    component: AttachmentForm,
    props: true,
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router
