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
        <Button color="primary" :loading="isLoading" @click="submit">Dodaj klienta</Button>&nbsp;&nbsp;&nbsp;
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
      submit() {
        this.isLoading = true;
        let validResult = this.$refs.form.valid();
        if (validResult.result) {
          this.$Message('Successful verification');
          setTimeout(() => {
            this.isLoading = false;
          }, 1000);
        } else {
          this.isLoading = false;
        }
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
