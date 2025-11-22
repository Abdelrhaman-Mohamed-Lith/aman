<template>
<v-card>
<v-card-title>Orders <v-spacer /><v-btn small @click="fetch">Refresh</v-btn></v-card-title>
<v-data-table :items="items" :headers="headers" item-key="id">
<template #item.member="{ item }">
<span>{{ item.memberId }}</span>
</template>
<template #item.actions="{ item }">
<v-btn icon @click="$emit('edit', item)"><v-icon>mdi-pencil</v-icon></v-btn>
<v-btn icon color="error" @click="remove(item.id)"><v-icon>mdi-delete</v-icon></v-btn>
</template>
</v-data-table>
</v-card>
</template>
<script setup>
import { ref, onMounted } from 'vue'
import orderService from '../../services/orderService'
const items = ref([])
const headers = [{ title:'ID', key:'id' },{ title:'Member', key:'member' },{ title:'Cards', key:'cards' },{ title:'Coupons', key:'couponsCount' },{ title:'Actions', key:'actions' }]
async function fetch(){ items.value = await orderService.getAll() }
async function remove(id){ await orderService.delete(id); await fetch(); $emit('deleted') }
onMounted(fetch)
defineExpose({ fetch })
</script>