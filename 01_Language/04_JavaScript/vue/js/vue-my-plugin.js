// 1. 自定义 Vue 插件
(function () {
    const MyPlugin = {}
    MyPlugin.install = function (Vue, options) {
        // 1. 添加全局方法或属性
        Vue.myGlobalMethod = function () {
            alert("Vue 函数对象方法执行")
        }
        // 2. 添加全局资源
        Vue.directive("my-directive", function (el, binding) {
            el.innerHTML = "MyPlugin my-directive " + binding.value
        })
        // 3. 添加实例方法
        Vue.prototype.$myMethod = function () {
            alert("Vue 实例对象方法执行")
        }
    }
    window.MyPlugin = MyPlugin
})()
