<template>
<v-card>
<v-card-title>Members<v-spacer /><v-text-field v-model="q" placeholder="Search" dense hide-details append-inner-icon="mdi-magnify" @input="fetch"/></v-card-title>
<v-data-table :items="members" :headers="headers" item-key="id">
<template #item.actions="{ item }">
<v-btn icon @click="$emit('edit', item)"><v-icon>mdi-pencil</v-icon></v-btn>
<v-btn icon color="error" @click="remove(item.id)"><v-icon>mdi-delete</v-icon></v-btn>
</template>
</v-data-table>
</v-card>
</template>
<script setup>
import { ref, onMounted } from 'vue'
import memberService from '../../services/memberService'
const members = ref([])
const q = ref('')
const headers = [{ title: 'ID', key: 'id' },{ title:'Code', key:'code' },{ title:'Registration #', key:'registrationNumber' },{ title:'Phone', key:'phone1' },{ title:'Actions', key:'actions' }]
async function fetch() { members.value = await memberService.getAll() }
async function remove(id) { await memberService.delete(id); await fetch(); $emit('deleted') }
onMounted(fetch)
defineExpose({ fetch })
</script>