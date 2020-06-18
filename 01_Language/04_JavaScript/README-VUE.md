# JavaScript篇之Vue

## Vue

1. Vue扩展插件
   1. vue-cli：vue脚手架
   2. vue-resource（axios）：ajax请求
   3. vue-router：路由
   4. vuex：状态管理
   5. vue-lazyload：图片懒加载
   6. vue-scroller：页面滑动相关
   7. mint-ui：基于vue的UI组件库（移动端）
   8. element-ui：基于vue的UI组件库（PC端）

2. 模板语法

   1. 双大括号表达式
      1. 语法：{{exp}}
      2. 功能：向页面输出数据
      3. 可以调用对象方法
   2. 指令一：强制数据绑定
      1. 功能：指定变化的属性值
      2. 完整写法：v-bind:xxx="yyy"，yyy会作为表达式解析执行
      3. 简洁写法：:xxx="yyy"
   3. 指令二：绑定事件监听
      1. 功能：绑定指定事件名的回调函数
      2. 完整写法：
         1. v-on:keyup="xxx"
         2. v-on:keyup="xxx(参数)"
         3. v-on:keyup.enter="xxx"
      3. 简洁写法：
         1. @keyup="xxx"
         2. @keyup.enter="xxx"

3. 计算属性和监视

   1. 计算属性
      1. 在computed属性对象中定义计算属性的方法
      2. 在页面中使用{{方法名}}来显示计算的结果
   2. 监视属性
      1. 通过vm对象的$watch()或watch配置来监视指定的属性
      2. 当属性变化时，回调函数自动调用，在函数内部进行计算
   3. 计算属性高级
      1. 通过getter/setter实现对属性数据的显示和监视
      2. 计算属性存在缓存，多次读取只执行一次getter计算

4. class与style绑定

   1. 理解
      1. 在应用界面中，某个（些）元素的样式是变化的
      2. class/style绑定就是专门用来实现动态样式效果的技术
   2. class绑定
      1. :class="xxx"
      2. 表达式是字符串：:class="classA"
      3. 表达式是对象：:class="{classA: true, classB: false}"
      4. 表达式是数组：:class="['classA', 'classB']"
   3. style绑定
      1. :style="{color: activeColor, fontSize: fontSize + 'px'}"
      2. 其中 activeColor/fontSize 是 data 属性

5. 条件渲染

   1. 条件渲染指令
      1. v-if 与 v-else
      2. v-show
   2. 比较v-if与v-show
      1. 如果需要频繁切换，v-show较好
      2. 当条件不成立时，v-if的所有字节点不会解析（项目中使用）

6. 列表渲染

   1. 列表显示指令
      1. 数组：v-for/index
      2. 对象：v-for/key
   2. 列表的更新显示
      1. 删除item
      2. 替换item
   3. 列表的高级处理
      1. 列表过滤
      2. 列表排序

7. 事件处理

   1. 绑定监听：
      1. v-on:xxx="func"
      2. @xxx="func"
      3. @xxx="func(参数)"
      4. 默认事件形参：event
      5. 隐含属性对象：$event
   2. 事件修饰符
      1. .prevent：阻止事件的默认行为 event.preventDefault()
      2. .stop：停止事件冒泡 event.stopPropagation()
   3. 按键修饰符
      1. .keycode：操作的是某个keycode值
      2. .keyName：操作的某个按键名的键（少部分）

8. 表单输入绑定

   1. 使用v-model对表单数据自动收集
      1. text/textarea
      2. checkbox
      3. radio
      4. select

9. Vue实例声明周期

   1. 初始化显示
      1. beforeCreate()
      2. created()
      3. beforeMount()
      4. mounted()
   2. 更新状态：this.xxx = value
      1. beforeUpdate()
      2. updated()
   3. 销毁vue实例：vm.$destroy()
      1. beforeDestroy()
      2. destroyed()
   4. 常用的生命周期方法
      1. created()/mounted()：发送ajax请求，启动定时器等异步任务
      2. beforeDestroy()：做收尾工作，如：清除定时器

