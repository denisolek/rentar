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
    validateAdd(param) {
      return Ajax.postJson('/customers/validateAdd', param);
    }
  }
};

export default Request;
