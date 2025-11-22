<template>
<v-card>
<v-card-title>{{ editing ? 'Edit Attachment' : 'New Attachment' }}</v-card-title>
<v-card-text>
<v-form>
<v-text-field v-model="model.fileName" label="File name" />
<v-text-field v-model="model.fileType" label="File type" />
<v-text-field v-model="model.fileSize" label="File size" type="number" />
<v-text-field v-model="model.storagePath" label="Storage path" />
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
import attachmentService from '../../services/attachmentService'
const props = defineProps({ editing: Object })
const emit = defineEmits(['saved','cancel'])
const model = ref({})
watch(() => props.editing, v => { model.value = v ? { ...v } : { fileName:'', fileType:'', fileSize:0, storagePath:'' } }, { immediate:true })
async function save(){ if(model.value.id) await attachmentService.update(model.value.id, model.value); else await attachmentService.create(model.value); emit('saved') }
</script>