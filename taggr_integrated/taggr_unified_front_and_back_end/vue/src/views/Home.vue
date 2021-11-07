<template>
  <div id="home">
    <h1>Welcome to <span class="logo">taggr</span></h1>
    <br>
    <router-link v-bind:to="{name: 'add-photo-view'}">Add A New Instagram Photo</router-link> &nbsp;|&nbsp;
    <router-link v-bind:to="{name: 'tag-index-view'}">Your Tags</router-link> &nbsp;|&nbsp;
    <form v-on:submit.prevent="descriptionFilter(searchKeyword)">
      <label for="keyword">Keyword To Search For In Descriptions: </label>
      <input id="keyword" name="keyword" type="text" v-model="searchKeyword">
      <button type="submit" class="keyword search" >Search</button>
      </form>
    <br>
    <photo-list />
  </div>
</template>

<script>
import PhotoList from '../components/PhotoList.vue';
import PhotoService from "@/services/PhotoService.js"

export default {
  
  name: "home",
  components: {
    PhotoList
    
  },
  data() {
    return {
      searchKeyword:""
      }
  },
  methods:{
    descriptionFilter(keyword){
      PhotoService.
      keywordSearchUserPhotos(keyword)
      .then((response) => {
        this.$store.commit("FILTER_PHOTOS", response.data);
           if (response.data.length > 0) {
      this.$store.commit("CHANGE_FILTER_TITLE", "Photos Filtered By Description Keyword '"+keyword+"'");
      } else {
        this.$store.commit("CHANGE_FILTER_TITLE", "No results found for Description Keyword '" + keyword + "'");
      }
      });

      this.$router.push({ name: "filtered-photos-view" });
    }
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
