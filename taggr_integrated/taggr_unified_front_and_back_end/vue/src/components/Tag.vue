<template>
  <div class="tag-button">
    <button v-bind:value="tagElement.tag_Id" v-on:click="tagFilter(tagElement.tag_Name)">{{ tagElement.tag_Name }}</button>
  </div>
</template>
 
<script>
import PhotoService from "@/services/PhotoService.js"

export default {
  name: 'tag',
  props: ["tagElement"],
    methods: {
    tagFilter(tagName){
      PhotoService.
      tagFilterUserPhotos(tagName)
      .then((response) => {
        this.$store.commit("FILTER_PHOTOS", response.data);
      });
      this.$store.commit("CHANGE_FILTER_TITLE", "Photos Filtered By Tag '"+tagName+"'")
      this.$router.push({ name: "filtered-photos-view" });
    }
  }
  
};
</script>
 
<style>
.tag-button {
  font-family: "Courier New", Courier, monospace;
}
</style>