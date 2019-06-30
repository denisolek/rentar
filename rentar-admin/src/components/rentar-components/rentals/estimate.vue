<template>
  <Row v-padding="30">
    <h2>Szukaj pojazdu</h2><br>
    <div>
      <div>
        <Form
          ref="form"
          :validOnChange="validOnChange"
          :labelPosition="labelPosition"
          :labelWidth="40"
          :rules="validationRules"
          :model="search"
        >
          <FormItem label="Pole 'od' " prop="from">
            <template v-slot:label>Od</template>
            <DatePicker v-model="search.from" type="datetime" :option="{end: search.to, start: yesterday}"></DatePicker>
          </FormItem>
          <FormItem label="Pole 'do' " prop="to">
            <template v-slot:label>Do</template>
            <DatePicker v-model="search.to" type="datetime" :option="{start: search.from}"></DatePicker>
          </FormItem>
          <FormItem>
            <Button color="blue" :circle="true" icon="h-icon-search" @click="estimate" :loading="isLoading">Search
            </Button>
          </FormItem>
        </Form>
        <Table :datas="resultsTable">
          <TableItem title="Pojazd" prop="carName"></TableItem>
          <TableItem title="Cena" prop="price"></TableItem>
          <TableItem title="Akcja" align="center" :width="100">
            <template slot-scope="{data}">
              <router-link
                :to="{name: 'CreateRental', query: {carId: data.carId, from: data.from, to: data.to, price: data.price}}">
                <button class="h-btn h-btn-blue h-btn-circle">Wybierz</button>
              </router-link>
            </template>
          </TableItem>
          <div slot="empty">Brak danych</div>
        </Table>
        <p/>
        <Pagination v-if="isPaginationVisible" v-model="pagination" @change="changePage"
                    layout="total,pager,jumper" small></Pagination>
      </div>
      <Loading text="Loading" :loading="loadingSearch"></Loading>
    </div>
  </Row>
</template>

<script>
  import moment from "moment";
  import dinero from "../../../../node_modules/dinero.js/build/esm/dinero"

  export default {
    data() {
      return {
        mode: 'single',
        isLoading: false,
        labelPosition: 'left',
        validationRules: {
          required: ['from', 'to']
        },
        validOnChange: false,
        search: {
          from: null,
          to: null
        },
        yesterday: moment().subtract(1, 'days').toDate(),
        pagination: {
          page: 1,
          size: 10,
          total: 0
        },
        isPaginationVisible: true,
        loadingSearch: false,
        resultsTable: [],
        results: [],
      }
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
        let results = this.results.slice(firstIndex, lastIndex);
        this.resultsTable = this.mapToTable(results)
      },
      mapToTable: function (results) {
        return results.map(function (result) {
          return {
            carId: result.carId,
            carName: result.carName,
            price: dinero({amount: result.price, currency: 'PLN'}).setLocale('pl-PL').toFormat('$0,0'),
            from: result.from,
            to: result.to,
          }
        });
      },
      estimate() {
        this.loadingSearch = true;
        let from = moment(this.search.from).format('YYYY-MM-DDTHH:mm');
        let to = moment(this.search.to).format('YYYY-MM-DDTHH:mm');
        R.Rentals.estimate(from, to).then(resp => {
          if (resp.status === 200) {
            this.loadingSearch = false;
            this.results = resp.data;
            this.pagination.total = this.results.length;
            this.refreshTable();
          }
        })
      }
    }
  }
</script>
