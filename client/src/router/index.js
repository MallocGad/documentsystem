import Vue from 'vue'
import Router from 'vue-router'
import store from '@/store/index'
Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/',
      redirect: '/home'
    },
    {
      path: '/home',
      name: 'Home',
      component: () => import('./../views/index.vue'),
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/recycle',
      name: "Recycle",
      component: () => import('./../views/recycle.vue'),
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/upload',
      name: "Upload",
      component: () => import('./../views/upload.vue'),
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/login',
      name: "Login",
      component: () => import('./../views/login.vue')
    }
  ]
})

router.beforeEach((to, from, next) => {
  const isLogin = store.state.login
  if (to.meta.requiresAuth) {
    if (isLogin) {
      next()
    } else {
      next({
        path: '/login',
        query: { redirect: to.fullPath }  // 将刚刚要去的路由path（却无权限）作为参数，方便登录成功后直接跳转到该路由
      });
    }
  } else {
    next()
  }
})

export default router