10. 效果

    1. 动画
       1. 操作css的trasition或animation
       2. vue会给目标元素添加/移除特定的class
       3. 过度的相关类名
          1. xxx-enter-active：指定显示的transition
          2. xxx-leave-active：指定隐藏的transition
          3. xxx-enter/xxx-leave-to：指定隐藏时的样式

11. 过滤器

    1. 理解过滤器

       1. 功能：对要显示的数据进行特定格式化后再显示
       2. 注意：并没有改变原本的数据，可是产生新的对应的数据

    2. 定义和使用过滤器

       1. 定义过滤器

          ```
          Vue.filter(filterName, function(value[,arg1,arg2,...])) {
            // 进行一定的数据处理
            return newValue
          }
          ```

       2. 使用过滤器

          ```
          <div>{{myData | filterName}}</div>
          <div>{{myData | filterName(arg)}}</div>
          ```

12. 内置指令和自定义指令

    1. 常用内置指令

       1. v-text：更新元素的textContent
       2. v-html：更新元素的innerHtml
       3. v-if：如果为true，当前标签才会输出到页面
       4. v-else：如果为false，当前标签才会输出到页面
       5. v-show：通过控制display样式来控制显示/隐藏
       6. v-for：遍历数组/对象
       7. v-on：绑定事件监听，一般简写为@
       8. v-bind：强制绑定解析表达式，可以省略v-bind
       9. v-model：双向数据绑定
       10. ref：指定唯一标识，vue对象通过$els属性访问这个元素对象
       11. v-cloak：防止闪现，与css配置：[v-cloak] { display:none }

    2. 自定义指令

       1. 注册全局指令

          ```
          Vue.directive("my-directive", function(el, binding) {
            el.innerHtml = binding.value.toUpperCase()
          })
          ```

       2. 注册局部指令

          ```
          directives: {
            "my-directive": {
              bind(el, binding) {
                el.innerHTML = binding.value.toUpperCase()
              }
            }
          }
          ```

       3. 使用指令

          ```
          v-my-directive="xxx"
          ```

13. 自定义插件

    1. 说明
       1. Vue插件是一个包含install方法的对象
       2. 通过install方法给Vue或Vue实例添加方法，定义全局指令等

## Vue组件化编码

1. 使用vue-cli创建模版项目

   1. 说明

      1. vue-cli时vue官方提供的脚手架工具
      2. github：https://github.com/vuejs/vue-cli
      3. 作用：从 https://github.com/vuejs-templates下载模版项目

   2. 创建vue项目

      ```
      npm install -g vue-cli
      vue init webpack vue_demo
      cd vue_demo
      npm install
      npm run dev
      访问 http://localhost:8080
      ```

   3. 模版项目的结构

   4. 效果

2. 项目的打包与发布

   1. 打包：npm run build

   2. 发布1：使用静态服务器工具包

      ```
      npm install -g serve
      serve dist
      访问 http://localhost:5000
      ```

   3. 发布2：使用动态web服务器（tomcat）

      ```
      修改配置 webpack.prod.conf.js
      output: {
        publicPath: "/xxx/" // 打包文件夹的名称
      }
      重新打包
      npm run build
      修改dist文件夹为项目名称：xxx
      将xxx拷贝至运行的tomcat的webapps目录下
      访问 http://localhost:8080/xxx
      ```

3. eslint

   1. 说明
      1. ESLint时一个代码规范检查工具
      2. 它定义了很多特定的规则，一旦你的代码违背了某一规则，eslint会做出非常有用的提示
      3. 官方：http://eslint.org/
      4. 基本以替代以前的jslint
      
   2. ESLint提供以下支持
      1. ES
      2. JSX
      3. style检查
      4. 自定义错误和提示
      
   3. ESLint提供以下几种校验
   
      1. 语法错误校验
      2. 不重要或丢失的标点符号，如分号
      3. 没法运行到的代码块
      4. 未被使用的参数提醒
      5. 确保样式的统一规则，如sass或者less
      6. 检查变量的命名
   
   4. 规则的错误等级有三种
   
      1. 0：关闭规则
      2. 1：打开规则，并且作为一个警告（信息打印黄色字体）
      3. 2：代开规则，并且作为一个错误（信息打印红色字体）
   
   5. 相关配置文件
   
      1. .eslintrc.js：全局规则配置文件
   
         ```
         "rules": {
           "no-new": 1
         }
         ```
   
      2. 在js/vue文件中修改局部规则
   
         ```
         /* eslint-disable no-new */
         new Vue({
           el: "body",
           components: { App }
         })
         ```
   
      3. .eslintignore：指令检查忽略的文件
   
         ```
         *.js
         *.vue
         ```
   
