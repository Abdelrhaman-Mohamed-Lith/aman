<template>
  <v-container fluid>
    <v-row class="mb-4" align="center" justify="space-between">
      <v-col cols="auto">
        <h2 class="text-h5 font-weight-medium">📎 Attachments</h2>
      </v-col>
      <v-col cols="auto">
        <v-btn color="primary" @click="openDialog()">Add Attachment</v-btn>
      </v-col>
    </v-row>

    <!-- Attachment list -->
    <v-card>
      <v-data-table
        :headers="headers"
        :items="attachments"
        :loading="loading"
        class="elevation-1"
      >
        <template #item.actions="{ item }">
          <v-btn icon @click="editAttachment(item)">
            <v-icon>mdi-pencil</v-icon>
          </v-btn>
          <v-btn icon color="error" @click="deleteAttachment(item.id)">
            <v-icon>mdi-delete</v-icon>
          </v-btn>
        </template>
      </v-data-table>
    </v-card>

    <!-- Dialog for create / edit -->
    <v-dialog v-model="dialog" max-width="500px">
      <v-card>
        <v-card-title>{{ editMode ? 'Edit Attachment' : 'New Attachment' }}</v-card-title>
        <v-card-text>
          <v-text-field v-model="form.fileName" label="File Name" />
          <v-text-field v-model="form.fileType" label="File Type" />
          <v-text-field v-model.number="form.fileSize" label="File Size (bytes)" type="number" />
          <v-text-field v-model="form.storagePath" label="Storage Path" />
        </v-card-text>
        <v-card-actions>
          <v-spacer />
          <v-btn text @click="closeDialog()">Cancel</v-btn>
          <v-btn color="primary" @click="saveAttachment()">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import AttachmentService from '@/services/attachmentService'
import { useAppStore } from '@/stores'

// state
const attachments = ref([])
const loading = ref(false)
const dialog = ref(false)
const editMode = ref(false)
const form = ref({
  id: null,
  fileName: '',
  fileType: '',
  fileSize: null,
  storagePath: ''
})

const headers = [
  { title: 'ID', key: 'id', width: 60 },
  { title: 'File Name', key: 'fileName' },
  { title: 'Type', key: 'fileType' },
  { title: 'Size (bytes)', key: 'fileSize' },
  { title: 'Path', key: 'storagePath' },
  { title: 'Actions', key: 'actions', sortable: false, align: 'center' }
]

const appStore = useAppStore()

async function loadAttachments() {
  loading.value = true
  try {
    attachments.value = await AttachmentService.getAll()
  } catch (error) {
    console.error(error)
    appStore.notify('Failed to load attachments', 'error')
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
      fileName: '',
      fileType: '',
      fileSize: null,
      storagePath: ''
    }
  }
  dialog.value = true
}

function closeDialog() {
  dialog.value = false
}

async function saveAttachment() {
  try {
    if (editMode.value) {
      await AttachmentService.update(form.value.id, form.value)
      appStore.notify('Attachment updated successfully', 'success')
    } else {
      await AttachmentService.create(form.value)
      appStore.notify('Attachment created successfully', 'success')
    }
    dialog.value = false
    await loadAttachments()
  } catch (error) {
    console.error(error)
    appStore.notify('Failed to save attachment', 'error')
  }
}

function editAttachment(item) {
  openDialog(item)
}

async function deleteAttachment(id) {
  if (!confirm('Are you sure you want to delete this attachment?')) return
  try {
    await AttachmentService.delete(id)
    appStore.notify('Attachment deleted', 'success')
    await loadAttachments()
  } catch (error) {
    console.error(error)
    appStore.notify('Failed to delete attachment', 'error')
  }
}

onMounted(loadAttachments)
</script>

<style scoped>
.v-data-table {
  border-radius: 12px;
}
.v-btn {
  text-transform: none;
}
</style>
