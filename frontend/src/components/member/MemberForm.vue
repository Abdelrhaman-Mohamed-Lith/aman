<template>
<v-card>
<v-card-title>{{ editing ? 'Edit Member' : 'New Member' }}</v-card-title>
<v-card-text>
<v-form ref="formRef">
<v-text-field v-model="model.code" label="Code" />
<v-text-field v-model="model.registrationNumber" label="Registration #" />
<v-text-field v-model="model.phone1" label="Phone 1" />
<v-text-field v-model="model.phone2" label="Phone 2" />
<v-text-field v-model="model.whatsappNumber" label="WhatsApp" />
<v-textarea v-model="model.address" label="Address" rows="2" />
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
import memberService from '../../services/memberService'
const props = defineProps({ editing: Object })
const emit = defineEmits(['saved','cancel'])
const model = ref({})
watch(() => props.editing, v => { model.value = v ? { ...v } : { code:'', registrationNumber:'', phone1:'', phone2:'', whatsappNumber:'', address:'' } }, { immediate:true })
async function save() { if(model.value.id) await memberService.update(model.value.id, model.value); else await memberService.create(model.value); emit('saved') }
</script>