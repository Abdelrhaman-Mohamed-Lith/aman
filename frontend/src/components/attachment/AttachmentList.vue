<template>
<v-card>
<v-card-title>Attachments <v-spacer /><v-btn small @click="fetch">Refresh</v-btn></v-card-title>
<v-data-table :items="items" :headers="headers" item-key="id">
<template #item.actions="{ item }">
<v-btn icon @click="$emit('edit', item)"><v-icon>mdi-pencil</v-icon></v-btn>
<v-btn icon color="error" @click="remove(item.id)"><v-icon>mdi-delete</v-icon></v-btn>
</template>
</v-data-table>
</v-card>
</template>
<script setup>
import { ref, onMounted } from 'vue'
import attachmentService from '../../services/attachmentService'
const items = ref([])
const headers = [{ title:'ID', key:'id' },{ title:'File', key:'fileName' },{ title:'Type', key:'fileType' },{ title:'Size', key:'fileSize' },{ title:'Actions', key:'actions' }]
async function fetch(){ items.value = await attachmentService.getAll() }
async function remove(id){ await attachmentService.delete(id); await fetch(); $emit('deleted') }
onMounted(fetch)
defineExpose({ fetch })
</script>