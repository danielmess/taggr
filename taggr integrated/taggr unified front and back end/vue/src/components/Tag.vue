<template>
  <div class="tag-button">
    <button v-bind:value="tagElement.tag_Id" v-on:click="tagFilterUserPhotos(this.tag.tag_Name)">{{ tagElement.tag_Name }}</button>
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