import Vue from 'vue'
import VueRouter from 'vue-router'
import Register from '../views/Register.vue'
import Login from '../views/Login.vue'
import Homepage from '../views/Homepage.vue'
import Videoshow from '../views/Videoshow.vue'
import Videodetails from '../views/Videodetails.vue'
import Personalcenter from '../views/Personalcenter.vue'
import Myinformation from '../views/Myinformation.vue'
import Resetpassword from '../views/Resetpassword.vue'
import Mycollection from '../views/Mycollection.vue'
import Customerservice from '../views/Customerservice.vue'
import Useguide from '../views/Useguide.vue'



Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/homepage',
    name: 'Homepage',
    component: Homepage
  },
  {
    path: '/videoshow',
    name: 'Videoshow',
    component: Videoshow
  },
  // {
  //   path: '/videoshow/:videoId/videodetails',
  //   name: 'Videodetails',
  //   component: Videodetails
  // },
    //假的视频播放页跳转
  {
    path: '/videoshow/videodetails',
    name: 'Videodetails',
    component: Videodetails
  },
  {
    path: '/personalcenter',
    name: 'Personalcenter',
    component: Personalcenter
  },
  {
    path: '/personalcenter/myinformation',
    name: 'Myinformation',
    component: Myinformation
  },
  {
    path: '/personalcenter/resetpassword',
    name: 'Resetpassword',
    component: Resetpassword
  },
  {
    path: '/personalcenter/mycollection',
    name: 'Mycollection',
    component: Mycollection
  },
  {
    path: '/personalcenter/customerservice',
    name: 'Customerservice',
    component: Customerservice
  },
  {
    path: '/useguide',
    name: 'Useguide',
    component: Useguide
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
