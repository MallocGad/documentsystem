import Vuex from 'vuex'
import Vue from 'vue'

Vue.use(Vuex)

const Store = new Vuex.Store({
  state: {
    login: window.sessionStorage.getItem('login') || false,
    user: JSON.parse(window.sessionStorage.getItem('user')) || ""
  },
  mutations: {
    add(state, data) {
      state.user = data
      state.login = true

      window.sessionStorage.setItem("user", JSON.stringify(data))
      window.sessionStorage.setItem("login", true)
    },
    
    remove(state) {
      state.user = ""
      state.login = false

      window.sessionStorage.removeItem("user")
      window.sessionStorage.removeItem("login")
    }
  },
  actions: {
    login() {
      
    }
  }
})

export default Store
