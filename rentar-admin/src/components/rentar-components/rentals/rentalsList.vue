<template>
  <Row>
    <div>
      <div>
        <Table :datas="rentalsTable">
          <TableItem title="Od" prop="from"></TableItem>
          <TableItem title="Do" prop="to"></TableItem>
          <TableItem title="Ilość dni" prop="days"></TableItem>
          <TableItem title="Cena" prop="price"></TableItem>
          <TableItem title="Status" align="center" :width="130">
            <template slot-scope="{data}">
              <Button :circle="true" transparent>{{data.status}}</Button>
            </template>
          </TableItem>
          <TableItem title="Akcja" align="center" :width="100">
            <template slot-scope="{data}">
              <router-link :to="{name: 'Rental', params: {id: data.id}}">
                <button class="h-btn h-btn-red h-btn-circle">
                  <i class="h-icon-edit"></i>
                </button>
              </router-link>
            </template>
          </TableItem>
          <div slot="empty">Brak danych</div>
        </Table>
        <p/>
        <Pagination v-if="isPaginationVisible" v-model="pagination" @change="changePage"
                    layout="total,pager,jumper" small></Pagination>
      </div>
      <Loading text="Loading" :loading="loadingRentals"></Loading>
    </div>
  </Row>
</template>

<script>
  import moment from "moment";
  import dinero from "../../../../node_modules/dinero.js/build/esm/dinero"

  export default {
    data() {
      return {
        pagination: {
          page: 1,
          size: 10,
          total: 0
        },
        isPaginationVisible: true,
        loadingRentals: false,
        rentalsTable: [],
        rentals: [],
      }
    },
    created() {
      this.fetchRentals();
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
        let rentals = this.rentals.slice(firstIndex, lastIndex);
        this.rentalsTable = this.mapToTable(rentals)
      },
      mapToTable: function (rentals) {
        return rentals.map(function (rental) {
          return {
            id: rental.id,
            from: moment(rental.from).format("YYYY/MM/DD "),
            to: moment(rental.to).format("YYYY/MM/DD "),
            days: moment(rental.to).diff(moment(rental.from), 'days'),
            price: dinero({amount: rental.price, currency: 'PLN'}).setLocale('pl-PL').toFormat('$0,0'),
            status: {
              'Cancelled': 'Anulowana',
              'Completed': 'Zakończona',
              'Ongoing': 'W trakcie',
              'Upcoming': 'Nadchodząca'
            }[rental.status]
          }
        });
      },
      fetchRentals() {
        this.loadingRentals = true;
        R.Rentals.fetchAll(this.$route.params.id).then(resp => {
          if (resp.status === 200) {
            this.loadingRentals = false;
            this.rentals = resp.data.sort(function (a, b) {
              return new Date(a.from).getTime() - new Date(b.from).getTime()
            }).reverse();
            this.pagination.total = this.rentals.length;
            this.refreshTable();
          }
        })
      }
    },
    components: {},
  }
</script>
