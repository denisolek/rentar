<template>
  <div class="app-home-vue frame-page">
    <Row :space="30">
      <Cell :xs='24' :sm='24' :md='24' :lg='8' :xl='8'>
        <div class="h-panel">
          <div class="h-panel-bar">
            <div class="h-panel-title">Agregaty</div>
          </div>
          <div class="home-part-body2">
            <template>
              <div id="chart">
                <apexchart type=pie width=380 :options="chartOptions" :series="series"/>
              </div>
            </template>
          </div>
        </div>
      </Cell>

    </Row>
  </div>
</template>
<script>

  export default {
    data() {
      return {
        series: [0, 0, 0],
        chartOptions: {
          labels: ['Rezerwacje', 'Klienci', 'Pojazdy'],
          responsive: [{
            breakpoint: 480,
            options: {
              chart: {
                width: 200
              },
              legend: {
                position: 'bottom'
              }
            }
          }]
        }
      }
    },
    mounted() {
      this.fetchRentals();
      this.fetchCustomers();
      this.fetchCars();
    },
    methods: {
      fetchRentals() {
        R.Rentals.fetchAll().then(resp => {
          if (resp.status === 200) {
            this.series[0] = resp.data.length;
          }
        })
      },
      fetchCustomers() {
        R.Customers.fetchAll().then(resp => {
          if (resp.status === 200) {
            this.series[1] = resp.data.length;
          }
        })
      },
      fetchCars() {
        R.Cars.fetchAll().then(resp => {
          if (resp.status === 200) {
            this.series[2] = resp.data.length;
          }
        })
      }
    }
  };
</script>
