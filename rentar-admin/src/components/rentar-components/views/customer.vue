<template>
  <div class="h-panel frame-page">
    <div class="h-panel-bar">
      <Breadcrumb/>
    </div>
    <div class="h-panel-body">
      <Row>
        <Cell width="14">
          left
        </Cell>
        <Cell width="10">
          right
        </Cell>
      </Row>
    </div>
  </div>
</template>

<script>
  import Breadcrumb from 'components/rentar-components/breadcrumbs/customerBC'
  export default {
    data() {
      return {
        loadingCustomer: false,
        customer: {}
      }
    },
    created() {
      this.fetchCustomer();
    },
    methods: {
      fetchCustomer() {
        this.loadingCustomer = true;
        R.Customers.fetchOne(this.$route.params.id).then(resp => {
          if (resp.status === 200) {
            this.loadingCustomer = false;
            this.customer = resp.data;
          }
        })
      }
    },
    components: {
      Breadcrumb
    },
  }
</script>
