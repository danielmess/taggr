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
    v-bind="photo.tags in $store.state.currentUserPhotos" :key="photo.url" 
    v-bind:tagArray="photo.tags"/>
  </div>
</template>

<script>
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

</style>