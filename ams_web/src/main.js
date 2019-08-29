import babelpolyfill from 'babel-polyfill'
import Vue from 'vue'
import App from './App'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
//import './assets/theme/theme-green/index.css'
import VueRouter from 'vue-router'
import store from './vuex/store'
import Vuex from 'vuex'
//import NProgress from 'nprogress'
//import 'nprogress/nprogress.css'
import routes from './routes'

/*去掉mock数据*/
/*import Mock from './mock'
Mock.bootstrap();*/
import 'font-awesome/css/font-awesome.min.css'

import axios from 'axios'

Vue.use(ElementUI)
Vue.use(VueRouter)
Vue.use(Vuex)

//配置axios的全局基本路径
axios.defaults.baseURL='http://172.16.6.216:80/'

//全局属性配置，在任意组件内可以使用this.$http获取axios对象
Vue.prototype.$http = axios;
// axios.defaults.headers.post['Content-Type'] = "application/json;chartset=UTF-8";
/*添加令牌*/
axios.interceptors.request.use(config => {
    //如果已经登录了,每次都把token作为一个请求头传递过程
    console.log(sessionStorage.getItem('token'));
    if (sessionStorage.getItem('token')) {
        // 让每个请求携带token--['X-Token']为自定义key 请根据实际情况自行修改
        config.headers['X-TOKEN'] = sessionStorage.getItem('token')
    }
    console.debug('config',config)
    return config
}, error => {
    // Do something with request error
    Promise.reject(error)
})

//NProgress.configure({ showSpinner: false });

const router = new VueRouter({
  routes
})

//每次路由了之前都要执行，每次请求都要经过路由
router.beforeEach((to, from, next) => {
  //NProgress.start();
  if (to.path == '/login') {
    sessionStorage.removeItem('user');
  }
  let user = JSON.parse(sessionStorage.getItem('user'));
  if (!user && to.path != '/login') {
    next({ path: '/login' })
  } else {
    next()
  }
})

//router.afterEach(transition => {
//NProgress.done();
//});

new Vue({
  //el: '#app',
  //template: '<App/>',
  router,
  store,
  //components: { App }
  //使用App组件
  render: h => h(App)
}).$mount('#app')

