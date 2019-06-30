<template>
  <div class="h-panel frame-page">
    <div class="h-panel-bar">
      <Breadcrumb/>
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
                <TableItem title="Imię" prop="firstName" :width="150"></TableItem>
                <TableItem title="Nazwisko" prop="lastName" :width="150"></TableItem>
                <TableItem title="Telefon" prop="phoneNumber" :width="150"></TableItem>
                <TableItem title="Prawo jazdy" prop="drivingLicence" :width="150"></TableItem>
                <TableItem title="Akcja" align="center" :width="100">
                  <template slot-scope="{data}">
                    <router-link :to="{name: 'Customer', params: {id: data.id}}">
                      <button class="h-btn h-btn-blue h-btn-circle">
                        <i class="h-icon-edit"></i>
                      </button>
                    </router-link>
                  </template>
                </TableItem>
                <div slot="empty">Brak danych</div>
              </Table>
            </div>
            <Loading text="Loading" :loading="loadingCustomers"></Loading>
          </div>
        </Cell>
        <Cell width="10">
          <AddCustomer/>
        </Cell>
      </Row>
    </div>
  </div>
</template>

<script>
  import AddCustomer from 'components/rentar-components/addCustomer'
  import Breadcrumb from 'components/rentar-components/breadcrumbs/customersBC'
  import {parsePhoneNumberFromString as parseMin} from 'libphonenumber-js/max'

  export default {
    data() {
      return {
        loadingCustomers: false,
        customerSearchField: null,
        customerTable: [],
        customers: [],
      }
    },
    created() {
      this.fetchCustomers();
      this.$bus.on('CustomerCreated', () => {
        setTimeout(() => {
          this.fetchCustomers()
        }, 1000);
      })
    },
    methods: {
      mapToTable: function (customers) {
        return customers.map(customer => ({
          id: customer.id,
          firstName: customer.firstName,
          lastName: customer.lastName,
          phoneNumber: parseMin(customer.phoneNumber).formatInternational(),
          drivingLicence: customer.drivingLicence
        }));
      }, fetchCustomers() {
        this.loadingCustomers = true;
        R.Customers.fetchAll().then(resp => {
          if (resp.status === 200) {
            this.loadingCustomers = false;
            this.customers = resp.data;
            this.customerTable = this.mapToTable(this.customers)
          }
        })
      },
      customerSearch(value) {
        if (value === '') {
          this.customerTable = this.mapToTable(this.customers)
        } else {
          let loweredValue = value.toLowerCase();
          let filteredCustomers = this.customers.filter(function (el) {
            return el.firstName.toLowerCase().includes(loweredValue) ||
              el.lastName.toLowerCase().includes(loweredValue) ||
              el.phoneNumber.toLowerCase().includes(loweredValue) ||
              el.drivingLicence.toLowerCase().includes(loweredValue)
          })
          this.customerTable = this.mapToTable(filteredCustomers)
        }
      },
    },
    components: {
      Breadcrumb,
      AddCustomer,
    },
  }
</script>
