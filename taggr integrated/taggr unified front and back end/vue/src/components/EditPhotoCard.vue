<template>
<div class="edit-photo-container">
  <div class="edit-card">
    <br>
    <iframe
      class="edit-photo-iframe"
      :src="instagramEmbed($store.state.photoToEdit.url)"
    />
    <a :href="$store.state.photoToEdit.url">Original</a>
    <p class="photo-description">
      {{ $store.state.photoToEdit.description }}
    </p>
    <form>
        <div>
            <label for="newDescription">Enter New Description:</label>
              <input type="text" name ="newDescription" v-model="newDescription">
            </div>
            <button type="submit" class="edit-description-button" 
            v-on:click.prevent="editDescription(newDescription, $store.state.photoToEdit.photo_Id)">Change Description</button>
        </form>
    <br>
    <p>Click on a tag to delete it. </p>
    <edit-tag-list 
    v-bind:tagArray="$store.state.photoToEdit.tags"/>
    <br>    
    <form>
      <div>
        <label for="newTag">Enter New Tag Name:</label>
              <input type="text" name ="newTag" v-model="newTag">
            </div>
            <button type="submit" class="new-tag-button" 
            v-on:click.prevent="addTag(newTag, $store.state.photoToEdit.photo_Id)">Add Tag</button>
    </form>
  </div>
  </div>
</template>

<script>
import PhotoService from '../services/PhotoService';
import EditTagList from './EditTagList.vue';

export default {
    name: 'edit-photo-card',
    data() {
        return{
            newDescription:"",
            newTag:""
        }
    },
    components: {
        EditTagList
    },
    // props: ["photo"],
    methods:{
        instagramEmbed(url){
            if(url.includes('instagram')){
                url = url + 'embed';
                return url;
            } else {
                return url;
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

        editDescription(newDescription, photo_Id){
          if(confirm("Do you want to change this photo's description to '" + newDescription + "' ? There is no undo."
          )
          ){
            PhotoService.updatePhotoDescription(photo_Id, newDescription)
            .then((response) =>{
              if(response.status === 202){
                alert("Description successfully updated.");
                this.newDescription = "";
                this.editPhoto(this.$store.state.photoToEdit.photo_Id);
              }
            })
            .catch((error => {
              this.handleErrorResponse(error, "updating");
            }))

          }
        },
        addTag(newTag, photo_Id){
          if(confirm("Do you want to add '" + newTag + "' as a new tag?"
          )
          ){
            PhotoService.addNewTag(photo_Id, newTag)
            .then((response) =>{
              if(response.status === 202){
                alert("Tag successfully added.");
                this.newTag = "";
                this.editPhoto(this.$store.state.photoToEdit.photo_Id);
              }
            })
            .catch((error =>{
              this.handleErrorResponse(error, "updating");
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
.edit-card {
  border: 2px solid black;
  border-radius: 10px;
  width: 350px;
  height: 750px;
  margin: 20px;
  background-color: rgba(255, 255, 255, 0.692);


}

.edit-photo-iframe{
    display: flex-column;
    height: 51%;
}

.edit-photo-container {
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
}

.edit-description-button{
  background-color: rgba(209, 186, 159, 0.794);
  font-family: Avenir, Helvetica, Arial, sans-serif;
} 
.new-tag-button{
  font-family: Avenir, Helvetica, Arial, sans-serif;
  background-color: rgba(209, 186, 159, 0.794);
}

</style>