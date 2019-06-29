import 'core-js/stable';
import 'regenerator-runtime/runtime';
import Vue from 'vue';
import App from 'components/App';
import pl from './lang/pl-PL.js';
import VueBus from 'vue-bus';

import heyuiConfig from 'js/config/heyui-config';
import routerConfig from 'js/config/router-config';
import store from 'js/vuex/store';
import 'js/vue/components';
import 'js/vue/filters';

require('./css/app.less');

// process.env.NODE_ENV == 'development'

require('./mock/index');

heyuiConfig();
Vue.use(HeyUI, {locale: pl});
Vue.use(VueBus);

const router = routerConfig();
export default new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app');
