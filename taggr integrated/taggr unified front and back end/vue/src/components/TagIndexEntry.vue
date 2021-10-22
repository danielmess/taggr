<template>
  <div class="tag-index-entry-button">
    <button 
    v-bind:value="tagIndexElement.tag_Id"
    v-on:click="tagFilter(tagIndexElement.tagName)"
    >{{ tagIndexElement.tagName }} ( {{tagIndexElement.count}} )</button>
  </div>
</template>
 
<script>
import PhotoService from "@/services/PhotoService.js"

export default {
  name: 'tag-index-entry',
  props: ["tagIndexElement"],
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
.tag-index-entry-button {
  font-family: "Courier New", Courier, monospace;
}
</style>