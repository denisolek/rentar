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
                  <span class="h-panel-title">Dane klienta</span>
                  <div class="h-panel-right">
                    <router-link :to="{name: 'Customer', params: {id: customer.id}}">
                      <Button :circle="true" color="primary">Więcej</Button>
                    </router-link>
                  </div>
                </div>
                <div class="h-panel-body">
                  <template>
                    <Form :readonly="true" :labelWidth="150">
                      <FormItem label="Identyfikator">{{customer.id}}</FormItem>
                      <FormItem label="Imię">{{customer.firstName}}</FormItem>
                      <FormItem label="Nazwisko">{{customer.lastName}}</FormItem>
                      <FormItem label="Numer telefonu">{{customer.phoneNumber}}</FormItem>
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
                  <div class="h-panel-right">
                    <router-link :to="{name: 'Car', params: {id: car.id}}">
                      <Button :circle="true" color="primary">Więcej</Button>
                    </router-link>
                  </div>
                </div>
                <div class="h-panel-body">
                  <template>
                    <Form :readonly="true" :labelWidth="150">
                      <FormItem label="Identyfikator">{{car.id}}</FormItem>
                      <FormItem label="Marka">{{car.manufacturer}}</FormItem>
                      <FormItem label="Model">{{car.model}}</FormItem>
                      <FormItem label="Segment">{{car.segment}}</FormItem>
                      <FormItem label="Numer rejestracyjny">{{car.registrationNumber}}</FormItem>
                    </Form>
                  </template>
                </div>
              </div>
            </div>
          </template>
        </Cell>
        <Cell width="8">
          <template>
            <div>
              <div class="h-panel">
                <div class="h-panel-bar">
                  <span class="h-panel-title">Podsumowanie</span>
                  <div v-if="rental.status !== 'Anulowana'" class="h-panel-right">
                    <Poptip content="Jesteś tego pewny?" @confirm="cancelRental">
                      <Button :circle="true" color="red">Anuluj</Button>
                    </Poptip>
                  </div>
                </div>
                <div class="h-panel-body">
                  <template>
                    <Form :readonly="true" :labelWidth="150">
                      <FormItem label="Identyfikator">{{rental.id}}</FormItem>
                      <FormItem label="Data utworzenia">{{rental.createdAt}}</FormItem>
                      <FormItem label="Data edycji">{{rental.updatedAt}}</FormItem>
                      <FormItem label="Data wynajmu">{{rental.from}}</FormItem>
                      <FormItem label="Data zwrotu">{{rental.to}}</FormItem>
                      <FormItem label="Status">{{rental.status}}</FormItem>
                      <FormItem label="Ilość dni">{{rental.days}}</FormItem>
                      <FormItem label="Cena">{{rental.price}}</FormItem>
                    </Form>
                  </template>
                </div>
              </div>
            </div>
          </template>
        </Cell>
      </Row>
    </div>
  </div>
</template>

<script>
  import Breadcrumb from 'components/rentar-components/breadcrumbs/rentalBC'
  import dinero from "../../../../node_modules/dinero.js/build/esm/dinero";
  import {parsePhoneNumberFromString as parseMax} from 'libphonenumber-js/max'
  import moment from "moment";

  export default {
    data() {
      return {
        customer: {
          id: null,
          firstName: null,
          lastName: null,
          phoneNumber: null
        },
        car: {
          id: null,
          manufacturer: null,
          model: null,
          segment: null,
          registrationNumber: null
        },
        rental: {
          id: null,
          carId: null,
          customerId: null,
          createdAt: null,
          updatedAt: null,
          from: null,
          to: null,
          status: null,
          days: null,
          price: null
        }
      }
    },
    created() {
      this.loadViewData();
    },
    methods: {
      loadViewData: async function () {
        let rental = await R.Rentals.fetchOne(this.$route.params.id).then(resp => {
          if (resp.status === 200) {
            this.updateRental(resp.data)
            this.loadCarInfo();
            this.loadCustomerInfo();
          }
        });
      },
      updateRental(data) {
        this.rental.id = data.id;
        this.rental.carId = data.carId;
        this.rental.customerId = data.customerId;
        this.rental.createdAt = moment(data.createdAt).format("YYYY/MM/DD HH:mm");
        this.rental.updatedAt = moment(data.updatedAt).format("YYYY/MM/DD HH:mm");
        this.rental.from = moment(data.from).format("YYYY/MM/DD HH:mm");
        this.rental.to = moment(data.to).format("YYYY/MM/DD HH:mm");
        this.rental.status = {
          'Cancelled': 'Anulowana',
          'Completed': 'Zakończona',
          'Ongoing': 'W trakcie',
          'Upcoming': 'Nadchodząca'
        }[data.status];
        this.rental.days = moment(data.to).diff(moment(data.from), 'days');
        this.rental.price = dinero({
          amount: parseInt(data.price),
          currency: 'PLN'
        }).setLocale('pl-PL').toFormat('$0,0');
      },
      loadCarInfo() {
        R.Cars.fetchOne(this.rental.carId).then(resp => {
          if (resp.status === 200) {
            this.car.id = resp.data.id;
            this.car.manufacturer = resp.data.manufacturer;
            this.car.model = resp.data.model;
            this.car.segment = resp.data.segment;
            this.car.registrationNumber = resp.data.registrationNumber;
          }
        });
      },
      loadCustomerInfo() {
        R.Customers.fetchOne(this.rental.customerId).then(resp => {
          if (resp.status === 200) {
            this.customer.id = resp.data.id;
            this.customer.firstName = resp.data.firstName;
            this.customer.lastName = resp.data.lastName;
            this.customer.phoneNumber = parseMax(resp.data.phoneNumber).formatInternational();
          }
        });
      },
      cancelRental() {
        R.Rentals.cancel(this.rental.id).then(resp => {
          if (resp.status === 200) {
            this.$Message.success('Rezerwacja została anulowana');
            this.loadViewData();
          }
        });
      }
    },
    components: {
      Breadcrumb
    }
  }
</script>
