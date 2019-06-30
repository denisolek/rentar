<template>
  <div class="h-panel frame-page">
    <div class="h-panel-bar">
      <Breadcrumb/>
    </div>
    <div class="h-panel-body">
      <Row>
        <Cell width="10">
          <EstimateRentals/>
        </Cell>
        <Cell width="14">
          <RentalsList/>
        </Cell>
      </Row>
    </div>
  </div>
</template>

<script>
  import EstimateRentals from 'components/rentar-components/rentals/estimate'
  import RentalsList from 'components/rentar-components/rentals/rentalsList'
  import Breadcrumb from 'components/rentar-components/breadcrumbs/rentalsBC'

  export default {
    components: {
      Breadcrumb,
      EstimateRentals,
      RentalsList
    },
  }
</script>
