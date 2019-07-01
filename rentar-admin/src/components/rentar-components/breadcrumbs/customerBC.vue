<template>
  <div>
    <Breadcrumb :datas="datas"></Breadcrumb>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        datas: [{
          icon: 'h-icon-home',
          route: {name: 'Home'}
        }, {
          title: 'Klienci',
          route: {name: 'Customers'}
        }, {
          title: this.$route.params.id
        }]
      };
    }
  };
</script>
