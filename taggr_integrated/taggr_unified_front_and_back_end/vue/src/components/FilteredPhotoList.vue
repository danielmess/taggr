<template>
  <div>
    
    
    <div class="photo-container">
      <photo-card
        v-for="photo in $store.state.currentUserPhotosFiltered"
        v-bind:key="photo.url"
        v-bind:photo="photo"
      />
    </div>
  </div>
</template>

<script>
import PhotoCard from "./PhotoCard.vue";

export default {
  name: "filtered-photo-list",
  components: {
    PhotoCard,
  }
};
</script>

<style>
.photo-container {
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
}
</style>