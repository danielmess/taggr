<template>
  <div id="addphotoview">
    <h1>Add a photo to <span class="logo">taggr</span></h1>
    <br>
    <add-photo />
  </div>
</template>

<script>
import AddPhoto from '../components/AddPhoto.vue';



export default {
  
  name: "add-photo-view",
  components: {
    AddPhoto
    
    
  }
};
</script>

<style>
#addphotoview {
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