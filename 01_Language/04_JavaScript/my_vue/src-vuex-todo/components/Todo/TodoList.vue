<template>
  <ul class="todo-main">
    <TodoItem
      v-for="(todo, index) in todos"
      :key="index"
      :todo="todo"
      :todoIndex="index"
    />
  </ul>
</template>

<script>
import TodoItem from './TodoItem'
import {mapState} from 'vuex'
import storageUtl from '@/util/StorageUtil'

export default {
  computed: {
    ...mapState(['todos'])
  },
  watch: { // 监视todos的所有变化
    todos: {
      deep: true, // 深度监视
      handler: storageUtl.saveTodos
    }
  },
  components: {
    TodoItem
  }
}
</script>

<style scoped>
  .todo-main {
    margin-left: 0px;
    border: 1px solid #ddd;
    border-radius: 2px;
    padding: 0px;
  }

  .todo-empty {
    height: 40px;
    line-height: 40px;
    border: 1px solid #ddd;
    border-radius: 2px;
    padding-left: 5px;
    margin-top: 10px;
  }
</style>
