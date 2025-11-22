<template>
  <v-container fluid>
    <v-row class="mb-4" align="center" justify="space-between">
      <v-col cols="auto">
        <h2 class="text-h5 font-weight-medium">📑 Sheets</h2>
      </v-col>
      <v-col cols="auto">
        <v-btn color="primary" @click="openDialog()">Add Sheet</v-btn>
      </v-col>
    </v-row>

    <!-- Search Bar -->
    <v-text-field
      v-model="search"
      label="Search by Sheet ID or Status"
      prepend-inner-icon="mdi-magnify"
      clearable
      class="mb-3"
    />

    <!-- Sheets List -->
    <v-card>
      <v-data-table
        :headers="headers"
        :items="filteredSheets"
        :loading="loading"
        class="elevation-1"
      >
        <template #item.status="{ item }">
          <v-chip :color="statusColor(item.status)" dark>
            {{ item.status }}
          </v-chip>
        </template>

        <template #item.actions="{ item }">
          <v-btn icon @click="editSheet(item)">
            <v-icon>mdi-pencil</v-icon>
          </v-btn>
          <v-btn icon color="error" @click="deleteSheet(item.id)">
            <v-icon>mdi-delete</v-icon>
          </v-btn>
        </template>
      </v-data-table>
    </v-card>

    <!-- Dialog for Create/Edit -->
    <v-dialog v-model="dialog" max-width="600px">
      <v-card>
        <v-card-title>{{ editMode ? 'Edit Sheet' : 'New Sheet' }}</v-card-title>
        <v-card-text>
          <v-row dense>
            <v-col cols="12" sm="6">
              <v-menu
                v-model="fromDateMenu"
                :close-on-content-click="false"
                transition="scale-transition"
                offset-y
                min-width="auto"
              >
                <template #activator="{ props }">
                  <v-text-field
                    v-model="form.fromDate"
                    label="From Date"
                    readonly
                    v-bind="props"
                  />
                </template>
                <v-date-picker v-model="form.fromDate" @input="fromDateMenu = false" />
              </v-menu>
            </v-col>
            <v-col cols="12" sm="6">
              <v-menu
                v-model="toDateMenu"
                :close-on-content-click="false"
                transition="scale-transition"
                offset-y
                min-width="auto"
              >
                <template #activator="{ props }">
                  <v-text-field
                    v-model="form.toDate"
                    label="To Date"
                    readonly
                    v-bind="props"
                  />
                </template>
                <v-date-picker v-model="form.toDate" @input="toDateMenu = false" />
              </v-menu>
            </v-col>
            <v-col cols="12" sm="6">
              <v-select
                v-model="form.status"
                :items="statuses"
                label="Status"
              />
            </v-col>
          </v-row>
        </v-card-text>
        <v-card-actions>
          <v-spacer />
          <v-btn text @click="closeDialog()">Cancel</v-btn>
          <v-btn color="primary" @click="saveSheet()">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import SheetService from '@/services/sheetService'
import { useAppStore } from '@/stores'

const sheets = ref([])
const loading = ref(false)
const dialog = ref(false)
const editMode = ref(false)
const search = ref('')

const form = ref({
  id: null,
  fromDate: '',
  toDate: '',
  status: ''
})

const statuses = ['Draft', 'Submitted', 'Approved', 'Rejected']

const headers = [
  { title: 'ID', key: 'id', width: 60 },
  { title: 'From Date', key: 'fromDate' },
  { title: 'To Date', key: 'toDate' },
  { title: 'Status', key: 'status' },
  { title: 'Actions', key: 'actions', sortable: false, align: 'center' }
]

const fromDateMenu = ref(false)
const toDateMenu = ref(false)
const appStore = useAppStore()

const filteredSheets = computed(() => {
  const q = search.value.toLowerCase()
  return sheets.value.filter(
    (s) =>
      s.id?.toString().includes(q) ||
      s.status?.toLowerCase().includes(q)
  )
})

function statusColor(status) {
  switch (status) {
    case 'Draft': return 'grey'
    case 'Submitted': return 'blue'
    case 'Approved': return 'green'
    case 'Rejected': return 'red'
    default: return 'grey'
  }
}

async function loadSheets() {
  loading.value = true
  try {
    sheets.value = await SheetService.getAll()
  } catch (error) {
    console.error(error)
    appStore.notify('Failed to load sheets', 'error')
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
      fromDate: '',
      toDate: '',
      status: ''
    }
  }
  dialog.value = true
}

function closeDialog() {
  dialog.value = false
}

async function saveSheet() {
  try {
    if (editMode.value) {
      await SheetService.update(form.value.id, form.value)
      appStore.notify('Sheet updated successfully', 'success')
    } else {
      await SheetService.create(form.value)
      appStore.notify('Sheet created successfully', 'success')
    }
    dialog.value = false
    await loadSheets()
  } catch (error) {
    console.error(error)
    appStore.notify('Failed to save sheet', 'error')
  }
}

function editSheet(item) {
  openDialog(item)
}

async function deleteSheet(id) {
  if (!confirm('Are you sure you want to delete this sheet?')) return
  try {
    await SheetService.delete(id)
    appStore.notify('Sheet deleted', 'success')
    await loadSheets()
  } catch (error) {
    console.error(error)
    appStore.notify('Failed to delete sheet', 'error')
  }
}

onMounted(loadSheets)
</script>

<style scoped>
.v-data-table {
  border-radius: 12px;
}
.v-btn {
  text-transform: none;
}
</style>
