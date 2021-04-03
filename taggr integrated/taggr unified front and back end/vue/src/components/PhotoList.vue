<template>
  <div class="photo-container">
    <photo-card
      v-for="photo in $store.state.currentUserPhotos"
      v-bind:key="photo.url"
      v-bind:photo="photo"
    />
  </div>
</template>

<script>
import PhotoCard from './PhotoCard.vue'
export default {
    name: 'photo-list',
    components: {
        PhotoCard
    }
}
</script>

<style>
.photo-container{
        display:flex;
    justify-content: space-evenly;
    flex-wrap: wrap;
}

</style>