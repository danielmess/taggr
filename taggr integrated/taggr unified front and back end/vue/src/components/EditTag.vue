<template>
  <div class="tag-button">
    <button v-bind:value="tagElement.tag_Id" v-on:click="deleteTag(this.$store.photoToEdit.photo_Id,tagElement.tag_Id)">{{ tagElement.tag_Name }}</button>
  </div>
</template>
 
<script>
import PhotoService from "@/services/PhotoService.js"

export default {
  name: 'edit-tag',
  props: ["tagElement"],
    methods: {
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
          "Error " + verb + " tag. Response received was '" +
          error.response.statusText +
          "'.";
      } else if (error.request) {
        this.errorMsg =
          "Error " + verb + " tag. Server could not be reached.";
      } else {
        this.errorMsg =
          "Error " + verb + " tag. Request could not be created.";
      }
    }
    ,

    deleteTag(photo_Id, tag_Id){
        if (confirm("Deleting this tag from this photo is permanent and there is no undo. Are you sure?")
        ){
        PhotoService.
        deleteTagFromPhoto(photo_Id, tag_Id)
        .then(response => {
            if(response.status === 202){
                alert("Tag successfully deleted");
                this.editPhoto(photo_Id);
            }
        })
        .catch((error => {
            this.handleErrorResponse(error, "deleting")
        }))
    }},    

  }
  
};
</script>
 
<style>
.tag-button {
  font-family: "Courier New", Courier, monospace;
}
</style>