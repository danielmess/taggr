<template>
  <div>
    
    
    <div class="photo-container">
      <photo-card
        v-for="photo in $store.state.currentUserPhotos"
        v-bind:key="photo.url"
        v-bind:photo="photo"
      />
    </div>
  </div>
</template>

<script>
import PhotoCard from "./PhotoCard.vue";
import PhotoService from "@/services/PhotoService.js";
export default {
  name: "photo-list",
  components: {
    PhotoCard,
  },
  methods: {
    getCurrentUserPhotos() {
      PhotoService.
      listUserPhotos()
      .then((response) => {
        this.$store.commit("SET_USER_PHOTOS", response.data);
      });
    }
  },
      created() {
    this.getCurrentUserPhotos();
    this.$store.commit("FILTER_PHOTOS", []);
    this.$store.commit("CHANGE_FILTER_TITLE", "");
    this.$store.commit("SET_PHOTO_TO_EDIT",{});
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