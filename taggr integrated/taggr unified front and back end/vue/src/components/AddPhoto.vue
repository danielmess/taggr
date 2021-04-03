<template>
  <div>
      <form>
          <div class ="field">
              <label for="photoURL">URL for new photo:</label>
              <input type="text" name ="photoURL" v-model="newPhotoJSON.photoURL">
              </div>
              <div class ="field">
              <label for="photoDescription">Description for new photo:</label>
              <input type="text" name="photoDescription" v-model="newPhotoJSON.photoDescription">
              </div>
              <div class ="field">
              <label for="tagsAsCSV" >Tags for new photo, separated by commas:</label>
              <input type="text" name="tagsAsCSV" v-model="newPhotoJSON.tagsAsCSV">
              </div>
              <button type="submit" class="btn add" v-on:click="savePhoto(newPhotoJSON)">Add Photo</button>

          </form>
      </div>
</template>

<script>
export default {
    data() {
        return {
            newPhotoJSON: {
                photoURL: "",
                photoDescription: "",
                tagsAsCSV: ""
            }
        }
    },
    methods: {
        savePhoto(photoJSON){
            
        },
        resetForm(){
            this.newPhotoJSON = {};
        }
    }

}
</script>

<style>

</style>