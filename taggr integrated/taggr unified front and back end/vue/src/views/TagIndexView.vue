<template>
  <div id="tagindexview">
    <h1>Welcome to <span class="logo">taggr</span></h1>
    <br>
    <router-link v-bind:to="{name: 'home'}">All Photos</router-link> <br>
    <br>
    <h2>Your Tags</h2>
    <tag-index class="tag-index-container" />
  
  </div>
</template>

<script>
import TagIndex from '../components/TagIndex.vue';
import PhotoService from "@/services/PhotoService.js"

export default {
  
  name: "tagindexview",
  components: {
    TagIndex
    
  },
  methods:{
    getCurrentUserTagIndex(){
      PhotoService.
      getUserTagIndex()
      .then((response) => {
        this.$store.commit("SET_USER_TAGINDEX", response.data);
      });
    }
  },
  created(){
    this.getCurrentUserTagIndex();
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

.tag-index-container {
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
}

html{
  background-color: aliceblue;
}

h1, h2{
  text-align: center;
}

.logo{
  color: rgb(27, 27, 194);
  font-weight: bold;
}

</style>
