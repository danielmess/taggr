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
        <div class="buttons">
    <button class="editbutton" v-on:click="editPhoto(photo.photo_Id)">Edit this photo</button>
    <button class="deletebutton" v-on:click="deletePhoto(photo.photo_Id)">Delete this photo</button>
    </div>
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
        
         editPhoto(id){
           PhotoService.
           getPhotoToEditInfo(id)
           .then((response) => {
             this.$store.commit("SET_PHOTO_TO_EDIT", response.data);
           });
           this.$router.push({name: "edit-photo-view"});

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
.card {
  border: 2px solid black;
  border-radius: 10px;
  width: 350px;
  height: 650px;
  margin: 20px;
  background-color: rgba(255, 255, 255, 0.692);

}

.photo-iframe{
    display: flex-column;
    height: 60%;
}

.deletebutton{
  background-color: rgba(204, 180, 211, 0.794);
}

.editbutton{
  background-color: rgba(209, 186, 159, 0.794);
  
}

.buttons{
  display: flex;
  justify-content: space-evenly;
}

</style>