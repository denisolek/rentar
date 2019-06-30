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
              <Table :datas="carTable" :height="400">
                <TableItem title="Nr rejestracyjny" prop="registrationNumber" :width="150"></TableItem>
                <TableItem title="Marka" prop="manufacturer" :width="150"></TableItem>
                <TableItem title="Model" prop="model" :width="150"></TableItem>
                <TableItem title="Rocznik" prop="productionYear" :width="150"></TableItem>
                <TableItem title="Akcja" align="center" :width="100">
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
  import AddCar from 'components/rentar-components/addCar'
  import Breadcrumb from 'components/rentar-components/breadcrumbs/carsBC'

  export default {
    data() {
      return {
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
      mapToTable: function (cars) {
        return cars.map(car => ({
          id: car.id,
          registrationNumber: car.registrationNumber,
          manufacturer: car.manufacturer,
          model: car.model,
          productionYear: car.productionYear
        }));
      }, fetchCars() {
        this.loadingCars = true;
        R.Cars.fetchAll().then(resp => {
          if (resp.status === 200) {
            this.loadingCars = false;
            this.cars = resp.data;
            this.carTable = this.mapToTable(this.cars)
          }
        })
      },
      carSearch(value) {
        if (value === '') {
          this.carTable = this.mapToTable(this.cars)
        } else {
          let loweredValue = value.toLowerCase();
          let filteredCars = this.cars.filter(function (el) {
            return el.registrationNumber.toLowerCase().includes(loweredValue) ||
              el.manufacturer.toLowerCase().includes(loweredValue) ||
              el.model.toLowerCase().includes(loweredValue) ||
              el.productionYear.toString().includes(loweredValue)
          });
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
