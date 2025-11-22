<template>
  <v-container fluid>
    <v-row class="mb-4" align="center" justify="space-between">
      <v-col cols="auto">
        <h2 class="text-h5 font-weight-medium">🌍 Regions</h2>
      </v-col>
      <v-col cols="auto">
        <v-btn color="primary" @click="openDialog()">Add Region</v-btn>
      </v-col>
    </v-row>

    <!-- Search Bar -->
    <v-text-field
      v-model="search"
      label="Search by Region Name"
      prepend-inner-icon="mdi-magnify"
      clearable
      class="mb-3"
    />

    <!-- Regions List -->
    <v-card>
      <v-data-table
        :headers="headers"
        :items="filteredRegions"
        :loading="loading"
        class="elevation-1"
      >
        <template #item.actions="{ item }">
          <v-btn icon @click="editRegion(item)">
            <v-icon>mdi-pencil</v-icon>
          </v-btn>
          <v-btn icon color="error" @click="deleteRegion(item.id)">
            <v-icon>mdi-delete</v-icon>
          </v-btn>
        </template>
      </v-data-table>
    </v-card>

    <!-- Dialog for Create/Edit -->
    <v-dialog v-model="dialog" max-width="500px">
      <v-card>
        <v-card-title>{{ editMode ? 'Edit Region' : 'New Region' }}</v-card-title>
        <v-card-text>
          <v-text-field v-model="form.name" label="Region Name" />
          <v-textarea v-model="form.remarks" label="Remarks" />
        </v-card-text>
        <v-card-actions>
          <v-spacer />
          <v-btn text @click="closeDialog()">Cancel</v-btn>
          <v-btn color="primary" @click="saveRegion()">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import RegionService from '@/services/regionService'
import { useAppStore } from '@/stores'

// state
const regions = ref([])
const loading = ref(false)
const dialog = ref(false)
const editMode = ref(false)
const search = ref('')

const form = ref({
  id: null,
  name: '',
  remarks: ''
})

const headers = [
  { title: 'ID', key: 'id', width: 60 },
  { title: 'Region Name', key: 'name' },
  { title: 'Remarks', key: 'remarks' },
  { title: 'Actions', key: 'actions', sortable: false, align: 'center' }
]

const appStore = useAppStore()

const filteredRegions = computed(() => {
  const q = search.value.toLowerCase()
  return regions.value.filter(
    (r) =>
      r.name?.toLowerCase().includes(q) ||
      r.remarks?.toLowerCase().includes(q)
  )
})

async function loadRegions() {
  loading.value = true
  try {
    regions.value = await RegionService.getAll()
  } catch (error) {
    console.error(error)
    appStore.notify('Failed to load regions', 'error')
  } finally {
    loading.value = false
  }
}

function openDialog(item = null) {
  if (item) {
    editMode.value = true
    form.value = { ...item }
  } else {
    editMode.value = false
    form.value = {
      id: null,
      name: '',
      remarks: ''
    }
  }
  dialog.value = true
}

function closeDialog() {
  dialog.value = false
}

async function saveRegion() {
  try {
    if (editMode.value) {
      await RegionService.update(form.value.id, form.value)
      appStore.notify('Region updated successfully', 'success')
    } else {
      await RegionService.create(form.value)
      appStore.notify('Region created successfully', 'success')
    }
    dialog.value = false
    await loadRegions()
  } catch (error) {
    console.error(error)
    appStore.notify('Failed to save region', 'error')
  }
}

function editRegion(item) {
  openDialog(item)
}

async function deleteRegion(id) {
  if (!confirm('Are you sure you want to delete this region?')) return
  try {
    await RegionService.delete(id)
    appStore.notify('Region deleted', 'success')
    await loadRegions()
  } catch (error) {
    console.error(error)
    appStore.notify('Failed to delete region', 'error')
  }
}

onMounted(loadRegions)
</script>

<style scoped>
.v-data-table {
  border-radius: 12px;
}
.v-btn {
  text-transform: none;
}
</style>
