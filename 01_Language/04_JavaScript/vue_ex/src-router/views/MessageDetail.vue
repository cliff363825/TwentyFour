<template>
  <div>
    <p>ID：{{$route.params.id}}</p>
    <ul>
      <li>id: {{messageDetail.id}}</li>
      <li>title: {{messageDetail.title}}</li>
      <li>content: {{messageDetail.content}}</li>
    </ul>
  </div>
</template>

<script>
export default {
  data () {
    return {
      messageDetail: {}
    }
  },
  mounted () {
    setTimeout(() => {
      const allMessageDetails = [
        {
          id: 1,
          title: 'message01',
          content: 'message01 content...'
        },
        {
          id: 2,
          title: 'message02',
          content: 'message02 content...'
        },
        {
          id: 3,
          title: 'message03',
          content: 'message03 content...'
        }
      ]
      this.allMessageDetails = allMessageDetails
    }, 1000)
  },
  watch: {
    $route: function (value) { // 路由路径（param）发生了改变
      const id = value.params.id * 1
      this.messageDetail = this.allMessageDetails.find((detail) => detail.id === id)
    }
  }
}
</script>

<style scoped>

</style>
