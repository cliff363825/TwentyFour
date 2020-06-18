// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
// 入口js
import Vue from 'vue'
import {Button} from 'mint-ui'
import VueLazyload from 'vue-lazyload'
import App from './App'
import router from './router'
import store from './store'

import './assets/css/base.css'
import './mock/mockServer' // 加载mockServer即可
import loading from './assets/images/loading.gif'
import './filters' // 加载过滤器

// 注册全局组件标签
Vue.component(Button.name, Button) // <mt-button>

Vue.use(VueLazyload, { // 内部自定义一个指令lazy
  loading
})

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  // components: { App },
  // template: '<App/>',
  render: h => h(App),
  router, // 使用上vue-router
  store // 使用上vuex
})
