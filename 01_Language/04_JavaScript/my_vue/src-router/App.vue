<template>
  <div>
    <div class="row">
      <div class="col-xs-offset-2 col-xs-8">
        <div class="page-header"><h2>Router Basic - 01</h2></div>
      </div>
    </div>

    <div class="row">
      <div class="col-xs-2 col-xs-offset-2">
        <div class="list-group">
          <router-link to="/about" class="list-group-item">About</router-link>
          <router-link to="/home" class="list-group-item">Home</router-link>
          <!--<a href="#/about" class="list-group-item router-link-exact-active activeClass">About</a>-->
          <!--<a href="#/home" class="list-group-item">Home</a>-->
        </div>
      </div>

      <div class="col-xs-6">
        <div class="panel">
          <div class="panel-body">
            <div>
              <keep-alive>
              <router-view msg="abc"></router-view>
              </keep-alive>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
  a.router-link-active, a.router-link-active:focus {
    color: red;
  }
</style>
