<template>
  <div class="h-panel frame-page">
    <div class="h-panel-bar">
      <Breadcrumb/>
    </div>
    <div class="h-panel-body">
      <Row>
        <Cell width="10" v-padding="30">
          <h2>Dane rezerwacji</h2><br>
          <b>Data wynajmu: </b><span>{{ reservation.from }}</span><br>
          <b>Data zwrotu: </b><span>{{ reservation.to }}</span><br>
          <b>Ilość dni: </b><span>{{ reservation.days }}</span><br>
          <b>Cena: </b><span>{{ reservation.price }}</span><br><br><br>
          <h2>Dane pojazdu</h2><br>
          <b>Identyfikator: </b><span>{{ car.id }}</span><br>
          <b>Marka: </b><span>{{ car.manufacturer }}</span><br>
          <b>Model: </b><span>{{ car.model }}</span><br>
          <b>Segment: </b><span>{{ car.segment }}</span><br>
          <b>Rocznik: </b><span>{{ car.productionYear }}</span><br>
          <b>Przebieg (w km): </b><span>{{ car.mileage }}</span><br>
          <b>Skrzynia biegów: </b><span>{{ car.transmission }}</span><br>
          <b>Rodzaj paliwa: </b><span>{{ car.fuel }}</span><br>
          <b>Numer rejestracyjny: </b><span>{{ car.registrationNumber }}</span><br><br>
        </Cell>
        <Cell width="14">
          <RentalsList/>
        </Cell>
      </Row>
    </div>
  </div>
</template>

<script>
  import EstimateRentals from 'components/rentar-components/rentals/estimate'
  import RentalsList from 'components/rentar-components/rentals/rentalsList'
  import Breadcrumb from 'components/rentar-components/breadcrumbs/createRentalBC'
  import dinero from "../../../../node_modules/dinero.js/build/esm/dinero";
  import moment from "moment";

  export default {
    data() {
      return {
        reservation: {
          from: null,
          to: null,
          days: null,
          price: null
        },
        car: {
          id: null,
          manufacturer: null,
          model: null,
          segment: null,
          productionYear: null,
          mileage: null,
          transmission: null,
          fuel: null,
          registrationNumber: null
        }
      }
    },
    created() {
      this.loadViewData();
    },
    methods: {
      loadViewData() {
        this.loadReservationInfo();
        this.loadCarInfo();
      },
      loadReservationInfo() {
        this.reservation.from = moment(this.$route.query.from).format("YYYY-MM-DD HH:mm:ss");
        this.reservation.to = moment(this.$route.query.to).format("YYYY-MM-DD HH:mm:ss");
        this.reservation.days = moment(this.$route.query.to).diff(moment(this.$route.query.from), 'days');
        this.reservation.price = dinero({
          amount: parseInt(this.$route.query.price),
          currency: 'PLN'
        }).setLocale('pl-PL').toFormat('$0,0');
      },
      loadCarInfo() {
        R.Cars.fetchOne(this.$route.query.carId).then(resp => {
          if (resp.status === 200) {
            this.car.id = resp.data.id;
            this.car.manufacturer = resp.data.manufacturer;
            this.car.model = resp.data.model;
            this.car.segment = resp.data.segment;
            this.car.productionYear = resp.data.productionYear;
            this.car.mileage = resp.data.mileage;
            this.car.transmission = {
              'MANUAL': 'manualna',
              'AUTOMATIC': 'automatyczna'
            }[resp.data.transmission];
            this.car.fuel = {
              'PETROL': 'benzyna',
              'DIESEL': 'diesel',
              'ELECTRICITY': 'elektryczny'
            }[resp.data.fuel];
            this.car.registrationNumber = resp.data.registrationNumber;
          }
        });
      }
    },
    components: {
      Breadcrumb,
      EstimateRentals,
      RentalsList
    },
  }
</script>
