<template>
  <div class="card">
    <br>
    <iframe
      class="photo-iframe"
      :src="instagramEmbed(photo.url)"
    />
    <a :href="photo.url">Original</a>
    <p class="photo-description">
      {{ photo.description }}
    </p>
    <tag-list 
    v-bind="photo.photoTagsSet in $store.state.currentUserPhotos" :key="photo.url" 
    v-bind:tagArray="photo.tags"/>
    <br>
    <button class="deletebutton" v-on:click="deletePhoto(photo.photo_Id)">Delete this photo</button>
  </div>
</template>

<script>
import PhotoService from '../services/PhotoService';
import TagList from './TagList.vue';

export default {
    name: 'photo-card',
    components: {
        TagList
    },
    props: ["photo"],
    methods:{
        instagramEmbed(url){
            if(url.includes('instagram')){
                url = url + 'embed';
                return url;
            } else {
                return url;
            }

        },
        deletePhoto(id){
          if (confirm("Deleting this photo is permanent and there is no undo. Are you sure?"
          )
          ){
            PhotoService.deletePhoto(id)
            .then((response) => {
              if(response.status === 202){
                alert("Photo successfully deleted.");
                this.$store.commit("DELETE_USER_PHOTO", id);
                this.$router.push('/');
              }
            })
            .catch((error => {
              this.handleErrorResponse(error, "deleting");
            }))
          }

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
          "Error " + verb + " phpto. Request could not be created.";
      }
    }
    }
}
</script>

<style>
.card {
  border: 2px solid black;
  border-radius: 10px;
  width: 350px;
  height: 650px;
  margin: 20px;
}

.photo-iframe{
    display: flex-column;
    height: 60%;
}

.deletebutton{
  background-color: rgba(204, 180, 211, 0.794);
}

</style>