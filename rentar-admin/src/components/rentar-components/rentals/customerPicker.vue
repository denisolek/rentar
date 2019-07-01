<template>
  <Row>
    <template>
      <div>
        <div class="h-panel">
          <div class="h-panel-bar">
            <span class="h-panel-title">Wybierz klienta</span>
          </div>
          <div class="h-panel-body">
            <div>
              <div>
                <template>
                  <div>
                    <p>
                      <Search @search="customerSearch" v-model="customerSearchField"
                              placeholder="Szukaj klienta"></Search>
                    </p>
                  </div>
                </template>
                <Table :datas="customersTable">
                  <TableItem title="Klient" prop="name"></TableItem>
                  <TableItem title="Prawo jazdy" prop="drivingLicence"></TableItem>
                  <TableItem title="Akcja" align="center">
                    <template slot-scope="{data}">
                      <Button class="h-btn h-btn-blue h-btn-circle" :circle="true" @click="selectCustomer(data)">Wybierz
                      </Button>
                    </template>
                  </TableItem>
                  <div slot="empty">Brak danych</div>
                </Table>
                <p></p>
                <Pagination v-if="isPaginationVisible" v-model="pagination" @change="changePage"
                            layout="total,pager,jumper" small></Pagination>
              </div>
              <Loading text="Loading" :loading="loadingCustomers"></Loading>
            </div>
          </div>
        </div>
      </div>
    </template>
  </Row>
</template>

<script>
  import moment from "moment";

  export default {
    data() {
      return {
        pagination: {
          page: 1,
          size: 10,
          total: 0
        },
        isPaginationVisible: true,
        loadingCustomers: false,
        customerSearchField: null,
        customersTable: [],
        customers: []
      }
    },
    created() {
      this.fetchCustomers();
    },
    methods: {
      changePage(value) {
        this.pagination.page = value.cur;
        this.refreshTable()
      },
      refreshTable() {
        this.isPaginationVisible = true;
        let lastIndex = this.pagination.page * this.pagination.size;
        let firstIndex = lastIndex - this.pagination.size;
        let customers = this.customers.slice(firstIndex, lastIndex);
        this.customersTable = this.mapToTable(customers)
      },
      mapToTable: function (customers) {
        return customers.map(customer => ({
          id: customer.id,
          name: customer.firstName + ' ' + customer.lastName,
          drivingLicence: customer.drivingLicence
        }));
      },
      fetchCustomers() {
        this.loadingCustomers = true;
        R.Customers.fetchAll().then(resp => {
          if (resp.status === 200) {
            this.loadingCustomers = false;
            this.customers = resp.data;
            this.pagination.total = this.customers.length;
            this.refreshTable();
          }
        })
      },
      customerSearch(value) {
        if (value === '') {
          this.refreshTable();
        } else {
          let loweredValue = value.toLowerCase();
          let filteredCustomers = this.customers.filter(function (el) {
            return el.firstName.toLowerCase().includes(loweredValue) ||
              el.lastName.toLowerCase().includes(loweredValue) ||
              el.phoneNumber.toLowerCase().includes(loweredValue) ||
              el.drivingLicence.toLowerCase().includes(loweredValue) ||
              el.id.includes(loweredValue)
          });
          this.isPaginationVisible = false;
          this.customersTable = this.mapToTable(filteredCustomers)
        }
      },
      selectCustomer: async function (data) {
        let rentalDTO = {
          carId: this.$route.query.carId,
          customerId: data.id,
          from: moment(this.$route.query.from).format("YYYY-MM-DDTHH:mm"),
          to: moment(this.$route.query.to).format("YYYY-MM-DDTHH:mm")
        };
        let validateCreate = await R.Rentals.validateCreate(rentalDTO);

        if (validateCreate.status === 200) {
          let createRental = await R.Rentals.create(rentalDTO);
          if (createRental.status === 201) {
            this.$Message.success('Stworzono rezerwacje [' + createRental.data + ']!');
            this.$router.push('/rentals');
          }
        }
      }
    },
    components: {},
  }
</script>
