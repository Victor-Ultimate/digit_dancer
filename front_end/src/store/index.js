import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    username: '' || localStorage.getItem('username'),
    password: '' || localStorage.getItem('password'),
    email: '' || localStorage.getItem('email')
  },
  // getters 只会依赖 state 中的成员去更新
  getters: {
},
  // 改变state的值
  mutations: {
    handleUserName: (state, username) => {
      state.username = username
      // 把登录的用户的名保存到localStorage中，防止页面刷新，导致vuex重新启动，用户名就成为初始值（初始值为空）的情况
      localStorage.setItem('username', username)
    },
    handlePassword: (state, password) => {
      state.password = password
      // 把登录的用户的名保存到localStorage中，防止页面刷新，导致vuex重新启动，用户名就成为初始值（初始值为空）的情况
      localStorage.setItem('password', password)
    },
    handleEmail: (state, email) => {
      state.email = email
      // 把登录的用户的名保存到localStorage中，防止页面刷新，导致vuex重新启动，用户名就成为初始值（初始值为空）的情况
      localStorage.setItem('email', email)
    }
  },
  actions: {
  },
  modules: {
  }
})
