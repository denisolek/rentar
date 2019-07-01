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
          title: 'Flota',
          route: {name: 'Cars'}
        }, {
          title: this.$route.params.id
        }]
      };
    }
  };
</script>
