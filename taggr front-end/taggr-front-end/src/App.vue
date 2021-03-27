<template>
  <div id="app">
    <h1>Welcome to taggr</h1>
    <photo-list />
  </div>
</template>

<script>

import PhotoList from './components/PhotoList.vue'

export default {
  name: 'App',
  components: {
    PhotoList
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
  background-color: aliceblue;
}

h1{
  text-align: center;
}

</style>
