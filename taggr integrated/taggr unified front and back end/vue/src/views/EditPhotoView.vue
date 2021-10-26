<template>
  <div id="edit photoview">
    <h1>Edit Photo Information</h1>
    <br>
    <router-link v-bind:to="{name: 'home'}">Return To Photo List</router-link> <br>
    <br>
    <edit-photo />
  </div>
</template>

<script>


import EditPhoto from '../components/EditPhoto.vue'

export default {
  
  name: "edit-photo-view",
  components: {
    EditPhoto
    
    
  }
};
</script>

<style>
#editphotoview {
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