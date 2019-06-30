<template>
  <div class="h-panel frame-page">
    <div class="h-panel-bar">
      <Breadcrumb/>
    </div>
    <div class="h-panel-body">
      <Row>
        <Cell width="10">
          <UpdateCar/>
        </Cell>
        <Cell width="14">
          <CarRentals/>
        </Cell>
      </Row>
    </div>
  </div>
</template>

<script>
  import UpdateCar from 'components/rentar-components/cars/updateCar'
  import CarRentals from 'components/rentar-components/cars/carRentals'
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
