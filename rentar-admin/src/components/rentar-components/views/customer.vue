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
                  <span class="h-panel-title">Dane klienta</span>
                </div>
                <div class="h-panel-body">
                  <UpdateCustomer/>
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
                  <CustomerRentals/>
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
