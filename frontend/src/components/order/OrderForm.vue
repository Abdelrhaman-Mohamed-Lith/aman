<template>
<v-card>
<v-card-title>{{ editing ? 'Edit Order' : 'New Order' }}</v-card-title>
<v-card-text>
<v-form>
<v-text-field v-model="model.memberId" label="Member ID" type="number" />
<v-switch v-model="model.cards" label="Cards" />
<v-text-field v-model="model.couponsCount" label="Coupons Count" type="number" />
<v-text-field v-model="model.attachment1Id" label="Attachment1 ID" type="number" />
<v-text-field v-model="model.attachment2Id" label="Attachment2 ID" type="number" />
<v-text-field v-model="model.attachment3Id" label="Attachment3 ID" type="number" />
</v-form>
</v-card-text>
<v-card-actions>
<v-btn color="primary" @click="save">Save</v-btn>
<v-btn text @click="$emit('cancel')">Cancel</v-btn>
</v-card-actions>
</v-card>
</template>
<script setup>
import { ref, watch } from 'vue'
import orderService from '../../services/orderService'
const props = defineProps({ editing: Object })
const emit = defineEmits(['saved','cancel'])
const model = ref({})
watch(() => props.editing, v => { model.value = v ? { ...v } : { memberId:null, cards:false, couponsCount:0, attachment1Id:null, attachment2Id:null, attachment3Id:null } }, { immediate:true })
async function save(){ if(model.value.id) await orderService.update(model.value.id, model.value); else await orderService.create(model.value); emit('saved') }
</script>