<template>
  <div class="h-panel frame-page">
    <div class="h-panel-bar">
      <Breadcrumb/>
    </div>
    <div class="h-panel-body">
      <Row>
        <Cell width="10">
          <UpdateCustomer/>
        </Cell>
        <Cell width="14">
          <CustomerRentals/>
        </Cell>
      </Row>
    </div>
  </div>
</template>

<script>
  import UpdateCustomer from 'components/rentar-components/customers/updateCustomer'
  import CustomerRentals from 'components/rentar-components/customers/customerRentals'
  import Breadcrumb from 'components/rentar-components/breadcrumbs/customerBC'
  export default {
    data() {
      return {
      }
    },
    components: {
      UpdateCustomer,
      CustomerRentals,
      Breadcrumb
    },
  }
</script>
