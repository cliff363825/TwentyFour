// 包含所有给予state的getter计算属性的对象
export default {
  totalCount (state) {
    return state.todos.length
  },
  // 完成的数量
  completeCount (state) {
    return state.todos.reduce((preTotal, todo) => {
      return preTotal + (todo.complete ? 1 : 0)
    }, 0)
  },
  // 判断是否全部选中
  isAllSelected (state, getters) {
    return getters.totalCount === getters.completeCount && getters.totalCount > 0
  }
}
