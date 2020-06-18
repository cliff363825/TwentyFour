<template>
  <div class="todo-container">
    <div class="todo-wrap">
      <!--
      组件通信1 通过绑定属性的方式
        设置属性
        :addTodo="addTodo"

        接收属性
        props: {
          addTodo: Function
        }
      -->
      <!--<TodoHeader :addTodo="addTodo"/>-->

      <!--
      组件通信2 通过自定义事件的方式
        设置事件
        @addTodo="addTodo"

        触发事件
        this.$emit("addTodo", ...)
      -->
      <!-- 给 TodoHeader 标签对象绑定 addTodo 事件 -->
      <!--<TodoHeader @addTodo="addTodo"/>-->

      <!--
      组件通信3 通过自定义事件的方式2
        设置事件
        mounted () {
          this.$refs.header.$on('addTodo', this.addTodo)
        }

        触发事件
        this.$emit("addTodo", ...)
      -->
      <TodoHeader ref="header"/>

      <!--
      <TodoList
        :todos="todos"
        :deleteTodo="deleteTodo"
      />
      -->
      <!--
      组件通信4 PubSubJs
        订阅
        PubSub.subscribe('deleteTodo', (msg, index) => {...})

        发布
        PubSub.publish('deleteTodo', todoIndex)
      -->
      <TodoList
        :todos="todos"
      />
      <TodoFooter
        :todos="todos"
        :deleteCompleteTodos="deleteCompleteTodos"
        :selectAllTodos="selectAllTodos"
      >
        <input type="checkbox" v-model="isAllCheck" slot="checkAll"/>
        <span slot="count">已完成{{completedSize}} / 全部{{todos.length}}</span>
        <button class="btn btn-danger" v-show="completedSize > 0" @click="deleteCompleteTodos" slot="delete">清除已完成任务</button>
      </TodoFooter>
    </div>
  </div>
</template>

<script>
import PubSub from 'pubsub-js'
import TodoHeader from './components/Todo/TodoHeader'
import TodoList from './components/Todo/TodoList'
import TodoFooter from './components/Todo/TodoFooter'
import storageUtil from './util/StorageUtil'

export default {
  data () {
    return {
      // 从 localStorage 读取 todos
      // todos: JSON.parse(window.localStorage.getItem('todos_key') || '[]')
      todos: storageUtil.readTodos()
    }
  },
  computed: {
    completedSize: function () {
      return this.todos.reduce((preTotal, todo) => preTotal + (todo.complete ? 1 : 0), 0)
    },
    isAllCheck: {
      get () {
        return this.todos.length > 0 && this.completedSize === this.todos.length
      },
      set (value) { // value是当前checkbox最新的值
        this.selectAllTodos(value)
      }
    }
  },
  mounted () { // 执行异步代码
    // 给 <TodoHeader/>绑定addTodo事件监听
    // this.$on('addTodo', this.addTodo) // 给App绑定的监听，不对
    this.$refs.header.$on('addTodo', this.addTodo)

    // 订阅消息
    PubSub.subscribe('deleteTodo', (msg, index) => {
      this.deleteTodo(index)
    })
  },
  methods: {
    addTodo (todo) {
      this.todos.push(todo)
    },
    deleteTodo (index) {
      this.todos.splice(index, 1)
    },
    // 删除所有选中的todo
    deleteCompleteTodos () {
      this.todos = this.todos.filter(todo => todo.complete === false)
    },
    // 全选/全不选
    selectAllTodos (check) {
      this.todos.forEach((todo) => {
        todo.complete = check
      })
    }
  },
  watch: {
    todos: {
      deep: true, // 深度监视
      // handler: (newValue, oldValue) => {
      //   // 将todos最新的值的json数据，保存到localStorage
      //   // window.localStorage.setItem('todos_key', JSON.stringify(newValue))
      //   storageUtil.saveTodos(newValue)
      // }
      handler: storageUtil.saveTodos
    }
  },
  components: {
    TodoHeader,
    TodoList,
    TodoFooter
  }
}
</script>

<style scoped>
  .todo-container {
    width: 600px;
    margin: 0 auto;
  }

  .todo-container .todo-wrap {
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 5px;
  }
</style>
