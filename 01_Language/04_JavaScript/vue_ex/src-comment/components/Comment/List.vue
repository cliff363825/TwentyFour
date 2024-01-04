<template>
  <div class="col-md-8">
    <h3 class="reply">评论回复：</h3>
    <h2 v-if="comments.length === 0">暂无评论，点击左侧添加评论！！！</h2>
    <ul class="list-group">
      <CommentListItem
        v-for="(comment,index) in comments"
        :key="index"
        :comment="comment"
        :deleteComment="deleteComment"
        :commentIndex="index"
      >
      </CommentListItem>
    </ul>
  </div>
</template>

<script>
import CommentListItem from './Item'
export default {
  // 声明接收属性：这个属性就会成为组件对象的属性
  props: ['comments', 'deleteComment'], // 只指定属性名
  components: {
    CommentListItem
  }
}
</script>

<style scoped>
  .reply {
    margin-top: 0px;
  }
</style>
