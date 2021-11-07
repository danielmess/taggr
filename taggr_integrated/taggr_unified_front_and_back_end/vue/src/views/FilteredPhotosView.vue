<template>
  <div id="filtered-photo-view">
    <h1>Welcome to <span class="logo">taggr</span></h1>
    <br>
    <router-link v-bind:to="{name: 'home'}">Return To All Photos</router-link> <br>
    <br>
    <h1>{{this.$store.state.filteredByText}}</h1><br>
    <filtered-photo-list />
  </div>
</template>

<script>

import FilteredPhotoList from '../components/FilteredPhotoList.vue';

export default {
  
  name: "filtered-photos-view",
  components: {
    FilteredPhotoList
  },
  data() {
    return {
      searchKeyword:""
      }
  },
  methods:{
  }
};
</script>

<style>
#filtered-photo-view {
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