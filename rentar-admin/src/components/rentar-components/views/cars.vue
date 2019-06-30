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
                <Search @search="carSearch" v-model="carSearchField" placeholder="Szukaj auta"></Search>
              </p>
            </div>
          </template>
          <div>
            <div>
              <Table :datas="carTable">
                <TableItem title="Nr rejestracyjny" prop="registrationNumber"></TableItem>
                <TableItem title="Marka" prop="manufacturer"></TableItem>
                <TableItem title="Model" prop="model"></TableItem>
                <TableItem title="Rocznik" prop="productionYear"></TableItem>
                <TableItem title="Akcja" align="center">
                  <template slot-scope="{data}">
                    <router-link :to="{name: 'Car', params: {id: data.id}}">
                      <button class="h-btn h-btn-blue h-btn-circle">
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
            <Loading text="Loading" :loading="loadingCars"></Loading>
          </div>
        </Cell>
        <Cell width="10">
          <AddCar/>
        </Cell>
      </Row>
    </div>
  </div>
</template>

<script>
  import AddCar from 'components/rentar-components/cars/addCar'
  import Breadcrumb from 'components/rentar-components/breadcrumbs/carsBC'

  export default {
    data() {
      return {
        pagination: {
          page: 1,
          size: 10,
          total: 0
        },
        isPaginationVisible: true,
        loadingCars: false,
        carSearchField: null,
        carTable: [],
        cars: [],
      }
    },
    created() {
      this.fetchCars();
      this.$bus.on('CarCreated', () => {
        setTimeout(() => {
          this.fetchCars()
        }, 700);
      })
    },
    methods: {
      changePage(value) {
        this.pagination.page = value.cur;
        this.refreshTable()
      },
      mapToTable: function (cars) {
        return cars.map(car => ({
          id: car.id,
          registrationNumber: car.registrationNumber,
          manufacturer: car.manufacturer,
          model: car.model,
          productionYear: car.productionYear
        }));
      },
      refreshTable() {
        this.isPaginationVisible = true;
        let lastIndex = this.pagination.page * this.pagination.size;
        let firstIndex = lastIndex - this.pagination.size;
        let cars = this.cars.slice(firstIndex, lastIndex);
        this.carTable = this.mapToTable(cars)
      },
      fetchCars() {
        this.loadingCars = true;
        R.Cars.fetchAll().then(resp => {
          if (resp.status === 200) {
            this.loadingCars = false;
            this.cars = resp.data;
            this.pagination.total = this.cars.length;
            this.refreshTable();
          }
        })
      },
      carSearch(value) {
        if (value === '') {
          this.refreshTable();
        } else {
          let loweredValue = value.toLowerCase();
          let filteredCars = this.cars.filter(function (el) {
            return el.registrationNumber.toLowerCase().includes(loweredValue) ||
              el.manufacturer.toLowerCase().includes(loweredValue) ||
              el.model.toLowerCase().includes(loweredValue) ||
              el.productionYear.toString().includes(loweredValue) ||
              el.id.includes(loweredValue)
          });
          this.isPaginationVisible = false;
          this.carTable = this.mapToTable(filteredCars)
        }
      },
    },
    components: {
      Breadcrumb,
      AddCar,
    },
  }
</script>
