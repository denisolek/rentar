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
      <FormItem label="Imię" prop="firstName">
        <template v-slot:label>Imię</template>
        <input type="text" v-model="model.firstName">
      </FormItem>
      <FormItem label="lastName" prop="lastName">
        <template v-slot:label>Nazwisko</template>
        <input type="text" v-model="model.lastName">
      </FormItem>
      <FormItem label="email" prop="email">
        <template v-slot:label>Email</template>
        <input type="text" v-model="model.email">
      </FormItem>
      <FormItem label="birthDate" prop="birthDate">
        <template v-slot:label>Data urodzenia</template>
        <input type="text" v-model="model.birthDate">
      </FormItem>
      <FormItem label="phoneNumber" prop="phoneNumber">
        <template v-slot:label>Numer telefonu</template>
        <input type="text" v-model="model.phoneNumber">
      </FormItem>
      <FormItem label="drivingLicence" prop="drivingLicence">
        <template v-slot:label>Prawo jazdy</template>
        <input type="text" v-model="model.drivingLicence">
      </FormItem>
      <FormItem label="passport" prop="passport">
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
          required: ['lastName', 'email', 'birthDate', 'phoneNumber', 'drivingLicence', 'passport'],
          rules:{
            firstName: {

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
      reset() {
        this.$refs.form.resetValid();
      }
    }
  };
</script>
