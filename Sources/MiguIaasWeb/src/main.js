import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import locale from 'element-ui/lib/locale/lang/en';
import App from '@/App';
import router from '@/router';
import store from '@/store';
import '@/icons'; // icon
import '@/permission'; // 权限

Vue.use(ElementUI, { locale });

Vue.config.productionTip = false;
// eslint-disable-next-line no-new
new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: { App },
});
