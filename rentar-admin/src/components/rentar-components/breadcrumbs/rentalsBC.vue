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
          title: 'Rezerwacje',
          route: {name: 'Rentals'}
        }]
      };
    }
  };
</script>
