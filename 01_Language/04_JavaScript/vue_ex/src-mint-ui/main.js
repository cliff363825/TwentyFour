// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
// import MintUI from 'mint-ui'
import 'mint-ui/lib/style.css'
import {Button, Swipe} from 'mint-ui'
import App from './App'

Vue.config.productionTip = false

// 注册成标签（全局）
Vue.component(Button.name, Button)
Vue.component(Swipe.name, Swipe)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  components: { App },
  template: '<App/>'
})
