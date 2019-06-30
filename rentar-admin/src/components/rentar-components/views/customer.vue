<template>
  <div class="h-panel frame-page">
    <div class="h-panel-bar">
      <Breadcrumb/>
    </div>
    <div class="h-panel-body">
      <Row>
        <Cell width="8">
          <UpdateCustomer/>
        </Cell>
        <Cell width="16">
          <CustomerReservations/>
        </Cell>
      </Row>
    </div>
  </div>
</template>

<script>
  import UpdateCustomer from 'components/rentar-components/updateCustomer'
  import CustomerReservations from 'components/rentar-components/customerRentals'
  import Breadcrumb from 'components/rentar-components/breadcrumbs/customerBC'
  export default {
    data() {
      return {
      }
    },
    components: {
      UpdateCustomer,
      CustomerReservations,
      Breadcrumb
    },
  }
</script>
