<template>
  <v-container fluid>
    <v-row class="mb-4" align="center" justify="space-between">
      <v-col cols="auto">
        <h2 class="text-h5 font-weight-medium">👤 Members</h2>
      </v-col>
      <v-col cols="auto">
        <v-btn color="primary" @click="openDialog()">Add Member</v-btn>
      </v-col>
    </v-row>

    <!-- Search Bar -->
    <v-text-field
      v-model="search"
      label="Search by Name or Registration Number"
      prepend-inner-icon="mdi-magnify"
      clearable
      class="mb-3"
    />

    <!-- Members List -->
    <v-card>
      <v-data-table
        :headers="headers"
        :items="filteredMembers"
        :loading="loading"
        class="elevation-1"
      >
        <template #item.actions="{ item }">
          <v-btn icon @click="editMember(item)">
            <v-icon>mdi-pencil</v-icon>
          </v-btn>
          <v-btn icon color="error" @click="deleteMember(item.id)">
            <v-icon>mdi-delete</v-icon>
          </v-btn>
        </template>
      </v-data-table>
    </v-card>

    <!-- Dialog for Create/Edit -->
    <v-dialog v-model="dialog" max-width="600px">
      <v-card>
        <v-card-title>{{ editMode ? 'Edit Member' : 'New Member' }}</v-card-title>
        <v-card-text>
          <v-row dense>
            <v-col cols="12" sm="6">
              <v-text-field v-model="form.name" label="Name" />
            </v-col>
            <v-col cols="12" sm="6">
              <v-text-field v-model="form.registrationNumber" label="Registration Number" />
            </v-col>
            <v-col cols="12" sm="6">
              <v-text-field v-model="form.medicalCardNumber" label="Medical Card Number" />
            </v-col>
            <v-col cols="12" sm="6">
              <v-select
                v-model="form.syndicate"
                :items="syndicates"
                label="Syndicate"
              />
            </v-col>
            <v-col cols="12" sm="6">
              <v-select
                v-model="form.governorate"
                :items="governorates"
                label="Governorate"
              />
            </v-col>
            <v-col cols="12" sm="6">
              <v-text-field v-model="form.region" label="Region" />
            </v-col>
            <v-col cols="12" sm="6">
              <v-text-field v-model="form.phone1" label="Phone 1" />
            </v-col>
            <v-col cols="12" sm="6">
              <v-text-field v-model="form.phone2" label="Phone 2" />
            </v-col>
            <v-col cols="12" sm="6">
              <v-text-field v-model="form.whatsappNumber" label="WhatsApp Number" />
            </v-col>
            <v-col cols="12">
              <v-textarea v-model="form.address" label="Address" />
            </v-col>
          </v-row>
        </v-card-text>
        <v-card-actions>
          <v-spacer />
          <v-btn text @click="closeDialog()">Cancel</v-btn>
          <v-btn color="primary" @click="saveMember()">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import MemberService from '@/services/memberService'
import { useAppStore } from '@/stores'

// state
const members = ref([])
const loading = ref(false)
const dialog = ref(false)
const editMode = ref(false)
const search = ref('')

const form = ref({
  id: null,
  name: '',
  registrationNumber: '',
  medicalCardNumber: '',
  syndicate: '',
  governorate: '',
  region: '',
  whatsappNumber: '',
  phone1: '',
  phone2: '',
  address: ''
})

// example data (replace with enum API if needed)
const syndicates = ['Medical', 'Veterinary', 'Pharmaceutical']
const governorates = ['Cairo', 'Giza', 'Alexandria', 'Aswan', 'Luxor']

const headers = [
  { title: 'ID', key: 'id', width: 60 },
  { title: 'Name', key: 'name' },
  { title: 'Registration #', key: 'registrationNumber' },
  { title: 'Syndicate', key: 'syndicate' },
  { title: 'Governorate', key: 'governorate' },
  { title: 'Phone', key: 'phone1' },
  { title: 'Actions', key: 'actions', sortable: false, align: 'center' }
]

const appStore = useAppStore()

const filteredMembers = computed(() => {
  const q = search.value.toLowerCase()
  return members.value.filter(
    (m) =>
      m.name?.toLowerCase().includes(q) ||
      m.registrationNumber?.toLowerCase().includes(q)
  )
})

async function loadMembers() {
  loading.value = true
  try {
    members.value = await MemberService.getAll()
  } catch (error) {
    console.error(error)
    appStore.notify('Failed to load members', 'error')
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
      registrationNumber: '',
      medicalCardNumber: '',
      syndicate: '',
      governorate: '',
      region: '',
      whatsappNumber: '',
      phone1: '',
      phone2: '',
      address: ''
    }
  }
  dialog.value = true
}

function closeDialog() {
  dialog.value = false
}

async function saveMember() {
  try {
    if (editMode.value) {
      await MemberService.update(form.value.id, form.value)
      appStore.notify('Member updated successfully', 'success')
    } else {
      await MemberService.create(form.value)
      appStore.notify('Member created successfully', 'success')
    }
    dialog.value = false
    await loadMembers()
  } catch (error) {
    console.error(error)
    appStore.notify('Failed to save member', 'error')
  }
}

function editMember(item) {
  openDialog(item)
}

async function deleteMember(id) {
  if (!confirm('Are you sure you want to delete this member?')) return
  try {
    await MemberService.delete(id)
    appStore.notify('Member deleted', 'success')
    await loadMembers()
  } catch (error) {
    console.error(error)
    appStore.notify('Failed to delete member', 'error')
  }
}

onMounted(loadMembers)
</script>

<style scoped>
.v-data-table {
  border-radius: 12px;
}
.v-btn {
  text-transform: none;
}
</style>
