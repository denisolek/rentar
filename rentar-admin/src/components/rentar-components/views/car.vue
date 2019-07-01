<template>
  <div class="h-panel frame-page">
    <div class="h-panel-bar">
      <Breadcrumb/>
    </div>
    <div class="h-panel-body">
      <Row type="flex" justify="space-around">
        <Cell width="8">
          <template>
            <div>
              <div class="h-panel">
                <div class="h-panel-bar">
                  <span class="h-panel-title">Dane pojazdu</span>
                </div>
                <div class="h-panel-body">
                  <UpdateCar/>
                </div>
              </div>
            </div>
          </template>
        </Cell>
        <Cell width="14">
          <template>
            <div>
              <div class="h-panel">
                <div class="h-panel-bar">
                  <span class="h-panel-title">Rezerwacje</span>
                </div>
                <div class="h-panel-body">
                  <CarRentals/>
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
