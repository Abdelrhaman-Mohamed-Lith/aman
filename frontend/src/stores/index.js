// src/stores/index.js
import { createPinia, defineStore } from 'pinia'
import { createApp } from 'vue'
import App from '@/App.vue'
import router from '@/router'

/**
 * Create and export the Pinia store instance
 */
const pinia = createPinia()

/**
 * Example global store (you can remove or extend)
 * This can hold app-level state like loading indicators, user info, etc.
 */
export const useAppStore = defineStore('app', {
  state: () => ({
    loading: false,
    notification: null,
  }),

  actions: {
    setLoading(value) {
      this.loading = value
    },
    notify(message, type = 'info') {
      this.notification = { message, type }
      setTimeout(() => (this.notification = null), 3000)
    },
  },
})

/**
 * Setup function to integrate Pinia with Vue app
 */
export function setupStore() {
  const app = createApp(App)
  app.use(pinia)
  app.use(router)
  app.mount('#app')
}

export default pinia
