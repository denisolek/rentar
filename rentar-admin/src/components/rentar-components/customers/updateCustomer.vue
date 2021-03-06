<template>
  <Row>
    <template>
      <Form :readonly="true" :labelWidth="130">
        <FormItem label="Identyfikator">{{editedCustomer.id}}</FormItem>
        <FormItem label="Data utworzenia">{{editedCustomer.createdAt}}</FormItem>
        <FormItem label="Data edycji">{{editedCustomer.updatedAt}}</FormItem>
      </Form>
    </template>
    <br>
    <Form
      ref="form"
      :validOnChange="validOnChange"
      :labelPosition="labelPosition"
      :labelWidth="130"
      :rules="validationRules"
      :model="editedCustomer"
      :mode="mode"
    >
      <FormItem label="Pole 'Imię' " prop="firstName">
        <template v-slot:label>Imię</template>
        <input type="text" v-model="editedCustomer.firstName">
      </FormItem>
      <FormItem label="Pole 'Nazwisko'" prop="lastName">
        <template v-slot:label>Nazwisko</template>
        <input type="text" v-model="editedCustomer.lastName">
      </FormItem>
      <FormItem label="Pole 'Email'" prop="email">
        <template v-slot:label>Email</template>
        <input type="text" v-model="editedCustomer.email">
      </FormItem>
      <FormItem label="Pole 'Data urodzenia'" ref="datepicker" prop="birthDate">
        <template v-slot:label>Data urodzenia</template>
        <DatePicker v-model="editedCustomer.birthDate"></DatePicker>
      </FormItem>
      <FormItem label="Pole 'Numer telefonu'" prop="phoneNumber">
        <template v-slot:label>Numer telefonu</template>
        <input type="text" v-model="editedCustomer.phoneNumber">
      </FormItem>
      <FormItem label="Pole 'Prawo jazdy'" prop="drivingLicence">
        <template v-slot:label>Prawo jazdy</template>
        <input type="text" v-model="editedCustomer.drivingLicence">
      </FormItem>
      <FormItem label="Pole 'Paszport'" prop="passport">
        <template v-slot:label>Paszport</template>
        <input type="text" v-model="editedCustomer.passport">
      </FormItem>
      <FormItem>
        <Button color="blue" :circle="true" :loading="isLoading" @click="submitAsync">Zapisz</Button>
        <Button @click="updateViewCustomer" :circle="true">Przywróć</Button>
      </FormItem>
    </Form>
  </Row>
</template>
<script>
  import {parsePhoneNumberFromString as parseMax} from 'libphonenumber-js/max'
  import moment from "moment";

  export default {
    data() {
      return {
        mode: 'single',
        isLoading: false,
        labelPosition: 'right',
        customer: null,
        editedCustomer: {
          id: '',
          createdAt: '',
          updatedAt: '',
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
    created() {
      this.fetchCustomer();
    },
    methods: {
      updateViewCustomer() {
        this.editedCustomer.id = this.customer.id;
        this.editedCustomer.createdAt = moment(this.customer.createdAt).format("YYYY/MM/DD HH:mm");
        this.editedCustomer.updatedAt = moment(this.customer.updatedAt).format("YYYY/MM/DD HH:mm");
        this.editedCustomer.firstName = this.customer.firstName;
        this.editedCustomer.lastName = this.customer.lastName;
        this.editedCustomer.email = this.customer.email;
        this.editedCustomer.birthDate = this.customer.birthDate;
        this.editedCustomer.phoneNumber = parseMax(this.customer.phoneNumber).formatInternational();
        this.editedCustomer.drivingLicence = this.customer.drivingLicence;
        this.editedCustomer.passport = this.customer.passport;
      },
      fetchCustomer() {
        R.Customers.fetchOne(this.$route.params.id).then(resp => {
          if (resp.status === 200) {
            this.customer = resp.data;
            this.updateViewCustomer();
          }
        });
      },
      submitAsync: async function () {
        this.isLoading = true;
        if (this.$refs.form.valid().result === false) {
          this.isLoading = false;
          return;
        }

        if (parseMax(this.editedCustomer.phoneNumber, 'PL').isValid() === false) {
          this.$Message.error('Taki numer telefonu nie istnieje');
          this.isLoading = false;
          return;
        }

        if (moment(this.editedCustomer.birthDate) > moment().subtract(18, 'years')) {
          this.$Message.error('Klient musi mieć ukończone 18 lat');
          this.isLoading = false;
          return;
        }

        let validateUpdateDTO = {
          email: this.editedCustomer.email,
          drivingLicence: this.editedCustomer.drivingLicence,
          passport: this.editedCustomer.passport
        };
        let validateUnique = await R.Customers.validateUpdate(this.editedCustomer.id, validateUpdateDTO);

        if (validateUnique.status === 200) {
          let updateDTO = {
            firstName: this.editedCustomer.firstName,
            lastName: this.editedCustomer.lastName,
            email: this.editedCustomer.email,
            birthDate: moment(this.editedCustomer.birthDate).format('YYYY-MM-DD'),
            phoneNumber: parseMax(this.editedCustomer.phoneNumber, 'PL').format("E.164"),
            drivingLicence: this.editedCustomer.drivingLicence,
            passport: this.editedCustomer.passport
          };
          let updateCustomer = await R.Customers.update(this.editedCustomer.id, updateDTO);
          if (updateCustomer.status === 200) {
            this.$Message.success('Zapisano klienta [' + this.editedCustomer.id + ']!');
            this.$bus.emit('CustomerUpdated', updateCustomer.data);
          }
        }
        this.fetchCustomer();
        this.isLoading = false;
      }
    }
  };
</script>
