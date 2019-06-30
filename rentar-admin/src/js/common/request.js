import Ajax from './ajax';

const Request = {
  User: {
    info() {
      return Ajax.get('/account/info');
    }
  },
  Dict: {
    get() {
      return Ajax.get(`/dict`);
    }
  },
  Home: {
    getMessageList() {
      return Ajax.get(`/home/messages`);
    }
  },
  Login: {
    login(param) {
      return Ajax.postJson('/login', param);
    },
    logout(param) {
      return Ajax.post('/logout', param);
    }
  },
  // RENTAR
  Customers: {
    fetchAll() {
      return Ajax.get('/customers');
    },
    fetchOne(id) {
      return Ajax.get('/customers/' + id)
    },
    validateAdd(param) {
      return Ajax.postJson('/customers/validateAdd', param);
    },
    add(param) {
      return Ajax.postJson('/customers', param);
    },
    validateUpdate(id, param) {
      return Ajax.postJson('/customers/' + id + '/validateUpdate', param);
    },
    update(id, param) {
      return Ajax.putJson('/customers/' + id, param);
    },
  },
  Reservations: {
    fetchAll() {
      return Ajax.get('/rentals');
    },
    fetchOne(id) {
      return Ajax.get('/rentals' + id);
    },
    fetchForCustomer(id) {
      return Ajax.get('/rentals/customers/' + id);
    },
    fetchForCar(id) {
      return Ajax.get('/rentals/cars/' + id);
    },
    estimate(from, to) {
      return Ajax.get('/rentals/estimate', {from: from, to: to})
    },
    validateCreate(param) {
      return Ajax.postJson('/rentals/validateCreate', param)
    },
    create(param) {
      return Ajax.post('/rentals', param)
    },
    cancel(id) {
      return Ajax.delete('/rentals/' + id + '/cancel')
    }
  }
};

export default Request;
