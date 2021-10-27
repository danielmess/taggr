<template>
  <div>
      <form>
          <div class ="field">
              <label for="photoURL">Instagram URL for new photo: </label>
              <input type="text" name ="photoURL" v-model="newPhotoJSON.url">
              </div>
              <div class ="field">
              <label for="photoDescription">Description for new photo: </label>
              <input type="text" name="photoDescription" v-model="newPhotoJSON.description">
              </div>
              <div class ="field">
              <label for="tagsAsCSV" >Tags for new photo, separated by commas: </label>
              <input type="text" name="tagsAsCSV" v-model="newPhotoJSON.tagsAsCSV">
              </div>
              <button type="submit" class="btn add" v-on:click.prevent="savePhoto">Add Photo</button>

          </form>
      </div>
</template>

<script>
import photoService from '../services/PhotoService'

export default {
    name: "add-photo",
    data() {
        return {
            newPhotoJSON: {
                url: "",
                description: "",
                tagsAsCSV: ""
            }
        }
    },
    methods: {
        savePhoto(){
            photoService.addCurrentUserPhoto(this.newPhotoJSON)
            .then(response => {
                if(response.status === 201) {
                    this.$router.push('/');
                    this.resetForm();
                }
            })
            .catch(error => {
                this.handleErrorResponse(error, "creating");
                alert("Please check to make sure you entered things correctly!");
                this.resetForm();
            })
        },
        resetForm(){
            this.newPhotoJSON = {};
        },
        handleErrorResponse(error, verb) {
      if (error.response) {
        this.errorMsg =
          "Error " + verb + " photo. Response received was '" +
          error.response.statusText +
          "'.";
      } else if (error.request) {
        this.errorMsg =
          "Error " + verb + " photo. Server could not be reached.";
      } else {
        this.errorMsg =
          "Error " + verb + " photo. Request could not be created.";
      }
    }
    }

}
</script>

<style>

</style>