<template>
  <div class="todo-container">
    <div class="todo-wrap">
      <TodoHeader/>
      <TodoList/>
      <TodoFooter/>
    </div>
  </div>
</template>

<script>
import TodoHeader from './components/Todo/TodoHeader'
import TodoList from './components/Todo/TodoList'
import TodoFooter from './components/Todo/TodoFooter'

export default {
  mounted () {
    // 发送命令给action：异步获取保存todos数据并显示
    this.$store.dispatch('reqTodos')
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
