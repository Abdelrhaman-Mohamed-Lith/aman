<template>
  <v-container class="pa-6">
    <v-card>
      <v-card-title class="d-flex justify-space-between align-center">
        <span class="text-h6">Sheets</span>
        <v-btn color="primary" @click="goToCreate">
          <v-icon left>mdi-plus</v-icon>
          New Sheet
        </v-btn>
      </v-card-title>

      <v-data-table
        :headers="headers"
        :items="sheets"
        :loading="loading"
        class="elevation-1"
        loading-text="Loading sheets..."
      >
        <template #item.actions="{ item }">
          <v-btn icon @click="editSheet(item)">
            <v-icon>mdi-pencil</v-icon>
          </v-btn>
          <v-btn icon @click="deleteSheet(item.id)">
            <v-icon color="red">mdi-delete</v-icon>
          </v-btn>
        </template>
      </v-data-table>
    </v-card>
  </v-container>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'

const router = useRouter()
const sheets = ref([])
const loading = ref(true)

const headers = [
  { text: 'ID', value: 'id', align: 'start' },
  { text: 'Name', value: 'name' },
  { text: 'Description', value: 'description' },
  { text: 'Created By', value: 'createdBy' },
  { text: 'Actions', value: 'actions', sortable: false },
]

onMounted(fetchSheets)

async function fetchSheets() {
  loading.value = true
  try {
    const res = await axios.get('/sheets')
    sheets.value = res.data
  } catch (e) {
    console.error('Failed to fetch sheets', e)
  } finally {
    loading.value = false
  }
}

function goToCreate() {
  router.push('/sheets/create')
}

function editSheet(sheet) {
  router.push(`/sheets/${sheet.id}/edit`)
}

async function deleteSheet(id) {
  if (!confirm('Are you sure you want to delete this sheet?')) return
  try {
    await axios.delete(`/sheets/${id}`)
    await fetchSheets()
  } catch (e) {
    console.error('Delete failed', e)
  }
}
</script>
