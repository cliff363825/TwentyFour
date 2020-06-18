<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
  </div>
</template>

<script>
// 配置对象（与Vue一致）
export default {
  data () { // data 必须写函数
    return {
      msg: 'Hello Vue'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1, h2 {
    font-weight: normal;
  }

  .hello {
    color: red;
  }
</style>
