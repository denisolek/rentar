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
          route: {name: 'Dashboard'}
        }, {
          title: 'Wypożyczenia',
          route: {name: 'Rentals'}
        }]
      };
    }
  };
</script>
