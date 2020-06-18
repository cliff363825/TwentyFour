<template>
  <div class="container">
    <Search/>
    <UsersMain/>
  </div>
</template>

<script>
import Search from './components/Users/Search'
import Main from './components/Users/Main'

export default {
  components: {
    Search,
    UsersMain: Main
  }
}
</script>

<style>
</style>
