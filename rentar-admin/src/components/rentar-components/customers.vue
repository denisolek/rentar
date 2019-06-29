<template>
  <div>
    <template>
      <div>
        <p>
          <Search @search="customerSearch" v-model="customerSearchField" placeholder="Szukaj klienta" ></Search>
        </p>
      </div>
    </template>
    <Table :datas="customerTable" :height="400">
      <TableItem title="Index" prop="$index" align="center" :width="30"></TableItem>
      <TableItem title="Imię" prop="firstName" :width="150"></TableItem>
      <TableItem title="Nazwisko" prop="lastName" :width="150"></TableItem>
      <TableItem title="Telefon" prop="phoneNumber" :width="150"></TableItem>
      <TableItem title="Prawo jazdy" prop="drivingLicence" :width="150"></TableItem>
      <TableItem title="Akcja" align="center" :width="100"><template slot-scope="{data}"><button class="h-btn h-btn-blue h-btn-circle" @click="edit(data.id)"><i class="h-icon-edit"></i></button></template></TableItem>
      <div slot="empty">Brak danych</div>
    </Table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      loadingCustomers: false,
      customerSearchField: null,
      customerTable: [],
      customers: [
        { id: 5, firstName: 'Jan', lastName: 'Nowak', phoneNumber: "+48 508 201 113", drivingLicence: '41225/12/0118' },
        { id: 5, firstName: 'Anna', lastName: 'Kowalska', phoneNumber: "+48 508 201 114", drivingLicence: '41225/12/0118' }
      ]
    };
  },
  created() {
    this.fetchCustomers();
  },
  methods: {
    fetchCustomers() {
      this.loading = true
      R.Customers.fetchAll().then(resp => {
        if (resp.ok) {
          this.loading = false
          this.customers = resp
          this.customerTable = [...this.customers]
        } else {
          this.$Message.info(`Couldn't fetch users`);
        }
      });
    },
    edit(customerId) {
      console.log("editing " + customerId)
    },
    customerSearch(value) {
      if (value == "") {
        this.customerTable = [...this.customers]
      } else {
        var loweredValue = value.toLowerCase()
        this.customerTable = this.customers.filter(function (el) {
          return el.firstName.toLowerCase().includes(loweredValue) || 
          el.lastName.toLowerCase().includes(loweredValue) || 
          el.phoneNumber.toLowerCase().includes(loweredValue) || 
          el.drivingLicence.toLowerCase().includes(loweredValue);
        });
      }
    }
  }
};
</script>