<template>
  <div class="h-panel frame-page">
    <div class="h-panel-bar">
      <span class="h-panel-title">Klienci</span>
    </div>
    <div class="h-panel-body">
      <Row>
        <Cell width="14">
          <template>
            <div>
              <p>
                <Search @search="customerSearch" v-model="customerSearchField" placeholder="Szukaj klienta"></Search>
              </p>
            </div>
          </template>
          <div>
            <div>
              <Table :datas="customerTable" :height="400">
                <TableItem prop="$index" :width="30"></TableItem>
                <TableItem title="Imię" prop="firstName" :width="150"></TableItem>
                <TableItem title="Nazwisko" prop="lastName" :width="150"></TableItem>
                <TableItem title="Telefon" prop="phoneNumber" :width="150"></TableItem>
                <TableItem title="Prawo jazdy" prop="drivingLicence" :width="150"></TableItem>
                <TableItem title="Akcja" align="center" :width="100">
                  <template slot-scope="{data}">
                    <button class="h-btn h-btn-blue h-btn-circle" @click="edit(data.id)"><i class="h-icon-edit"></i>
                    </button>
                  </template>
                </TableItem>
                <div slot="empty">Brak danych</div>
              </Table>
            </div>
            <Loading text="Loading" :loading="loadingCustomers"></Loading>
          </div>
        </Cell>
        <Cell width="10">
          <AddCustomer />
        </Cell>
      </Row>
    </div>
  </div>
</template>

<script>
  import AddCustomer from '../addCustomer'

  export default {
    data () {
      return {
        loadingCustomers: false,
        customerSearchField: null,
        customerTable: [],
        customers: [],
      }
    },
    created () {
      this.fetchCustomers()
    },
    methods: {
      fetchCustomers () {
        this.loadingCustomers = true;
        R.Customers.fetchAll().then(resp => {
          if (resp.status === 200) {
            this.loadingCustomers = false;
            this.customers = resp.data;
            this.customerTable = [...this.customers]
          } else {
            this.$Message.info(`Couldn't fetch users`)
          }
        })
      },
      edit (customerId) {
        console.log('editing ' + customerId)
      },
      customerSearch (value) {
        if (value === '') {
          this.customerTable = [...this.customers]
        } else {
          let loweredValue = value.toLowerCase();
          this.customerTable = this.customers.filter(function (el) {
            return el.firstName.toLowerCase().includes(loweredValue) ||
              el.lastName.toLowerCase().includes(loweredValue) ||
              el.phoneNumber.toLowerCase().includes(loweredValue) ||
              el.drivingLicence.toLowerCase().includes(loweredValue)
          })
        }
      },
    },
    components: {
      AddCustomer,
    },
  }
</script>