4. 组建定义与使用

   1. vue文件的组成（3个部分）

      1. 模板页面

         ```
         <template>
         页面模板
         </template>
         ```

      2. JS模块对象

         ```
         <script>
         export default {
           data() { return {} },
           methods: {},
           computed: {},
           components: {}
         }
         </script>
         ```

      3. 样式

         ```
         <style>
         样式
         </style>
         ```

   2. 基本使用

      1. 引用组件
      2. 映射成标签
      3. 使用组件标签

   3. 关于标签名与标签属性名书写问题

      1. 写法一：一摸一样
      2. 写法二：大写变小写，并用-连接

5. 组件间通信

   1. 组件间通信基本原则
      1. 不要在子组件中直接修改父组件的状态数据
      2. 数据在哪，更新数据的行为（函数）就应该定义在哪
   2. vue组件间通信方式
      1. props
      2. vue的自定义事件
      3. 消息订阅与发布（如：pubsub库）
      4. slot
      5. vuex

6. 组件间通信1：props

   1. 使用组件标签时

      ```
      <my-component name="tom" :age="3" :set-name="setName"></my-component>
      ```

   2. 定义 MyComponent 时

      1. 在组件内声明所有的props

      2. 方式一：只指定名称

         ```
         props: ["name", "age", "setName"]
         ```

      3. 方式二：指定名称和类型

         ```
         props: {
           name: String,
           age: Number,
           setName: Function
         }
         ```

      4. 方式三：指定名称/类型/必要性/默认值

         ```
         props: {
           name: {type: String, required: true, default: xxx}
         }
         ```

   3. 注意

      1. 此方式用于父组件向子组件传递数据
      2. 所有标签属性都会成为组件对象的属性，模版页面可以直接引用
      3. 问题：
         1. 如果需要向非子后代传递数据必须多层逐层传递
         2. 兄弟组件间也不能直接props通信，必须借助父组件才可以

7. 组件间通信2：vue自定义事件

   1. 绑定事件监听

      ```
      // 方式一：通过 v-on 绑定
      @delete_todo="deleteTodo"
      // 方式二：通过 $on()
      this.$refs.xxx.$on("delete_todo", function(todo) {
        this.deleteTodo(todo)
      })
      ```

   2. 触发事件

      ```
      // 触发事件（只能在父组件中接收）
      this.$emit(eventName, data)
      ```

   3. 注意：

      1. 此方式只用于子组件向父组件发送消息（数据）
      2. 问题：隔代组件或兄弟组件间通信此种方式不合适

8. 组件间通信3：消息订阅与发布（PubSubJS库）

   1. 订阅消息

      ```
      PubSub.subscribe("msg", function(msg, data){})
      ```

   2. 发布消息

      ```
      PubSub.publish("msg", data)
      ```

   3. 注意

      1. 优点：此方式可实现任意关系组件间通信（数据）

   4. 事件的2个重要操作（总结）

      1. 绑定事件监听（订阅消息）
         1. 目标：标签元素`<button>`
         2. 事件名（类型）：click/focus
         3. 回调函数：function(event){}
      2. 触发事件（发布消息）
         1. DOM事件：用户在浏览器上对应的界面上做对应的操作
         2. 自定义：编码手动触发

9. 组件间通信4：slot

   1. 理解
      1. 此方式用于父组件向子组件传递 `标签数据`
      2. 子组件：Child.vue
      3. 父组件：Parent.vue

## vue-ajax

1. Vue项目中常见的2个ajax库

   1. vue-resource
      1. vue插件，非官方库，vue1.x使用广泛
   2. axios
      1. 通用的ajax请求库，官方推荐，vue2.x使用广泛

