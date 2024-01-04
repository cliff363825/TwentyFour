<template>
  <div>
    <CommentHeader/>
    <div class="container">
      <CommentAdd
        :addComment="addComment"
      />
      <CommentList
        :comments="comments"
        :deleteComment="deleteComment"
      />
    </div>
  </div>
</template>

<script>
import CommentAdd from './components/Comment/Add'
import CommentList from './components/Comment/List'
import CommentHeader from './components/Comment/Header'

export default {
  data () {
    return {
      comments: [
        {
          name: 'Boy',
          content: 'Vue 还不错'
        },
        {
          name: 'Cat',
          content: 'Vue So Easy'
        },
        {
          name: 'Dog',
          content: 'Vue So So'
        }
      ]
    }
  },
  // 数据在哪个组件，更新数据的行为（方法）就应该定义在哪个组件
  methods: {
    // 添加评论
    addComment (comment) {
      this.comments.unshift(comment)
    },
    // 删除指定下标的评论
    deleteComment (index) {
      this.comments.splice(index, 1)
    }
  },
  components: {
    CommentAdd,
    CommentList,
    CommentHeader
  }
}
</script>

<style>
</style>
