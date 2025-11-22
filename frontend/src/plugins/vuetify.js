// src/plugins/vuetify.js
import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import { aliases, mdi } from 'vuetify/iconsets/mdi'

// Optional: import a theme or define your own
const customTheme = {
  dark: false,
  colors: {
    background: '#F9FAFB',
    surface: '#FFFFFF',
    primary: '#1976D2',
    secondary: '#03A9F4',
    error: '#D32F2F',
    info: '#0288D1',
    success: '#2E7D32',
    warning: '#ED6C02',
  },
}

export default createVuetify({
  theme: {
    defaultTheme: 'customTheme',
    themes: {
      customTheme,
    },
  },
  icons: {
    defaultSet: 'mdi',
    aliases,
    sets: { mdi },
  },
})
