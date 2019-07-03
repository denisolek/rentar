<template>
  <div class="h-panel frame-page">
    <div class="h-panel-bar">
      <Breadcrumb/>
    </div>
    <div class="h-panel-body">
      <Row type="flex" justify="space-around">
        <Cell width="14">
          <template>
            <div>
              <div class="h-panel">
                <div class="h-panel-bar">
                  <span class="h-panel-title">Rezerwacje</span>
                </div>
                <div class="h-panel-body">
                  <RentalsList/>
                </div>
              </div>
            </div>
          </template>
        </Cell>
        <Cell width="8">
          <template>
            <div>
              <div class="h-panel">
                <div class="h-panel-bar">
                  <span class="h-panel-title">Szukaj pojazdu</span>
                </div>
                <div class="h-panel-body">
                  <EstimateRentals/>
                </div>
              </div>
            </div>
          </template>
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
