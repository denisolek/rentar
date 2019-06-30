<template>
  <Row v-padding="30">
    <h2>Dane auta</h2><br>
    <b>Identyfikator: </b><span>{{ editedCar.id }}</span><br>
    <b>Data utworzenia: </b><span>{{ editedCar.createdAt }}</span><br>
    <b>Data edycji: </b><span>{{ editedCar.updatedAt }}</span><br><br>
    <Form
      ref="form"
      :validOnChange="validOnChange"
      :labelPosition="labelPosition"
      :labelWidth="130"
      :rules="validationRules"
      :model="editedCar"
      :mode="mode"
    >
      <FormItem label="Pole 'Marka' " prop="manufacturer">
        <template v-slot:label>Marka</template>
        <input type="text" v-model="editedCar.manufacturer">
      </FormItem>
      <FormItem label="Pole 'Model'" prop="model">
        <template v-slot:label>Model</template>
        <input type="text" v-model="editedCar.model">
      </FormItem>
      <FormItem label="Pole 'Segment'" prop="segment">
        <template v-slot:label>Segment</template>
        <Select v-model="editedCar.segment" :datas="segments"></Select>
      </FormItem>
      <FormItem label="Pole 'Rocznik'" ref="datepicker" prop="productionYear">
        <template v-slot:label>Rocznik</template>
        <DatePicker v-model="editedCar.productionYear" :type="'year'"></DatePicker>
      </FormItem>
      <FormItem label="Pole 'Przebieg'" prop="mileage">
        <template v-slot:label>Przebieg (w km)</template>
        <input type="text" v-model="editedCar.mileage">
      </FormItem>
      <FormItem label="Pole 'Skrzynia biegów'" prop="transmission">
        <template v-slot:label>Skrzynia biegów</template>
        <Select v-model="editedCar.transmission" :datas="transmissions"></Select>
      </FormItem>
      <FormItem label="Pole 'Rodzaj paliwa'" prop="fuel">
        <template v-slot:label>Rodzaj paliwa</template>
        <Select v-model="editedCar.fuel" :datas="fuels"></Select>
      </FormItem>
      <FormItem label="Pole 'Numer rejestracyjny'" prop="registrationNumber">
        <template v-slot:label>Numer rejestracyjny</template>
        <input type="text" v-model="editedCar.registrationNumber">
      </FormItem>
      <FormItem label="Pole 'Cena / dzień'" prop="dailyPrice">
        <template v-slot:label>Cena / dzień</template>
        <input type="text" v-model="editedCar.dailyPrice">
      </FormItem>
      <FormItem>
        <Button color="blue" :loading="isLoading" @click="submitAsync">Zapisz</Button>
        <Button @click="updateViewCar">Przywróć</Button>
      </FormItem>
    </Form>
  </Row>
</template>
<script>
  import moment from "moment";
  import dinero from "dinero.js";

  export default {
    data() {
      return {
        mode: 'single',
        isLoading: false,
        labelPosition: 'left',
        segments: ['A', 'A1', 'B', 'B2', 'C', 'C1', 'D2', 'E', 'E1', 'E2', 'F1', 'G1', 'G3'],
        transmissions: [
          {title: 'Manualna', key: 'MANUAL'},
          {title: 'Automatyczna', key: 'AUTOMATIC'}
        ],
        fuels: [
          {title: 'Benzyna', key: 'PETROL'},
          {title: 'Diesel', key: 'DIESEL'},
          {title: 'Elektryczny', key: 'ELECTRICITY'},
        ],
        car: null,
        editedCar: {
          id: '',
          createdAt: '',
          updatedAt: '',
          manufacturer: '',
          model: '',
          segment: '',
          productionYear: '',
          mileage: '',
          transmission: '',
          fuel: '',
          registrationNumber: '',
          dailyPrice: ''
        },
        validationRules: {
          required: ['manufacturer', 'model', 'segment', 'productionYear', 'mileage', 'transmission', 'fuel', 'registrationNumber', 'dailyPrice'],
          rules: {
            manufacturer: {
              minLen: 2,
              maxLen: 50
            },
            model: {
              minLen: 2,
              maxLen: 50
            },
            mileage: {
              type: 'number',
              min: 1,
              max: 9999999
            },
            registrationNumber: {
              minLen: 4,
              maxLen: 10
            },
            dailyPrice: {
              type: 'number',
              min: 1
            }
          }
        },
        validOnChange: true
      };
    },
    created() {
      this.fetchCar();
    },
    methods: {
      updateViewCar() {
        this.editedCar.id = this.car.id;
        this.editedCar.createdAt = moment(this.car.createdAt).format("YYYY-MM-DD HH:mm:ss");
        this.editedCar.updatedAt = moment(this.car.updatedAt).format("YYYY-MM-DD HH:mm:ss");
        this.editedCar.manufacturer = this.car.manufacturer;
        this.editedCar.model = this.car.model;
        this.editedCar.segment = this.car.segment;
        this.editedCar.productionYear = this.car.productionYear.toString();
        this.editedCar.mileage = this.car.mileage;
        this.editedCar.transmission = this.car.transmission;
        this.editedCar.fuel = this.car.fuel;
        this.editedCar.registrationNumber = this.car.registrationNumber;
        this.editedCar.dailyPrice = this.car.dailyPrice / 100;
      },
      fetchCar() {
        R.Cars.fetchOne(this.$route.params.id).then(resp => {
          if (resp.status === 200) {
            this.car = resp.data;
            this.updateViewCar();
          }
        });
      },
      submitAsync: async function () {
        this.isLoading = true;
        if (this.$refs.form.valid().result === false) {
          this.isLoading = false;
          return;
        }

        if (this.editedCar.productionYear > new Date().getFullYear()) {
          this.$Message.error('Samochód z przyszłości?');
          this.isLoading = false;
          return;
        }

        let validateUnique = await R.Cars.validateUpdate(
          this.$route.params.id,
          {registrationNumber: this.editedCar.registrationNumber}
        );

        if (validateUnique.status === 200) {
          let updateDTO = {
            manufacturer: this.editedCar.manufacturer,
            model: this.editedCar.model,
            segment: this.editedCar.segment,
            productionYear: this.editedCar.productionYear,
            mileage: this.editedCar.mileage,
            transmission: this.editedCar.transmission,
            fuel: this.editedCar.fuel,
            registrationNumber: this.editedCar.registrationNumber,
            dailyPrice: this.editedCar.dailyPrice * 100
          };
          let updateCar = await R.Cars.update(this.$route.params.id, updateDTO);
          if (updateCar.status === 200) {
            this.$Message.success('Zapisano auto [' + this.$route.params.id + ']!');
            this.$bus.emit('CarUpdated', updateCar.data);
          }
        }
        this.fetchCar();
        this.isLoading = false;
      }
    }
  };
</script>
