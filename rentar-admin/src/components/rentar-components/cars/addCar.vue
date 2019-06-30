<template>
  <div>
    <Form
      ref="form"
      :validOnChange="validOnChange"
      :labelPosition="labelPosition"
      :labelWidth="130"
      :rules="validationRules"
      :model="carModel"
    >
      <FormItem>
        <h2>Dodaj nowe auto</h2>
      </FormItem>
      <FormItem label="Pole 'Marka' " prop="manufacturer">
        <template v-slot:label>Marka</template>
        <input type="text" v-model="carModel.manufacturer">
      </FormItem>
      <FormItem label="Pole 'Model'" prop="model">
        <template v-slot:label>Model</template>
        <input type="text" v-model="carModel.model">
      </FormItem>
      <FormItem label="Pole 'Segment'" prop="segment">
        <template v-slot:label>Segment</template>
        <Select v-model="carModel.segment" :datas="segments"></Select>
      </FormItem>
      <FormItem label="Pole 'Rocznik'" ref="datepicker" prop="productionYear">
        <template v-slot:label>Rocznik</template>
        <DatePicker v-model="carModel.productionYear" :type="'year'"></DatePicker>
      </FormItem>
      <FormItem label="Pole 'Przebieg'" prop="mileage">
        <template v-slot:label>Przebieg (w km)</template>
        <input type="text" v-model="carModel.mileage">
      </FormItem>
      <FormItem label="Pole 'Skrzynia biegów'" prop="transmission">
        <template v-slot:label>Skrzynia biegów</template>
        <Select v-model="carModel.transmission" :datas="transmissions"></Select>
      </FormItem>
      <FormItem label="Pole 'Rodzaj paliwa'" prop="fuel">
        <template v-slot:label>Rodzaj paliwa</template>
        <Select v-model="carModel.fuel" :datas="fuels"></Select>
      </FormItem>
      <FormItem label="Pole 'Numer rejestracyjny'" prop="registrationNumber">
        <template v-slot:label>Numer rejestracyjny</template>
        <input type="text" v-model="carModel.registrationNumber">
      </FormItem>
      <FormItem label="Pole 'Cena / dzień'" prop="dailyPrice">
        <template v-slot:label>Cena / dzień</template>
        <input type="text" v-model="carModel.dailyPrice">
      </FormItem>
      <FormItem>
        <Button color="blue" :loading="isLoading" @click="submitAsync">Dodaj auto</Button>
        <Button @click="reset">wyczyść</Button>
      </FormItem>
    </Form>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        isLoading: false,
        labelPosition: 'right',
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
        carModel: {
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
    methods: {
      submitAsync: async function () {
        this.isLoading = true;
        if (this.$refs.form.valid().result === false) {
          this.isLoading = false;
          return;
        }

        if (this.carModel.productionYear > new Date().getFullYear()) {
          this.$Message.error('Samochód z przyszłości?');
          this.isLoading = false;
          return;
        }

        let validateUnique = await R.Cars.validateAdd({registrationNumber: this.carModel.registrationNumber});

        if (validateUnique.status === 200) {
          let addCar = await R.Cars.add({
            manufacturer: this.carModel.manufacturer,
            model: this.carModel.model,
            segment: this.carModel.segment,
            productionYear: this.carModel.productionYear,
            mileage: this.carModel.mileage,
            transmission: this.carModel.transmission,
            fuel: this.carModel.fuel,
            registrationNumber: this.carModel.registrationNumber,
            dailyPrice: this.carModel.dailyPrice * 100
          });
          if (addCar.status === 201) {
            this.$Message.success('Dodano auto [' + addCar.data + ']!');
            this.$bus.emit('CarCreated', addCar.data);
          }
        }
        this.reset();
        this.isLoading = false;
      },
      reset() {
        this.$refs.form.resetValid();
      }
    }
  };
</script>
