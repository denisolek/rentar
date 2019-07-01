export default [{
  path: '/customers',
  name: 'Customers',
  component: (resolve) => require(['components/rentar-components/views/customers'], resolve),
  meta: {title: 'Customers'}
}, {
  path: '/customers/:id',
  name: 'Customer',
  component: (resolve) => require(['components/rentar-components/views/customer'], resolve),
  meta: {title: 'Customer'}
}, {
  path: '/cars',
  name: 'Cars',
  component: (resolve) => require(['components/rentar-components/views/cars'], resolve),
  meta: {title: 'Cars'}
}, {
  path: '/cars/:id',
  name: 'Car',
  component: (resolve) => require(['components/rentar-components/views/car'], resolve),
  meta: {title: 'Car'}
}, {
  path: '/rentals',
  name: 'Rentals',
  component: (resolve) => require(['components/rentar-components/views/rentals'], resolve),
  meta: {title: 'Rentals'}
}, {
  path: '/rentals/create',
  name: 'CreateRental',
  component: (resolve) => require(['components/rentar-components/views/createRental'], resolve),
  meta: {title: 'Create rental'}
}, {
  path: '/rentals/:id',
  name: 'Rental',
  component: (resolve) => require(['components/rentar-components/views/rental'], resolve),
  meta: {title: 'Rental'}
}];
