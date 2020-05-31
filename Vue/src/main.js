// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import store from './store'
import 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

var axios = require('axios')
axios.defaults.baseURL = 'http://localhost:8014/api'
Vue.prototype.$axios = axios
Vue.config.productionTip = false
Vue.use(ElementUI)

router.beforeEach((to, from, next) => {
  // 判断访问页面是否需要登录
  if (to.meta.requireAuth){
    // 判断 store 中是否存在 user 信息
    if(store.state.user.username){
      next()
    }else{
      // 跳转到 login 界面，并存储访问的页面路径以便登录后跳转
      next({
        path: 'login',
        query: {redirect: to.fullPath}
      })
    }
  }else{
    next()
  }
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  render: h => h(App),
  router,
  store,
  components: { App },
  template: '<App/>'
})
