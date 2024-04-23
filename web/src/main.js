import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
//引入element UI
import ElementUI from 'element-ui'
import "element-ui/lib/theme-chalk/index.css"
//引入axios
import axios from 'axios'
//引入滑动验证
import SlideVerify from 'vue-monoplasty-slide-verify';
Vue.use(SlideVerify);

Vue.use(ElementUI)

Vue.config.productionTip = false
Vue.prototype.$axios = axios

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
