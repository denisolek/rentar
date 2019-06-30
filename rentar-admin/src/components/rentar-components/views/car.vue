<template>
  <div class="h-panel frame-page">
    <div class="h-panel-bar">
      <Breadcrumb/>
    </div>
    <div class="h-panel-body">
      <Row>
        <Cell width="8">
          <UpdateCar/>
        </Cell>
        <Cell width="16">
          <CarRentals/>
        </Cell>
      </Row>
    </div>
  </div>
</template>

<script>
  import UpdateCar from 'components/rentar-components/updateCar'
  import CarRentals from 'components/rentar-components/carRentals'
  import Breadcrumb from 'components/rentar-components/breadcrumbs/carBC'

  export default {
    data() {
      return {}
    },
    components: {
      UpdateCar,
      CarRentals,
      Breadcrumb
    },
  }
</script>
