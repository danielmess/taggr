<template>
  <div id="home">
    <h1>Welcome to <span class="logo">taggr</span></h1>
    
    <photo-list />
  </div>
</template>

<script>
import PhotoList from '../components/PhotoList.vue';


export default {
  
  name: "home",
  components: {
    PhotoList
    
  }
};
</script>

<style>
#home {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
  background-color: aliceblue;
}

html{
  background-color: aliceblue;
}

h1{
  text-align: center;
}

.logo{
  color: rgb(27, 27, 194);
  font-weight: bold;
}

</style>
