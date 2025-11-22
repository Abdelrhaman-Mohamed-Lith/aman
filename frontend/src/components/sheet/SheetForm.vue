<template>
  <v-container class="pa-6">
    <v-card class="mx-auto pa-6" max-width="600">
      <v-card-title>{{ isEdit ? 'Edit Sheet' : 'Create New Sheet' }}</v-card-title>

      <v-form ref="form" v-model="valid" lazy-validation>
        <v-text-field
          label="Sheet Name"
          v-model="sheet.name"
          :rules="[rules.required]"
        ></v-text-field>

        <v-textarea
          label="Description"
          v-model="sheet.description"
          auto-grow
        ></v-textarea>

        <v-text-field
          label="Created By"
          v-model="sheet.createdBy"
          :rules="[rules.required]"
        ></v-text-field>

        <v-btn
          color="primary"
          class="mt-4"
          :disabled="!valid"
          @click="saveSheet"
        >
          {{ isEdit ? 'Update' : 'Save' }}
        </v-btn>
        <v-btn text class="mt-4 ml-2" @click="goBack">Cancel</v-btn>
      </v-form>
    </v-card>
  </v-container>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import axios from 'axios'

const route = useRoute()
const router = useRouter()

const valid = ref(false)
const isEdit = ref(false)
const sheet = reactive({
  id: null,
  name: '',
  description: '',
  createdBy: '',
})

const rules = {
  required: (value) => !!value || 'Required field',
}

onMounted(async () => {
  if (route.params.id) {
    isEdit.value = true
    const res = await axios.get(`/sheets/${route.params.id}`)
    Object.assign(sheet, res.data)
  }
})

async function saveSheet() {
  try {
    if (isEdit.value) {
      await axios.put(`/sheets/${sheet.id}`, sheet)
    } else {
      await axios.post('/sheets', sheet)
    }
    router.push('/sheets')
  } catch (e) {
    console.error(e)
  }
}

function goBack() {
  router.push('/sheets')
}
</script>
