<template>

  <div class="card">
    <br>
    <iframe
      class="photo-iframe"
      :src="instagramEmbed(this.$store.photoToEdit.url)"
    />
    <a :href="this.$store.photoToEdit.url">Original</a>
    <p class="photo-description">
      {{ this.$store.photoToEdit.description }}
    </p>
    <form>
        <div>
            <label for="newDescription">Enter New Description:</label>
              <input type="text" name ="newDescription" v-model="newDescription">
            </div>
            <button type="submit" class="editDescription" v-on:click.prevent="editDescription">Change Description</button>
        </form>
    <tag-list 
    v-bind:tagArray="this.$store.photoToEdit.tags"/>
        <br>

  </div>
</template>

<script>
// import PhotoService from '../services/PhotoService';
import TagList from './TagList.vue';

export default {
    name: 'edit-photo-card',
    data() {
        return{
            // newDescription = '',
            // newTag = ''
        }
    },
    components: {
        TagList
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

        editDescription(){},

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
  background-color: rgba(255, 255, 255, 0.692);

}

.photo-iframe{
    display: flex-column;
    height: 60%;
}



</style>