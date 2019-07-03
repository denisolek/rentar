<template>
  <div>
    <Form
      ref="form"
      :validOnChange="validOnChange"
      :labelPosition="labelPosition"
      :labelWidth="130"
      :rules="validationRules"
      :model="model"
    >
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
        <Button color="blue" :circle="true" :loading="isLoading" @click="submitAsync">Dodaj klienta</Button>
        <Button @click="reset" :circle="true">wyczyść</Button>
      </FormItem>
    </Form>
  </div>
</template>
<script>
  import {parsePhoneNumberFromString as parseMax} from 'libphonenumber-js/max'
  import moment from "moment";

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
          required: ['firstName', 'lastName', 'email', 'birthDate', 'phoneNumber', 'drivingLicence', 'passport'],
          email: ['email'],
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
          this.$Message.error('Taki numer telefonu nie istnieje');
          this.isLoading = false;
          return;
        }

        if (moment(this.model.birthDate) > moment().subtract(18, 'years')) {
          this.$Message.error('Klient musi mieć ukończone 18 lat');
          this.isLoading = false;
          return;
        }

        let validateUnique = await R.Customers.validateAdd({
          email: this.model.email,
          drivingLicence: this.model.drivingLicence,
          passport: this.model.passport
        });

        if (validateUnique.status === 200) {
          let addCustomer = await R.Customers.add({
            firstName: this.model.firstName,
            lastName: this.model.lastName,
            email: this.model.email,
            birthDate: moment(this.model.birthDate).format('YYYY-MM-DD'),
            phoneNumber: parseMax(this.model.phoneNumber, 'PL').format("E.164"),
            drivingLicence: this.model.drivingLicence,
            passport: this.model.passport
          });
          if (addCustomer.status === 201) {
            this.$Message.success('Dodano klienta [' + addCustomer.data + ']!');
            this.$bus.emit('CustomerCreated', addCustomer.data);
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
