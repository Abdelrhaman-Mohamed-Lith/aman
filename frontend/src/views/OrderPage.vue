<template>
  <v-container fluid>
    <v-row class="mb-4" align="center" justify="space-between">
      <v-col cols="auto">
        <h2 class="text-h5 font-weight-medium">📦 Orders</h2>
      </v-col>
      <v-col cols="auto">
        <v-btn color="primary" @click="openDialog()">Add Order</v-btn>
      </v-col>
    </v-row>

    <!-- Search Bar -->
    <v-text-field
      v-model="search"
      label="Search by Member Name or ID"
      prepend-inner-icon="mdi-magnify"
      clearable
      class="mb-3"
    />

    <!-- Orders List -->
    <v-card>
      <v-data-table
        :headers="headers"
        :items="filteredOrders"
        :loading="loading"
        class="elevation-1"
      >
        <template #item.cards="{ item }">
          <v-chip :color="item.cards ? 'green' : 'grey'" dark>
            {{ item.cards ? 'Yes' : 'No' }}
          </v-chip>
        </template>

        <template #item.actions="{ item }">
          <v-btn icon @click="editOrder(item)">
            <v-icon>mdi-pencil</v-icon>
          </v-btn>
          <v-btn icon color="error" @click="deleteOrder(item.id)">
            <v-icon>mdi-delete</v-icon>
          </v-btn>
        </template>
      </v-data-table>
    </v-card>

    <!-- Dialog for Create/Edit -->
    <v-dialog v-model="dialog" max-width="600px">
      <v-card>
        <v-card-title>{{ editMode ? 'Edit Order' : 'New Order' }}</v-card-title>
        <v-card-text>
          <v-row dense>
            <v-col cols="12" sm="6">
              <v-text-field v-model="form.memberId" label="Member ID" type="number" />
            </v-col>
            <v-col cols="12" sm="6">
              <v-switch v-model="form.cards" label="Cards Included?" />
            </v-col>
            <v-col cols="12" sm="6">
              <v-text-field v-model.number="form.couponsCount" label="Coupons Count" type="number" />
            </v-col>
            <v-col cols="12" sm="6">
              <v-text-field v-model="form.attachment1Id" label="Attachment 1 ID" type="number" />
            </v-col>
            <v-col cols="12" sm="6">
              <v-text-field v-model="form.attachment2Id" label="Attachment 2 ID" type="number" />
            </v-col>
            <v-col cols="12" sm="6">
              <v-text-field v-model="form.attachment3Id" label="Attachment 3 ID" type="number" />
            </v-col>
          </v-row>
        </v-card-text>
        <v-card-actions>
          <v-spacer />
          <v-btn text @click="closeDialog()">Cancel</v-btn>
          <v-btn color="primary" @click="saveOrder()">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import OrderService from '@/services/orderService'
import { useAppStore } from '@/stores'

const orders = ref([])
const loading = ref(false)
const dialog = ref(false)
const editMode = ref(false)
const search = ref('')

const form = ref({
  id: null,
  memberId: null,
  cards: false,
  couponsCount: 0,
  attachment1Id: null,
  attachment2Id: null,
  attachment3Id: null
})

const headers = [
  { title: 'ID', key: 'id', width: 60 },
  { title: 'Member ID', key: 'memberId' },
  { title: 'Cards', key: 'cards' },
  { title: 'Coupons', key: 'couponsCount' },
  { title: 'Attachment 1', key: 'attachment1Id' },
  { title: 'Attachment 2', key: 'attachment2Id' },
  { title: 'Attachment 3', key: 'attachment3Id' },
  { title: 'Actions', key: 'actions', sortable: false, align: 'center' }
]

const appStore = useAppStore()

const filteredOrders = computed(() => {
  const q = search.value.toLowerCase()
  return orders.value.filter(
    (o) =>
      o.memberId?.toString().includes(q) ||
      o.id?.toString().includes(q)
  )
})

async function loadOrders() {
  loading.value = true
  try {
    orders.value = await OrderService.getAll()
  } catch (error) {
    console.error(error)
    appStore.notify('Failed to load orders', 'error')
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
      memberId: null,
      cards: false,
      couponsCount: 0,
      attachment1Id: null,
      attachment2Id: null,
      attachment3Id: null
    }
  }
  dialog.value = true
}

function closeDialog() {
  dialog.value = false
}

async function saveOrder() {
  try {
    if (editMode.value) {
      await OrderService.update(form.value.id, form.value)
      appStore.notify('Order updated successfully', 'success')
    } else {
      await OrderService.create(form.value)
      appStore.notify('Order created successfully', 'success')
    }
    dialog.value = false
    await loadOrders()
  } catch (error) {
    console.error(error)
    appStore.notify('Failed to save order', 'error')
  }
}

function editOrder(item) {
  openDialog(item)
}

async function deleteOrder(id) {
  if (!confirm('Are you sure you want to delete this order?')) return
  try {
    await OrderService.delete(id)
    appStore.notify('Order deleted', 'success')
    await loadOrders()
  } catch (error) {
    console.error(error)
    appStore.notify('Failed to delete order', 'error')
  }
}

onMounted(loadOrders)
</script>

<style scoped>
.v-data-table {
  border-radius: 12px;
}
.v-btn {
  text-transform: none;
}
</style>
