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
//引入视频播放插件
import VideoPlayer from 'vue-video-player'
// 第一个是videoJs的样式，后一个是vue-video-player的样式，因为考虑到我其他业务组件可能也会用到视频播放，所以就放在了main.js内
require("E:\\大二下\\本基\\数字人展示\\digital-dancer-webside\\front_end\\node_modules\\video.js\\dist\\video-js.css")
require('vue-video-player/src/custom-theme.css')

Vue.use(VideoPlayer)
Vue.use(SlideVerify);

Vue.use(ElementUI)

Vue.config.productionTip = false
Vue.prototype.$axios = axios

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
