<template>
  <div v-width="450">
    <Form
      ref="form"
      :validOnChange="validOnChange"
      :labelPosition="labelPosition"
      :labelWidth="130"
      :rules="validationRules"
      :model="model"
    >
      <FormItem>
        <h2>Dodaj nowego klienta</h2>
      </FormItem>
      <FormItem label="Pole 'Imię' " prop="firstName">
        <template v-slot:label>Imię</template>
        <input type="text" v-model="model.firstName">
      </FormItem>
      <FormItem label="Pole 'Nazwisko'" prop="lastName">
        <template v-slot:label>Nazwisko</template>
        <input type="text" v-model="model.lastName">
      </FormItem>
      <FormItem label="Pole 'Email'" prop="email">
        <template v-slot:label>Email</template>
        <input type="text" v-model="model.email">
      </FormItem>
      <FormItem label="Pole 'Data urodzenia'" ref="datepicker" prop="birthDate">
        <template v-slot:label>Data urodzenia</template>
        <DatePicker v-model="model.birthDate"></DatePicker>
      </FormItem>
      <FormItem label="Pole 'Numer telefonu'" prop="phoneNumber">
        <template v-slot:label>Numer telefonu</template>
        <input type="text" v-model="model.phoneNumber">
      </FormItem>
      <FormItem label="Pole 'Prawo jazdy'" prop="drivingLicence">
        <template v-slot:label>Prawo jazdy</template>
        <input type="text" v-model="model.drivingLicence">
      </FormItem>
      <FormItem label="Pole 'Paszport'" prop="passport">
        <template v-slot:label>Paszport</template>
        <input type="text" v-model="model.passport">
      </FormItem>
      <FormItem>
        <Button color="blue" :loading="isLoading" @click="submitAsync">Dodaj klienta</Button>
        <Button @click="reset">wyczyść</Button>
      </FormItem>
    </Form>
  </div>
</template>
<script>
  import { parsePhoneNumberFromString as parseMax } from 'libphonenumber-js/max'
  const moment = require('moment');

  export default {
    data() {
      return {
        isLoading: false,
        labelPosition: 'right',
        model: {
          firstName: '',
          lastName: '',
          email: '',
          birthDate: '',
          phoneNumber: '',
          drivingLicence: '',
          passport: ''
        },
        validationRules: {
          required: ['firstName','lastName', 'email', 'birthDate', 'phoneNumber', 'drivingLicence', 'passport'],
          email: ['email'],
          globalmobile: ['phoneNumber'],
          rules: {
            firstName: {
              minLen: 2,
              maxLen: 50
            },
            lastName: {
              minLen: 2,
              maxLen: 50
            },
            drivingLicence: {
              minLen: 2,
              maxLen: 50
            },
            passport: {
              minLen: 2,
              maxLen: 50
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

        if (parseMax(this.model.phoneNumber, 'PL').isValid() === false) {
          this.$Message['error']('Taki numer telefonu nie istnieje');
          this.isLoading = false;
          return;
        }

        if (moment(this.model.birthDate) > moment().subtract(18, 'years')){
          this.$Message['error']('Klient musi mieć ukończone 18 lat');
          this.isLoading = false;
          return;
        }

        let isUnique = await R.Customers.validateAdd({email: this.model.email, drivingLicence: this.model.drivingLicence, passport: this.model.passport});
        if (isUnique.status === 200) {

        }

        this.isLoading = false;
      },
      resetDatepicker() {
        this.$refs.datepicker.resetValid();
      },
      reset() {
        this.$refs.form.resetValid();
      }
    }
  };
</script>