2. vue-resource的使用

   1. [在线文档](https://github.com/pagekit/vue-resource/blob/develop/docs/http.md)

   2. 下载

      ```
      npm install vue-resource --save
      ```

   3. 编码

3. axios的使用

   1. 在线文档

   2. 下载

      ```
      npm install axios --save
      ```

   3. 编码

## vue UI组件库

1. 常用

   1. Mint UI：
      1. 主页：http://mint-ui.github.io/#!/zh-cn
      2. 说明：饿了么开源的基于vue的移动端UI组件库
   2. Element
      1. 主页：https://element.eleme.cn/#/zh-CN
      2. 说明：饿了么开源的基于vue的PC端UI组件库

2. 使用Mint UI

   1. 下载

      ```
      npm install --save mint-ui
      ```

   2. 实现按需打包

      ```
      1. 下载
      npm install --save-dev babel-plugin-component
      2. 修改babel 配置
      "plugins": ["transform-runtime", ["component", [
        {
          "libraryName": "mint-ui",
          "style": true
        }
      ]]]
      ```

   3. mint-ui 组件分类

      1. 标签组件
      2. 非标签组件

   4. 使用mint-ui的组件

## vue-router

1. 理解

   1. 说明

      1. 官方提供的用来实现SPA的vue插件
      2. github：https://github.com/vuejs/vue-router
      3. 中文文档：http://router.vuejs.org/zh-cn/
      4. 下载：npm install vue-router --save

   2. 相关API说明

      1. VueRouter()：用于创建路由器的构建函数

         ```
         new VueRouter({
           // 多个配置项
         })
         ```

      2. 路由配置

         ```
         routes: [
           {
             // 一般路由
             path: '/about',
             component: About
           },
        {
             // 自动跳转路由
             path: '/',
             redirect: '/about'
           }
         ]
         ```
         
      3. 注册路由器
      
         ```
         import router from './router'
         new Vue({
           router
         })
         ```
      
      4. 使用路由组件标签
      
         ```
         1. <router-link>: 用来生成路由链接
             <router-link to="/xxx">Go to XXX</router-link>
         2. <router-view>: 用来显示当前路由组件界面
             <router-view></router-view>
         ```
   
2. 基本路由

   1. 路由组件
   2. 应用组件：App.vue
   3. 路由器模块：src/router/index.js
   4. 注册路由器：main.js
   5. 优化路由器配置
   6. 总结：编写使用路由的3步
      1. 定义路由组件
      2. 注册路由
      3. 使用路由
         1. `<router-link>`
         2. `<router-view>`

3. 嵌套路由

   1. 子路由组件
   2. 配置嵌套路由：router.js
   3. 路由链接：home.vue

4. 向路由组件传递数据

   1. 方式一：路由路径携带参数（param/query）
   2. 方式二：`<router-view>`属性携带数据

5. 缓存路由组件对象

   1. 理解
      1. 默认情况下，被切换的路由组件对象会死亡释放，再次回来时是重新创建的
      2. 如果可以缓存路由组件对象，可以提高用户体验

6. 编程式路由导航

   1. 相关API
      1. this.$router.push(path)：相当于点击路由链接（可以返回到当前路由界面）
      2. this.$router.replace(path)：用新路由替换当前路由（不可以返回到当前路由界面）
      3. this.$router.back()：请求（返回）上一个记录路由
      4. this.$router.go(-1)：请求（返回）上一个记录路由
      5. this.$router.go(1)：请求下一个记录路由

## vuex

1. vuex理解

   1. vuex是什么
      1. github站点：https://github.com/vuejs/vuex
      2. 在线文档：https://vuex.vuejs.org/zh-cn/
      3. 简单来说，对vue应用中多个组件的共享状态进行集中式的管理（读/写）
   2. 状态自管理应用
      1. state：驱动应用的数据源
      2. view：以声明方式将state映射到视图
      3. actions：响应在view上的用户输入导致的状态变化（包含n个更新状态的方法）
   3. 多组件共享状态的问题
      1. 多个视图依赖同一状态
      2. 来自不同视图的行为需要变更同一状态
      3. 以前的解决方法
         1. 将数据以及操作数据的行为都定义在父组件
         2. 将数据以及操作数据的行为传递给需要的各个子组件（有可能需要多级传递）
      4. vuex就是用来解决这个问题的

2. vuex核心概念和API

   1. state

      1. vuex管理的状态对象

      2. 他应该是唯一的

         ```
         const state = {
           xxx: initValue
         }
         ```

   2. mutations

      1. 包含多个直接更新state的方法（回调函数）的对象

      2. 谁来触发:action中的commit('mutation名称')

      3. 只能包含同步的代码，不能写异步代码

         ```
         const mutations = {
           yyy(state, {data1}) {
             // 更新state的某个属性
           }
         }
         ```

   3. actions

      1. 包含多个事件回调函数的对象

      2. 通过执行:commit()来触发mutation的调用，间接更新state

      3. 谁来触发：组件中：$store.dispatch('action名称', data1) // 'zzz'

      4. 可以包含异步代码（定时器，ajax）

         ```
         const actions = {
           zzz({commit, state}, data1) {
             commit('yyy', {data1})
           }
         }
         ```

   4. getters

      1. 包含多个计算属性（get）的对象

      2. 谁来读取：组件中:$store.getters.xxx

         ```
         const getters = {
           mmm (state) {
             return ...
           }
         }
         ```

   5. modules

      1. 包含多个module
      2. 一个module是一个store的配置对象
      3. 与一个组件（包含有共享数据）对应

   6. 向外暴露store对象

      ```
      export default new Vuex.Storage({
        state,
        mutations,
        actions,
        getters
      })
      ```

   7. 组件中

      ```
      import {mapState, mapGetters, mapActions} from 'vuex'
      export default {
        computed: {
          ...mapState(['xxx']),
          ...mapGetters(['mmm']),
        },
        methods: mapActions(['zzz'])
      }
      {{xxx}} {{mmm}} @click="zzz(data)"
      ```

   8. 映射store

      ```
      import store from './store'
      new Vue({
        store
      })
      ```

   9. store对象

      1. 所有用vuex管理的组件中都多了一个属性$store，它就是一个store对象
      2. 属性
         1. state：注册的state对象
         2. getters：注册的getters对象
      3. 方法：
         1. dispatch(actionName, data)：分发调用action

## vue源码分析

1. 说明

   1. 分析vue作为一个MVVM框架的基本实现原理
      1. 数据代理
      2. 模版解析
      3. 数据绑定
   2. 不直接看vue.js的源码
   3. 剖析github上仿vue实现的mvvm库
   4. 地址：https://github.com/DMQ/mvvm

2. 准备知识

   1. [].slice.call(this)：将伪数组转换为真数组
   2. node.nodeType：得到节点类型
   3. Object.defineProperty(obj, propName, {})：给对象添加/修改属性（指定描述符）
      1. configurable：true/false 是否可以重新define
      2. enumerable：true/false 是否可以枚举（for ... in / keys()）
      3. value：指定初始值
      4. writable：true/false value 是否可以修改
      5. get：回调函数，用来得到当前属性值
      6. set：回调函数，用来监视当前属性值的变化
   4. Object.keys(obj)：得到对象自身可枚举的属性名的数组
   5. DocumentFragment：文档碎片（高效批量更新多个节点）
   6. obj.hasOwnProperty(prop)：判断prop是否是obj自身的属性

3. 数据代理

   1. 数据代理：通过一个对象代理对另一个对象（在前一个对象内部）中属性的操作（读/写）
   2. vue数据代理：通过vm对象来代理data对象中所有属性的操作
   3. 好处：更方便的操作data中的数据
   4. 基本实现流程
      1. 通过 Object.defineProperty() 给vm添加与 data 对象的属性对应的属性描述符
      2. 所有添加的属性都包含getter/setter
      3. getter/setter内部去操作data中对应的属性数据

4. 模版就系

   1. 模版解析的基本流程
      1. 将el的所有子节点取出，添加到一个新建的文档fragment对象中
      2. 对fragment中的所有层次子节点递归进行编译解析处理
         1. 对大括号表达式文本节点进行解析
         2. 对元素节点的指令属性进行解析
            1. 事件指令解析
            2. 一般指令解析
      3. 将解析后的fragment添加到el中显示
   2. 模版解析（1）：大括号表达式解析
      1. 根据正则对象得到匹配出的表达式字符串：子匹配/RegExp.$1 name
      2. 从data中取出表达式对应的属性值
      3. 将属性值设置为文本节点的textContent
   3. 模版解析（2）：事件指令解析
      1. 从指令名中取出事件名
      2. 根据指令的值（表达式）从methods中得到对应的事件处理函数对象
      3. 给当前元素节点绑定指定事件名和回调函数的dom事件监听
      4. 指令解析完后，移除此指令属性
   4. 模版解析（3）：一般指令解析
      1. 得到指令名和指令值（表达式） text/html/class msg/myClass
      2. 从data中根据表达式得到对应的值
      3. 根据指令名确定需要操作元素节点的什么属性
         1. v-text  textContent属性
         2. v-html innerHTML属性
         3. v-class className属性
      4. 将得到的表达式的值设置到对应的属性上
      5. 移除元素的指令属性

5. 数据绑定

   1. 数据绑定

      1. 一旦更新了data中的某个属性数据，所有界面上直接使用或间接使用了此属性的节点都会更新

   2. 数据劫持

      1. 数据劫持时vue中用来实现数据绑定的一种技术
      2. 基本思想：通过defineProperty()来监视data中所有属性（任意层次）数据的变化，一旦变化就会去更新界面

   3. 四个重要对象

      1. Observer

         1. 用来对data所有属性数据进行劫持的构造函数
         2. 给data中所有属性重新定义属性描述（get/set）
         3. 为data中的每个属性创建对应的dep对象

      2. Dep（Depend）

         1. data中的每个属性（所有层次）都对应一个dep对象

         2. 创建的时间

            1. 在初始化define data中各个属性时创建对应的dep对象
            2. 在data中的某个属性值被设置为新的对象时

         3. 对象的结构

            ```
            {
              id, // 每个dep都有一个唯一的id
              subs // 包含n个对应watcher的数组（subscribes的简写）
            }
            ```

         4. subs属性说明

            1. 当watcher被创建时，内部将当前watcher对象添加到对应的dep对象的subs中
            2. 当此data属性的值发生改变时，subs中所有的watcher都会收到更新的通知，从而最终更新对应的界面

      3. Complier

         1. 用来解析模版页面的对象的构造函数（一个实例）
         2. 利用complie对象解析模板页面
         3. 没解析一个表达式（非事件指令）都会创建一个对应的watcher对象，并建立watcher与dep的关系
         4. complie与watcher关系：一对多的关系

      4. Watcher

         1. 模版中每个非事件指令或表达式都对应一个watcher对象

         2. 监视当前表达式数据的变化

         3. 创建的时间：在初始化编译模板时

         4. 对象的组成

            ```
            {
              vm, // vm对象
              exp, // 对应指令的表达式
              cb, // 当表达式所对应的数据发生改变的回调函数
              value, // 表达式当前的值
              depIds // 表达式中各级属性所对应的dep对象的集合对象
              // 属性名为dep的id，属性值为dep
            }
            ```

      5. 总结：dep与watcher的关系：多对多

         1. data中的一个属性对应一个dep，一个dep中可能包含多个watcher（模版中有几个表达式使用到了同一个属性）
         2. 模版中一个非事件表达式对应一个watcher，一个watcher中可能包含多个dep（表达式时多层：a.b）
         3. 数据绑定使用到2个核心技术
            1. defineProperty()
            2. 消息订阅与发布

      6. MVVM原理图分析

      7. 双向数据绑定

         1. 双向数据绑定时建立在单向数据绑定（model => view）的基础之上的
         2. 双向数据绑定的实现流程
            1. 在解析v-model指令时，给当前元素添加input监听
            2. 当input的value发生改变时，将最新的值赋值给当前表达式所对应的data属性

