<template>
  <div class="h-panel frame-page">
    <div class="h-panel-bar">
      <Breadcrumb/>
    </div>
    <div class="h-panel-body">
      <Row type="flex" justify="space-around">
        <Cell width="8">
          <template>
            <div>
              <div class="h-panel">
                <div class="h-panel-bar">
                  <span class="h-panel-title">Dane rezerwacji</span>
                </div>
                <div class="h-panel-body">
                  <template>
                    <Form :readonly="true" :labelWidth="150">
                      <FormItem label="Data wynajmu">{{reservation.from}}</FormItem>
                      <FormItem label="Data zwrotu">{{reservation.to}}</FormItem>
                      <FormItem label="Ilość dni">{{reservation.days}}</FormItem>
                      <FormItem label="Cena">{{reservation.price}}</FormItem>
                    </Form>
                  </template>
                </div>
              </div>
            </div>
          </template>
          <template>
            <div>
              <div class="h-panel">
                <div class="h-panel-bar">
                  <span class="h-panel-title">Dane pojazdu</span>
                </div>
                <div class="h-panel-body">
                  <template>
                    <Form :readonly="true" :labelWidth="150">
                      <FormItem label="Identyfikator">{{car.id}}</FormItem>
                      <FormItem label="Marka">{{car.manufacturer}}</FormItem>
                      <FormItem label="Model">{{car.model}}</FormItem>
                      <FormItem label="Segment">{{car.segment}}</FormItem>
                      <FormItem label="Rocznik">{{car.productionYear}}</FormItem>
                      <FormItem label="Przebieg (w km)">{{car.mileage}}</FormItem>
                      <FormItem label="Skrzynia biegów">{{car.transmission}}</FormItem>
                      <FormItem label="Rodzaj paliwa">{{car.fuel}}</FormItem>
                      <FormItem label="Numer rejestracyjny">{{car.registrationNumber}}</FormItem>
                    </Form>
                  </template>
                </div>
              </div>
            </div>
          </template>
        </Cell>
        <Cell width="14">
          <CustomerPicker/>
        </Cell>
      </Row>
    </div>
  </div>
</template>

<script>
  import CustomerPicker from 'components/rentar-components/rentals/customerPicker'
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
        this.reservation.from = moment(this.$route.query.from).format("YYYY/MM/DD HH:mm");
        this.reservation.to = moment(this.$route.query.to).format("YYYY/MM/DD HH:mm");
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
      CustomerPicker
    }
  }
</script>
