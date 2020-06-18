<template>
  <div>
    <ul>
      <li v-for="(message, index) in messages" :key="message.id">
        <router-link :to="`/home/message/detail/${message.id}`">{{message.title}}</router-link>
        <button @click="pushShow(message.id)">Push查看</button>
        <button @click="replaceShow(message.id)">Replace查看</button>
      </li>
    </ul>
    <button @click="$router.back()">回退</button>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  data () {
    return {
      messages: []
    }
  },
  mounted () {
    // 模拟ajax请求从后台获取数据
    setTimeout(() => {
      const messages = [
        {
          id: 1,
          title: 'message01'
        },
        {
          id: 2,
          title: 'message02'
        },
        {
          id: 3,
          title: 'message03'
        }
      ]
      this.messages = messages
    }, 1000)
  },
  methods: {
    pushShow (id) {
      this.$router.push(`/home/message/detail/${id}`)
    },
    replaceShow(id) {
      this.$router.replace(`/home/message/detail/${id}`)
    }
  }
}
</script>

<style scoped>

</style>
